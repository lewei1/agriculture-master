package org.zcy.agriculture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbPlanMaterial;
import org.zcy.agriculture.vo.TbPlanMaterialVo;	

/**
 * 计划使用的物品 数据层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface TbPlanMaterialMapper 
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
	 * 批量新增数据
	 * @param list
	 * @return
	 */
	public int insertBatchTbPlanMaterial(List<TbPlanMaterial> list);
	
	/**
	 * 根据计划ID复制数据
	 * @param tbPlanPlot
	 * @return
	 */
	public int copyPlanMaterial(@Param("planId")Long planId
			,@Param("newPlanId") Long newPlanId);
	
	/**
     * 修改计划使用的物品
     * 
     * @param tbPlanMaterial 计划使用的物品信息
     * @return 结果
     */
	public int updateTbPlanMaterial(TbPlanMaterial tbPlanMaterial);
	
	/**
     * 删除计划使用的物品
     * 
     * @param planMaterialId 计划使用的物品ID
     * @return 结果
     */
	public int deleteTbPlanMaterialById(Long planMaterialId);
	
	public int deleteTbPlanMaterialByPlanId(Long planId);
	
	/**
     * 批量删除计划使用的物品
     * 
     * @param planMaterialIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlanMaterialByIds(String[] planMaterialIds);
	/**
	 * 查询物品信息 报过物品名称 和仓库名称
	 * @param tbPlanMaterialVo
	 * @return
	 */
	public List<TbPlanMaterialVo> selectByPlanIdList(TbPlanMaterialVo tbPlanMaterialVo);
	
}