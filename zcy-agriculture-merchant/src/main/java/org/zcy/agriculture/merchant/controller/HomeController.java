package org.zcy.agriculture.merchant.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.enums.AlarmThresholdTypes;
import org.zcy.agriculture.enums.DevStatusEnum;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.param.MonitorDeviceInfoParam;
import org.zcy.agriculture.service.IMonitorCenterService;
import org.zcy.agriculture.service.ITbAlarmRecordService;
import org.zcy.agriculture.service.farm.ITbFarmService;
import org.zcy.agriculture.service.ITbFarmingPlanService;
import org.zcy.agriculture.service.ITbHomeGradeService;
import org.zcy.agriculture.service.irrigation.ITbIrrigationLogService;
import org.zcy.agriculture.service.ITbMessageRecordService;
import org.zcy.agriculture.service.ITbResDeviceAttributesService;
import org.zcy.agriculture.service.ITbResDeviceService;
import org.zcy.agriculture.service.ITbWarehouseRecordService;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.HttpCodeMsg;
import org.zcy.agriculture.util.HttpHelper;
import org.zcy.agriculture.util.HttpUtils;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.util.UUIDUtils;
import org.zcy.agriculture.vo.TbHomeGradeVo;
import org.zcy.agriculture.vo.TbResDeviceAttributesVo;
import org.zcy.agriculture.vo.TbWarehouseRecordVo;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

