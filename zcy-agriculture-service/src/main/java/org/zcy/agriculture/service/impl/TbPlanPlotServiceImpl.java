package org.zcy.agriculture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbPlanPlot;
import org.zcy.agriculture.mapper.TbPlanPlotMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbPlanPlotService;

/**
 * 计划对应的地块 服务层实现
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbPlanPlotServiceImpl implements ITbPlanPlotService 
{
	@Autowired
	private TbPlanPlotMapper tbPlanPlotMapper;

	/**
     * 查询计划对应的地块信息
     * 
     * @param planPlotId 计划对应的地块ID
     * @return 计划对应的地块信息
     */
    @Override
	public TbPlanPlot selectTbPlanPlotById(Long planPlotId)
	{
	    return tbPlanPlotMapper.selectTbPlanPlotById(planPlotId);
	}
	
	/**
     * 查询计划对应的地块列表
     * 
     * @param tbPlanPlot 计划对应的地块信息
     * @return 计划对应的地块集合
     */
	@Override
	public List<TbPlanPlot> selectTbPlanPlotList(TbPlanPlot tbPlanPlot)
	{
	    return tbPlanPlotMapper.selectTbPlanPlotList(tbPlanPlot);
	}
	
    /**
     * 新增计划对应的地块
     * 
     * @param tbPlanPlot 计划对应的地块信息
     * @return 结果
     */
	@Override
	public int insertTbPlanPlot(TbPlanPlot tbPlanPlot)
	{
	    return tbPlanPlotMapper.insertTbPlanPlot(tbPlanPlot);
	}
	
	/**
     * 修改计划对应的地块
     * 
     * @param tbPlanPlot 计划对应的地块信息
     * @return 结果
     */
	@Override
	public int updateTbPlanPlot(TbPlanPlot tbPlanPlot)
	{
	    return tbPlanPlotMapper.updateTbPlanPlot(tbPlanPlot);
	}

	/**
     * 删除计划对应的地块对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbPlanPlotByIds(String ids)
	{
		return tbPlanPlotMapper.deleteTbPlanPlotByIds(Convert.toStrArray(ids));
	}
	/**
	 * 根据农事计划ID查询 地块ID
	 * @param planId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByPlanIdGetPlotId(Long planId){
		return tbPlanPlotMapper.selectByPlanIdGetPlotId(planId);
	}
}
