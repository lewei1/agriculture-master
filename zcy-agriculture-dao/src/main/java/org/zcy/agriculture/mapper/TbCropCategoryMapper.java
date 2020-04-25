package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbCropCategory;

import java.util.List;

/**
 * 农作物种类 数据层
 * 
 * @author numberone
 * @date 2019-07-19
 */
public interface TbCropCategoryMapper {
	/**
	 * 查询农作物种类信息
	 * 
	 * @param cropCategoryId 农作物种类ID
	 * @return 农作物种类信息
	 */
	TbCropCategory selectTbCropCategoryById(Long cropCategoryId);

	/**
	 * 查询农作物种类列表
	 * 
	 * @param tbCropCategory 农作物种类信息
	 * @return 农作物种类集合
	 */
	List<TbCropCategory> selectTbCropCategoryList(TbCropCategory tbCropCategory);

	/**
	 * 新增农作物种类
	 * 
	 * @param tbCropCategory 农作物种类信息
	 * @return 结果
	 */
	int insertTbCropCategory(TbCropCategory tbCropCategory);

	/**
	 * 修改农作物种类
	 * 
	 * @param tbCropCategory 农作物种类信息
	 * @return 结果
	 */
	int updateTbCropCategory(TbCropCategory tbCropCategory);

	/**
	 * 删除农作物种类
	 * 
	 * @param cropCategoryId 农作物种类ID
	 * @return 结果
	 */
	int deleteTbCropCategoryById(Long cropCategoryId);

	/**
	 * 批量删除农作物种类
	 * 
	 * @param cropCategoryIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbCropCategoryByIds(String[] cropCategoryIds);

	/**
	 * 根据农场判断是否重复
	 * 
	 * @param categoryName
	 * @param farmId
	 * @return
	 */
	public int selectByCategoryNameRepeat(String categoryName, String farmId,Long cropCategoryId);
}