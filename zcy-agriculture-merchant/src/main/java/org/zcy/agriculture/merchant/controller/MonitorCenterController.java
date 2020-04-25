package org.zcy.agriculture.merchant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.enums.AlarmThresholdTypes;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.PageDomain;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.page.TableSupport;
import org.zcy.agriculture.param.MonitorDeviceInfoParam;
import org.zcy.agriculture.service.*;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.TbPlantingVo;
import org.zcy.agriculture.vo.TbResDeviceAttributesVo;
import org.zcy.agriculture.vo.ThingsboardDeviceAtrributeVo;
import org.zcy.agriculture.vo.device.DeviceAttributesVo;
import org.zcy.agriculture.vo.monitoringcenter.DeviceExportInfoVo;
import org.zcy.agriculture.vo.monitoringcenter.DeviceMaxMinMsgVo;
import org.zcy.agriculture.vo.monitoringcenter.PlotAndDeviceDetailsVo;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/api/monitor/center")
public class MonitorCenterController extends BaseController {

    private String prefix = "api/monitor/center";

    @Autowired
    private IMonitorCenterService monitorCenterService;

    @Autowired
    private ITbResDeviceService deviceService;

    @Autowired
    private ITbResDeviceAttributesService deviceAttributesService;

    @Autowired
    private ITbHarvestService tbHarvestService;

    @Autowired
    private ITbPlantingService tbPlantingService;

    @Autowired
    private ITbFarmingPlanService tbFarmingPlanService;

    /**
     * 获取监控中心信息
     */
    @GetMapping( "/info")
    @ResponseBody
    public AjaxResult info(@RequestParam Long plotId)
    {
        if (ValidationUtil.isEmpty(plotId)) {
            return error("地块ID不能为空");
        }

        /**根据传入的地块ID获取地块和设备信息*/
        PlotAndDeviceDetailsVo vo = monitorCenterService.getPlotAndDeviceDetailsInfo(getFarmUUID(),plotId);
        HashMap<String, Object> map =  tbHarvestService.selectByStatisticsToDay(plotId); //今日采收 汇总
        vo.setTodayHarvest(map);

        /**获取种植信息*/
        TbPlantingVo planting = new TbPlantingVo();
        planting.setPlotId(plotId);
        planting.setFarmId(getFarmUUID());
        List<TbPlantingVo> listPlanting = tbPlantingService.selectByNncultivatedList(planting);
        vo.setListPlanting(listPlanting);

        /**获取今日任务数，今日完成任务数*/
        HashMap<String, Object> mapFarmingPlan = tbFarmingPlanService.selectByStatisticsToday(plotId);
        vo.setFarmingPlan(mapFarmingPlan);

        return success(vo);
    }

    /**
     * 获取监控中心设备数据（设备都有哪些传感器属性）
     */
    @PostMapping( "/device/attributes/list")
    @ResponseBody
    public AjaxResult deviceInfo(@RequestBody MonitorDeviceInfoParam param)
    {
        if (ValidationUtil.isEmpty(param.getPlotId())) {
            return error("地块ID不能为空");
        }
        HashMap<String, Object> m = Maps.newHashMap();

        /**根据传入的地块ID，查询出地块下所有设备列表*/
        TbResDevice rDevice = new TbResDevice();
        rDevice.setFarmId(getFarmUUID());
        rDevice.setPlotId(param.getPlotId());


        List<DeviceAttributesVo> listAttributesVo = new ArrayList<>();

        List<TbResDevice> listDevice = deviceService.selectTbResDeviceList(rDevice);
        /**遍历设备列表*/
        for (TbResDevice d : listDevice) {
            if (d.getDevId()!= null) {
                TbResDeviceAttributesVo attributes = new TbResDeviceAttributesVo();
                attributes.setDevId(d.getDevId());

                /**根据传入的设备ID，获取出对应设备的传感器列表*/
                List<TbResDeviceAttributes> listAttributesTemp =  deviceAttributesService.selectTbResDeviceAttributesList(attributes);
                for (TbResDeviceAttributes a:listAttributesTemp) {
                    if (a.getThingsboardKey()!=null
                            && m.get(a.getThingsboardKey()) == null
                            && a.getThingsboardKey()!= ""
                            && a.getTypesIndex() !=null
                            && a.getTypesIndex()<= AlarmThresholdTypes.PH.getCode()) { //只有小于等于枚举类型AlarmThresholdTypes下PH的值才是有效的值
                        DeviceAttributesVo vo = new DeviceAttributesVo();
                        try {
                            vo.setAttributesName(AlarmThresholdTypes.valueOf(a.getThingsboardKey()).getVal());
                            vo.setUnit(AlarmThresholdTypes.valueOf(a.getThingsboardKey()).getUnit());
                            vo.setTypesIndex(AlarmThresholdTypes.valueOf(a.getThingsboardKey()).getCode());
                            vo.setThingsboardKey(a.getThingsboardKey());

                            m.put(a.getThingsboardKey(),a.getThingsboardKey());
                            listAttributesVo .add(vo);
                        }catch (Exception e) {}
                    }
                }
            }
        }

        return success(listAttributesVo);
    }

