package org.zcy.agriculture.merchant.controller.farm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.Constants;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.SysDictData;
import org.zcy.agriculture.entity.TbFarm;
import org.zcy.agriculture.entity.TbFarmUser;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.ServletUtils;
import org.zcy.agriculture.redis.JedisClient;
import org.zcy.agriculture.service.system.ISysDictDataService;
import org.zcy.agriculture.service.farm.ITbFarmService;
import org.zcy.agriculture.service.ITbFarmUserService;
import org.zcy.agriculture.util.CookieUtils;
import org.zcy.agriculture.util.UUIDUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.registerlogin.LoginInfoVo;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 农场(基础) 信息操作处理
 *
 * @author numberone
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/api/tbFarm")
public class TbFarmController extends BaseController {

    @Value("${login.farm.timeout}")
    private int farmTimeout;

    @Value("${login.info.timeout}")
    private int userTimeout;


    @Autowired
    private ITbFarmService tbFarmService;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private ITbFarmUserService tbFarmUserService;

    @Autowired
    private ISysDictDataService sysDictDataService;


    /**
     * 查询农场(基础)列表
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list() {
        LoginUserVo farmUser = getFarmUser();
        if(ValidationUtil.isEmpty(farmUser))
            return error("用户"+ ValidationConstants.SUFFIX_NOT_LOGIN);

        LoginInfoVo infoVo = getLoginUserList(farmUser.getPhone(), null);
        if(!ValidationUtil.isEmpty(infoVo))
            return success(tbFarmService.selectFarmDetailList(infoVo));

        return error();
    }

    /**
     * 获取当前账号所有的农场列表（名称）
     * @return
     */
    @GetMapping("/farmList")
    @ResponseBody
    public AjaxResult farmList() {
        LoginUserVo farmUser = getFarmUser();
        if(ValidationUtil.isEmpty(farmUser))
            return error("用户"+ ValidationConstants.SUFFIX_NOT_LOGIN);
        return success(tbFarmService.selectAllFarmsByPhone(farmUser.getPhone()));
    }


    /**
     * 新增保存农场(基础)-只有超管有创建农场的权限
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody TbFarm tbFarm) {

        AjaxResult validation = validation(tbFarm);
        if(AjaxResult.getResultStatus(validation) != RequestStatus.SUCCESS.getStatus())
            return validation;

        //经纬度
        try {
            SysDictData sysDictData = sysDictDataService.selectDictDataByValue(tbFarm.getRegion());
            tbFarm.setLongitude(new BigDecimal(sysDictData.getCssClass()));
            tbFarm.setLatitude(new BigDecimal(sysDictData.getListClass()));
        } catch(Exception e) {
            // TODO 地区ID 可能在字典表中找不到或找到多条记录导致查询出现异常
        }

        int result = 0;
        try {
            if(ValidationUtil.isEmpty(tbFarm.getFarmId())) {
                //生成农场id
                tbFarm.setFarmId(UUIDUtils.generateUuid());
                //农场创建人
                tbFarm.setCreateBy(getFarmUserCode());
                result = tbFarmService.insertFarmDetail(tbFarm, getFarmUser());
            } else {
                //更新人
                tbFarm.setUpdateBy(getFarmUserCode());
                tbFarm.setUpdateTime(new Date());
                result = tbFarmService.updateTbFarm(tbFarm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toAjax(result);
    }

    /**
     * 选择列表，进入农场，更新登录信息
     * @param farmId
     * @return
     */
    @GetMapping("/enter")
    @ResponseBody
    public AjaxResult enterFarm(String farmId, Long roleId) {

        if(ValidationUtil.isEmpty(farmId))
            return error("农场id"+ValidationConstants.SUFFIX_NOT_EMPTY);
        if(ValidationUtil.isEmpty(roleId))
            return error("角色id"+ValidationConstants.SUFFIX_NOT_EMPTY);

        String loginUserKey = getLoginUserKey();

        //普通成员，需要在切换农场之后，更新roleId
        if(!ValidationUtil.isEmpty(loginUserKey) && roleId != 1L) {

            //校验是否是合法的农场匹配
            TbFarmUser farmUser = new TbFarmUser();
            LoginUserVo userVo = getFarmUser();

            farmUser.setUserId(userVo.getId());
            farmUser.setFarmId(farmId);
            farmUser.setRoleId(roleId);
            List<TbFarmUser> farmUserList = tbFarmUserService.selectTbFarmUserList(farmUser);
            if(ValidationUtil.isEmpty(farmUserList))
                return error("该农场和角色以及当前登录用户"+ValidationConstants.SUFFIX_NOT_MATCH);

            //普通用户每次切换农场，更新用户角色
            userVo.setRoleId(roleId);
            jedisClient.setEntityExpire(loginUserKey, userVo, userTimeout);
        }

        //缓存farmId
        cacheFarmId(farmId);

        return success(roleId);
    }


    /**
     * 删除农场(基础)
     */
    @GetMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String farmId) {
        if(ValidationUtil.isEmpty(farmId))
            return error("农场id"+ValidationConstants.SUFFIX_NOT_EMPTY);

        TbFarm farm = new TbFarm();
        farm.setFarmId(farmId);
        //删除状态
        farm.setFarmStatus(NormalOrDeleteEnum.DELETE.getCode());
        return toAjax(tbFarmService.updateTbFarm(farm));
    }


    /**
     * 缓存farmId到cookie中
     * @param farmId
     */
    private void cacheFarmId(String farmId) {
        //清除
        CookieUtils.addCookie(ServletUtils.getResponse(), Constants.FARM_ID, null, 0);
        //再添加
        CookieUtils.addCookie(ServletUtils.getResponse(), Constants.FARM_ID, farmId, farmTimeout);
    }


    /**
     * 校验
     * @param object
     * @return
     */
    @Override
    protected AjaxResult validation(Object object) {

        if(object instanceof TbFarm) {
            TbFarm tbFarm = (TbFarm)object;

            if (ValidationUtil.isEmpty(tbFarm.getFarmName()))
                return error("农场名称"+ValidationConstants.SUFFIX_NOT_EMPTY);

            //新增才校验
            if(ValidationUtil.isEmpty(tbFarm.getFarmId())) {

                //判断当前用户创建的农场名称是否重复
                TbFarm farm = new TbFarm();
                farm.setFarmName(tbFarm.getFarmName());
                farm.setFarmStatus(NormalOrDeleteEnum.NORMAL.getCode());
                farm.setCreateBy(getFarmUserCode());
                List<TbFarm> farmList = tbFarmService.selectTbFarmList(farm);
                if(!ValidationUtil.isEmpty(farmList))
                    return error("农场名称" + ValidationConstants.SUFFIX_HAS_EXIST);
            }

            if (ValidationUtil.isEmpty(tbFarm.getRegion()))
                return error("农场所在省、市、区"+ValidationConstants.SUFFIX_NOT_EMPTY);
        }

        return success();
    }
}
