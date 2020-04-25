package org.zcy.agriculture.service;

import java.util.HashMap;
import java.util.List;

import org.zcy.agriculture.entity.TbPlanPlot;

/**
 * 计划对应的地块 服务层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbPlanPlotService 
{
	/**
     * 查询计划对应的地块信息
     * 
     * @param planPlotId 计划对应的地块ID
     * @return 计划对应的地块信息
     */
	public TbPlanPlot selectTbPlanPlotById(Long planPlotId);
	
	/**
     * 查询计划对应的地块列表
     * 
     * @param tbPlanPlot 计划对应的地块信息
     * @return 计划对应的地块集合
     */
	public List<TbPlanPlot> selectTbPlanPlotList(TbPlanPlot tbPlanPlot);
	
	/**
     * 新增计划对应的地块
     * 
     * @param tbPlanPlot 计划对应的地块信息
     * @return 结果
     */
	public int insertTbPlanPlot(TbPlanPlot tbPlanPlot);
	
	/**
     * 修改计划对应的地块
     * 
     * @param tbPlanPlot 计划对应的地块信息
     * @return 结果
     */
	public int updateTbPlanPlot(TbPlanPlot tbPlanPlot);
		
	/**
     * 删除计划对应的地块信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlanPlotByIds(String ids);
	/**
	 * 根据农事计划ID查询 地块ID
	 * @param planId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByPlanIdGetPlotId(Long planId);
}
