package org.zcy.agriculture.merchant.controller.irrigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.enums.DevStatusEnum;
import org.zcy.agriculture.enums.DevTypeEnum;
import org.zcy.agriculture.enums.ThingsboardDeviceEnum;
import org.zcy.agriculture.merchant.annotation.IrrigationOperationLog;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.param.irrigation.DateParam;
import org.zcy.agriculture.param.irrigation.IrrigationDetailParam;
import org.zcy.agriculture.param.irrigation.IrrigationDeviceDetailParam;
import org.zcy.agriculture.param.irrigation.IrrigationLogParam;
import org.zcy.agriculture.service.ITbUserService;
import org.zcy.agriculture.service.irrigation.ITbIrrigationGroupService;
import org.zcy.agriculture.service.ITbResDeviceService;
import org.zcy.agriculture.service.irrigation.ITbIrrigationLogService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.irrigation.IrrigationDeviceDetailVo;
import org.zcy.agriculture.vo.irrigation.IrrigationGroupDetailVo;
import org.zcy.agriculture.vo.irrigation.TbIrrigationLogVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 灌溉中心分组 信息操作处理
 *
 * @author numberone
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/api/irrigation")
public class TbIrrigationGroupController extends BaseController {

    @Autowired
    private ITbIrrigationGroupService tbIrrigationGroupService;

    @Autowired
    private ITbResDeviceService tbResDeviceService;

    @Autowired
    private ITbIrrigationLogService tbIrrigationLogService;

    @Autowired
    private ITbUserService tbUserService;

    /**
     * 查询灌溉设备列表
     */
    @GetMapping("/device/list")
    @ResponseBody
    public AjaxResult deviceList() {

        TbResDevice resDevice = new TbResDevice();
        resDevice.setFarmId(getFarmUUID());
        resDevice.setDevType(DevTypeEnum.AUTOMATION_DEV.getCode());
        List<IrrigationDeviceDetailVo> list = tbIrrigationGroupService.selectDeviceDetailList(resDevice);
        return success(list);
    }

    /**
     * 查询默认灌溉设备图标列表
     * @return
     */
    @GetMapping("/device/img/defaultList")
    @ResponseBody
    public AjaxResult defaultImgList() {
        List<TbIrrigationDeviceImg> imgList = tbIrrigationGroupService.selectDefaultDeviceImgList();
        return success(imgList);
    }

