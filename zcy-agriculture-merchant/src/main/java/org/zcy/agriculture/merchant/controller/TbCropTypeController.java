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
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbCropCategory;
import org.zcy.agriculture.entity.TbCropType;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbCropCategoryService;
import org.zcy.agriculture.service.ITbCropTypeService;

/**
 * 农作物品种 信息操作处理
 * 
 * @author numberone
 * @date 2019-06-26
 */
@Controller
@RequestMapping("/api/system/tbCropType")
public class TbCropTypeController extends BaseController {

	@Autowired
	private ITbCropCategoryService tbCropCategoryService;
	@Autowired
	private ITbCropTypeService tbCropTypeService;

	/**
	 * 查询农作物种类列表
	 */
	@GetMapping("/cropCategoryList")
	@ResponseBody
	public TableDataInfo cropCategoryList(TbCropCategory tbCropCategory) {
		tbCropCategory.setCategoryStatus(NormalOrDeleteEnum.NORMAL.getCode());
		tbCropCategory.setFarmId(getFarmUUID());
		startPage();
		List<TbCropCategory> list = tbCropCategoryService.selectTbCropCategoryList(tbCropCategory);
		return getDataTable(list);
	}

	/**
	 * 查询农作物品种列表
	 */
	@GetMapping("/cropTypeList")
	@ResponseBody
	public TableDataInfo cropTypeList(TbCropType tbCropType) {
		// 判断验证参数
		if (tbCropType == null || tbCropType.getCropCategoryId() == null) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbCropType.setFarmId(getFarmUUID());
		tbCropType.setCropStatus(NormalOrDeleteEnum.NORMAL.getCode());
		startPage();
		List<TbCropType> list = tbCropTypeService.selectTbCropTypeList(tbCropType);
		return getDataTable(list);
	}

	/**
	 * 新增保存农作物品种
	 */
	@PostMapping("/addCropType")
	@ResponseBody
	public AjaxResult addCropType(@RequestBody TbCropType tbCropType) {
		// 判断验证参数
		if (tbCropType == null || tbCropType.getCropCategoryId() == null || tbCropType.getCropName() == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbCropType.setCreateBy(getFarmUserCode());
		tbCropType.setUpdateBy(getFarmUserCode());
		tbCropType.setUpdateTime(new Date());
		tbCropType.setFarmId(getFarmUUID());
		try {
			if(tbCropTypeService.selectByCropNameRepeat(tbCropType.getFarmId(), tbCropType.getCropName(), tbCropType.getCropCategoryId(), null)>0) {
				return error("农作物品种不能重复！");
			}
			tbCropTypeService.insertTbCropType(tbCropType);
			return success();
		} catch (Exception e) {
			logger.error("新增保存农作物品种 异常！", e);
			return error();
		}
	}

	/**
	 * 新增保存农作物种类
	 */
	@PostMapping("/addCropCategory")
	@ResponseBody
	public AjaxResult addCropCategory(@RequestBody TbCropCategory tbCropCategory) {
		// 判断验证参数
		if (tbCropCategory == null || tbCropCategory.getCategoryName() == null || tbCropCategory.getSamplePicture() == null) {
			return error(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbCropCategory.setCreateBy(getFarmUserCode());
		tbCropCategory.setUpdateTime(new Date());
		tbCropCategory.setUpdateBy(getFarmUserCode());
		tbCropCategory.setFarmId(getFarmUUID());
		try {
			if(tbCropCategoryService.selectByCategoryNameRepeat(tbCropCategory.getCategoryName(), tbCropCategory.getFarmId(), null)>0) {
				return error("农作物种类不能重复！");
			}
			tbCropCategoryService.insertTbCropCategory(tbCropCategory);
			return success();
		} catch (Exception e) {
			logger.error(" 新增保存农作物种类 异常！", e);
			return error();
		}
	}

}
