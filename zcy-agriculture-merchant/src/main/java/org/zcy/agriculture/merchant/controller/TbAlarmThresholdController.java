package org.zcy.agriculture.merchant.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.DayAlarmDefaultConstants;
import org.zcy.agriculture.entity.TbAlarmDayTime;
import org.zcy.agriculture.entity.TbAlarmThreshold;
import org.zcy.agriculture.entity.TbPlot;
import org.zcy.agriculture.enums.AlarmThresholdTypes;
import org.zcy.agriculture.enums.AlarmThresholdDefaultVlue;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.service.ITbAlarmDayTimeService;
import org.zcy.agriculture.service.ITbAlarmThresholdService;
import org.zcy.agriculture.service.plot.ITbPlotService;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.AlarmThresholdInfoVo;
import org.zcy.agriculture.vo.AlarmThresholdVo;

/**
 * 报警阈值 信息操作处理
 *
 * @author zh
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/api/alarm/Threshold")
public class TbAlarmThresholdController extends BaseController {
    private String prefix = "/api/alarm/Threshold";

    @Autowired
    private ITbAlarmThresholdService tbAlarmThresholdService;

    @Autowired
    private ITbAlarmDayTimeService tbAlarmDayTimeService;

    @Autowired
    private ITbPlotService tbPlotService;

    @GetMapping()
    public String tbAlarmThreshold() {
        return prefix + "/tbAlarmThreshold";
    }

    /**
     * 查询报警阈值列表
     */
    @PostMapping("/info")
    @ResponseBody
    public AjaxResult getAlarmThresholdInfo(@RequestBody TbAlarmThreshold alarmThreshold) {
        /**判断农场ID和地块ID是否有传入*/
//        if (ValidationUtil.isEmpty(alarmThreshold.getFarmId())) {
//            return error("农场ID没有传入");
//        }
        if (ValidationUtil.isEmpty(alarmThreshold.getPlotId())) {
            return error("地块ID没有传入");
        }

        /**这里根据传入的地块ID到地块表中去检测地块是否已存在，如果不存在就不往下走了*/
        TbPlot tbPlot = new TbPlot();
        tbPlot.setFarmId(getFarmUUID());
        tbPlot.setPlotId(alarmThreshold.getPlotId());
        List<TbPlot> listOfPlots = tbPlotService.selectTbPlotList(tbPlot);
        if (listOfPlots.size() <= 0) {
            return error("传入的农场ID或地块ID有误，没查询到地块信息");
        }

        /**获取并设置一个默认值到数据库表中(如果已经存在不做处理，如果不存在按报警阈值默认值插入一条记录)*/
        getAndsetDefaultAlarmThreshold(alarmThreshold);

        AlarmThresholdInfoVo aTVo = new AlarmThresholdInfoVo();
        List<AlarmThresholdVo> listAlarmThresholdVo = new ArrayList<AlarmThresholdVo>();

        /**获取报警阈值表（tb_alarm_threshold）中存储的值*/
        List<TbAlarmThreshold> listAlarmThreshold = tbAlarmThresholdService.selectTbAlarmThresholdList(alarmThreshold);
        for (TbAlarmThreshold aT : listAlarmThreshold) {
            if (aT != null) {
                AlarmThresholdVo temp = new AlarmThresholdVo();
                temp.setDayMaxValue(aT.getDayMaxValue());     //获取并返回白天最大值
                temp.setDayMinValue(aT.getDayMinValue());     //获取并返回白天最小值
                temp.setNightMaxValue(aT.getNightMaxValue()); //获取并返回夜晚最大值
                temp.setNightMinValue(aT.getNightMinValue()); //获取并返回夜晚最小值
                temp.setTypesIndex(aT.getTypesIndex());       //获取并返回阈值类型index
                try {
                    temp.setName(AlarmThresholdTypes.getDescByCode(aT.getTypesIndex()));
                    temp.setUnit(AlarmThresholdTypes.getUnitByCode(aT.getTypesIndex()));
                }catch (Exception e){}
                listAlarmThresholdVo.add(temp);
            }
        }
        aTVo.setList(listAlarmThresholdVo);

        /**查询并获取白天/夜晚阈值*/
        TbAlarmDayTime tbAlarmDayTime = new TbAlarmDayTime();
        tbAlarmDayTime.setFarmId(getFarmUUID());
        tbAlarmDayTime.setPlotId(alarmThreshold.getPlotId());
        if (tbAlarmDayTimeService.getCountFromTbAlarmDayTime(tbAlarmDayTime) <= 0) { //如果白天黑夜起止时间用户还没有设置，生成默认的到数据库表里吧
            TbAlarmDayTime ad = new TbAlarmDayTime();
            ad.setFarmId(getFarmUUID());
            ad.setPlotId(tbAlarmDayTime.getPlotId());

            Time startT = new Time(DayAlarmDefaultConstants.DAY_START_HOUR, //默认白天起始时间为7:00:00
                    DayAlarmDefaultConstants.DAY_START_MINUTE,
                    DayAlarmDefaultConstants.DAY_START_SECOND);
            Time endT = new Time(DayAlarmDefaultConstants.DAY_END_HOUR, //默认白天结束时间18:59:59
                    DayAlarmDefaultConstants.DAY_END_MINUTE,
                    DayAlarmDefaultConstants.DAY_END_SECOND);

            ad.setDayStartTime(startT);
            ad.setDayEndTime(endT);
            tbAlarmDayTimeService.insertTbAlarmDayTime(ad);
        }
        /**查询数据库表（tb_alarm_day_time）并返回相应的数据*/
        List<TbAlarmDayTime> listAlarmDayTime = tbAlarmDayTimeService.selectTbAlarmDayTimeList(tbAlarmDayTime);
        if (listAlarmDayTime.size() > 0) {
            TbAlarmDayTime alarmDayTime = listAlarmDayTime.get(0);
            if (alarmDayTime != null) {
                aTVo.setDayStartTime(alarmDayTime.getDayStartTime());
                aTVo.setDayEndTime(alarmDayTime.getDayEndTime());
            }
        }

        aTVo.setFarmId(alarmThreshold.getFarmId());
        aTVo.setPlotId(alarmThreshold.getPlotId());

        return success(aTVo);
    }

    /**
     * 根据枚举类型（AlarmThresholdTypes）的code设置一个默认值到数据库表中
     */
    private void getAndsetDefaultAlarmThreshold(TbAlarmThreshold alarmThreshold) {
        for (AlarmThresholdTypes status : AlarmThresholdTypes.values()) { //遍历枚举类型AlarmThresholdTypes
            Integer typesIndex = status.getCode(); //从查询出的listTypes里依次获取typesIndex字段的值
            if (typesIndex <= AlarmThresholdTypes.PH.getCode()) {
                TbAlarmThreshold alarmTemp = new TbAlarmThreshold();
                alarmTemp.setFarmId(getFarmUUID()); //设定农场ID
                alarmTemp.setPlotId(alarmThreshold.getPlotId()); //设定地块ID
                alarmTemp.setTypesIndex(typesIndex);

                /**根据默认阈值来设定*/
                if (tbAlarmThresholdService.getCountFromTbAlarmThreshold(alarmTemp) <= 0) {
                    alarmTemp.setDayMinValue(AlarmThresholdDefaultVlue.getDescByCode(typesIndex).getDayMinValue());
                    alarmTemp.setDayMaxValue(AlarmThresholdDefaultVlue.getDescByCode(typesIndex).getDayMaxValue());
                    alarmTemp.setNightMinValue(AlarmThresholdDefaultVlue.getDescByCode(typesIndex).getNightMinValue());
                    alarmTemp.setNightMaxValue(AlarmThresholdDefaultVlue.getDescByCode(typesIndex).getNightMaxValue());
                    tbAlarmThresholdService.insertTbAlarmThreshold(alarmTemp);
                }
            }
        }
    }

    /**
     * 新增报警阈值
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存报警阈值
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbAlarmThreshold tbAlarmThreshold) {
        return toAjax(tbAlarmThresholdService.insertTbAlarmThreshold(tbAlarmThreshold));
    }

    /**
     * 修改报警阈值
     */
    @GetMapping("/edit/{alarmThresholdId}")
    public String edit(@PathVariable("alarmThresholdId") Long alarmThresholdId, ModelMap mmap) {
        TbAlarmThreshold tbAlarmThreshold = tbAlarmThresholdService.selectTbAlarmThresholdById(alarmThresholdId);
        mmap.put("tbAlarmThreshold", tbAlarmThreshold);
        return prefix + "/edit";
    }

    /**
     * 修改保存报警阈值
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody TbAlarmThreshold alarmThreshold) {
        /**判断农场ID和地块ID是否有传入*/
//        if (ValidationUtil.isEmpty(alarmThreshold.getFarmId())) {
//            return error("农场ID没有传入");
//        }
        if (ValidationUtil.isEmpty(alarmThreshold.getPlotId())) {
            return error("地块ID没有传入");
        }

        if (ValidationUtil.isEmpty(alarmThreshold.getTypesIndex())) {
            return error("阈值类型index没有传入");
        }
        if (alarmThreshold.getTypesIndex() > AlarmThresholdTypes.PH.getCode() || alarmThreshold.getTypesIndex() < AlarmThresholdTypes.AIR_TEMPERATURE.getCode()) {
            return error("错误的阈值类型index");
        }

        alarmThreshold.setFarmId(getFarmUUID());
        return toAjax(tbAlarmThresholdService.updateTbAlarmThreshold(alarmThreshold));
    }

    /**
     * 删除报警阈值
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tbAlarmThresholdService.deleteTbAlarmThresholdByIds(ids));
    }

}
