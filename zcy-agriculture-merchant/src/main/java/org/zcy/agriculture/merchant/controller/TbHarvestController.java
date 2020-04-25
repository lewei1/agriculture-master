package org.zcy.agriculture.merchant.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbHarvest;
import org.zcy.agriculture.entity.TbHarvestSpec;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbHarvestService;
import org.zcy.agriculture.service.ITbHarvestSpecService;
import org.zcy.agriculture.service.ITbPlantingService;
import org.zcy.agriculture.service.ITbSubPlotService;
import org.zcy.agriculture.service.ITbUserService;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.vo.TbHarvestExportVo;
import org.zcy.agriculture.vo.TbHarvestVo;
import org.zcy.agriculture.vo.TbPlantingVo;
import org.zcy.agriculture.vo.TbUserVo;

/**
 * 作物采收 信息操作处理
 * 
 * @author numberone
 * @date 2019-06-27
 */
@Controller
@RequestMapping("/api/system/tbHarvest")
public class TbHarvestController extends BaseController {
	@Value("${file.img}")
	private String imgPath;
	@Autowired
	private ITbHarvestService tbHarvestService;
	@Autowired
	private ITbPlantingService tbPlantingService;
	@Autowired
	private ITbHarvestSpecService tbHarvestSpecService;
	@Autowired
	private ITbUserService tbUserService;
	@Autowired
	private ITbSubPlotService tbSubPlotService;

	/**
	 * 查询作物采收列表(汇总式采收列表)
	 */
	@GetMapping("/hzList")
	@ResponseBody
	public TableDataInfo hzList(TbHarvestVo tbHarvest, HttpServletRequest req) {
		String pageNum = req.getParameter("pageNum");
		// 验证参数
		if (tbHarvest == null || StringUtils.isEmpty(pageNum) || tbHarvest.getPageSize() == null) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbHarvest.setPageNo((Integer.parseInt(pageNum) - 1) * tbHarvest.getPageSize());
		tbHarvest.setFarmId(getFarmUUID());
		List<TbHarvestVo> list = tbHarvestService.selectByCshz(tbHarvest);
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(list);
		rspData.setTotal(tbHarvestService.selectByCshzListCou(tbHarvest));
		return rspData;
	}

	/**
	 * 采收记录
	 */
	@GetMapping("/xqList")
	@ResponseBody
	public TableDataInfo xqList(TbHarvestVo tbHarvest) {
		// 验证参数
		if (tbHarvest == null || tbHarvest.getSubPlotId() == null || tbHarvest.getPlantingId() == null) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		startPage();
		List<TbHarvestVo> list = tbHarvestService.selectByCsjlList(tbHarvest);
		for (TbHarvestVo hv : list) {
			if (StringUtils.isNotEmpty(hv.getImgUrl())) {
				hv.setImgUrls(hv.getImgUrl().split(";"));
			}
			hv.setImgUrl(null);
		}
		return getDataTable(list);
	}

	/**
	 * 导出 采收记录
	 */
	@GetMapping("/xqExport")
	@ResponseBody
	public void xqExport(TbHarvestVo tbHarvest, HttpServletResponse response) {
		// 验证参数
		if (tbHarvest != null && tbHarvest.getSubPlotId() != null && tbHarvest.getPlantingId() != null) {
			List<TbHarvestVo> list = tbHarvestService.selectByCsjlList(tbHarvest);
			ExcelUtil<TbHarvestVo> util = new ExcelUtil<TbHarvestVo>(TbHarvestVo.class);
			util.exportExcelByStream(list, "采收记录", response);
		}
	}