    /**
     * 新增灌溉设备
     */
    @PostMapping("/device/addOrUpdate")
    @ResponseBody
    public AjaxResult addOrUpdateDevice(@RequestBody IrrigationDeviceDetailParam param) {
        int result = -1;
        String devName = param.getDevName();
        Long devId = param.getDevId();

        if(ValidationUtil.isEmpty(devName))
            return error("设备名称"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        if(ValidationUtil.isEmpty(param.getImgUrl()))
            return error("设备图片"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        try {
            TbResDevice dev = new TbResDevice();
            dev.setFarmId(getFarmUUID());
            dev.setDevName(param.getDevName());
            List<TbResDevice> listDevice = tbResDeviceService.selectTbResDeviceList(dev);

            if(ValidationUtil.isEmpty(devId)) {
                if (listDevice.size() > 0){
                    return error("设备名"+ValidationConstants.SUFFIX_HAS_EXIST);
                }
                if (ValidationUtil.isEmpty(param.getDevNum()))
                    return error("设备编号"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                //判断添加的设备是否在物联网注册
                if (tbResDeviceService.isDeviceExistinDevice(param.getDevNum().trim()) == ThingsboardDeviceEnum.NOT_EXIST.getCode())
                    return error("设备"+ValidationConstants.SUFFIX_NOT_REGISTER);
                //判断设备是否已添加到物联网，去重
                if(tbResDeviceService.getCountFromTbResDeviceByDevNum(param.getDevNum().trim()) > 0)
                    return error("设备"+ValidationConstants.SUFFIX_HAS_EXIST);

                param.setFarmId(getFarmUUID());
                param.setCreateBy(getFarmUserCode());
                //设备类型
                param.setDevType(DevTypeEnum.AUTOMATION_DEV.getCode());
                //默认待机状态
                param.setStatus(DevStatusEnum.READY.getCode());
                result = tbIrrigationGroupService.insertIrrigationDevice(param);
            } else {
                if (listDevice.size() > 0){
                    TbResDevice device = listDevice.get(0);
                    if (device.getDevId()!= param.getDevId())
                        return error("设备名"+ValidationConstants.SUFFIX_HAS_EXIST);
                }

                //只能修改设备名和设备图片
                IrrigationDeviceDetailParam updateParam = new IrrigationDeviceDetailParam();
                updateParam.setDevName(param.getDevName());
                updateParam.setImgUrl(param.getImgUrl());
                updateParam.setDevId(param.getDevId());
                result = tbIrrigationGroupService.updateIrrigationDevice(updateParam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toAjax(result);
    }

    /**
     * 更新灌溉设备状态 1-运行中（开机），3-待机(关机)
     */
    @GetMapping("/device/updateStatus")
    @IrrigationOperationLog
    @ResponseBody
    public AjaxResult updateDeviceStatus(Long devId, Integer status, String devNum) {
        if(ValidationUtil.isEmpty(devId))
            return error("设备id"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        if(!ValidationUtil.isEmpty(status) && status != DevStatusEnum.READY.getCode() && status != DevStatusEnum.RUNING.getCode()){
            return error("设备状态"+ ValidationConstants.SUFFIX_ILLEGAL_PARAMS);
        }
        if(ValidationUtil.isEmpty(devNum))
            return error("设备序列号"+ValidationConstants.SUFFIX_NOT_EMPTY);
        else {
            //判断设备序列号是否是物联网设备
            if (tbResDeviceService.isDeviceExistinDevice(devNum.trim()) == ThingsboardDeviceEnum.NOT_EXIST.getCode())
                return error("设备"+ValidationConstants.SUFFIX_NOT_REGISTER);
        }

        int result = -1;

        try {
            //校验是否是灌溉设备
            TbResDevice tbResDevice = tbResDeviceService.selectTbResDeviceById(devId);
            if(tbResDevice.getDevType() != DevTypeEnum.AUTOMATION_DEV.getCode())
                return error("设备类型"+ ValidationConstants.SUFFIX_NOT_MATCH);


            TbResDevice resDevice = new TbResDevice();
            resDevice.setDevId(devId);
            resDevice.setDevNum(devNum);
            resDevice.setStatus(status);
            result = tbResDeviceService.updateIrrigationDevice(resDevice);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return processOperateResult(result);
    }

    /**
     * 通过设备id和序列号，移除设备
     * @param devId
     * @return
     */
    @GetMapping("/device/remove")
    @ResponseBody
    public AjaxResult removeDevice(Long devId, String devNum) {
        if(ValidationUtil.isEmpty(devId))
            return error("设备id"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        if(ValidationUtil.isEmpty(devNum))
            return error("设备序列号"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        TbResDevice device = new TbResDevice();
        device.setDevId(devId);
        device.setDevNum(devNum);
        Long count = tbResDeviceService.getCountFromTbResDevice(device);
        if(count == 0)
            return error("设备"+ ValidationConstants.SUFFIX_NOT_EXIST_PARAMS);

        int result = tbResDeviceService.deleteTbResDeviceById(devId);
        return toAjax(result);
    }

    /**
     * 查询灌溉中心分组列表
     */
    @GetMapping("/group/list")
    @ResponseBody
    public AjaxResult list() {
        List<IrrigationGroupDetailVo> list = tbIrrigationGroupService.selectIrrigationGroupDetailList(getFarmUUID());
        return success(list);
    }

    /**
     * 新增或修改灌溉中心分组信息
     */
    @PostMapping("/group/addOrUpdate")
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody IrrigationDetailParam irrigationDetailParam) {
        int result = -1;
        if(ValidationUtil.isEmpty(irrigationDetailParam.getGroupName()))
            return error("分组名称"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        else {
            //判断当前农场的灌溉分组名称是否重复
            TbIrrigationGroup group = new TbIrrigationGroup();
            group.setGroupName(irrigationDetailParam.getGroupName());
            group.setGroupStatus(NormalOrDeleteEnum.NORMAL.getCode());
            group.setFarmId(getFarmUUID());
            List<TbIrrigationGroup> irrigationGroups = tbIrrigationGroupService.selectTbIrrigationGroupList(group);
            //分组名称存在，并且分组id为空时，说明是新增
            if(!ValidationUtil.isEmpty(irrigationGroups) && ValidationUtil.isEmpty(irrigationDetailParam.getGroupId()))
                return error("分组名称"+ ValidationConstants.SUFFIX_HAS_EXIST);
        }

        try {

            if(ValidationUtil.isEmpty(irrigationDetailParam.getGroupId())) {
                irrigationDetailParam.setCreateBy(getFarmUserCode());
                irrigationDetailParam.setFarmId(getFarmUUID());
                result = tbIrrigationGroupService.insertTbIrrigationGroupDetail(irrigationDetailParam);
            } else {
                irrigationDetailParam.setUpdateBy(getFarmUserCode());
                irrigationDetailParam.setUpdateTime(new Date());
                result = tbIrrigationGroupService.updateTbIrrigationGroup(irrigationDetailParam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toAjax(result);
    }


    /**
     * 删除灌溉中心分组设备
     */
    @PostMapping("/group/device/remove")
    @ResponseBody
    public AjaxResult removeGroupDevice(@RequestBody TbIrrigationDevice irrigationDevice) {
        if(ValidationUtil.isEmpty(irrigationDevice.getDeviceId()))
            return error("设备id"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        if(ValidationUtil.isEmpty(irrigationDevice.getGroupId()))
            return error("分组id"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        return toAjax(tbIrrigationGroupService.deleteTbIrrigationDevice(irrigationDevice));
    }


    /**
     * 更新分组状态：0-关闭全部，1-打开全部，2-删除
     */
    @GetMapping("/group/updateStatus")
    @IrrigationOperationLog
    @ResponseBody
    public AjaxResult updateStatus(Long groupId, Integer groupStatus) {
        if(ValidationUtil.isEmpty(groupId))
            return error("分组id"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        if(ValidationUtil.isEmpty(groupStatus))
            return error("分组状态"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        else {
            if(groupStatus < 0 || groupStatus > 2)
                return error("分组状态"+ ValidationConstants.SUFFIX_ILLEGAL_PARAMS);
        }

        TbIrrigationGroup group = new TbIrrigationGroup();
        group.setGroupStatus(groupStatus);
        group.setGroupId(groupId);

        group.setUpdateBy(getFarmUserCode());
        group.setUpdateTime(new Date());
        group.setFarmId(getFarmUUID());

        return  processOperateResult(tbIrrigationGroupService.updateIrrigationGroupStatus(group));
    }


    /**
     * 日志列表
     * @return
     */
    @GetMapping("/log/list")
    @ResponseBody
    public TableDataInfo logList() {
        startPage();
        TbIrrigationLog irrigationLog = new TbIrrigationLog();
        irrigationLog.setFarmId(getFarmUUID());
        List<TbIrrigationLog> list = tbIrrigationLogService.selectTbIrrigationLogList(irrigationLog);
        return getDataTable(list);
    }

    /**
     * 灌溉日志列表
     * @return
     */
    @PostMapping("/log/list/by_date")
    @ResponseBody
    public TableDataInfo logListByDate(@RequestBody DateParam param) {
        startPage();
        IrrigationLogParam irrigationLog = new IrrigationLogParam();
        irrigationLog.setFarmId(getFarmUUID());

        try{
            Date start = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, param.getStartDateStr() + " 00:00:00");
            Date end = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, param.getEndDateStr() + " 23:59:59");
            irrigationLog.setStartDate(start);
            irrigationLog.setEndDate(end);
        }catch (Exception e){}

        List<TbIrrigationLogVo> listR = new ArrayList<>();
        List<TbIrrigationLog> list = tbIrrigationLogService.selectTbIrrigationLogList(irrigationLog);
        for (TbIrrigationLog log: list) {
            TbIrrigationLogVo vo = new TbIrrigationLogVo();
            BeanUtils.copyBeanProp(vo,log);
            TbUser user = tbUserService.selectVbUserByCode(log.getActionBy(),getFarmUUID());
            if (user!= null && user.getNickName()!=null) {
                vo.setNickName(user.getNickName());
            }
            listR.add(vo);
        }

        return getDataTable(listR);
    }


    /**
     * 返回带有农场id的AjaxResult,用于切面添加log
     * @param result
     * @return
     */
    private AjaxResult processOperateResult(int result) {
        AjaxResult json ;
        json = result > 0 ? success() : error();
        json.put("farmId", getFarmUUID());
        json.put("actionBy", getFarmUserCode());

        return json;
    }
}
