package org.zcy.agriculture.mapper;

import java.util.List;

import org.zcy.agriculture.entity.TbFarmingPlanImplement;
import org.zcy.agriculture.vo.TbFarmingPlanImplementVo;	

/**
 * 智能计划执行 数据层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface TbFarmingPlanImplementMapper 
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
     * 删除智能计划执行
     * 
     * @param id 智能计划执行ID
     * @return 结果
     */
	public int deleteTbFarmingPlanImplementById(Long id);
	
	/**
	 * 根据planId 删除记录
	 * @param planId
	 * @return
	 */
	public int deleteTbFarmingPlanImplementByPlanId(Long planId);
	
	/**
     * 批量删除智能计划执行
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbFarmingPlanImplementByIds(String[] ids);
	
	/**
	 * 查询未调用过的  且在传入的时间 是 在开启到结束时间内的  智能计划
	 * @param today
	 * @return
	 */
	public List<TbFarmingPlanImplementVo> selectByIntelligentPlanningList(String today,int type);
}