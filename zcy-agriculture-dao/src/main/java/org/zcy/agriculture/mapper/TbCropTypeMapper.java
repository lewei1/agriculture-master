package org.zcy.agriculture.mapper;

import java.util.List;

import org.zcy.agriculture.entity.TbCropType;

/**
 * 农作物品种 数据层
 * 
 * @author numberone
 * @date 2019-06-26
 */
public interface TbCropTypeMapper {
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
	 * 删除农作物品种
	 * 
	 * @param cropTypeId 农作物品种ID
	 * @return 结果
	 */
	public int deleteTbCropTypeById(Long cropTypeId);

	/**
	 * 批量删除农作物品种
	 * 
	 * @param cropTypeIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbCropTypeByIds(String[] cropTypeIds);

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