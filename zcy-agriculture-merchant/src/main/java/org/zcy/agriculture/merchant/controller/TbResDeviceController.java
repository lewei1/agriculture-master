package org.zcy.agriculture.merchant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pagehelper.PageHelper;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbPlot;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.enums.AlarmThresholdTypes;
import org.zcy.agriculture.enums.DevTypeEnum;
import org.zcy.agriculture.enums.ThingsboardDeviceEnum;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.PageDomain;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.page.TableSupport;
import org.zcy.agriculture.param.MonitorDeviceInfoParam;
import org.zcy.agriculture.service.IMonitorCenterService;
import org.zcy.agriculture.service.plot.ITbPlotService;
import org.zcy.agriculture.service.ITbResDeviceAttributesService;
import org.zcy.agriculture.service.ITbResDeviceService;
import org.zcy.agriculture.service.IThingsboardService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.HttpCodeMsg;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.DeviceBaseInfoVo;
import org.zcy.agriculture.vo.DeviceDetails;
import org.zcy.agriculture.vo.DeviceStatusStatisticsVo;
import org.zcy.agriculture.vo.DeviceTypeStatisticsVo;
import org.zcy.agriculture.vo.DeviceTypesBaseInfoVo;
import org.zcy.agriculture.vo.TbResDeviceAttributesVo;
import org.zcy.agriculture.vo.TbResDeviceExtraVo;
import org.zcy.agriculture.vo.ThingsboardDeviceAtrributeVo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.zcy.agriculture.vo.device.TbResDeviceAttributesExtraVo;