/**
 * 首页
 * 
 * @author zhengy
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/api/home")
public class HomeController extends BaseController {
	private final Long jg = 600L;
	@Autowired
	private ITbFarmService tbFarmService;
	@Autowired
	private ITbWarehouseRecordService tbWarehouseRecordService;
	@Autowired
	private ITbHomeGradeService tbHomeGradeService;
	@Autowired
	private ITbFarmingPlanService tbFarmingPlanService;
	@Autowired
	private ITbMessageRecordService tbMessageRecordService;
	@Autowired
	private ITbAlarmRecordService tbAlarmRecordService;
	@Autowired
	private ITbResDeviceService tbResDeviceService;
	@Autowired
	private ITbIrrigationLogService tbIrrigationLogService;
	@Autowired
	private ITbResDeviceAttributesService tbResDeviceAttributesService;
	@Autowired
	private IMonitorCenterService ionitorCenterService;

	/**
	 * 查询种植 概览
	 */
	@GetMapping("/info")
	@ResponseBody
	public AjaxResult home() {
		List<HashMap<String, Object>> list = new ArrayList<>();
		String farmId = getFarmUUID();
		Long userId = null;
		LoginUserVo u = getFarmUser();
		if (u.getRoleId() == null || u.getRoleId() != 1) {// 如果不是超级管理员
			userId = u.getCode();
		}

		try {
			// 根据农场ID 查询农场信息
			HashMap<String, Object> farm = tbFarmService.selectTbFarmByCity(getFarmUUID());
			if (farm == null || StringUtils.isEmpty(farm.get("farmId").toString())) {
				return error("农场有误！");
			}
			String region = (String) farm.get("dictLabel");
			List<TbHomeGradeVo> hgList = tbHomeGradeService.selectByHomeIndexList(farmId, 0);
			if (hgList != null && hgList.size() > 0) {
				for (TbHomeGradeVo hg : hgList) {
					switch (hg.getDictValue()) {
					case "sb_tj":
						// 1设备统计
						HashMap<String, Object> m1 = Maps.newHashMap();
						m1.put("name", hg.getName());
						m1.put("type", 1);
						List<HashMap<String, Object>> map11List = Lists.newArrayList();
						List<HashMap<String, Object>> map12List = tbResDeviceService.selectByDeviceType(farmId);
						for (int i = 0; i < 2; i++) {
							HashMap<String, Object> map13 = Maps.newHashMap();
							map13.put("column1", i);
							map13.put("column2", 0);
							map13.put("column3", 0);
							map13.put("column4", 0);
							if (i == 0) {
								List<HashMap<String, Object>> map13List = tbAlarmRecordService.selectByStatisticsDateList(farmId, DateUtils.getDate() + " 00:00:00", null, 0);
								map13.put("column4", Long.parseLong(map13List.get(0).get("cou").toString()));
							} else if (i == 1) {
								map13.put("column4", tbIrrigationLogService.selectByFarmIdCou(farmId, DateUtils.getDate()));
							}

							map11List.add(map13);
						}

						if (map12List != null && map12List.size() > 0) {
							for (HashMap<String, Object> m12 : map12List) {
								if ("0".equals(m12.get("devType").toString())) {
									HashMap<String, Object> map13 = map11List.get(0);
									map13.put("column2", m12.get("cou"));
									map13.put("column3", m12.get("bj"));
								} else if ("2".equals(m12.get("devType").toString())) {
									HashMap<String, Object> map13 = map11List.get(1);
									map13.put("column2", m12.get("cou"));
									map13.put("column3", m12.get("yx"));
//								} else if ("3".equals(m12.get("devType").toString())) {
//									HashMap<String, Object> map13 = map11List.get(1);
//									map13.put("column2", m12.get("cou"));
//									map13.put("column3", m12.get("yx"));
								}
							}
						}
						m1.put("data", map11List);
						list.add(m1);
						break;
					case "bj_tj":
						// 2报警统计
						HashMap<String, Object> m2 = Maps.newHashMap();
						m2.put("name", hg.getName());
						m2.put("type", 2);
						List<HashMap<String, Object>> map21List = Lists.newArrayList();
						HashMap<String, Object> m23 = Maps.newHashMap();
						// 获取当前日期
						Calendar ca2 = Calendar.getInstance();
						long b1 = ca2.getTimeInMillis();
						String mj = DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", ca2.getTime());
						ca2.add(Calendar.DATE, -29);
						ca2.set(Calendar.HOUR_OF_DAY, 0);
						ca2.set(Calendar.MINUTE, 0);// 分
						ca2.set(Calendar.SECOND, 0);// 秒
						ca2.set(Calendar.MILLISECOND, 0);// 毫秒
						String mq = DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", ca2.getTime());
						List<HashMap<String, Object>> map22List = tbMessageRecordService.selectByStatisticsDateList(farmId, mq, null, 1);
						long b2 = 0L;
						while (b1 >= b2) {
							String date = UUIDUtils.repairString(ca2.get(Calendar.DATE) + "", 2, '0');
							String month = UUIDUtils.repairString((ca2.get(Calendar.MONTH) + 1) + "", 2, '0');
							int year = ca2.get(Calendar.YEAR);
							HashMap<String, Object> map23 = Maps.newHashMap();
							map23.put("name", month + "/" + date);
							map23.put("value", 0);
							if (map22List != null && map22List.size() > 0) {
								for (HashMap<String, Object> m : map22List) {
									if (m.get("date") != null && m.get("date").toString().equals(year + month + date)) {
										map23.put("value", m.get("cou"));
										break;
									}
								}
							}
							map21List.add(map23);
							ca2.add(Calendar.DATE, 1);
							b2 = ca2.getTimeInMillis();
						}

						ca2.add(Calendar.DATE, -60);
						String mz = DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", ca2.getTime());
						// 查询这个月总共报警数
						List<HashMap<String, Object>> map25List = tbMessageRecordService.selectByStatisticsDateList(farmId, mq, mj, 2);
						// 查询上个月总共报警数
						List<HashMap<String, Object>> map26List = tbMessageRecordService.selectByStatisticsDateList(farmId, mz, mq, 2);
						Long dm = Long.parseLong(map25List.get(0).get("cou").toString());// 近30天报警数
						Long sm = Long.parseLong(map26List.get(0).get("cou").toString());// 近30-60天报警数
						Long dt = Long.parseLong(map21List.get(map21List.size() - 1).get("value").toString());// 今天报警数
						Long st = Long.parseLong(map21List.get(map21List.size() - 2).get("value").toString());// 昨天报警数
						m23.put("todayTotal", dt);
						m23.put("todayRatio", (st == 0 && dt == 0 ? "0" : (st == 0 && dt > 0 ? "100" : String.format("%.2f", (double) (dt - st) * 100 / st))) + "%");
						m23.put("monthTotal", dm);
						m23.put("monthRatio", (sm == 0 && dm == 0 ? "0" : (sm == 0 && dm > 0 ? "100" : String.format("%.2f", (double) (dm - sm) * 100 / sm))) + "%");

						m23.put("list", map21List);
						m2.put("data", m23);
						list.add(m2);
						break;
					case "xx_tj":
						// 3消息统计
						HashMap<String, Object> m3 = Maps.newHashMap();
						m3.put("name", hg.getName());
						m3.put("type", 3);
						HashMap<String, Object> m31 = Maps.newHashMap();
						m31.put("alarmUnread", 0);
						m31.put("alarmTotal", 0);
						m31.put("noticeUnread", 0);
						m31.put("noticeTotal", 0);
						List<HashMap<String, Object>> map3List = tbMessageRecordService.selectBystatisticsType(userId, farmId);
						if (map3List != null && map3List.size() > 0) {
							for (HashMap<String, Object> m : map3List) {
								if ("0".equals(m.get("messageType").toString())) {
									m31.put("alarmUnread", Integer.parseInt(m.get("count").toString()) - Integer.parseInt(m.get("msgTotal").toString()));
									m31.put("alarmTotal", m.get("count"));
								} else if ("1".equals(m.get("messageType").toString())) {
									m31.put("noticeUnread", Integer.parseInt(m.get("count").toString()) - Integer.parseInt(m.get("msgTotal").toString()));
									m31.put("noticeTotal", m.get("count"));
								}
							}
						}
						m3.put("data", m31);
						list.add(m3);
						break;
					case "bj_sj_fb":
						// 4报警时间分布
						HashMap<String, Object> m4 = Maps.newHashMap();
						m4.put("name", hg.getName());
						m4.put("type", 4);
						List<HashMap<String, Object>> map41List = Lists.newArrayList();

						// 获取当前日期
						Calendar ca4 = Calendar.getInstance();
						long t1 = ca4.getTimeInMillis();
						ca4.add(Calendar.DATE, -1);
						ca4.add(Calendar.HOUR_OF_DAY, 1);
						ca4.set(Calendar.MINUTE, 0);// 分
						ca4.set(Calendar.SECOND, 0);// 秒
						ca4.set(Calendar.MILLISECOND, 0);// 毫秒
						List<HashMap<String, Object>> map42List = tbMessageRecordService.selectByStatisticsTimeList(farmId, DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", ca4.getTime()));
						long t2 = 0L;
						ca4.add(Calendar.HOUR_OF_DAY, 1);
						while (t1 >= t2) {
							int hour = ca4.get(Calendar.HOUR_OF_DAY);
							HashMap<String, Object> map43 = Maps.newHashMap();
							map43.put("name", UUIDUtils.repairString(hour + "", 2, '0') + ":00");
							map43.put("value", 0);
							if (map42List != null && map42List.size() > 0) {
								for (HashMap<String, Object> m : map42List) {
									if (m.get("time") != null && Integer.parseInt(m.get("time").toString()) == hour) {
										map43.put("value", m.get("cou"));
										break;
									}
								}
							}
							map41List.add(map43);
							ca4.add(Calendar.HOUR_OF_DAY, 1);
							t2 = ca4.getTimeInMillis();
						}
						m4.put("data", map41List);
						list.add(m4);
						break;
					case "bj_cs_fb":
						// 5报警参数分布
						HashMap<String, Object> m5 = Maps.newHashMap();
						m5.put("name", hg.getName());
						m5.put("type", 5);

						// 获取当前日期
						Calendar ca5 = Calendar.getInstance();
						ca5.add(Calendar.DATE, -29);
						List<HashMap<String, Object>> map5List = tbAlarmRecordService.selectByStatisticsList(farmId, DateUtils.dateTime(ca5.getTime()));
						if (map5List != null && map5List.size() > 0) {
							for (HashMap<String, Object> m : map5List) {
								m.put("name", AlarmThresholdTypes.getDescByCode(Integer.parseInt(m.get("typesIndex").toString())));
							}
						}
						m5.put("data", map5List);
						list.add(m5);
						break;
					case "tq":
						// 6天气
						HashMap<String, Object> m6 = Maps.newHashMap();
						m6.put("name", hg.getName());
						m6.put("type", 6);
						if (StringUtils.isNotEmpty(region)) {
							m6.put("data", tq(region));
						}
						list.add(m6);
						break;
					case "qxz":
						// 7气象站
						HashMap<String, Object> m7 = Maps.newHashMap();
						m7.put("name", hg.getName());
						m7.put("type", 7);

						HashMap<String, Object> m71 = Maps.newHashMap();
						TbResDevice tbResDevice = new TbResDevice();
						tbResDevice.setFarmId(farmId);
						tbResDevice.setDevType(1);// 气象站
						List<TbResDevice> rd = tbResDeviceService.selectTbResDeviceList(tbResDevice);
						m71.put("list", rd);
						if (rd != null && rd.size() > 0) {
							m71.put("qxz", getQxz(rd.get(0).getDevNum()));
						}

						m7.put("data", m71);
						list.add(m7);
						break;
					case "hj_zs":
						// 8环境走势
						HashMap<String, Object> m8 = Maps.newHashMap();
						m8.put("name", hg.getName());
						m8.put("type", 8);
						HashMap<String, Object> m81 = Maps.newHashMap();

						TbResDevice r8 = new TbResDevice();
						r8.setFarmId(farmId);
						r8.setDevType(0);// 监测设备
						List<TbResDevice> r8List = tbResDeviceService.selectTbResDeviceList(r8);
						m81.put("listSb", r8List);
						if (r8List != null && r8List.size() > 0) {
							TbResDeviceAttributesVo tbResDeviceAttributes = new TbResDeviceAttributesVo();
							tbResDeviceAttributes.setDevId(r8List.get(0).getDevId());
							List<TbResDeviceAttributes> r81List = tbResDeviceAttributesService.selectTbResDeviceAttributesList(tbResDeviceAttributes);
							m81.put("listCgq", r81List);
							if (r81List != null && r81List.size() > 0) {
								Calendar ca8 = Calendar.getInstance();
								MonitorDeviceInfoParam param = new MonitorDeviceInfoParam();
								param.setDevNum(r8List.get(0).getDevNum());
								param.setAttributes(r81List.get(0).getThingsboardKey());
								param.setIntervalSecond(jg);
								param.setEndDateStr(DateUtils.parseDateToStr("yyyy-MM-dd", ca8.getTime()));
								ca8.add(Calendar.DATE, -1);
								param.setStartDateStr(DateUtils.parseDateToStr("yyyy-MM-dd", ca8.getTime()));
								String res = ionitorCenterService.deviceAttributeHistoryInfo(param);
								if (StringUtils.isNotEmpty(res)) {
									JSONArray json = (JSONArray) JSONObject.parseObject(res).get(r81List.get(0).getThingsboardKey());
									if (json != null && json.size() > 0) {
										for (int i = 0; i < json.size(); i++) {
											JSONObject job = json.getJSONObject(i);
											job.put("value", String.format("%.2f", Double.parseDouble(job.get("value").toString())));
											Long r = job.getLong("ts");
											job.put("name", DateUtils.getHourAndMinute(r, "MM-dd HH:mm"));
										}
									}
									m81.put("list", json);
								}
							}
						}

						m8.put("data", m81);
						list.add(m8);
						break;
					case "ys_tj":
						// 9用水统计
						HashMap<String, Object> m9 = Maps.newHashMap();
						m9.put("name", hg.getName());
						m9.put("type", 9);
						list.add(m9);
						break;
					case "ns_dt":
						List<HashMap<String, Object>> m = tbFarmingPlanService.selectByHomeList(farmId);
						m.forEach(h -> {
							h.put("updateTime", h.get("updateTime") == null ? "" : DateUtils.getTimes((Date) h.get("updateTime")));
						});
						// 10农事动态
						HashMap<String, Object> m10 = Maps.newHashMap();
						m10.put("name", hg.getName());
						m10.put("type", 10);
						m10.put("data", m);
						list.add(m10);
						break;
					case "sb_cz_jl":
						// 11设备操作记录
						HashMap<String, Object> m11 = Maps.newHashMap();
						m11.put("name", hg.getName());
						m11.put("type", 11);
						m11.put("data", tbIrrigationLogService.selectByFarmIdList(farmId));
						list.add(m11);
						break;
					case "rw_jd":
						// 12任务进度
						HashMap<String, Object> m12 = Maps.newHashMap();
						m12.put("name", hg.getName());
						m12.put("type", 12);
						m12.put("data", tbFarmingPlanService.selectByHomeTaskCompletion(userId, farmId));
						list.add(m12);
						break;
					case "ck_tj":
						// 13仓库统计
						HashMap<String, Object> m13 = Maps.newHashMap();
						m13.put("name", hg.getName());
						m13.put("type", 13);

						HashMap<String, Object> m131 = Maps.newHashMap();
						m131.put("inTypeTotal", 0);
						m131.put("outTypeTotal", 0);
						List<TbWarehouseRecordVo> wr = tbWarehouseRecordService.selectByTodayStatistics(getFarmUUID(), DateUtils.dateTimeNow("yyyyMMdd"));
						if (wr != null && wr.size() > 0) {
							for (TbWarehouseRecordVo w : wr) {
								if ("O".equals(w.getInOutType())) {
									m131.put("outTypeTotal", w.getQuantity());
								} else if ("I".equals(w.getInOutType())) {
									m131.put("inTypeTotal", w.getQuantity());
								}
							}
						}
						m13.put("data", m131);
						list.add(m13);
						break;
					default:
						break;
					}
				}
			}
			return success(list);
		} catch (Exception e) {
			logger.error("查询首页！", e);
			return error();
		}

	}

	/**
	 * 配置首页展示面板
	 */
	@GetMapping("/getHomeStatus")
	@ResponseBody
	public AjaxResult getHomeStatus() {
		return success(tbHomeGradeService.selectByHomeIndexList(getFarmUUID(), null));
	}

	/**
	 * 保存&&修改 配置首页展示面板
	 */
	@PostMapping("/saveHome")
	@ResponseBody
	public AjaxResult saveHome(@RequestBody List<TbHomeGradeVo> tbHomeGradeVo) {
		if (tbHomeGradeVo == null || tbHomeGradeVo.size() == 0) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbHomeGradeVo.forEach(h -> {
			h.setFarmId(getFarmUUID());
		});
		try {
			tbHomeGradeService.saveHome(tbHomeGradeVo);
			return success();
		} catch (Exception e) {
			logger.error("保存&&修改  配置首页展示面板异常！", e);
			return error();
		}
	}

	/**
	 * 获取天气参数
	 */
	@GetMapping("/getWeather")
	@ResponseBody
	public AjaxResult getWeather(String city) {
		if (StringUtils.isEmpty(city)) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		return success(tq(city));
	}

	/**
	 * 获取环境走势
	 */
	@GetMapping("/getSb")
	@ResponseBody
	public AjaxResult getSb(String devNum, String thingsboardKey) {
		if (StringUtils.isEmpty(devNum) || StringUtils.isEmpty(thingsboardKey)) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		Calendar ca8 = Calendar.getInstance();
		MonitorDeviceInfoParam param = new MonitorDeviceInfoParam();
		param.setDevNum(devNum);
		param.setAttributes(thingsboardKey);
		param.setIntervalSecond(jg);
		param.setEndDateStr(DateUtils.parseDateToStr("yyyy-MM-dd", ca8.getTime()));
		ca8.add(Calendar.DATE, -1);
		param.setStartDateStr(DateUtils.parseDateToStr("yyyy-MM-dd", ca8.getTime()));
		String res = ionitorCenterService.deviceAttributeHistoryInfo(param);
		if (StringUtils.isNotEmpty(res)) {
			JSONArray json = (JSONArray) JSONObject.parseObject(res).get(thingsboardKey);
			if (json != null && json.size() > 0) {
				for (int i = 0; i < json.size(); i++) {
					JSONObject job = json.getJSONObject(i);
					job.put("value", String.format("%.2f", Double.parseDouble(job.get("value").toString())));
					Long r = job.getLong("ts");
					job.put("name", DateUtils.getHourAndMinute(r, "MM-dd HH:mm"));
				}
			}
			return success(json);
		} else {
			return success();
		}

	}

	/**
	 * 根据设备序列号 实时查询设备信息
	 * 
	 * @param devNum
	 * @param devId
	 * @return
	 */
	@GetMapping("/getWeatherStation")
	@ResponseBody
	public AjaxResult getWeatherStation(String devNum) {
		if (StringUtils.isEmpty(devNum)) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		return success(getQxz(devNum));
	}

	/**
	 * 获取天气参数
	 * 
	 * @param city
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object tq(String city) {
		String tq;
		HashMap<String, Object> jo = null;
		String res = "汉族,蒙古族,回族,藏族,维吾尔族,苗族,彝族,壮族,布依族,朝鲜族,满族,侗族,瑶族,白族,土家族,哈尼族,哈萨克族,傣族,黎族,傈僳族,佤族,畲族,高山族,拉祜族,水族,东乡族,纳西族,景颇族,柯尔克孜族,土族,达斡尔族,仫佬族,羌族,布朗族,撒拉族,毛难族,仡佬族,锡伯族,阿昌族,普米族,塔吉克族,怒族,乌孜别克族,俄罗斯族,鄂温克族,崩龙族,保安族,裕固族,京族,塔塔尔族,独龙族,鄂伦春族,赫哲族,门巴族,珞巴族,基诺族,自治县,自治州";
		String mz[] = res.split(",");
		for (String m : mz) {
			city = city.replaceAll(m, "");
		}
		try {
			tq = HttpHelper.getWeather("http://wthrcdn.etouch.cn/weather_mini?city=" + city);
			if (StringUtils.isNotEmpty(tq)) {// 可能因为名字太长查询失败
				jo = JSONObject.parseObject(tq, HashMap.class);
				if ((jo.get("status") == null || 1000 != Integer.parseInt(jo.get("status").toString())) && city.length() > 3) {
					tq = HttpHelper.getWeather("http://wthrcdn.etouch.cn/weather_mini?city=" + city.substring(0, 3));
					jo = JSONObject.parseObject(tq, HashMap.class);
				}
				return jo.get("data");
			}
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return null;
	}

	/**
	 * 获取设备数据
	 * 
	 * @param devNum
	 * @param devId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object getQxz(String devNum) {
		String attributesDataURL = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1;
		attributesDataURL = attributesDataURL + devNum + ThingsboardUrlConstants.DEVICE_VALUES_TIMESERIES_SUB_URL + "?";
		HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(attributesDataURL, tbResDeviceService.getIOTToken());
		List<HashMap<String, Object>> list1 = Lists.newArrayList();
		if (StringUtils.isNotEmpty(msg.getMsg())) {
			HashMap<String, Object> jsonObject = JSONObject.parseObject(msg.getMsg(), HashMap.class);
			for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
				System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
				if (entry.getValue() != null) {
					List<JSONObject> list2 = (List<JSONObject>) entry.getValue();
					HashMap<String, Object> m = Maps.newHashMap();
					m.put("type", entry.getKey());
					m.put("name", AlarmThresholdTypes.getCoinAddress(entry.getKey(), "getVal"));
					m.put("time", DateUtils.getTimes(new Date(list2.get(0).getLongValue("ts"))));
					m.put("dateTime", DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date(list2.get(0).getLongValue("ts"))));

					if ("WEATHERDATA_WINDD".equals(entry.getKey().toString())) {// 如果 是风向 没有单位
						Double d = Double.parseDouble(list2.get(0).get("value").toString());
						m.put("value", "");
						if (d < 45d) {
							m.put("unit", "北风");
						} else if (d < 90d) {
							m.put("unit", "东北风");
						} else if (d < 135d) {
							m.put("unit", "东风");
						} else if (d < 180) {
							m.put("unit", "东南风");
						} else if (d < 225d) {
							m.put("unit", "南风");
						} else if (d < 270d) {
							m.put("unit", "西南风");
						} else if (d < 315d) {
							m.put("unit", "西风");
						} else if (d >= 315d) {
							m.put("unit", "西北风");
						}
					} else {
						m.put("value", list2.get(0).get("value"));
						m.put("unit", AlarmThresholdTypes.getCoinAddress(entry.getKey(), "getUnit"));
					}
					list1.add(m);
				}
			}

			TbResDevice tbResDevice = new TbResDevice();
			tbResDevice.setDevNum(devNum);
			List<TbResDevice> listDevice = tbResDeviceService.selectTbResDeviceList(tbResDevice);
			if (listDevice.size() > 0) {
				TbResDevice d = listDevice.get(0);
				d.setStatus(DevStatusEnum.RUNING.getCode());
				tbResDeviceService.updateTbResDevice(d);
			}

			return list1;
		}
		return null;
	}

	public static void main(String[] args) {
		Calendar ca = Calendar.getInstance();
		long t1 = ca.getTimeInMillis();
		ca.add(Calendar.DATE, -1);
		long t2 = ca.getTimeInMillis();
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t1 - t2);
		System.out.println(String.format("%.2f", -100.1));
	}
}
