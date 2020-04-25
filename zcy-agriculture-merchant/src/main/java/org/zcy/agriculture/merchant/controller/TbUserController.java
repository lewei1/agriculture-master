package org.zcy.agriculture.merchant.controller;

import java.util.*;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.Constants;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.enums.UserEditTypeEnum;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.param.TbUserParam;
import org.zcy.agriculture.param.TbUserSearchParam;
import org.zcy.agriculture.param.UserEditParam;
import org.zcy.agriculture.redis.JedisClient;
import org.zcy.agriculture.service.*;
import org.zcy.agriculture.service.plot.ITbPlotService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.Md5Utils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.TbFarmingPlanVo;
import org.zcy.agriculture.vo.TbUserRoleVo;
import org.zcy.agriculture.vo.registerlogin.LoginInfoVo;
import org.zcy.agriculture.vo.TbUserVo;
import org.zcy.agriculture.vo.TbUserWithSubPlotInfoVo;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

/**
 * 用户（成员） 信息操作处理
 *
 * @author zh
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/api/user")
public class TbUserController extends BaseController {
    private String prefix = "api/user";

    @Autowired
    private ITbUserService tbUserService;

    @Autowired
    private ITbFarmUserService iTbFarmUserService;

    @Autowired
    private JedisClient jedisClient;

    @GetMapping()
    public String tbUser() {
        return prefix + "/tbUser";
    }

    @Autowired
    private ITbSubPlotService tbSubPlotService;

    @Autowired
    private ITbMerchantService tbMerchantService;

    @Autowired
    private ITbPlotService iTbPlotService;

    @Autowired
    ITbPlanParticipantService iTbPlanParticipantService;


    /**
     * 查询用户（成员）列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbUserSearchParam searchParam) {

        List<TbUserWithSubPlotInfoVo> list = new ArrayList<>();

        startPage();
        searchParam.setStatus(0); //只有为0的表示是有效的
        searchParam.setFarmId(getFarmUUID());
        if("".equals(searchParam.getRoleId())){
            searchParam.setRoleId(null);
        }
        List<TbUser> listUser = tbUserService.searchTbUserList(searchParam);
        if(!CollectionUtils.isEmpty(listUser)){
            for (TbUser user:listUser) {
                TbSubPlot tbSubPlot = new TbSubPlot();
                tbSubPlot.setSubPlotPerson(user.getUserCode());
                List<TbSubPlot> tbSubPlotList = tbSubPlotService.selectTbSubPlotList(tbSubPlot);
                /**  给子地块名称重新赋值,变成(主地块名称/子地块名称)  **/
                if(!CollectionUtils.isEmpty(tbSubPlotList)){
                    for(TbSubPlot subPlot : tbSubPlotList){
                        TbPlot tbPlot = iTbPlotService.selectTbPlotById(subPlot.getPlotId());
                        subPlot.setSubPlotName(tbPlot.getPlotName()+"/"+subPlot.getSubPlotName());
                    }
                }
                TbUserRoleVo tbUserRoleVo = tbUserService.getUserRoleByUserId(user.getUserId(),getFarmUUID());
                TbUserWithSubPlotInfoVo vo = new TbUserWithSubPlotInfoVo();
                vo.setListSubPlot(tbSubPlotList);
                vo.setRoleId(tbUserRoleVo.getRoleId());
                vo.setRoleName(tbUserRoleVo.getRoleName());
                BeanUtils.copyBeanProp(vo,user);
                list.add(vo);
            }
        }
        TableDataInfo dataTable = getDataTable(list);
        if (listUser instanceof Page) {
            Page page = (Page) listUser;
            dataTable.setTotal(page.getTotal());
        }
        return dataTable;
    }

    /**
     * 新增成员
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@RequestBody TbUserParam tbUserParam) {
        int result = -1;
        if(StringUtils.isEmpty(tbUserParam.getRoleId())){
            return error("角色id不能为空");
        }
         /**手机号是否为空*/
        Boolean isPhoneEmpty = ValidationUtil.isEmpty(tbUserParam.getPhone());
        try {
            if (!isPhoneEmpty && tbUserParam.getPhone().length() < 11) {
                return error("手机号不能少于11位");
            } else if (!isPhoneEmpty) {
                //判断是否超管
                List<TbMerchant> tbMerchants = this.getRegisterMerchantList(tbUserParam.getPhone());
                if(!CollectionUtils.isEmpty(tbMerchants)){
                    return error("该手机号是超管,不能添加");
                }

                /**手机号不为空则用手机号进行判断并注册账号*/
                if (tbUserService.isExistWithPhone(tbUserParam.getPhone())) {
                    //如果存在该成员,就查看该成员是否在当前农场下
                    TbUser tbUserByPhone = tbUserService.getTbUserByPhone(tbUserParam.getPhone());
                    TbFarmUser tbFarmUser = new TbFarmUser();
                    tbFarmUser.setUserId(tbUserByPhone.getUserId());
                    tbFarmUser.setFarmId(getFarmUUID());
                    List<TbFarmUser> tbFarmUsers = iTbFarmUserService.selectTbFarmUserList(tbFarmUser);
                    //如果不存在就往表插入一条记录该成员在当前农场下,返回添加成员成功
                    if(CollectionUtils.isEmpty(tbFarmUsers)){
                        tbFarmUser.setRoleId(Long.parseLong(tbUserParam.getRoleId()));
                       if(iTbFarmUserService.insertTbFarmUser(tbFarmUser)>0){
                           //更新成员昵称
                           tbUserByPhone.setNickName(tbUserParam.getNickName());
                           tbUserService.updateTbUser(tbUserByPhone);
                           return toAjax(1);
                       }
                    }
                    return error("该成员已在当前农场下");
                } else {
                    TbUser tbUser = new TbUser();
                    tbUser.setCreateBy(getFarmUserCode());
                    tbUser.setAccount(tbUserParam.getPhone()); //手机号注册账号
                    tbUser.setPhone(tbUserParam.getPhone());
                    //默认密码123456
                    tbUser.setPassword(Md5Utils.hash(Constants.DEFAULT_PASSWORD));
                    //默认头像
                    tbUser.setHeadUrl(Constants.DEFAULT_HEADIMG_PATH);
                    tbUser.setNickName(tbUserParam.getNickName());
                    //插入主表和关联表
                    result = tbUserService.insertUserRelationship(tbUser, getFarmUUID(),Long.parseLong(tbUserParam.getRoleId()));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toAjax(result);
    }


    /**
     * 个人资料-编辑用户信息
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody UserEditParam editParam) {
        AjaxResult validation = validation(editParam);
        if(AjaxResult.getResultStatus(validation) != RequestStatus.SUCCESS.getStatus())
            return validation;
        //账户绑定-第一步，不进入更新程序
        if(editParam.getEditType() == UserEditTypeEnum.ACCOUNT_BONDING1.getCode())
            return success();

        LoginInfoVo infoVo = AjaxResult.getResultRows(validation, LoginInfoVo.class);
        LoginUserVo userVo = infoVo.getLoginUserVoList().get(0);
        String type = infoVo.getType();

        int result = -1;
        if(Constants.MERCHANT.equals(type)) {
            TbMerchant merchant = new TbMerchant();

            BeanUtils.copyBeanProp(merchant, userVo);
            merchant.setUpdateBy(getFarmUserCode());
            merchant.setUpdateTime(new Date());
            merchant.setMerchantId(userVo.getId());

            result = tbMerchantService.updateTbMerchant(merchant);
        }else if(Constants.USER.equals(type)) {
            TbUser tbUser = new TbUser();

            BeanUtils.copyBeanProp(tbUser, userVo);
            tbUser.setUserId(userVo.getId());
            tbUser.setUpdateBy(getFarmUserCode());
            tbUser.setUpdateTime(new Date());

            result = tbUserService.updateTbUser(tbUser);
        }


        if(result > 0 && (editParam.getEditType() == UserEditTypeEnum.PASSWORD_SECURITY.getCode())) {
            //重置密码，删掉redis缓存用户信息，强制重新登录
            jedisClient.del(getLoginUserKey());
        }
        return toAjax(result);
    }

    @PostMapping("/modify/info")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult editSave(@RequestBody TbUserParam tbUserParam) {
        /**手机号是否为空*/
        Boolean isPhoneEmpty = ValidationUtil.isEmpty(tbUserParam.getPhone());

        if (ValidationUtil.isEmpty(tbUserParam.getUserId())) {
            return error("用户ID不能为空");
        }
        if(StringUtils.isEmpty(tbUserParam.getRoleId())){
            return error("角色ID不能为空");
        }
        if(StringUtils.isEmpty(tbUserParam.getNickName())){
            return error("昵称不能为空");
        }

         if (tbUserService.selectTbUserById(tbUserParam.getUserId()) == null) {
             return error("用户不存在");
         }
         TbUser tbUser = new TbUser();
         tbUser.setNickName(tbUserParam.getNickName());
         tbUser.setStatus(0);
         tbUser.setUpdateBy(getFarmUserCode());
         tbUser.setUpdateTime(new Date());
         tbUser.setUserId(tbUserParam.getUserId());
         tbUserService.updateTbUser(tbUser);
         //更新tb_farm_user表
         TbFarmUser tbFarmUser = new TbFarmUser();
         tbFarmUser.setRoleId(Long.parseLong(tbUserParam.getRoleId()));
         tbFarmUser.setFarmId(getFarmUUID());
         tbFarmUser.setUserId(tbUserParam.getUserId());
         iTbFarmUserService.updateTbFarmUser(tbFarmUser);
        return AjaxResult.success();
    }

    /**
     * 删除用户（成员），（只做软删除处理）
     */
    @PostMapping("/delete")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult delete(@RequestBody Map<String,String> param) {
        //这里只能解绑该成员在当前农场的关联关系,不能在tb_user软删除,否则该成员在其他农场下不可用(其他农场添加该成员)
        //tbUserService.deleteTbUserById(Long.parseLong(param.get("userId")));

        //根据该userId查找userCode
        TbUser tbUser = tbUserService.selectTbUserById(Long.parseLong(param.get("userId")));

        //判断该成员是否负责某一地块,如果是,则把该成员负责的某地块改成其他成员后,方可删除该成员
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("farmId",getFarmUUID());
        hashMap.put("userCode",tbUser.getUserCode());
        List<TbSubPlot> tbSubPlots = tbSubPlotService.selectByFarmIdAndUserCode(hashMap);
        if(!CollectionUtils.isEmpty(tbSubPlots)){
            List<String> subPlotList = Lists.newArrayList();
            tbSubPlots.forEach(tbSubPlot -> {
                subPlotList.add(tbSubPlot.getSubPlotName());
            });
            return AjaxResult.error("该成员在子地块" + subPlotList.toString() + "中是负责人,请更改这些地块负责人后删除该成员");
        }

        //判断该成员是否参与农事计划,如果参与,直接删除跟该成员所有参与计划的记录,删除表是(tb_plan_participant)
        HashMap<String, Object> pmap = Maps.newHashMap();
        pmap.put("farmId",getFarmUUID());
        pmap.put("userCode",tbUser.getUserCode());
        iTbPlanParticipantService.deleteByFarmIdAndUserCode(pmap);


        //同时删除tb_farm_user关联表记录
        Map<String,Object> map = new HashMap();
        map.put("userId",param.get("userId"));
        map.put("farmId",getFarmUUID());
        iTbFarmUserService.deleteTbFarmUserByUserId(map);
        return AjaxResult.success();
    }

    /**
     * 根据农场ID 查询当前所有用户，包过管理员和成员
     */
    @GetMapping("/userAndAdminlist")
    @ResponseBody
    public TableDataInfo userAndAdminlist() {
        TbUserVo vo = new TbUserVo();
        vo.setFarmId(getFarmUUID());
        List<TbUserVo> list = tbUserService.selectTbUserOrAdminList(vo);
        return getDataTable(list);
    }


    @Override
    protected AjaxResult validation(Object object) {

        //判断登录用户是超管还是普通用户
        String phone = getFarmUser().getPhone();
        LoginInfoVo infoVo = getLoginUserList(phone, null);

        LoginUserVo userVo = infoVo.getLoginUserVoList().get(0);

//        TbUser tbUser = new TbUser();
        if(object instanceof UserEditParam) {

            UserEditParam param = (UserEditParam)object;
            Integer editType = param.getEditType();

            if(editType == UserEditTypeEnum.PERSONAL_INFO.getCode()) {
                //个人资料
                String nickName = param.getNickName();
                String companyName = param.getCompanyName();
                String headUrl = param.getHeadUrl();
                if(ValidationUtil.isEmpty(headUrl))
                    return error("头像"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                if(ValidationUtil.isEmpty(nickName))
                    return error("昵称"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                if(ValidationUtil.isEmpty(companyName))
                    return error("企业名称"+ ValidationConstants.SUFFIX_NOT_EMPTY);

                userVo.setNickName(nickName);
                userVo.setCompanyName(companyName);
                if(!ValidationUtil.isEmpty(headUrl))
                    userVo.setHeadUrl(headUrl);

            }else if(editType == UserEditTypeEnum.PASSWORD_SECURITY.getCode()) {
                //密码安全
                if(ValidationUtil.isEmpty(param.getPassword()))
                    return error("原密码"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                if(ValidationUtil.isEmpty(param.getNewPassword()))
                    return error("新密码"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                if(ValidationUtil.isEmpty(param.getRepeatPassword()))
                    return error("重复密码"+ ValidationConstants.SUFFIX_NOT_EMPTY);

                //原密码不正确
                if(ValidationUtil.isEmpty(getLoginUserList(getFarmUser().getPhone(), param.getPassword())))
                    return error("原密码"+ ValidationConstants.SUFFIX_NOT_RIGHT);
                //新密码和重复密码不一致
                if(!param.getNewPassword().equals(param.getRepeatPassword()))
                    return error("新密码和重复密码"+ ValidationConstants.SUFFIX_NOT_MATCH);

                userVo.setPassword(param.getNewPassword());
            }else if(editType == UserEditTypeEnum.ACCOUNT_BONDING1.getCode()) {
                //账户绑定
                Integer untiedType = param.getUntiedType();
                if(ValidationUtil.isEmpty(untiedType))
                    return error("解绑类型"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                else {
                    //第一步，解绑类型(方式一：短信验证，方式二：输入密码)
                    if(ValidationUtil.isEmpty(param.getPhone()))
                        return error("手机号"+ ValidationConstants.SUFFIX_NOT_RIGHT);
                    if(untiedType == 0) {

                        //方式一，短信验证码
                        AjaxResult msgVerificationCode = validateMsgVerificationCode(param.getPhone(), param.getVerificationType(), param.getMsgVerificationCode());
                        if(AjaxResult.getResultStatus(msgVerificationCode) != RequestStatus.SUCCESS.getStatus())
                            return msgVerificationCode;
                    }else if(untiedType == 1) {
                        //方式二，输入密码
                        LoginInfoVo loginInfoVo = getLoginUserList(param.getPhone(), param.getPassword());
                        if(ValidationUtil.isEmpty(loginInfoVo))
                            return error("用户名和密码"+ ValidationConstants.SUFFIX_NOT_RIGHT);
                    }else
                        return error("解绑类型"+ ValidationConstants.SUFFIX_ILLEGAL_PARAMS);
                }

            }else if(editType == UserEditTypeEnum.ACCOUNT_BONDING2.getCode()) {
                //第二步，重置手机号
                if(ValidationUtil.isEmpty(param.getNewPhone())) {
                    return error("新手机号" + ValidationConstants.SUFFIX_NOT_EMPTY);
                }
                //判断新设置的手机号是否正在使用
                List<TbMerchant> tbMerchants = this.getRegisterMerchantList(param.getNewPhone());
                if(!CollectionUtils.isEmpty(tbMerchants)){
                    return error("该手机号正在使用,不能添加");
                }
                TbUser tbUserByPhone = tbUserService.getTbUserByPhone(param.getNewPhone());
                if(!ValidationUtil.isEmpty(tbUserByPhone)){
                    return error("该手机号正在使用,不能添加");
                }

                AjaxResult msgVerificationCode = validateMsgVerificationCode(param.getNewPhone(), param.getVerificationType(), param.getMsgVerificationCode());
                if(AjaxResult.getResultStatus(msgVerificationCode) != RequestStatus.SUCCESS.getStatus())
                    return msgVerificationCode;

                userVo.setPhone(param.getNewPhone());
                userVo.setAccount(param.getNewPhone());
            }else
                return error("修改类型"+ ValidationConstants.SUFFIX_ILLEGAL_PARAMS);
        }
        return success(infoVo);
    }
}
