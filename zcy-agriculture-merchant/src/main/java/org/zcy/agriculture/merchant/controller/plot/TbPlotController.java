package org.zcy.agriculture.merchant.controller.plot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.enums.DevTypeEnum;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.param.plot.PlotDetailParam;
import org.zcy.agriculture.service.*;
import org.zcy.agriculture.service.farm.ITbFarmService;
import org.zcy.agriculture.service.plot.ITbPlotService;
import org.zcy.agriculture.service.system.ISysDictDataService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.*;
import org.springframework.beans.factory.annotation.Value;
import org.zcy.agriculture.vo.plot.PlotDetailVo;
import org.zcy.agriculture.vo.plot.PlotStaticsVo;
import org.zcy.agriculture.vo.plot.RegionPlotDetailVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 地块 信息操作处理
 *
 * @author numberone
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/api/plot")
public class TbPlotController extends BaseController {

    @Autowired
    private ITbPlotService tbPlotService;

    @Autowired
    private ITbResDeviceService deviceService;

    @Autowired
    private ITbFarmService tbFarmService;

    @Autowired
    private ITbPlantingService tbPlantingService;

    @Autowired
    private ITbUserService tbUserService;

    @Autowired
    private ISysDictDataService sysDictDataService;

    @Value("${file.img}")
    private String imgPath;


    /**
     * 查询当前农场的所有地块列表
     * @return
     */
    @GetMapping("/pureList")
    @ResponseBody
    public AjaxResult purePlotList() {
        List<TbPlot> plotList = tbPlotService.selectPurePlotList(getFarmUUID());
        return success(plotList);
    }


    /**
     * 查询地块详情列表
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list() {
        List<RegionPlotDetailVo> list = tbPlotService.selectTbPlotList(getFarmUUID());
        return success(list);
    }

    /**
     * 查询地块类型列表
     * @return
     */
    @GetMapping("/typeList")
    @ResponseBody
    public AjaxResult plotTypeList() {
        List<SysDictData> list = tbPlotService.selectPlotTypeList();
        return success(list);
    }