	/**
	 * 新增保存作物采收
	 */
	@PostMapping("/saveHarvest")
	@ResponseBody
	public AjaxResult addSave(@RequestBody TbHarvestVo tbHarvest) {
		// 验证参数
		if (tbHarvest == null) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		if (tbHarvest.getHarvestId() == null) {
			if (tbHarvest.getSubPlotId() == null) {
				return error("子地块ID不能为空");
			} else if (tbHarvest.getHarvestSpecId() == null) {
				return error("采收规格ID不能为空");
			} else if (tbHarvest.getHarvestTime() == null) {
				return error("采收时间不能为空");
			} else if (tbHarvest.getHarvestAmount() == null) {
				return error("采收总量不能为空");
			} else if (StringUtils.isEmpty(tbHarvest.getCropLevel())) {
				return error("作物等级不能为空");
			}
		}

		tbHarvest.setUpdateBy(getFarmUserCode());
		tbHarvest.setUpdateTime(new Date());
		TbPlantingVo pv = new TbPlantingVo();
		pv.setSubPlotId(tbHarvest.getSubPlotId());
		pv.setPlantingStatus(NormalOrDeleteEnum.NORMAL.getCode());
		try {
			if (tbHarvest.getHarvestId() == null) {
				if (tbSubPlotService.selectByFarmIdAndSubPlotId(getFarmUUID(), tbHarvest.getSubPlotId()) == 0) {
					return error("子地块ID有误，不存在或者不属于当前农场！");
				}

				tbHarvest.setCreateBy(getFarmUserCode());
				tbHarvest.setFarmId(getFarmUUID());
				// 查询子地块是否有种植
				List<TbPlantingVo> list = tbPlantingService.selectByPlantingAllList(pv);
				if (list == null || list.size() == 0) {
					return error("该地块未种植，无法采收！");
				}
				tbHarvest.setPlantingId(list.get(0).getPlantingId());
				tbHarvestService.insertTbHarvest(tbHarvest);

			} else {
				TbHarvest h = tbHarvestService.selectTbHarvestById(tbHarvest.getHarvestId());
				if (h == null || !getFarmUUID().equals(h.getFarmId())) {
					return error("采收harvestId有误，不存在或者不属于当前农场！");
				}
				tbHarvest.setPlotId(null);
				tbHarvest.setSubPlotId(null);
				tbHarvestService.updateTbHarvest(tbHarvest, imgPath);
			}
			return success();
		} catch (Exception e) {
			logger.error("新增保存作物采收异常！", e);
			return error();
		}
	}

	/**
	 * 批量修改作物采收
	 */
	@PostMapping("/updateHarvest")
	@ResponseBody
	public AjaxResult updateHarvest(@RequestBody List<TbHarvestVo> tbHarvest) {
		// 验证参数
		if (tbHarvest == null || tbHarvest.size() == 0) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbHarvest.get(0).setUpdateBy(getFarmUserCode());
		tbHarvest.get(0).setUpdateTime(new Date());
		try {
			tbHarvestService.updateTbHarvestBatch(tbHarvest);
			return success();
		} catch (Exception e) {
			logger.error("批量修改作物采收异常！", e);
			return error();
		}
	}

	/**
	 * 查询采收规格列表 && 作物等级 && 采收人员
	 */
	@GetMapping("/harvestSpecList")
	@ResponseBody
	public AjaxResult list(TbHarvestSpec tbHarvestSpec) {
		HashMap<String, Object> m = new HashMap<>();
		tbHarvestSpec.setFarmId(getFarmUUID());
		m.put("harvestSpecList", tbHarvestSpecService.selectTbHarvestSpecList(tbHarvestSpec));
		m.put("sictDataList", tbHarvestService.getLevelList("作物等级"));
		TbUserVo vo = new TbUserVo();
		vo.setFarmId(getFarmUUID());
		m.put("userList", tbUserService.selectTbUserOrAdminList(vo));

		return success(m);
	}

	/**
	 * 查询采收规格列表 && 作物等级 && 采收人员
	 */
	@GetMapping("/dictData")
	@ResponseBody
	public AjaxResult dictData(String dictName) {
		return success(tbHarvestService.getLevelList(dictName));
	}

