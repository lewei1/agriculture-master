package org.zcy.agriculture.merchant.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.DayAlarmDefaultConstants;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbAlarmDayTime;
import org.zcy.agriculture.entity.TbPlot;
import org.zcy.agriculture.enums.AlarmThresholdTypes;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbAlarmDayTimeService;
import org.zcy.agriculture.service.plot.ITbPlotService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 监控中心--白天报警时间段 信息操作处理
 * 
 * @author zh
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/api/alarmDayTime")
public class TbAlarmDayTimeController extends BaseController
{
    private String prefix = "system/tbAlarmDayTime";
	
	@Autowired
	private ITbAlarmDayTimeService tbAlarmDayTimeService;

	@Autowired
	private ITbPlotService tbPlotService;

	@GetMapping()
	public String tbAlarmDayTime()
	{
	    return prefix + "/tbAlarmDayTime";
	}
	
	/**
	 * 查询监控中心--白天报警时间段列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TbAlarmDayTime tbAlarmDayTime)
	{
		startPage();
        List<TbAlarmDayTime> list = tbAlarmDayTimeService.selectTbAlarmDayTimeList(tbAlarmDayTime);
		return getDataTable(list);
	}

	/**
	 * 查询监控中心--白天报警时间段列表
	 */
	@PostMapping("/plot/threshold/info")
	@ResponseBody
	public TableDataInfo listByFarmIdAndPlotId(@RequestBody TbAlarmDayTime tbAlarmDayTime)
	{
		startPage();

		if (ValidationUtil.isEmpty(tbAlarmDayTime.getPlotId())) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}

		/**如果没查询到相应记录则先添加一条到数据库表*/
		tbAlarmDayTime.setFarmId(getFarmUUID());
		if (tbAlarmDayTimeService.getCountFromTbAlarmDayTime(tbAlarmDayTime) <=0 )  {
			TbAlarmDayTime ad = new TbAlarmDayTime();
			ad.setFarmId(getFarmUUID());
			ad.setPlotId(tbAlarmDayTime.getPlotId());

			Time endT = new Time(DayAlarmDefaultConstants.DAY_END_HOUR, //默认白天结束时间18:59:59
					DayAlarmDefaultConstants.DAY_END_MINUTE,
					DayAlarmDefaultConstants.DAY_END_SECOND);

			Time startT = new Time(DayAlarmDefaultConstants.DAY_START_HOUR, //默认白天起始时间为7:00:00
					DayAlarmDefaultConstants.DAY_START_MINUTE,
					DayAlarmDefaultConstants.DAY_START_SECOND);

			ad.setDayStartTime(startT);
			ad.setDayEndTime(endT);
			tbAlarmDayTimeService.insertTbAlarmDayTime(ad);
		}

		/**返回相应的数据*/
		List<TbAlarmDayTime> list = tbAlarmDayTimeService.selectTbAlarmDayTimeList(tbAlarmDayTime);
		return getDataTable(list);
	}

	/**
	 * 新增监控中心--白天报警时间段
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 根据农场和地块ID新增保存监控中心--白天报警时间段
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody TbAlarmDayTime alarmDayTime)
	{
		if(ValidationUtil.isEmpty(alarmDayTime.getPlotId())) {
			return error("请输入地块ID");
		}
		if(ValidationUtil.isEmpty(alarmDayTime.getDayStartTime())) {
			return error("请输入起始时间");
		}
		if(ValidationUtil.isEmpty(alarmDayTime.getDayEndTime())) {
			return error("请输入终止时间");
		}
		alarmDayTime.setFarmId(getFarmUUID());
		return toAjax(tbAlarmDayTimeService.insertTbAlarmDayTime(alarmDayTime));
	}

	/**
	 * 修改监控中心--白天报警时间段
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TbAlarmDayTime tbAlarmDayTime = tbAlarmDayTimeService.selectTbAlarmDayTimeById(id);
		mmap.put("tbAlarmDayTime", tbAlarmDayTime);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存监控中心--白天报警时间段
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(@RequestBody TbAlarmDayTime alarmDayTime)
	{
//		if (ValidationUtil.isEmpty(alarmDayTime.getFarmId())) {
//			return error("农场ID没有传入");
//		}
		if (ValidationUtil.isEmpty(alarmDayTime.getPlotId())) {
			return error("地块ID没有传入");
		}
		if(ValidationUtil.isEmpty(alarmDayTime.getDayStartTime())) {
			return error("请输入起始时间");
		}
		if(ValidationUtil.isEmpty(alarmDayTime.getDayEndTime())) {
			return error("请输入终止时间");
		}

		/**这里根据传入的地块ID到地块表中去检测地块是否已存在，如果不存在就返回错误信息*/
		TbPlot tbPlot = new TbPlot();
		tbPlot.setPlotId(alarmDayTime.getPlotId());
		tbPlot.setFarmId(getFarmUUID());
		List<TbPlot> listOfPlots = tbPlotService.selectTbPlotList(tbPlot);
		if (listOfPlots.size() <= 0) {
			return error("传入的农场ID或地块ID有误，没查询到地块信息");
		}
		return toAjax(tbAlarmDayTimeService.updateTbAlarmDayTime(alarmDayTime));
	}
	
	/**
	 * 删除监控中心--白天报警时间段
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tbAlarmDayTimeService.deleteTbAlarmDayTimeByIds(ids));
	}
	
}
