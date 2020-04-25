package org.zcy.agriculture.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.entity.TbAlarmRecord;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.param.LastDayAlarmListParam;
import org.zcy.agriculture.service.ITbAlarmRecordService;
import org.zcy.agriculture.service.IThingsboardService;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.HttpCodeMsg;
import org.zcy.agriculture.vo.AlarmDayNumVo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 报警记录 信息操作处理
 * 
 * @author zh
 * @date 2019-07-02
 */
@Controller
@RequestMapping("/api/alarm")
public class TbAlarmRecordController extends BaseController
{
    private String prefix = "/api/alarm";
	
	@Autowired
	private ITbAlarmRecordService tbAlarmRecordService;

	@Autowired
	private IThingsboardService tingsboardService;

	@GetMapping()
	public String tbAlarmRecord()
	{
	    return prefix + "/tbAlarmRecord";
	}
	
	/**
	 * 查询报警记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TbAlarmRecord tbAlarmRecord)
	{
		startPage();
        List<TbAlarmRecord> list = tbAlarmRecordService.selectTbAlarmRecordList(tbAlarmRecord);
		return getDataTable(list);
	}

	/**
	 * 新增报警记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存报警记录
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TbAlarmRecord tbAlarmRecord)
	{		
		return toAjax(tbAlarmRecordService.insertTbAlarmRecord(tbAlarmRecord));
	}

	/**
	 * 修改报警记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TbAlarmRecord tbAlarmRecord = tbAlarmRecordService.selectTbAlarmRecordById(id);
		mmap.put("tbAlarmRecord", tbAlarmRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存报警记录
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TbAlarmRecord tbAlarmRecord)
	{		
		return toAjax(tbAlarmRecordService.updateTbAlarmRecord(tbAlarmRecord));
	}
	
	/**
	 * 删除报警记录
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tbAlarmRecordService.deleteTbAlarmRecordByIds(ids));
	}

	/**
	 * 获取过去每一天的报警统计
	 * @param dayNum 过去的天数
	 */
	@PostMapping( "/last/day/list")
	@ResponseBody
	public TableDataInfo LastDays(@RequestBody LastDayAlarmListParam dayNum)
	{
		Long lastNumber = dayNum.getLastNumber();
		if (lastNumber == null || lastNumber<= 0 || lastNumber>30){
			lastNumber = 30L; //默认统计最近30天的报警数据
		}

		List<AlarmDayNumVo> list = new ArrayList<AlarmDayNumVo>();
		/**以下代码是依次统计过去每一天有多少条报警记录*/
		for (int i = 0;i< lastNumber;i++) {
			try {
				DateFormat dateFormat = new SimpleDateFormat("MM-dd");
				DateFormat dateFormat2 = new SimpleDateFormat("YYYY-MM-dd 00:00:00");
				Date dateEnd = DateUtils.getLastDayByIndex(i-1); //每天结束的时间字符串
				Date dateStart = DateUtils.getLastDayByIndex(i); //每天开始的时间字符串
				Long number = tbAlarmRecordService.selectCountBetweenTheTime(dateFormat2.format(dateStart),dateFormat2.format(dateEnd));
				String dateStr = dateFormat.format(dateStart);
				AlarmDayNumVo vo= new AlarmDayNumVo();
				vo.setAlarmNum(number); //报警数
				vo.setDateStr(dateStr); //时间字符串
				list.add(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return getDataTable(list);
	}
}