    /**
     * 根据地块ID和传感器属性获取地块下对应的设备列表（包括传感器信息）
     */
    @PostMapping( "/device/list/byAttibute")
    @ResponseBody
    public AjaxResult deviceListByAttibute(@RequestBody MonitorDeviceInfoParam param )
    {
        if (ValidationUtil.isEmpty(param.getPlotId())) {
            return error("地块ID不能为空");
        }

        if (ValidationUtil.isEmpty(param.getAttributes())) {
            return error("传感器类型属性不能空");
        }

        List<TbResDevice> listDeviceRet = new ArrayList<>();

        TbResDevice rDevice = new TbResDevice();
        rDevice.setFarmId(getFarmUUID());
        rDevice.setPlotId(param.getPlotId());
        List<TbResDevice> listDevice = deviceService.selectTbResDeviceList(rDevice); //查询出地块下所有设备列表
        for (TbResDevice d : listDevice) {
            if (d.getDevId()!= null) {
                TbResDeviceAttributesVo attributesVo = new TbResDeviceAttributesVo();
                attributesVo.setDevId(d.getDevId());
                /**根据设备ID获取出对应的设备属性（传感器列表）*/
                List<TbResDeviceAttributes> listAttributesTemp =  deviceAttributesService.selectTbResDeviceAttributesList(attributesVo);
                for (TbResDeviceAttributes a:listAttributesTemp) {

                    if (a.getThingsboardKey()!=null && a.getThingsboardKey().equals(param.getAttributes()) ) {
                        listDeviceRet.add(d);
                        break;
                    }
                }
            }
        }

        return success(listDeviceRet);
    }

    /**
     * 根据传入的设备序列号和设备传感器属性获取设备传感器在一段时间内的最大值最小值
     */
    @PostMapping( "/device/attribute/now/or/history/max_min")
    @ResponseBody
    public AjaxResult deviceNowOrHistoryMaxMinAttribute(@RequestBody MonitorDeviceInfoParam param) {
        if (ValidationUtil.isEmpty(param.getDevNum())) {
            return error("设备序列号不能为空");
        }
        if (ValidationUtil.isEmpty(param.getAttributes())) {
            return error("传感器属性不能为空");
        }
        DeviceMaxMinMsgVo vo = new DeviceMaxMinMsgVo();

        TbResDevice tbResDevice = new TbResDevice();
        tbResDevice.setDevNum(param.getDevNum());
        List<TbResDevice> list = deviceService.selectTbResDeviceList(tbResDevice);
        if (list.size()>0){
            TbResDevice d = list.get(0);
            vo.setDevName(d.getDevName());
        }

        if ( param.getIsNow()== 1) {
            /**获取实时数据*/
            vo.setNow(parseMsg(deviceService.getDeviceAttributeNowDataFromThingsboard(param.getDevNum(),param.getAttributes())));
        } else {
            /**获取历史最大值、最小值*/
            vo.setMax(parseMsg(monitorCenterService.deviceNowOrHistoryMaxMinAttribute(param,1)));
            vo.setMin(parseMsg(monitorCenterService.deviceNowOrHistoryMaxMinAttribute(param,0)));
            vo.setAvg(parseMsg(monitorCenterService.deviceNowOrHistoryMaxMinAttribute(param,2)));
        }
        return success(vo);
    }