/**
 * 设备 信息操作处理
 * 
 * @author zh
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/device")
public class TbResDeviceController extends BaseController
{
    private String prefix = "api/device";
	
	@Autowired
	private ITbResDeviceService tbResDeviceService;

	@Autowired
	private ITbResDeviceAttributesService tbResDeviceAttributesService;

	@Autowired
	private ITbPlotService plotService;

	@Autowired
	private IThingsboardService tingsboardService;

	@GetMapping()
	public String tbResDevice()
	{
	    return prefix + "/tbResDevice";
	}
	
	/**
	 * 查询设备列表
	 */
	@PostMapping("/listByStatus")
	@ResponseBody
	public TableDataInfo listByStatus(@RequestBody TbResDevice tbResDevice)
	{
		tbResDevice.setFarmId(getFarmUUID());
		Long count = tbResDeviceService.getCountFromTbResDevice(tbResDevice);

		//startPage();
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

        List<TbResDevice> listDevice = tbResDeviceService.selectTbResDeviceList(tbResDevice); //根据分页条件查询设备列表
		List<TbResDeviceExtraVo> list = new ArrayList<>();
		for (TbResDevice d:listDevice) {
			TbResDeviceExtraVo vo = new TbResDeviceExtraVo();
			BeanUtils.copyBeanProp(vo,d);
			if (d.getPlotId()!=null) { //根据设备记录里的地块ID值查询出地块名
				TbPlot plot = plotService.selectTbPlotById(d.getPlotId());
				vo.setPlotName(plot.getPlotName());
			}
			list.add(vo);
		}

		rspData.setRows(list);
		rspData.setTotal(count);
		return rspData;
	}
	
	/**
	 * 查询设备列表（根据设备类型查询、没有分页）
	 */
	@GetMapping("/listByType")
	@ResponseBody
	public AjaxResult list()
	{
		Long monitorNum = 0L;       //监测设备数量
		Long weatherStaionNum = 0L; //气象站数量
		Long automationNum = 0L;    //自动化设备数量
		Long cameraNum = 0L;        //摄像头数量

		DeviceTypesBaseInfoVo TypesVo = new DeviceTypesBaseInfoVo(); //定义返回的VO对象

		List<DeviceBaseInfoVo> listMonitor = new LinkedList<>();  //监测设备list
		List<DeviceBaseInfoVo> listWeatherStation = new LinkedList<>(); //气象站list
		List<DeviceBaseInfoVo> listAutomation = new LinkedList<>();     //自动化设备list
		List<DeviceBaseInfoVo> listCamera = new LinkedList<>();         //摄像头list

		TbResDevice tbResDevice = new TbResDevice();
		tbResDevice.setFarmId(getFarmUUID());
		List<TbResDevice> list = tbResDeviceService.selectTbResDeviceList(tbResDevice); //查询出设备列表
		for(int i=0;i<list.size();i++){
			TbResDevice d = list.get(i);
			DeviceBaseInfoVo vo = new DeviceBaseInfoVo();
			vo.setDevId(d.getDevId());      //设备ID
			vo.setDevName(d.getDevName());  //设备名
			vo.setDevNum(d.getDevNum());    //设备序列号
			vo.setDevType(d.getDevType());  //设备类型
			vo.setDevStatus(d.getStatus()); //设备状态值

			if ( d.getDevType() == DevTypeEnum.MONITOR_DEV.getCode() ) { //监测设备
				listMonitor.add(vo);
				monitorNum ++;
			}
			if ( d.getDevType() == DevTypeEnum.WEATHER_STATION.getCode() ) { //气象站
				listWeatherStation.add(vo);
				weatherStaionNum ++;
			}
			if ( d.getDevType() == DevTypeEnum.AUTOMATION_DEV.getCode() ) { //自动化设备
				listAutomation.add(vo);
				automationNum ++;
			}
			if ( d.getDevType() == DevTypeEnum.CAMERA.getCode() ) { //摄像头
				listCamera.add(vo);
				cameraNum ++;
			}
		}

		/**下面是将各类设备数量和列表依次传给返回的VO对象*/
		TypesVo.setMonitorNum(monitorNum);
		TypesVo.setListMonitor(listMonitor);

		TypesVo.setWeatherStationNum(weatherStaionNum);
		TypesVo.setListWeatherStation(listWeatherStation);

		TypesVo.setAutomationNum(automationNum);
		TypesVo.setListAutomation(listAutomation);

		TypesVo.setCameraNum(cameraNum);
		TypesVo.setListCamera(listCamera);

		TypesVo.setTotalNum(monitorNum + weatherStaionNum + automationNum  + cameraNum); //总数

		return success(TypesVo);
	}
	
	/**
	 * 新增设备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 添加设备
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody TbResDevice device)
	{
		/**第一步，传入的设备序列号不能为空*/
		if (ValidationUtil.isEmpty(device.getDevNum())) {
			return error("设备序列号不能为空");
		}

		if (tbResDeviceService.getCountFromTbResDeviceByDevNum(device.getDevNum()) >0 ){
			return error("设备已添加，不可重复添加");
		}

		/**第二步，传入的设备名不能为空*/
		if (ValidationUtil.isEmpty(device.getDevType())) {
			return error("设备类型不能为空");
		}

		/**第二步，先确保设备序列号在物联网端是能查询得到的*/
		if (tbResDeviceService.isDeviceExistinDevice(device.getDevNum()) == ThingsboardDeviceEnum.NOT_EXIST.getCode()) {
			return error("设备不存在");
		}

		device.setFarmId(getFarmUUID()); //农场ID
		return toAjax(tbResDeviceService.insertTbResDevice(device));
	}

	/**
	 * 修改设备
	 */
	@GetMapping("/edit/{devId}")
	public String edit(@PathVariable("devId") Long devId, ModelMap mmap)
	{
		TbResDevice tbResDevice = tbResDeviceService.selectTbResDeviceById(devId);
		mmap.put("tbResDevice", tbResDevice);
	    return prefix + "/edit";
	}

	/**
	 * 获取设备详情
	 */
	@PostMapping("/detail/info")
	@ResponseBody
	public AjaxResult getDevInfo(@RequestBody TbResDevice dev) {
		/**获取设备基本信息*/
		DeviceDetails details = new DeviceDetails();
		TbResDevice devR = tbResDeviceService.selectTbResDeviceById(dev.getDevId());

		if (devR == null) {
			return success("设备不存在");
		}

		/**获取设备接口属性*/
		TbResDeviceAttributesVo da = new TbResDeviceAttributesVo();
		da.setDevId(dev.getDevId());
		List<TbResDeviceAttributes> list = tbResDeviceAttributesService.selectTbResDeviceAttributesList(da);
		if (list.size()==0){
			tbResDeviceService.getTbResDeviceAttributeFromThingsboard(dev);
			list = tbResDeviceAttributesService.selectTbResDeviceAttributesList(da);
		}
		List<TbResDeviceAttributesExtraVo> listAttributesExtraVo = new ArrayList<>();
		for (TbResDeviceAttributes attributes:list){
			TbResDeviceAttributesExtraVo vo = new TbResDeviceAttributesExtraVo();
			BeanUtils.copyBeanProp(vo,attributes);
			listAttributesExtraVo.add(vo);
		}

		/**获取设备所有传感器属性*/
		String thingsboardKeys = "";
		boolean isFirst = true;
		for (TbResDeviceAttributes deviceAttributes : list) {
			if (isFirst) {
				thingsboardKeys += deviceAttributes.getThingsboardKey();
				isFirst = false;
			} else { //如果不是第一个单词每个传感器属性前要加一个","
				thingsboardKeys += "," + deviceAttributes.getThingsboardKey();
			}
		}

		if ((devR != null) && (list != null)) {
			TbResDevice device = tbResDeviceService.selectTbResDeviceById(dev.getDevId());
			BeanUtils.copyBeanProp(details, device);
			if (device != null) {
				TbPlot plot = plotService.selectTbPlotById(device.getPlotId());
				if (plot!=null)
				details.setPlotName(plot.getPlotName());
			}
		}

			Map mapNowData = new HashMap();
			String msgNow = tbResDeviceService.getDeviceAttributeNowDataFromThingsboard(devR.getDevNum(),thingsboardKeys);
				if (StringUtils.isNotEmpty(msgNow)) {
				JSONObject jsonObject = (JSONObject) JSONObject.parse(msgNow);

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
						Long tsValue = Long.parseLong(tokenVo.getTs());

						JSONObject jsonObjectTemp2 = new JSONObject();
						jsonObjectTemp2.put("value", String.format("%.2f", Double.parseDouble(jsonObjectTemp.get("value").toString())));
						jsonObjectTemp2.put("ts", DateUtils.getHourAndMinute(tsValue, DateUtils.YYYY_MM_DD_HH_MM_SS));

						jR.add(jsonObjectTemp2);
					}
				} catch (Exception e) {
				}
				mapNowData.put(entry.getKey(), jR);
				}
			}

		for (TbResDeviceAttributesExtraVo attributesExtraVo:listAttributesExtraVo){
			String key = attributesExtraVo.getThingsboardKey();
			JSONArray jsonArray = (JSONArray)mapNowData.get(key);
			if(jsonArray.size()>0) {
				JSONObject job = jsonArray.getJSONObject(0);//把每一个对象转成json对象
				attributesExtraVo.setTs((String)job.get("ts")); //得到每个对象中的ts值
				attributesExtraVo.setValue((String)job.get("value"));
			}
		}

		details.setListAttributes(listAttributesExtraVo);
		details.setListThingsboardKeys(AlarmThresholdTypes.getAllKeyVal());

		/**根据设备序列号以及传感器属性获取设备实时数据*/
		if (thingsboardKeys!= "") {
			String msg = tbResDeviceService.deviceAttributeWithRecent24Hours(devR.getDevNum(), thingsboardKeys);
			if (StringUtils.isNotEmpty(msg)) {
				JSONObject jsonObject = (JSONObject) JSONObject.parse(msg);
				Map mapRet = new HashMap();

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
							Long tsValue = Long.parseLong(tokenVo.getTs());

							JSONObject jsonObjectTemp2 = new JSONObject();
							jsonObjectTemp2.put("value", String.format("%.2f", Double.parseDouble(jsonObjectTemp.get("value").toString())));
							jsonObjectTemp2.put("ts", DateUtils.getHourAndMinute(tsValue, DateUtils.YYYY_MM_DD_HH_MM_SS));

							if (tsValue >= System.currentTimeMillis() - 24 * 3600000) {
								jR.add(jsonObjectTemp2);
							}
						}
					} catch (Exception e) {
						return error();
					}
					mapRet.put(entry.getKey(), jR);
				}
				details.setTsData(mapRet);
				return success(details);
			} else {
				return error("查询失败");
			}
		}
		return success(details);
	}

	/**
	 * 修改并保存设备
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(@RequestBody TbResDevice device)
	{
		try {
			/**传入的设备序列号不能为空*/
			if (ValidationUtil.isEmpty(device.getDevId())) {
				return error("设备ID不能为空");
			}
			//result = tbResDeviceService.updateTbResDevice(device);
			return toAjax(tbResDeviceService.updateTbResDevice(device));
		} catch (Exception e) {
			e.printStackTrace();
			return error("服务器错误");
		}
	}
	
	/**
	 * 删除设备
	 */
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(@RequestBody TbResDevice tbResDevice)
	{		
		return toAjax(tbResDeviceService.deleteTbResDeviceById(tbResDevice.getDevId()));
	}


	@GetMapping("/status/statistics")
	@ResponseBody
	public AjaxResult statusStatistics()
	{
		DeviceStatusStatisticsVo s = tbResDeviceService.deviceStatusStatistics(getFarmUUID());
		return success(s);
	}

	@GetMapping("/type/statistics")
	@ResponseBody
	public AjaxResult typeStatistics()
	{
		DeviceTypeStatisticsVo s = tbResDeviceService.deviceTypeStatistics(getFarmUUID());
		return success(s);
	}

	@PostMapping( "/exist")
	@ResponseBody
	public AjaxResult DeviceExist(@RequestBody TbResDevice device)
	{
		/**传入的设备序列号不能为空*/
		if (ValidationUtil.isEmpty(device.getDevNum())) {
			return error("设备序列号不能为空");
		}

		HttpCodeMsg msg =  tingsboardService.getDeviceAttributesFromThingsboard(device.getDevNum());

		if (msg.getCode() == 401) { //token已过期，重新设置
			return error("服务器token过期，稍后再试");
		}
		if (msg.getCode() == 200 && msg.getMsg()!= null) {
			return success("存在的");
		} else {
			return success("设备不存在");
		}
	}

	/**
	 * 查询设备列表  后期版本已经取掉 根据地块查询设备
	 */
	@GetMapping("/getResDeviceList")
	@ResponseBody
	public AjaxResult getResDeviceList(String plotId,Integer devType) {
//		if (StringUtils.isEmpty(plotId)) {
//			return success(RequestStatus.PARAM_REQUIRED.getMessage());
//		}

//		过滤特殊字符
//		String regEx="[`~!@#$%^&*()+=|{}':;'\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(plotId);
//		
		HashMap<String,Object> map = Maps.newHashMap();
		map.put("farmId", getFarmUUID());
		map.put("devTypes", devType + "");
		if(devType == null) {
			map.put("devTypes", "0,1,2");
		}
		
//		map.put("plotIds", m.replaceAll("").trim());//查询sql 用到了${} 故要去掉特殊字符
		return success(tbResDeviceService.selectByDeviceList(map));
	}
}
