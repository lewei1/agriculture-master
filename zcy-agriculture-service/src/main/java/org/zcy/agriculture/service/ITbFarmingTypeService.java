package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbFarmingType;

import java.util.List;

/**
 * 农事类型 服务层
 * 
 * @author numberone
 * @date 2019-06-26
 */
public interface ITbFarmingTypeService 
{
	/**
     * 查询农事类型信息
     * 
     * @param farmingTypeId 农事类型ID
     * @return 农事类型信息
     */
	public TbFarmingType selectTbFarmingTypeById(Long farmingTypeId);
	
	/**
     * 查询农事类型列表
     * 
     * @param tbFarmingType 农事类型信息
     * @return 农事类型集合
     */
	public List<TbFarmingType> selectTbFarmingTypeList(TbFarmingType tbFarmingType);
	
	/**
     * 新增农事类型
     * 
     * @param tbFarmingType 农事类型信息
     * @return 结果
     */
	public int insertTbFarmingType(TbFarmingType tbFarmingType);
	
	/**
     * 修改农事类型
     * 
     * @param tbFarmingType 农事类型信息
     * @return 结果
     */
	public int updateTbFarmingType(TbFarmingType tbFarmingType);
		
	/**
     * 删除农事类型信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbFarmingTypeByIds(String ids);
	
}
