package org.zcy.agriculture.service;

import java.util.List;

import org.zcy.agriculture.entity.TbFarmingPlanImplement;

/**
 * 智能计划执行 服务层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbFarmingPlanImplementService 
{
	/**
     * 查询智能计划执行信息
     * 
     * @param id 智能计划执行ID
     * @return 智能计划执行信息
     */
	public TbFarmingPlanImplement selectTbFarmingPlanImplementById(Long id);
	
	/**
     * 查询智能计划执行列表
     * 
     * @param tbFarmingPlanImplement 智能计划执行信息
     * @return 智能计划执行集合
     */
	public List<TbFarmingPlanImplement> selectTbFarmingPlanImplementList(TbFarmingPlanImplement tbFarmingPlanImplement);
	
	/**
     * 新增智能计划执行
     * 
     * @param tbFarmingPlanImplement 智能计划执行信息
     * @return 结果
     */
	public int insertTbFarmingPlanImplement(TbFarmingPlanImplement tbFarmingPlanImplement);
	
	/**
     * 修改智能计划执行
     * 
     * @param tbFarmingPlanImplement 智能计划执行信息
     * @return 结果
     */
	public int updateTbFarmingPlanImplement(TbFarmingPlanImplement tbFarmingPlanImplement);
		
	/**
     * 删除智能计划执行信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbFarmingPlanImplementByIds(String ids);
	
}
