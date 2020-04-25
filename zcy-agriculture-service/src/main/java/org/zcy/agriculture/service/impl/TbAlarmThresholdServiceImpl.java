package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbAlarmThreshold;
import org.zcy.agriculture.mapper.TbAlarmThresholdMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbAlarmThresholdService;

/**
 * 报警阈值 服务层实现
 * 
 * @author zh
 * @date 2019-06-28
 */
@Service
public class TbAlarmThresholdServiceImpl implements ITbAlarmThresholdService
{
	@Autowired
	private TbAlarmThresholdMapper tbAlarmThresholdMapper;

	/**
     * 查询报警阈值信息
     * 
     * @param alarmThresholdId 报警阈值ID
     * @return 报警阈值信息
     */
    @Override
	public TbAlarmThreshold selectTbAlarmThresholdById(Long alarmThresholdId)
	{
	    return tbAlarmThresholdMapper.selectTbAlarmThresholdById(alarmThresholdId);
	}
	
	/**
     * 查询报警阈值列表
     * 
     * @param tbAlarmThreshold 报警阈值信息
     * @return 报警阈值集合
     */
	@Override
	public List<TbAlarmThreshold> selectTbAlarmThresholdList(TbAlarmThreshold tbAlarmThreshold)
	{
	    return tbAlarmThresholdMapper.selectTbAlarmThresholdList(tbAlarmThreshold);
	}
	
    /**
     * 新增报警阈值
     * 
     * @param tbAlarmThreshold 报警阈值信息
     * @return 结果
     */
	@Override
	public int insertTbAlarmThreshold(TbAlarmThreshold tbAlarmThreshold)
	{
	    return tbAlarmThresholdMapper.insertTbAlarmThreshold(tbAlarmThreshold);
	}
	
	/**
     * 修改报警阈值
     * 
     * @param tbAlarmThreshold 报警阈值信息
     * @return 结果
     */
	@Override
	public int updateTbAlarmThreshold(TbAlarmThreshold tbAlarmThreshold)
	{
	    return tbAlarmThresholdMapper.updateTbAlarmThreshold(tbAlarmThreshold);
	}

	/**
     * 删除报警阈值对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbAlarmThresholdByIds(String ids)
	{
		return tbAlarmThresholdMapper.deleteTbAlarmThresholdByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据传入的TbAlarmThreshold对象返回对应的数量
	 * @return 数量
	 */
	public Long getCountFromTbAlarmThreshold(TbAlarmThreshold tbAlarmThreshold)
	{
		return tbAlarmThresholdMapper.getCountFromTbAlarmThreshold(tbAlarmThreshold);
	}
}