    public Map parseMsg(String msg) {
        Map mapRet = new HashMap();
        if(StringUtils.isNotEmpty(msg)){
            JSONObject jsonObject = (JSONObject)JSONObject.parse(msg);


            Map<String, Object> map = jsonObject.getInnerMap();
            Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Object> entry = entries.next();
                JSONArray tr = jsonObject.getJSONArray(entry.getKey());
                JSONArray jR = new JSONArray();
                try {
                    for (int i = 0; i < tr.size(); i++) { //对数组元素进行遍历
                        JSONObject jsonObjectTemp = JSONObject.parseObject(tr.getString(i));
                        ThingsboardDeviceAtrributeVo tokenVo = JSONObject.toJavaObject(jsonObjectTemp, ThingsboardDeviceAtrributeVo.class);

                        JSONObject jsonObjectTemp2 = new JSONObject();
                        if (tokenVo.getTs()!=null && tokenVo.getValue()!=null){
                            jsonObjectTemp2.put("value", String.format("%.2f", Double.parseDouble(jsonObjectTemp.get("value").toString())));
                            jsonObjectTemp2.put("ts", DateUtils.getHourAndMinute(Long.parseLong(tokenVo.getTs()), DateUtils.YYYY_MM_DD_HH_MM_SS));
                            System.out.println();
                        }
                        jsonObjectTemp2.put("cn_name", AlarmThresholdTypes.getValByKey(entry.getKey()));

                        jR.add(jsonObjectTemp2);
                    }
                } catch (Exception e){
                    return error();
                }
                mapRet.put(entry.getKey(),jR);
            }
            return mapRet;
        }
        return null;
    }

    /**
     * 导出设备列表
     */
    @GetMapping("/device/attribute/history/info/export")
    @ResponseBody
    public void export(MonitorDeviceInfoParam param, HttpServletResponse response)
    {
        List<DeviceExportInfoVo> list = new ArrayList<>();

        if (ValidationUtil.isEmpty(param.getDevNum())) {
            return ;
        }
        if (ValidationUtil.isEmpty(param.getAttributes())) {
            return;
        }

        String msg = monitorCenterService.deviceAttributeHistoryInfo(param);
        if(StringUtils.isNotEmpty(msg)){
            JSONObject jsonObject = (JSONObject)JSONObject.parse(msg);

            Map<String, Object> map = jsonObject.getInnerMap();
            Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Object> entry = entries.next();
                JSONArray tr = jsonObject.getJSONArray(entry.getKey());
                try {
                    for (int i = 0; i < tr.size(); i++) { //对数组元素进行遍历
                        JSONObject jsonObjectTemp = JSONObject.parseObject(tr.getString(i));
                        ThingsboardDeviceAtrributeVo tokenVo = JSONObject.toJavaObject(jsonObjectTemp, ThingsboardDeviceAtrributeVo.class);
                        Long tsValue = Long.parseLong(tokenVo.getTs());

                        DeviceExportInfoVo vo = new DeviceExportInfoVo();
                        vo.setDevNum(param.getDevNum());
                        vo.setTs(DateUtils.getHourAndMinute(tsValue, DateUtils.YYYY_MM_DD_HH_MM_SS));
                        vo.setValue(String.format("%.2f", Double.parseDouble(jsonObjectTemp.get("value").toString())));
                        vo.setAttributesName(AlarmThresholdTypes.valueOf(entry.getKey()).getVal());

                        list.add(vo);
                    }
                } catch (Exception e){
                    break;
                }
            }
        }

        ExcelUtil<DeviceExportInfoVo> util = new ExcelUtil<DeviceExportInfoVo>(DeviceExportInfoVo.class);
        util.exportExcelByStream(list, "设备数据", response);
    }

    /**
     * 获取设备历史数据
     * */
    @PostMapping( "/device/attribute/history/info")
    @ResponseBody
    public AjaxResult deviceAttributeHistoryInfo(@RequestBody MonitorDeviceInfoParam param) {
        if (ValidationUtil.isEmpty(param.getDevNum())) {
            return error("设备序列号不能为空");
        }
        if (ValidationUtil.isEmpty(param.getAttributes())) {
            return error("传感器属性不能为空");
        }

        String msg = monitorCenterService.deviceAttributeHistoryInfo(param);
        if(StringUtils.isNotEmpty(msg)){
            JSONObject jsonObject = (JSONObject)JSONObject.parse(msg);
           Map mapRet = new HashMap();


            Map<String, Object> map = jsonObject.getInnerMap();
            Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
            boolean hasGetRecord = false;
            while (entries.hasNext()) {
                hasGetRecord = true;
                Map.Entry<String, Object> entry = entries.next();
                JSONArray tr = jsonObject.getJSONArray(entry.getKey());
                JSONArray jR = new JSONArray();
                try {
                    for (int i = 0; i < tr.size(); i++) { //对数组元素进行遍历
                        JSONObject jsonObjectTemp = JSONObject.parseObject(tr.getString(i));
                        ThingsboardDeviceAtrributeVo tokenVo = JSONObject.toJavaObject(jsonObjectTemp, ThingsboardDeviceAtrributeVo.class);
                        Long tsValue = Long.parseLong(tokenVo.getTs());
                        jsonObjectTemp.put("value", String.format("%.2f", Double.parseDouble(jsonObjectTemp.get("value").toString())));
                        jsonObjectTemp.put("ts", DateUtils.getHourAndMinute(tsValue, DateUtils.YYYY_MM_DD_HH_MM_SS));

                        JSONObject jsonObjectTemp2 = new JSONObject();
                        jsonObjectTemp2.put("value", String.format("%.2f", Double.parseDouble(jsonObjectTemp.get("value").toString())));
                        jsonObjectTemp2.put("ts", DateUtils.getHourAndMinute(tsValue, DateUtils.YYYY_MM_DD_HH_MM_SS));
                        jR.add(jsonObjectTemp2);
                    }
                } catch (Exception e){
                    return error();
                }
                mapRet.put(entry.getKey(),jR);
            }
            if (!hasGetRecord) {//如果一条记录都没有
                String str =  param.getAttributes();
                String[] strArr = str.split("\\,");
                for (int i = 0; i < strArr.length; ++i){
                    JSONArray jR = new JSONArray();
                    mapRet.put(strArr[i],jR);
                }
            }

            return success(mapRet);
        }
        return error();
    }

    /**
     * 获取设备历史数据（带分页功能）
     * */
    @PostMapping( "/device/attribute/history/info/with/page")
    @ResponseBody
    public TableDataInfo deviceAttributeHistoryInfoWithPage(@RequestBody MonitorDeviceInfoParam param) {
        if (ValidationUtil.isEmpty(param.getDevNum())) {
            return getDataTable(RequestStatus.FAILED.getStatus(),"设备序列号不能为空");
        }
        if (ValidationUtil.isEmpty(param.getAttributes())) {
            return getDataTable(RequestStatus.FAILED.getStatus(),"传感器属性不能为空");
        }

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);

        /**获取分页数据*/
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        //默认值
        if(ValidationUtil.isEmpty(pageNum)|| pageNum < 1) {
            pageNum = 0;
        } else {
            pageNum  = pageNum - 1; //物联网端是从第0页开始，为了跟本项目其它接口保持一致，所以前端传过来时候从1开始，做下减法换算
        }

        Integer pageSize = pageDomain.getPageSize();
        //默认值
        if(ValidationUtil.isEmpty(pageSize)) {
            pageSize = 10;
        }
        String orderBy = pageDomain.getOrderBy();
        PageHelper.startPage(pageNum, pageSize, orderBy);

        String msg = monitorCenterService.deviceAttributeHistoryInfoWithPage(param,pageNum,pageSize);
        if(StringUtils.isNotEmpty(msg)){
            JSONObject jsonObject = (JSONObject)JSONObject.parse(msg);
            Map mapRet = new HashMap();

            Map<String, Object> map = jsonObject.getInnerMap();
            Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Object> entry = entries.next();
                if (entry.getKey()== "total"){
                    rspData.setTotal(Integer.parseInt(entry.getValue().toString()));
                    continue;
                }
                JSONArray tr = jsonObject.getJSONArray(entry.getKey());
                JSONArray jR = new JSONArray();
                try {
                    for (int i = 0; i < tr.size(); i++) { //对数组元素进行遍历
                        JSONObject jsonObjectTemp = JSONObject.parseObject(tr.getString(i));
                        ThingsboardDeviceAtrributeVo tokenVo = JSONObject.toJavaObject(jsonObjectTemp, ThingsboardDeviceAtrributeVo.class);
                        Long tsValue = Long.parseLong(tokenVo.getTs());
                        jsonObjectTemp.put("ts", DateUtils.getHourAndMinute(tsValue, DateUtils.YYYY_MM_DD_HH_MM_SS));
                        jsonObjectTemp.put("value", String.format("%.2f", Double.parseDouble(jsonObjectTemp.get("value").toString())));

                        jR.add(jsonObjectTemp);
                    }
                } catch (Exception e){
                    return getDataTable(RequestStatus.FAILED.getStatus(),"");
                }
                mapRet.put(entry.getKey(),jR);
            }
            rspData.setRows(mapRet);
            return rspData;
        }
        return getDataTable(RequestStatus.FAILED.getStatus(),"");
    }
}
