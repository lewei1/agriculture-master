package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbAlarmDayTime;
import org.zcy.agriculture.mapper.TbAlarmDayTimeMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbAlarmDayTimeService;

/**
 * 监控中心--白天报警时间段 服务层实现
 * 
 * @author zh
 * @date 2019-06-28
 */
@Service
public class TbAlarmDayTimeServiceImpl implements ITbAlarmDayTimeService
{
	@Autowired
	private TbAlarmDayTimeMapper tbAlarmDayTimeMapper;

	/**
     * 查询监控中心--白天报警时间段信息
     * 
     * @param id 监控中心--白天报警时间段ID
     * @return 监控中心--白天报警时间段信息
     */
    @Override
	public TbAlarmDayTime selectTbAlarmDayTimeById(Long id)
	{
	    return tbAlarmDayTimeMapper.selectTbAlarmDayTimeById(id);
	}
	
	/**
     * 查询监控中心--白天报警时间段列表
     * 
     * @param tbAlarmDayTime 监控中心--白天报警时间段信息
     * @return 监控中心--白天报警时间段集合
     */
	@Override
	public List<TbAlarmDayTime> selectTbAlarmDayTimeList(TbAlarmDayTime tbAlarmDayTime)
	{
	    return tbAlarmDayTimeMapper.selectTbAlarmDayTimeList(tbAlarmDayTime);
	}
	
    /**
     * 新增监控中心--白天报警时间段
     * 
     * @param tbAlarmDayTime 监控中心--白天报警时间段信息
     * @return 结果
     */
	@Override
	public int insertTbAlarmDayTime(TbAlarmDayTime tbAlarmDayTime)
	{
	    return tbAlarmDayTimeMapper.insertTbAlarmDayTime(tbAlarmDayTime);
	}
	
	/**
     * 修改监控中心--白天报警时间段
     * 
     * @param tbAlarmDayTime 监控中心--白天报警时间段信息
     * @return 结果
     */
	@Override
	public int updateTbAlarmDayTime(TbAlarmDayTime tbAlarmDayTime)
	{
	    return tbAlarmDayTimeMapper.updateTbAlarmDayTime(tbAlarmDayTime);
	}

	/**
     * 删除监控中心--白天报警时间段对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbAlarmDayTimeByIds(String ids)
	{
		return tbAlarmDayTimeMapper.deleteTbAlarmDayTimeByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据传入的TbAlarmDayTime类型对象来获取对应的数据
	 *
	 * @return 统计结果
	 */
	public Long getCountFromTbAlarmDayTime(TbAlarmDayTime alarmDayTime)	{
		return tbAlarmDayTimeMapper.getCountFromTbAlarmDayTime(alarmDayTime);
	}
}
