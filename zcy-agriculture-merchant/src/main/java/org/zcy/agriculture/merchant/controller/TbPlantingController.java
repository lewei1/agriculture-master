package org.zcy.agriculture.merchant.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbCameraImage;
import org.zcy.agriculture.entity.TbPlanting;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbCameraImageService;
import org.zcy.agriculture.service.ITbPlantingService;
import org.zcy.agriculture.service.ITbSubPlotService;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.vo.TbPlantingVo;

/**
 * 种植 信息操作处理
 * 
 * @author numberone
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/api/tbPlanting")
public class TbPlantingController extends BaseController {

	@Autowired
	private ITbPlantingService tbPlantingService;
	@Autowired
	private ITbSubPlotService tbSubPlotService;
	@Autowired
	private ITbCameraImageService tbCameraImageService;

	/**
	 * 查询种植 概览
	 */
	@GetMapping("/uncultivatedList")
	@ResponseBody
	public TableDataInfo uncultivatedList(TbPlantingVo tbPlantingVo) {
		tbPlantingVo.setFarmId(getFarmUUID());
		startPage();
		List<TbPlantingVo> list = tbPlantingService.selectByNncultivatedList(tbPlantingVo);
		return getDataTable(list);
	}

	/**
	 * 种植管理 子地块种植列表
	 */
	@GetMapping("/plantingAllList")
	@ResponseBody
	public TableDataInfo plantingAllList(TbPlantingVo tbPlantingVo) {
		// 验证参数
		if (tbPlantingVo == null || tbPlantingVo.getSubPlotId() == null && tbPlantingVo.getPlotId() == null) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbPlantingVo.setFarmId(getFarmUUID());
		startPage();
		List<TbPlantingVo> list = tbPlantingService.selectByPlantingAllList(tbPlantingVo);
		return getDataTable(list);
	}

	/**
	 * 种植管理 批量新增种植
	 */
	@PostMapping("/save")
	@ResponseBody
	public AjaxResult save(@RequestBody List<TbPlanting> list) {
		// 验证参数
		if (list == null || list.size() == 0) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		for (TbPlanting p : list) {
			if (p.getCropCategoryId() == null) {
				return error("农作物种类ID不能为空！");
			} else if (p.getCropTypeId() == null) {
				return error("农作物品种不能为空！");
			} else if (p.getPlantTime() == null) {
				return error("定植时间不能为空！");
			} else if (p.getEndTime() == null) {
				return error("预计结束时间不能为空！");
			} else if (p.getSubPlotId() == null) {
				return error("子地块ID不能为空！");
			} else if (StringUtils.isEmpty(p.getPlantStandard())) {
				return error("种植标准不能为空！");
			}
		}
		String pd = "";
		try {
			if (tbPlantingService.selectByPlantingStatus(list) > 0) {
				return error("有部分子地块已经在种植中，请先结束种植然后重新种植！");
			}

			for (TbPlanting p : list) {
				if (pd.indexOf("," + p.getSubPlotId() + ",") >= 0) {
					return error("数据有误，子地块重复！");
				}
				pd += "," + p.getSubPlotId() + ",";
				p.setCreateBy(getFarmUserCode());
				p.setUpdateTime(new Date());
				p.setUpdateBy(getFarmUserCode());
				p.setFarmId(getFarmUUID());

				if (tbSubPlotService.selectByFarmIdAndSubPlotId(getFarmUUID(), p.getSubPlotId()) == 0) {
					return error("子地块ID有误，不存在或者不属于当前农场！");
				}
			}
			tbPlantingService.insertByPlantingBatch(list);
			return success();
		} catch (Exception e) {
			logger.error("种植管理   批量新增种植 异常！", e);
			return error();
		}
	}

	/**
	 * 结束种植
	 * 
	 * @param list
	 * @return
	 */
	@PostMapping("/endPlanting")
	@ResponseBody
	public AjaxResult endPlanting(@RequestBody List<TbPlanting> list) {
		// 验证参数
		if (list == null || list.size() == 0) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		try {
			if (tbPlantingService.selectByPlantingStatus(list) != list.size()) {
				return error("有部分子地块没有种植，无法结束种植！");
			}
			list.get(0).setUpdateBy(getFarmUserCode());
			list.get(0).setUpdateTime(new Date());
			tbPlantingService.updateTbPlantingBatch(list);
			return success();
		} catch (Exception e) {
			logger.error("结束种植 异常！", e);
			return error();
		}
	}

	/**
	 * 种植管理查询 图像记录
	 */
	@GetMapping("/imageRecording")
	@ResponseBody
	public TableDataInfo imageRecording(Long plotId, String createTimeStr, String createTimeEnd) {
		// 验证参数
		if (plotId == null) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		startPage();
		List<TbCameraImage> list = tbCameraImageService.selectByPlantingList(plotId, createTimeStr, createTimeEnd);
		return getDataTable(list);
	}
}
