package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbAlarmDayTime;
import org.zcy.agriculture.entity.TbAlarmThreshold;

import java.util.List;

/**
 * 报警阈值 数据层
 * 
 * @author zh
 * @date 2019-06-28
 */
public interface TbAlarmThresholdMapper 
{
	/**
     * 查询报警阈值信息
     * 
     * @param alarmThresholdId 报警阈值ID
     * @return 报警阈值信息
     */
	public TbAlarmThreshold selectTbAlarmThresholdById(Long alarmThresholdId);
	
	/**
     * 查询报警阈值列表
     * 
     * @param tbAlarmThreshold 报警阈值信息
     * @return 报警阈值集合
     */
	public List<TbAlarmThreshold> selectTbAlarmThresholdList(TbAlarmThreshold tbAlarmThreshold);
	
	/**
     * 新增报警阈值
     * 
     * @param tbAlarmThreshold 报警阈值信息
     * @return 结果
     */
	public int insertTbAlarmThreshold(TbAlarmThreshold tbAlarmThreshold);
	
	/**
     * 修改报警阈值
     * 
     * @param tbAlarmThreshold 报警阈值信息
     * @return 结果
     */
	public int updateTbAlarmThreshold(TbAlarmThreshold tbAlarmThreshold);
	
	/**
     * 删除报警阈值
     * 
     * @param alarmThresholdId 报警阈值ID
     * @return 结果
     */
	public int deleteTbAlarmThresholdById(Long alarmThresholdId);
	
	/**
     * 批量删除报警阈值
     * 
     * @param alarmThresholdIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbAlarmThresholdByIds(String[] alarmThresholdIds);

	/**
	 * 根据传入的TbAlarmThreshold对象返回对应的数量
	 * @return 数量
	 */
	public Long getCountFromTbAlarmThreshold(TbAlarmThreshold tbAlarmThreshold);
}