package org.zcy.agriculture.service;

import java.util.List;

import org.zcy.agriculture.entity.TbPlanMaterial;

/**
 * 计划使用的物品 服务层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbPlanMaterialService 
{
	/**
     * 查询计划使用的物品信息
     * 
     * @param planMaterialId 计划使用的物品ID
     * @return 计划使用的物品信息
     */
	public TbPlanMaterial selectTbPlanMaterialById(Long planMaterialId);
	
	/**
     * 查询计划使用的物品列表
     * 
     * @param tbPlanMaterial 计划使用的物品信息
     * @return 计划使用的物品集合
     */
	public List<TbPlanMaterial> selectTbPlanMaterialList(TbPlanMaterial tbPlanMaterial);
	
	/**
     * 新增计划使用的物品
     * 
     * @param tbPlanMaterial 计划使用的物品信息
     * @return 结果
     */
	public int insertTbPlanMaterial(TbPlanMaterial tbPlanMaterial);
	
	/**
     * 修改计划使用的物品
     * 
     * @param tbPlanMaterial 计划使用的物品信息
     * @return 结果
     */
	public int updateTbPlanMaterial(TbPlanMaterial tbPlanMaterial);
		
	/**
     * 删除计划使用的物品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlanMaterialByIds(String ids);
	
}
