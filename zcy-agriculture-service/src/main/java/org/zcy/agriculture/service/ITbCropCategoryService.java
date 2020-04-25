package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbCropCategory;

import java.util.List;

/**
 * 农作物种类 服务层
 * 
 * @author numberone
 * @date 2019-07-19
 */
public interface ITbCropCategoryService {
    /**
     * 查询农作物种类信息
     * 
     * @param cropCategoryId 农作物种类ID
     * @return 农作物种类信息
     */
    public TbCropCategory selectTbCropCategoryById(Long cropCategoryId);

    /**
     * 查询农作物种类列表
     *
     * @param tbCropCategory 农作物种类信息
     * @return 农作物种类集合
     */
    public List<TbCropCategory> selectTbCropCategoryList(TbCropCategory tbCropCategory);

    /**
     * 新增农作物种类
     * 
     * @param tbCropCategory 农作物种类信息
     * @return 结果
     */
    public int insertTbCropCategory(TbCropCategory tbCropCategory);

    /**
     * 修改农作物种类
     *
     * @param tbCropCategory 农作物种类信息
     * @return 结果
     */
	public int updateTbCropCategory(TbCropCategory tbCropCategory);
		
	/**
     * 删除农作物种类信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbCropCategoryByIds(String ids);
	/**
	 * 根据农场判断是否重复
	 * 
	 * @param categoryName
	 * @param farmId
	 * @return
	 */
	public int selectByCategoryNameRepeat(String categoryName, String farmId,Long cropCategoryId);
}