    @GetMapping("/detail")
    @ResponseBody
    public AjaxResult detail(Long plotId) {
        PlotDetailVo detailVo = new PlotDetailVo();

        if(ValidationUtil.isEmpty(plotId))
            return error("地块Id"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        TbPlot plot = tbPlotService.selectTbPlotById(plotId);
        if(!ValidationUtil.isEmpty(plot)) {
            BeanUtils.copyBeanProp(detailVo, plot);

            try { //获取地块类型对应名称
                if (plot.getPlotType()!=null){
                    long type = Long.parseLong(plot.getPlotType());
                    detailVo.setPlotTypeName(sysDictDataService.selectDictDataById(type).getDictLabel());
                }
            } catch (Exception e){}

            List<TbPlotCoordinate> coordinateList = tbPlotService.selectPlotCoordinateById(plotId);
            if(!ValidationUtil.isEmpty(coordinateList))
                detailVo.setCoordinateList(coordinateList);

            //拿到地块创建人信息
            TbUser user = tbUserService.selectVbUserByCode(plot.getCreateBy(), getFarmUUID());
            if(!ValidationUtil.isEmpty(user))
                detailVo.setResponser(user.getNickName());
        }
        return success(detailVo);
    }

    /**
     * 查询特定地块id对应的经纬度
     * @param plotId
     * @return
     */
    @GetMapping("/coordinate")
    @ResponseBody
    public AjaxResult getCoordinate(Long plotId) {
        if(ValidationUtil.isEmpty(plotId))
            return error("地块Id"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        List<TbPlotCoordinate> coordinateList = tbPlotService.selectPlotCoordinateById(plotId);
        return success(coordinateList);
    }


    @GetMapping("/monitorCenter/list")
    @ResponseBody
    public AjaxResult monitorCenterList() {
        MonitorCenterVo vo = new MonitorCenterVo();
        /**获取地块列表信息*/
        List<MonitorCenterPlotVo> plotList = tbPlotService.getMonitoringCenterPlotList(getFarmUUID());
        /**下面这段代码是获取每个地块下的子地块种植列表*/
        for (MonitorCenterPlotVo p:plotList) {
            TbPlantingVo planting = new TbPlantingVo();
            planting.setPlotId(p.getPlotId());
            planting.setFarmId(getFarmUUID());
            List<TbPlantingVo> listPlanting = tbPlantingService.selectByNncultivatedList(planting);

            TbResDevice d = new TbResDevice();
            d.setPlotId(p.getPlotId());
            d.setFarmId(getFarmUUID());
            List<String> listNameTemp = new ArrayList<>();
            d.setFarmId(getFarmUUID());
            List<TbResDevice> listTemp =  deviceService.selectTbResDeviceList(d);
            for (TbResDevice de : listTemp){
                listNameTemp.add(de.getDevName());
            }
            p.setDevNameList(listNameTemp);

            p.setPlantingList(listPlanting);

            try {
                if (p.getPlotType()!=null){
                    long type = Long.parseLong(p.getPlotType());
                    p.setPlotTypeName(sysDictDataService.selectDictDataById(type).getDictLabel());
                }
            } catch (Exception e){}
        }
        vo.setPlotList(plotList);

        /**以下是获取监控设备列表，返回设备基本信息*/
        List<MonitorCenterDeviceVo> monitorDeviceList = new ArrayList<>();
        TbResDevice deviceParam = new TbResDevice();
        deviceParam.setFarmId(getFarmUUID());
        deviceParam.setDevType(DevTypeEnum.MONITOR_DEV.getCode());
        List<TbResDevice> lisTemp1 = deviceService.selectTbResDeviceList(deviceParam); //根据设备类型查询出设备列表
        for(TbResDevice monitorVo : lisTemp1) {
            MonitorCenterDeviceVo tempVo = new MonitorCenterDeviceVo();
            /**依次取得设备属性并赋值给MonitorCenterDeviceVo对象*/
            tempVo.setDevId(monitorVo .getDevId()); //ID
            tempVo.setDevName(monitorVo.getDevName()); //设备名
            tempVo.setDevNum(monitorVo.getDevNum());   //设备序列号
            tempVo.setLat(monitorVo.getLat());         //经度
            tempVo.setLng(monitorVo.getLng());         //维度
            tempVo.setDevType(monitorVo.getDevType()); //设备类型
            tempVo.setStatus(monitorVo.getStatus());
            monitorDeviceList.add(tempVo);
        }
        vo.setMonitorDeviceList(monitorDeviceList);

        /**以下是获取自动化设备列表，返回设备基本信息*/
        List<MonitorCenterDeviceVo> automationDeviceList = new ArrayList<>();
        TbResDevice deviceParam2 = new TbResDevice();
        deviceParam2.setFarmId(getFarmUUID());
        deviceParam2.setDevType(DevTypeEnum.AUTOMATION_DEV.getCode());
        List<TbResDevice> lisTemp2 = deviceService.selectTbResDeviceList(deviceParam2); //根据灌溉设备类型查询出设备列表
        for(TbResDevice irrigationVo : lisTemp2) {
            MonitorCenterDeviceVo tempVo = new MonitorCenterDeviceVo();
            /**依次取得设备属性并赋值给tempVo*/
            tempVo.setDevName(irrigationVo.getDevName()); //设备名
            tempVo.setDevId(irrigationVo .getDevId()); //ID
            tempVo.setDevNum(irrigationVo.getDevNum());   //设备序列号
            tempVo.setLat(irrigationVo.getLat());         //经度
            tempVo.setLng(irrigationVo.getLng());         //维度
            tempVo.setDevType(irrigationVo.getDevType());
            tempVo.setStatus(irrigationVo.getStatus());
            automationDeviceList.add(tempVo);
        }
        vo.setAutomationDeviceList(automationDeviceList);


        /**以下是获取气象站列表，返回设备基本信息*/
        List<MonitorCenterDeviceVo> weatherStationList = new ArrayList<>();
        TbResDevice deviceParam3 = new TbResDevice();
        deviceParam3.setFarmId(getFarmUUID());
        deviceParam3.setDevType(DevTypeEnum.WEATHER_STATION.getCode());
        List<TbResDevice> lisTemp3 = deviceService.selectTbResDeviceList(deviceParam3); //根据设备类型查询出设备列表
        for(TbResDevice d : lisTemp3) {
            MonitorCenterDeviceVo tempVo = new MonitorCenterDeviceVo();
            /**依次取得设备属性并赋值给MonitorCenterDeviceVo对象*/
            tempVo.setDevId(d .getDevId()); //ID
            tempVo.setDevNum(d.getDevNum());   //设备序列号
            tempVo.setDevName(d.getDevName()); //设备名
            tempVo.setLat(d.getLat());         //经度
            tempVo.setLng(d.getLng());         //维度
            tempVo.setDevType(d.getDevType());
            tempVo.setStatus(d.getStatus());
            weatherStationList.add(tempVo);
        }
        vo.setWeatherDeviceList(weatherStationList);

        /**获取农场中心点经纬度*/
        TbFarm farm = tbFarmService.selectTbFarmById(getFarmUUID());
        if (farm != null) {
            vo.setLatitude(farm.getLatitude());
            vo.setLongitude(farm.getLatitude());
        }

        return success(vo);
    }

    /**
     * 统计数据
     * @return
     */
    @GetMapping("/statics")
    @ResponseBody
    public AjaxResult getStatics() {
        PlotStaticsVo statics = tbPlotService.selectTbPlotStatics(getFarmUUID());
        return success(statics);
    }

    /**
     * 新增保存地块
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody PlotDetailParam plotDetailParam) {
        int result = -1;
        try {
            if(ValidationUtil.isEmpty(plotDetailParam.getPlotId())) {
                //校验
                AjaxResult validation = validation(plotDetailParam);
                if(AjaxResult.getResultStatus(validation) == RequestStatus.FAILED.getStatus())
                    return validation;

                //新增
//                if(!ValidationUtil.isEmpty(plotDetailParam.getPlotImg())){
//                    try{
//                        String saveImgPath = "screenshot/" + UUIDUtils.generateUuid() + ".jpg";
//                        String bgPath = "bgbgbg2.jpg";
//                        ScreenshotUtil.generateImg(plotDetailParam.getPlotImg() ,imgPath+ bgPath ,imgPath+ saveImgPath);
//                        plotDetailParam.setPlotImg(saveImgPath);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                        return AjaxResult.error("生成截图失败");
//                    }
//                }

                plotDetailParam.setCreateBy(getFarmUserCode());
                plotDetailParam.setFarmId(getFarmUUID());
                result = tbPlotService.insertTbPlotDetail(plotDetailParam);
            }else { // 修改

                    // 地块名称不等于空的时候 验证地块名称是否重复
                    if(!ValidationUtil.isEmpty(plotDetailParam.getPlotName())) {
                        //判断当前农场的地块名称是否重复
                        TbPlot plot = new TbPlot();
                        plot.setFarmId(getFarmUUID());
                        plot.setPlotStatus(NormalOrDeleteEnum.NORMAL.getCode());
                        plot.setPlotName(plotDetailParam.getPlotName());
                        List<TbPlot> plots = tbPlotService.selectTbPlotList(plot);
                        if (!ValidationUtil.isEmpty(plots) ) {
                            if(plots.size() >= 2)
                                return error("地块名称" + ValidationConstants.SUFFIX_HAS_EXIST);

                            TbPlot tbPlot = plots.get(0);
                            if(tbPlot.getPlotId() != plotDetailParam.getPlotId())
                                return error("地块名称" + ValidationConstants.SUFFIX_HAS_EXIST);
                    }
                }

                //修改
                plotDetailParam.setUpdateTime(new Date());
                plotDetailParam.setUpdateBy(getFarmUserCode());
                result = tbPlotService.updateTbPlot(plotDetailParam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toAjax(result);
    }


    /**
     * 删除地块
     */
    @GetMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long plotId) {
        if(ValidationUtil.isEmpty(plotId)) {
            return error("地块id"+ValidationConstants.SUFFIX_NOT_EMPTY);
        }

        TbPlot tbPlot = new TbPlot();
        tbPlot.setFarmId(getFarmUUID());
        tbPlot.setPlotStatus(NormalOrDeleteEnum.DELETE.getCode());
        tbPlot.setUpdateTime(new Date());
        tbPlot.setUpdateBy(getFarmUserCode());
        tbPlot.setPlotId(plotId);

        int result = tbPlotService.updateTbPlot(tbPlot);
        return toAjax(result);
    }

    /**
     * 此方式要根据农场查询所有的地块，并且把子地块也一并查询
     */
    @GetMapping("/getPlotAndSubPlotList")
    @ResponseBody
    public AjaxResult getPlotAndSubPlotList(Integer plantingStatus) {
        List<TbPlotVo> list = tbPlotService.selectTbPlotResultBySubPlot(getFarmUUID(),plantingStatus);
        return success(list);
    }

    @Override
    protected AjaxResult validation(Object object) {
        if(object instanceof PlotDetailParam) {

            PlotDetailParam detailParam = (PlotDetailParam)object;

            if (ValidationUtil.isEmpty(detailParam.getPlotName()))
                return error("地块名称" + ValidationConstants.SUFFIX_NOT_EMPTY);
            if (ValidationUtil.isEmpty(detailParam.getRegionId()))
                return error("区域" + ValidationConstants.SUFFIX_NOT_EMPTY);
            if (ValidationUtil.isEmpty(detailParam.getPlotAcreage()))
                return error("地块面积" + ValidationConstants.SUFFIX_NOT_EMPTY);
            if (ValidationUtil.isEmpty(detailParam.getPlotType()))
                return error("地块类型" + ValidationConstants.SUFFIX_NOT_EMPTY);
            if (ValidationUtil.isEmpty(detailParam.getPlotColor()))
                return error("地块颜色" + ValidationConstants.SUFFIX_NOT_EMPTY);

            List<TbPlotCoordinate> coordinateList = detailParam.getCoordinateList();
            if (!ValidationUtil.isEmpty(coordinateList))
            {
                for (TbPlotCoordinate coordinate : coordinateList) {
                    if (ValidationUtil.isEmpty(coordinate.getLatitude())
                            && !ValidationUtil.isEmpty(coordinate.getLongitude()))
                    {
                        return error("纬度" + ValidationConstants.SUFFIX_LACK_PARAMS);
                    }
                    if (!ValidationUtil.isEmpty(coordinate.getLatitude())
                            && ValidationUtil.isEmpty(coordinate.getLongitude()))
                    {
                        return error("经度" + ValidationConstants.SUFFIX_LACK_PARAMS);
                    }
                }
            }

            if(ValidationUtil.isEmpty(detailParam.getPlotId())) {
                //新增
                //判断当前农场的地块名称是否重复
                TbPlot plot = new TbPlot();
                plot.setFarmId(getFarmUUID());
                plot.setPlotStatus(NormalOrDeleteEnum.NORMAL.getCode());
                plot.setPlotName(detailParam.getPlotName());
                List<TbPlot> plots = tbPlotService.selectTbPlotList(plot);
                if (!ValidationUtil.isEmpty(plots))
                    return error("地块名称" + ValidationConstants.SUFFIX_HAS_EXIST);
            } else {
                // 修改
                // 地块名称不等于空的时候 验证地块名称是否重复
                if(!ValidationUtil.isEmpty(detailParam.getPlotName())) {
                    //判断当前农场的地块名称是否重复
                    TbPlot plot = new TbPlot();
                    plot.setFarmId(getFarmUUID());
                    plot.setPlotStatus(NormalOrDeleteEnum.NORMAL.getCode());
                    plot.setPlotName(detailParam.getPlotName());
                    List<TbPlot> plots = tbPlotService.selectTbPlotList(plot);
                    if (!ValidationUtil.isEmpty(plots) ) {
                        if(plots.size() >= 2)
                            return error("地块名称" + ValidationConstants.SUFFIX_HAS_EXIST);

                        TbPlot tbPlot = plots.get(0);
                        if(tbPlot.getPlotId() != detailParam.getPlotId())
                            return error("地块名称" + ValidationConstants.SUFFIX_HAS_EXIST);
                    }
                }
            }
        }
        return success();
    }

}
