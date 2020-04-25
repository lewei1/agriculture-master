package org.zcy.agriculture.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbFarmingType;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbFarmingTypeService;
import org.zcy.agriculture.util.StringUtils;

/**
 * 农事类型 信息操作处理
 * 
 * @author numberone
 * @date 2019-06-26
 */
@Controller
@RequestMapping("/api/system/tbFarmingType")
public class TbFarmingTypeController extends BaseController {

	@Autowired
	private ITbFarmingTypeService tbFarmingTypeService;

	/**
	 * 查询农事类型列表
	 */
	@GetMapping("/list")
	@ResponseBody
	public TableDataInfo list(TbFarmingType tbFarmingType) {
//		startPage();
		tbFarmingType.setFarmId(getFarmUUID());
		tbFarmingType.setFarmingTypeStatus(NormalOrDeleteEnum.NORMAL.getCode());
		List<TbFarmingType> list = tbFarmingTypeService.selectTbFarmingTypeList(tbFarmingType);
		return getDataTable(list);
	}

	/**
	 * 新增保存农事类型
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody TbFarmingType tbFarmingType) {
		// 验证参数
		if (tbFarmingType == null || StringUtils.isEmpty(tbFarmingType.getFarmingTypeName()) || tbFarmingType.getTpyeRecord() == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbFarmingType.setFarmId(getFarmUUID());
		tbFarmingType.setCreateBy(getFarmUserCode());
		return toAjax(tbFarmingTypeService.insertTbFarmingType(tbFarmingType));
	}

	/**
	 * 修改保存农事类型
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TbFarmingType tbFarmingType) {
		return toAjax(tbFarmingTypeService.updateTbFarmingType(tbFarmingType));
	}

	/**
	 * 删除农事类型
	 */
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(tbFarmingTypeService.deleteTbFarmingTypeByIds(ids));
	}

}