	/**
	 * 新增保存采收规格
	 */
	@PostMapping("/addHarvestSpec")
	@ResponseBody
	public AjaxResult addHarvestSpec(@RequestBody TbHarvestSpec tbHarvestSpec) {
		// 验证参数
		if (tbHarvestSpec == null || StringUtils.isEmpty(tbHarvestSpec.getSpecName())) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbHarvestSpec.setCreateBy(getFarmUserCode());
		tbHarvestSpec.setUpdateTime(new Date());
		tbHarvestSpec.setUpdateBy(getFarmUserCode());
		tbHarvestSpec.setFarmId(getFarmUUID());
		try {
			if (tbHarvestSpecService.selectBySpecNameRepeat(tbHarvestSpec.getFarmId(), tbHarvestSpec.getSpecName(), null) > 0) {
				return error("采收规格不能重复！");
			}
			tbHarvestSpecService.insertTbHarvestSpec(tbHarvestSpec);
			return success();
		} catch (Exception e) {
			logger.error("新增保存采收规格异常！", e);
			return error();
		}
	}

	/**
	 * 采收统计
	 * 
	 * @throws ParseException
	 */
	@GetMapping("/statisticsList")
	@ResponseBody
	public TableDataInfo statistics(TbHarvestVo tbHarvest, String harvestDate, HttpServletRequest req) {
		String pageNum = req.getParameter("pageNum");
		// 验证参数
		if (tbHarvest == null || StringUtils.isEmpty(pageNum) || tbHarvest.getPageSize() == null) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		if (StringUtils.isNotEmpty(harvestDate) && harvestDate.length() == 4) {// 按年统计
			tbHarvest.setHarvestStr(harvestDate + "-01-01");
			tbHarvest.setHarvestEnd(harvestDate + "-12-31");
		} else if (StringUtils.isNotEmpty(harvestDate) && harvestDate.length() == 7) {// 按月统计
			tbHarvest.setHarvestStr(harvestDate + "-01");
			// 获得实体类
			Calendar ca = Calendar.getInstance();
			ca.setTime(DateUtils.dateTime("yyyy-MM", harvestDate));
			// 设置最后一天
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			tbHarvest.setHarvestEnd(DateUtils.parseDateToStr("yyyy-MM-dd", ca.getTime()));
//		} else if(StringUtils.isNotEmpty(harvestDate)){// 按天统计
//			tbHarvest.setHarvestStr(harvestDate);
//			tbHarvest.setHarvestEnd(harvestDate);
		}

		tbHarvest.setPageNo((Integer.parseInt(pageNum) - 1) * tbHarvest.getPageSize());
		tbHarvest.setFarmId(getFarmUUID());
		List<TbHarvestExportVo> list = tbHarvestService.selectByStatistics(tbHarvest);
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(list);
		rspData.setTotal(tbHarvestService.selectByStatisticsCou(tbHarvest));
		return rspData;
	}

	/**
	 * 导出 采收统计
	 */
	@GetMapping("/statisticsExport")
	@ResponseBody
	public void statisticsExport(TbHarvestVo tbHarvest, String harvestDate, HttpServletResponse response) {
		// 验证参数
		if (tbHarvest != null) {
			if (StringUtils.isNotEmpty(harvestDate) && harvestDate.length() == 4) {// 按年统计
				tbHarvest.setHarvestStr(harvestDate + "-01-01");
				tbHarvest.setHarvestEnd(harvestDate + "-12-31");
			} else if (StringUtils.isNotEmpty(harvestDate) && harvestDate.length() == 7) {// 按月统计
				tbHarvest.setHarvestStr(harvestDate + "-01");
				// 获得实体类
				Calendar ca = Calendar.getInstance();
				ca.setTime(DateUtils.dateTime("yyyy-MM", harvestDate));
				// 设置最后一天
				ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
				tbHarvest.setHarvestEnd(DateUtils.parseDateToStr("yyyy-MM-dd", ca.getTime()));
//			} else if(StringUtils.isNotEmpty(harvestDate)){// 按天统计
//				tbHarvest.setHarvestStr(harvestDate);
//				tbHarvest.setHarvestEnd(harvestDate);
			}

			tbHarvest.setFarmId(getFarmUUID());
			tbHarvest.setPageNo(0);
			tbHarvest.setPageSize(Integer.MAX_VALUE);
			List<TbHarvestExportVo> list = tbHarvestService.selectByStatistics(tbHarvest);
			ExcelUtil<TbHarvestExportVo> util = new ExcelUtil<TbHarvestExportVo>(TbHarvestExportVo.class);
			util.exportExcelByStream(list, "采收统计", response);
		}
	}
}
