package org.zcy.agriculture.merchant.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbFarmingPlan;
import org.zcy.agriculture.entity.TbPlanMaterial;
import org.zcy.agriculture.entity.TbPlanMessage;
import org.zcy.agriculture.entity.TbPlanParticipant;
import org.zcy.agriculture.entity.TbPlanPlot;
import org.zcy.agriculture.entity.TbPlanting;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.param.AddFarmingPlanParam;
import org.zcy.agriculture.param.AddPloatUserParam;
import org.zcy.agriculture.param.AddStandardPlanParam;
import org.zcy.agriculture.service.ITbFarmingPlanService;
import org.zcy.agriculture.service.ITbPlanMessageService;
import org.zcy.agriculture.service.ITbPlanParticipantService;
import org.zcy.agriculture.service.ITbPlanPlotService;
import org.zcy.agriculture.service.ITbPlantingService;
import org.zcy.agriculture.service.ITbSubPlotService;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.FarmingPlanSpecialVo;
import org.zcy.agriculture.vo.FarmingRecordVo;
import org.zcy.agriculture.vo.TbFarmingPlanVo;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

import com.google.common.collect.Maps;

/**
 * 农事计划（普通计划，智能计划） 信息操作处理
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/api/system/tbFarmingPlan")
public class TbFarmingPlanController extends BaseController {

	@Autowired
	private ITbFarmingPlanService tbFarmingPlanService;
	@Autowired
	private ITbPlanMessageService tbPlanMessageService;
	@Autowired
	private ITbSubPlotService tbSubPlotService;
	@Autowired
	private ITbPlanParticipantService tbPlanParticipantService;
	@Autowired
	private ITbPlanPlotService tbPlanPlotService;
	@Autowired
	private ITbPlantingService tbPlantingService;

	/**
	 * 查询农事计划列表
	 */
	@GetMapping("/all")
	@ResponseBody
	public AjaxResult all(FarmingPlanSpecialVo param) {
		LoginUserVo u = getFarmUser();
		if (u.getRoleId() == null || u.getRoleId() != 1) {// 如果不是超级管理员
			param.setCurrentUserCode(u.getCode());
		}
		param.setFarmId(getFarmUUID());
		return success(tbFarmingPlanService.selectBytjList(param));
	}

	/**
	 * 今日计划列表
	 */
	@GetMapping("/todayAll")
	@ResponseBody
	public AjaxResult todayAll(TbFarmingPlanVo tbFarmingPlanVo) {
		if (tbFarmingPlanVo == null || tbFarmingPlanVo.getPlotId() == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		LoginUserVo u = getFarmUser();
		if (u.getRoleId() == null || u.getRoleId() != 1) {// 如果不是超级管理员
			tbFarmingPlanVo.setCurrentUserCode(u.getCode());
		}
		HashMap<String, Object> m = Maps.newHashMap();
		tbFarmingPlanVo.setFarmId(getFarmUUID());
		tbFarmingPlanVo.setToday(DateUtils.dateTime(new Date()));
		m.put("ptList", tbFarmingPlanService.selectByTodayList(tbFarmingPlanVo));
		m.put("znList", tbFarmingPlanService.selectBySmartPlanList(tbFarmingPlanVo));
		return success(m);
	}

	/**
	 * 全部计划列表
	 */
	@GetMapping("/getAllList")
	@ResponseBody
	public AjaxResult getAllList(TbFarmingPlanVo tbFarmingPlanVo) {
		if (tbFarmingPlanVo == null || tbFarmingPlanVo.getPlotId() == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		LoginUserVo u = getFarmUser();
		if (u.getRoleId() == null || u.getRoleId() != 1) {// 如果不是超级管理员
			tbFarmingPlanVo.setCurrentUserCode(u.getCode());
		}
		HashMap<String, Object> m = Maps.newHashMap();
		tbFarmingPlanVo.setFarmId(getFarmUUID());
		m.put("ptList", tbFarmingPlanService.selectByAllList(tbFarmingPlanVo));
		m.put("znList", tbFarmingPlanService.selectBySmartPlanList(tbFarmingPlanVo));
		return success(m);
	}

	/**
	 * 计划列表
	 */
	@GetMapping("/getPlanList")
	@ResponseBody
	public AjaxResult getPlanList(TbFarmingPlanVo tbFarmingPlanVo) {
		if (tbFarmingPlanVo == null || (tbFarmingPlanVo.getFarmingTypeId() == null && tbFarmingPlanVo.getPlanRepeatPlanId() == null && tbFarmingPlanVo.getPlotId() == null)) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		// 如果是全部计划 点击进入的
		if (tbFarmingPlanVo.getFarmingTypeId() != null || tbFarmingPlanVo.getPlotId() != null) {
			LoginUserVo u = getFarmUser();
			if (u.getRoleId() == null || u.getRoleId() != 1) {// 如果不是超级管理员
				tbFarmingPlanVo.setCurrentUserCode(u.getCode());
			}
		}

		tbFarmingPlanVo.setFarmId(getFarmUUID());
		return success(tbFarmingPlanService.selectByPlanList(tbFarmingPlanVo));
	}

	/**
	 * 农事记录列表
	 */
	@GetMapping("/getRecordList")
	@ResponseBody
	public TableDataInfo getRecordList(FarmingRecordVo farmingRecordVo, HttpServletRequest req) {
		String pageNum = req.getParameter("pageNum");
		// 验证参数
		if (farmingRecordVo == null || StringUtils.isEmpty(pageNum)) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		farmingRecordVo.setPageNo((Integer.parseInt(pageNum) - 1) * farmingRecordVo.getPageSize());
		farmingRecordVo.setFarmId(getFarmUUID());
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		farmingRecordVo.setPlanStatus(NormalOrDeleteEnum.CLOSE.getCode());
		farmingRecordVo.setMsgCou(NormalOrDeleteEnum.CLOSE.getCode());// 暂时做为一个判断条件
		HashMap<String, Object> m = Maps.newHashMap();
		m.put("frList", tbFarmingPlanService.selectByLsRecordList(farmingRecordVo));
		rspData.setTotal(tbFarmingPlanService.selectByLsRecordCou(farmingRecordVo));

		farmingRecordVo.setTpyeRecord(null);
		m.put("frStatistics", tbFarmingPlanService.selectByStatisticsCou(farmingRecordVo));
		rspData.setRows(m);
		return rspData;
	}

	/**
	 * 导出 农事记录
	 */
	@GetMapping("/recordExport")
	@ResponseBody
	public void recordExport(FarmingRecordVo farmingRecordVo, HttpServletResponse response) {
		farmingRecordVo.setPageNo(0);
		farmingRecordVo.setPageSize(Integer.MAX_VALUE);
		farmingRecordVo.setFarmId(getFarmUUID());
		farmingRecordVo.setPlanStatus(NormalOrDeleteEnum.CLOSE.getCode());
		List<FarmingRecordVo> list = tbFarmingPlanService.selectByLsRecordList(farmingRecordVo);
		ExcelUtil<FarmingRecordVo> util = new ExcelUtil<FarmingRecordVo>(FarmingRecordVo.class);
		util.exportExcelByStream(list, "农事记录", response);
	}

	/**
	 * 种植管理，农事计划
	 */
	@GetMapping("/getPlantingJhList")
	@ResponseBody
	public TableDataInfo getPlantingJhList(FarmingRecordVo farmingRecordVo, HttpServletRequest req) {
		String pageNum = req.getParameter("pageNum");
		// 验证参数
		if (farmingRecordVo == null || StringUtils.isEmpty(pageNum) || farmingRecordVo.getPlantingId() == null || farmingRecordVo.getSubPlotId() == null) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		TbPlanting pf = tbPlantingService.selectTbPlantingById(farmingRecordVo.getPlantingId());
		if (pf == null) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), "种植ID不存在！");
		}
		farmingRecordVo.setNsTimeStr(DateUtils.parseDateToStr("yyyy-MM-dd", pf.getPlantTime()));
		farmingRecordVo.setNsTimeEnd(pf.getPlantingStatus() == 2 ? DateUtils.parseDateToStr("yyyy-MM-dd", pf.getUpdateTime()) : null);// 如果是结束了 就结束时间

		farmingRecordVo.setPageNo((Integer.parseInt(pageNum) - 1) * farmingRecordVo.getPageSize());
		farmingRecordVo.setFarmId(getFarmUUID());
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		farmingRecordVo.setPlanStatus(NormalOrDeleteEnum.NORMAL.getCode());
		rspData.setRows(tbFarmingPlanService.selectByLsRecordList(farmingRecordVo));
		rspData.setTotal(tbFarmingPlanService.selectByLsRecordCou(farmingRecordVo));
		return rspData;
	}

	/**
	 * 种植管理，农事记录
	 */
	@GetMapping("/getPlantingJlList")
	@ResponseBody
	public TableDataInfo getPlantingJlList(FarmingRecordVo farmingRecordVo, HttpServletRequest req) {
		String pageNum = req.getParameter("pageNum");
		// 验证参数
		if (farmingRecordVo == null || StringUtils.isEmpty(pageNum) || farmingRecordVo.getPlantingId() == null || farmingRecordVo.getSubPlotId() == null) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		TbPlanting pf = tbPlantingService.selectTbPlantingById(farmingRecordVo.getPlantingId());
		if (pf == null) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), "种植ID不存在！");
		}

		farmingRecordVo.setNsTimeStr(DateUtils.parseDateToStr("yyyy-MM-dd", pf.getPlantTime()));
		farmingRecordVo.setNsTimeEnd(pf.getPlantingStatus() == 2 ? DateUtils.parseDateToStr("yyyy-MM-dd", pf.getUpdateTime()) : null);// 如果是结束了 就结束时间

		farmingRecordVo.setPageNo((Integer.parseInt(pageNum) - 1) * farmingRecordVo.getPageSize());
		farmingRecordVo.setFarmId(getFarmUUID());
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		farmingRecordVo.setPlanStatus(NormalOrDeleteEnum.CLOSE.getCode());
		rspData.setRows(tbFarmingPlanService.selectByLsRecordList(farmingRecordVo));
		rspData.setTotal(tbFarmingPlanService.selectByLsRecordCou(farmingRecordVo));
		return rspData;
	}

	/**
	 * 新增&&修改农事计划（普通&&智能）
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/saveFarmingPlan")
	@ResponseBody
	public AjaxResult saveFarmingPlan(@RequestBody AddFarmingPlanParam param) {
//		if (null != param.getPlanRepeat() && (param.getPlanRepeat() != 0 || param.getPlanRepeat() != 1)) {
//			return error("重复参数有误（planRepeat）");
//		}
		// 先判断参数是否异常
		AjaxResult result = addFarmingPlanValidation(param);
		if ((Integer) result.get("code") != RequestStatus.SUCCESS.getStatus()) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), (String) result.get("msg"));
		}
		if (param.getPlanId() == null) {
			// 赋值农场ID和添加人数据
			param.setCreateBy(getFarmUserCode());
			param.setFarmId(getFarmUUID());
		} else {
			param.setUpdateBy(getFarmUserCode());
		}
//		param.setWorkHours(0d);
//		param.setImplement(null);
		try {
			if (param.getPlanId() != null) {
				TbFarmingPlan tp = tbFarmingPlanService.selectTbFarmingPlanById(param.getPlanId());
				if (tp == null || !getFarmUUID().equals(tp.getFarmId())) {
					return error("农事计划planId有误，不存在或者不属于当前农场！");
				} else if (tp.getPlanStatus() == 1) {
					return error("农事计划planId有误，计划已被删除，无法修改！");
				} else if (tp.getPlanStatus() == 2) {
					return error("农事计划任务已经完成，无法修改！");
				}
				if (tp.getIsLntelligence() == 1) {
					if (StringUtils.isNotEmpty(param.getStartTimeZn()) && StringUtils.isEmpty(param.getEndTimeZn())) {
						if (Integer.parseInt(param.getStartTimeZn().replaceAll(":", "")) > Integer.parseInt(DateUtils.parseDateToStr("HHmmss", param.getEndTime()))) {
							return error("农事计划开始时间有误！");
						}
					} else if (StringUtils.isEmpty(param.getStartTimeZn()) && StringUtils.isNotEmpty(param.getEndTimeZn())) {
						if (Integer.parseInt(param.getEndTimeZn().replaceAll(":", "")) < Integer.parseInt(DateUtils.parseDateToStr("HHmmss", param.getStartTime()))) {
							return error("农事计划结束时间有误！");
						}
					} else if (StringUtils.isNotEmpty(param.getStartTimeZn()) && StringUtils.isNotEmpty(param.getEndTimeZn())) {
						if (Integer.parseInt(param.getStartTimeZn().replaceAll(":", "")) > Integer.parseInt(param.getEndTimeZn().replaceAll(":", ""))) {
							return error("计划开始时间不能大于结束时间");
						}
					}
				} else {
					if (param.getStartTime() != null && param.getEndTime() == null) {
						if (param.getStartTime().getTime() > tp.getEndTime().getTime()) {
							return error("农事计划开始时间有误！");
						}
					} else if (param.getStartTime() == null && param.getEndTime() != null) {
						if (param.getEndTime().getTime() < tp.getStartTime().getTime()) {
							return error("农事计划结束时间有误！");
						}
					} else if (param.getStartTime() != null && param.getEndTime() != null) {
						if (param.getStartTime().getTime() > param.getEndTime().getTime()) {
							return error("计划开始时间不能大于结束时间");
						}
					}
				}
			}
			tbFarmingPlanService.insertTbFarmingPlan(param);
			return success();
		} catch (Exception e) {
			logger.error("新增&&修改农事计划（普通&&智能）异常！", e);
			return error("添加异常");
		}

	}

	/**
	 * 将智能计划转化成普通计划(测试使用)
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/changePlain")
	@ResponseBody
	public AjaxResult changePlain(@RequestBody AddFarmingPlanParam param) {
		AjaxResult result = tbFarmingPlanService.changePlain(param.getId());
		if ((Integer) result.get("code") != RequestStatus.SUCCESS.getStatus()) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), (String) result.get("msg"));
		}
		return success();
	}

	/**
	 * 新增标准计划
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/addStandardPlan")
	@ResponseBody
	public AjaxResult addStandardPlan(@RequestBody AddStandardPlanParam param) {
		// 现校验入参
		if (null == param.getFarmingIds() || param.getFarmingIds().size() == 0) {
			return error("请选择流程");
		}
		if (null == param.getPloats() || param.getPloats().size() == 0) {
			return error("请选择地块");
		}
		for (AddPloatUserParam ploatUser : param.getPloats()) {
			if (ValidationUtil.isEmpty(ploatUser.getSubPlotId())) {
				return error("地块信息错误");
			}
		}
		// 1.然后根据流程ID获取数据
		// 2.查询地块的种植时间
		// 3.然后组装普通计划数据
		// 4.插入计划表，插入地块关联表
		param.setCreateBy(getFarmUserCode());
		param.setFarmId(getFarmUUID());
		try {
			AjaxResult result = tbFarmingPlanService.addStandardPlan(param);
			if ((Integer) result.get("code") != RequestStatus.SUCCESS.getStatus()) {
				return error(RequestStatus.PARAM_REQUIRED.getStatus(), (String) result.get("msg"));
			}
		} catch (Exception e) {
			logger.error("新增标准计划异常！", e);
			return error("系统异常！");
		}

		return success();
	}

	private AjaxResult addFarmingPlanValidation(AddFarmingPlanParam param) {
		// 如果是新增
		if (param.getPlanId() == null) {
			if (ValidationUtil.isEmpty(param.getFarmingTypeId())) {
				return error("请选择农事类型");
			}
			if (param.getImplement() != null) {
				if (ValidationUtil.isEmpty(param.getStartTimeZn())) {
					return error("请输入开始时间");
				}
				if (ValidationUtil.isEmpty(param.getEndTimeZn())) {
					return error("请输入结束时间");
				}
				if (Integer.parseInt(param.getStartTimeZn().replaceAll(":", "")) > Integer.parseInt(param.getEndTimeZn().replaceAll(":", ""))) {
					return error("计划开始时间不能大于结束时间");
				}
				// 当为新增时 验证
				if (param.getWorkHours() == null || param.getWorkHours() < 0D) {
					return error("投入工时不能小于等于0！");
				}

			} else {
				if (ValidationUtil.isEmpty(param.getStartTime())) {
					return error("请输入开始时间");
				}
				if (ValidationUtil.isEmpty(param.getEndTime())) {
					return error("请输入结束时间");
				}
				if (param.getStartTime().getTime() > param.getEndTime().getTime()) {
					return error("计划开始时间不能大于结束时间");
				}
			}

			if (null == param.getPantUsers() || param.getPantUsers().size() == 0) {
				return error("执行人员不能为空");
			}
		} else if (param.getUpdateType() == null) {
			return error("编辑时updateType不能为空（1:修改智能计划的执行条（implement信息），2：修改物品（materials信息），3：修改图片（imgs信息），4：修改人员（包过负责人 pantUsers信息），5：修改主信息）");
		} else {
			// 当为修改时 验证
			if (param.getWorkHours() != null && param.getWorkHours() < 0D) {
				return error("投入工时不能小于等于0！");
			}
		}

		if (param.getPlanRepeat() != null && param.getPlanRepeat() == 1 && (param.getPlanRepeatData() == null || param.getPlanRepeatData() <= 0)) {
			return error("当选择重复时，重复天数不能为空且不能小于0的整数！");
		}
		if (param.getPantUsers() != null && param.getPantUsers().size() > 0 && (param.getPlanId() == null || param.getUpdateType().intValue() == 4)) {
			for (AddPloatUserParam userParam : param.getPantUsers()) {
				if (ValidationUtil.isEmpty(userParam.getSubPlotId())) {
					return error("执行子地块不能为空！");
				}
				String user = "," + userParam.getResponserId() + ",";
				if (StringUtils.isNotEmpty(userParam.getParticipantIds())) {
					String us[] = userParam.getParticipantIds().split(",");
					for (String u : us) {
						if (user.indexOf("," + u + ",") >= 0) {
							return error("子地块责任人或者参与人不能重复！");
						} else {
							user += "," + u + ",";
						}
					}
				}
				if (tbSubPlotService.selectByFarmIdAndSubPlotId(getFarmUUID(), userParam.getSubPlotId()) == 0) {
					return error("子地块ID有误，不存在或者不属于当前农场！");
				}
			}
		}

		if (param.getMaterials() != null && param.getMaterials().size() > 0) {
			for (TbPlanMaterial p : param.getMaterials()) {
				if (p.getWarehouseId() == null) {
					return error("仓库ID不能为空！（仓库ID-> warehouseId：" + p.getWarehouseId() + "）");
				} else if (p.getMaterialId() == null) {
					return error("物品ID不能为空！（仓库ID-> warehouseId：" + p.getWarehouseId() + "，物品ID-> materialId：" + p.getMaterialId() + "）");
				} else if (p.getMaterialAmount() == null) {
					return error("物品数量不能为空（仓库ID-> warehouseId：" + p.getWarehouseId() + "，物品ID-> materialId：" + p.getMaterialId() + "）！");
				} else if (StringUtils.isEmpty(p.getMaterialUnit())) {
					return error("物品单位不能为空（仓库ID-> warehouseId：" + p.getWarehouseId() + "，物品ID-> materialId：" + p.getMaterialId() + "）！");
				}
			}
		}
		if (param.getImplement() != null) {
			if (param.getImplement().getDevId() == null) {
				return error("（智能计划）设备ID不能为空！");
			} else if (StringUtils.isEmpty(param.getImplement().getThingsboardKey())) {
				return error("（智能计划）传感器标识不能为空！");
			} else if (param.getImplement().getConditionVar() == null) {
				return error("（智能计划）条件不能为空！");
			} else if (StringUtils.isEmpty(param.getImplement().getDataVar())) {
				return error("（智能计划）值不能为空！");
			}
		}
		if (null != param.getRemark() && param.getRemark().length() > 500) {
			return error("备注过长");
		}

		return success();
	}

	/**
	 * 农事计划详情
	 */
	@GetMapping("/info")
	@ResponseBody
	public AjaxResult info(Long planId, Long subPlotId) {
		if (planId == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		return success(tbFarmingPlanService.getInfo(planId, subPlotId));
	}

	/**
	 * 完成计划
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/changeFarmingPlan")
	@ResponseBody
	public AjaxResult changeFarmingPlan(@RequestBody TbFarmingPlan fp) {
		if (fp == null || fp.getPlanId() == null || fp.getPlanStatus() == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		fp.setUpdateBy(getFarmUserCode());
		fp.setUpdateTime(new Date());
		fp.setFarmId(getFarmUUID());
		fp.setPlanStatus(NormalOrDeleteEnum.CLOSE.getCode());// 如果是完成
		try {
			TbFarmingPlan fpInfo1 = tbFarmingPlanService.selectTbFarmingPlanById(fp.getPlanId());
			if (fpInfo1.getPlanStatus() == NormalOrDeleteEnum.CLOSE.getCode()) {
				return error(RequestStatus.PARAM_REQUIRED.getStatus(), "农事计划已经完成，无法再次完成！");
			} else if (fpInfo1.getPlanStatus() == NormalOrDeleteEnum.DELETE.getCode()) {
				return error(RequestStatus.PARAM_REQUIRED.getStatus(), "农事计划已经删除！");
			}
			Date d = new Date();
			if (d.getTime() < fpInfo1.getStartTime().getTime()) {
				return error(RequestStatus.PARAM_REQUIRED.getStatus(), "农事计划还没开始，请遵循计划安排，暂时不能提前完成！");
			}
			
			// 目前一个子地块 对应一个计划
			TbPlanParticipant tbPlanParticipant = new TbPlanParticipant();
			tbPlanParticipant.setPlanId(fp.getPlanId());
			List<TbPlanParticipant> ppList = tbPlanParticipantService.selectTbPlanParticipantList(tbPlanParticipant);
			if(ppList == null || ppList.size() == 0) {
				return error("请添加负责人或者参与者！");
			}
			TbFarmingPlan fpInfo2 = null;
			// 如果是重复，完成任务后 重新创建新的计划
			if (fpInfo1.getPlanRepeat() == 1) {
				// 获取最原始的 农事计划
				if (fpInfo1.getPlanId() != fpInfo1.getPlanRepeatPlanId()) {
					fpInfo2 = tbFarmingPlanService.selectTbFarmingPlanById(fpInfo1.getPlanRepeatPlanId());
					if (fpInfo2 != null && fpInfo2.getPlanStatus() == NormalOrDeleteEnum.DELETE.getCode()) {
						// 如果原计划被删除 计划结束
						tbFarmingPlanService.updateTbFarmingPlan(fp);
						return success("计划已完成");
					}
				}
				fpInfo2 = fpInfo2 == null ? fpInfo1 : fpInfo2;
				fpInfo2.setPlanId(null);

				// 方法1 此写法是，任务结束的后，在加上重复天数
//				Long dates = fpInfo1.getStartTime().getTime() / 86400000;
				Long times = fpInfo1.getStartTime().getTime() % 86400000;
//				Long datee = fpInfo1.getEndTime().getTime() / 86400000;
//				Long timee = fpInfo1.getEndTime().getTime() % 86400000;
//					// 如果正常时间内 完成任务
//					if (d.getTime() <= fpInfo1.getEndTime().getTime()) {
//						fpInfo2.setStartTime(new Date((datee + fpInfo2.getPlanRepeatData()) * 86400000 + times));
//						fpInfo2.setEndTime(new Date((datee + datee + fpInfo2.getPlanRepeatData() - dates) * 86400000 + timee));
//						fpInfo2.setPlanStatus(NormalOrDeleteEnum.NORMAL.getCode());
//						tbFarmingPlanService.planComplete(fpInfo2, fp.getPlanId());
//					}
				// 方法2 此写法是，根据当前完成时间 加上 重复天数
//				fpInfo2.setEndTime(new Date(d.getTime() + fpInfo1.getEndTime().getTime() - fpInfo1.getStartTime().getTime() + (86400000 * fpInfo2.getPlanRepeatData())));
//				fpInfo2.setStartTime(new Date(d.getTime() + (86400000 * fpInfo2.getPlanRepeatData())));

				// 方法3 此写法是，根据当前完成时间(以天为单位) 加上 重复天数
				fpInfo2.setEndTime(new Date(((d.getTime() / 86400000) + fpInfo2.getPlanRepeatData()) * 86400000 + times + (fpInfo1.getEndTime().getTime() - fpInfo1.getStartTime().getTime())));
				fpInfo2.setStartTime(new Date(((d.getTime() / 86400000) + fpInfo2.getPlanRepeatData()) * 86400000 + times));

				fpInfo2.setPlanStatus(NormalOrDeleteEnum.NORMAL.getCode());
				fpInfo2.setUpdateBy(fp.getUpdateBy());
				fpInfo2.setUpdateTime(fp.getUpdateTime());
				tbFarmingPlanService.planComplete(fpInfo2, fp.getPlanId());

			} else { // 如果不是重复，直接完成
				tbFarmingPlanService.updateTbFarmingPlan(fp);
			}
			return success("计划已完成");
		} catch (Exception e) {
			logger.error("完成计划异常！", e);
			return error();
		}
	}

	/**
	 * 删除计划
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/deleteFarmingPlan")
	@ResponseBody
	public AjaxResult deleteFarmingPlan(@RequestBody TbFarmingPlan fp) {
		if (fp == null || fp.getPlanId() == null || fp.getPlanStatus() == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		fp.setUpdateBy(getFarmUserCode());
		fp.setUpdateTime(new Date());
		fp.setFarmId(getFarmUUID());
		fp.setPlanStatus(NormalOrDeleteEnum.DELETE.getCode());// 如果是删除
		try {
			TbFarmingPlan f = tbFarmingPlanService.selectTbFarmingPlanById(fp.getPlanId());
			if (f == null || !f.getFarmId().equals(getFarmUUID())) {
				return error("计划ID有误！");
			}
			tbFarmingPlanService.updateTbFarmingPlan(fp);
			return success("删除计划成功");
		} catch (Exception e) {
			logger.error("删除计划异常！", e);
			return error();
		}
	}

	/**
	 * 保存聊天
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/saveMsg")
	@ResponseBody
	public AjaxResult saveMsg(@RequestBody TbPlanMessage tbPlanMessage) {
		if (tbPlanMessage == null || (StringUtils.isEmpty(tbPlanMessage.getMessageContent()) && StringUtils.isEmpty(tbPlanMessage.getImgUrl())) || tbPlanMessage.getPlanId() == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbPlanMessage.setCreateBy(getFarmUserCode());
		tbPlanMessage.setCreateTime(new Date());

		try {
			tbPlanMessageService.insertTbPlanMessage(tbPlanMessage);
			return success();
		} catch (Exception e) {
			logger.error("保存聊天异常！", e);
			return error();
		}
	}

	/**
	 * 删除智能计划的用户
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/deleteLeaguer")
	@ResponseBody
	public AjaxResult deleteLeaguer(@RequestBody TbPlanParticipant tbPlanParticipant) {
		if (tbPlanParticipant == null || tbPlanParticipant.getPlanUserId() == null || tbPlanParticipant.getPlanId() == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		try {
			TbPlanParticipant pp = tbPlanParticipantService.selectTbPlanParticipantById(tbPlanParticipant.getPlanUserId());
			if (pp == null || !pp.getPlanId().equals(tbPlanParticipant.getPlanId())) {
				return error("planUserId有误！");
			}
			tbPlanParticipantService.deleteTbPlanParticipantByIds(tbPlanParticipant.getPlanUserId() + "");
			return success("删除成功");
		} catch (Exception e) {
			logger.error("删除智能计划的用户异常！", e);
			return error();
		}
	}

	/**
	 * 添加智能计划的用户
	 * 
	 * @param tbPlanParticipant
	 * @return
	 */
	@PostMapping("/addLeaguer")
	@ResponseBody
	public AjaxResult addLeaguer(@RequestBody TbPlanParticipant tbPlanParticipant) {
		if (tbPlanParticipant == null || tbPlanParticipant.getParticipantId() == null || tbPlanParticipant.getPlanId() == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}

		TbPlanPlot tbPlanPlot = new TbPlanPlot();
		tbPlanPlot.setPlanId(tbPlanParticipant.getPlanId());
		try {
			List<TbPlanPlot> ppList = tbPlanPlotService.selectTbPlanPlotList(tbPlanPlot);
			if (ppList == null || ppList.size() == 0) {
				return error("planUserId有误！");
			}

			// 查询责任人是否重复
			TbPlanParticipant pp = new TbPlanParticipant();
			pp.setPlanId(tbPlanParticipant.getPlanId());
			pp.setResponserId(tbPlanParticipant.getParticipantId());
			pp.setSubPlotId(ppList.get(0).getSubPlotId());
			List<TbPlanParticipant> ppL = tbPlanParticipantService.selectTbPlanParticipantList(pp);
			if (ppL != null && ppL.size() > 0) {
				return error("已经存在此用户，请勿重复添加！");
			}
			// 查询参与者是否重复
			pp.setResponserId(null);
			pp.setParticipantId(tbPlanParticipant.getParticipantId());
			List<TbPlanParticipant> ppL1 = tbPlanParticipantService.selectTbPlanParticipantList(pp);
			if (ppL1 != null && ppL1.size() > 0) {
				return error("已经存在此用户，请勿重复添加！");
			}
			// 保存参与者
			tbPlanParticipantService.insertTbPlanParticipant(pp);
			return success(pp, "添加成功");
		} catch (Exception e) {
			logger.error("添加智能计划的用户异常！", e);
			return error();
		}
	}

}
