package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbAlarmDayTime;

import java.util.List;

/**
 * 监控中心--白天报警时间段 服务层
 * 
 * @author zh
 * @date 2019-06-28
 */
public interface ITbAlarmDayTimeService 
{
	/**
     * 查询监控中心--白天报警时间段信息
     * 
     * @param id 监控中心--白天报警时间段ID
     * @return 监控中心--白天报警时间段信息
     */
	public TbAlarmDayTime selectTbAlarmDayTimeById(Long id);
	
	/**
     * 查询监控中心--白天报警时间段列表
     * 
     * @param tbAlarmDayTime 监控中心--白天报警时间段信息
     * @return 监控中心--白天报警时间段集合
     */
	public List<TbAlarmDayTime> selectTbAlarmDayTimeList(TbAlarmDayTime tbAlarmDayTime);
	
	/**
     * 新增监控中心--白天报警时间段
     * 
     * @param tbAlarmDayTime 监控中心--白天报警时间段信息
     * @return 结果
     */
	public int insertTbAlarmDayTime(TbAlarmDayTime tbAlarmDayTime);
	
	/**
     * 修改监控中心--白天报警时间段
     * 
     * @param tbAlarmDayTime 监控中心--白天报警时间段信息
     * @return 结果
     */
	public int updateTbAlarmDayTime(TbAlarmDayTime tbAlarmDayTime);
		
	/**
     * 删除监控中心--白天报警时间段信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbAlarmDayTimeByIds(String ids);

	/**
	 * 根据传入的TbAlarmDayTime类型对象来获取对应的数据
	 *
	 * @return 统计结果
	 */
	public Long getCountFromTbAlarmDayTime(TbAlarmDayTime alarmDayTime);
}
