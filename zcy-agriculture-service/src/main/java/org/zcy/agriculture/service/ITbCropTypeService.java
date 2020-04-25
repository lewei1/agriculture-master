package org.zcy.agriculture.service;

import java.util.List;

import org.zcy.agriculture.entity.TbCropType;

/**
 * 农作物品种 服务层
 * 
 * @author numberone
 * @date 2019-06-26
 */
public interface ITbCropTypeService 
{
	/**
     * 查询农作物品种信息
     * 
     * @param cropTypeId 农作物品种ID
     * @return 农作物品种信息
     */
	public TbCropType selectTbCropTypeById(Long cropTypeId);
	
	/**
     * 查询农作物品种列表
     * 
     * @param tbCropType 农作物品种信息
     * @return 农作物品种集合
     */
	public List<TbCropType> selectTbCropTypeList(TbCropType tbCropType);
	
	/**
     * 新增农作物品种
     * 
     * @param tbCropType 农作物品种信息
     * @return 结果
     */
	public int insertTbCropType(TbCropType tbCropType);
	
	/**
     * 修改农作物品种
     * 
     * @param tbCropType 农作物品种信息
     * @return 结果
     */
	public int updateTbCropType(TbCropType tbCropType);
		
	/**
     * 删除农作物品种信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbCropTypeByIds(String ids);
	/**
	 * 根据农场判断名称是否重复
	 * 
	 * @param farmId
	 * @param cropName
	 * @param cropCategoryId
	 * @param cropTypeId
	 * @return
	 */
	public int selectByCropNameRepeat(String farmId, String cropName, Long cropCategoryId, Long cropTypeId);
}
