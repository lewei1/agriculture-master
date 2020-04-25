package org.zcy.agriculture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbFarmingPlanImplement;
import org.zcy.agriculture.mapper.TbFarmingPlanImplementMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbFarmingPlanImplementService;

/**
 * 智能计划执行 服务层实现
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbFarmingPlanImplementServiceImpl implements ITbFarmingPlanImplementService 
{
	@Autowired
	private TbFarmingPlanImplementMapper tbFarmingPlanImplementMapper;

	/**
     * 查询智能计划执行信息
     * 
     * @param id 智能计划执行ID
     * @return 智能计划执行信息
     */
    @Override
	public TbFarmingPlanImplement selectTbFarmingPlanImplementById(Long id)
	{
	    return tbFarmingPlanImplementMapper.selectTbFarmingPlanImplementById(id);
	}
	
	/**
     * 查询智能计划执行列表
     * 
     * @param tbFarmingPlanImplement 智能计划执行信息
     * @return 智能计划执行集合
     */
	@Override
	public List<TbFarmingPlanImplement> selectTbFarmingPlanImplementList(TbFarmingPlanImplement tbFarmingPlanImplement)
	{
	    return tbFarmingPlanImplementMapper.selectTbFarmingPlanImplementList(tbFarmingPlanImplement);
	}
	
    /**
     * 新增智能计划执行
     * 
     * @param tbFarmingPlanImplement 智能计划执行信息
     * @return 结果
     */
	@Override
	public int insertTbFarmingPlanImplement(TbFarmingPlanImplement tbFarmingPlanImplement)
	{
	    return tbFarmingPlanImplementMapper.insertTbFarmingPlanImplement(tbFarmingPlanImplement);
	}
	
	/**
     * 修改智能计划执行
     * 
     * @param tbFarmingPlanImplement 智能计划执行信息
     * @return 结果
     */
	@Override
	public int updateTbFarmingPlanImplement(TbFarmingPlanImplement tbFarmingPlanImplement)
	{
	    return tbFarmingPlanImplementMapper.updateTbFarmingPlanImplement(tbFarmingPlanImplement);
	}

	/**
     * 删除智能计划执行对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbFarmingPlanImplementByIds(String ids)
	{
		return tbFarmingPlanImplementMapper.deleteTbFarmingPlanImplementByIds(Convert.toStrArray(ids));
	}
	
}
