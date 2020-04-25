package org.zcy.agriculture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbAlarmRecord;
import org.zcy.agriculture.mapper.TbAlarmRecordMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbAlarmRecordService;

/**
 * 报警记录 服务层实现
 * 
 * @author zh
 * @date 2019-07-02
 */
@Service
public class TbAlarmRecordServiceImpl implements ITbAlarmRecordService
{
	@Autowired
	private TbAlarmRecordMapper tbAlarmRecordMapper;

	/**
     * 查询报警记录信息
     * 
     * @param id 报警记录ID
     * @return 报警记录信息
     */
    @Override
	public TbAlarmRecord selectTbAlarmRecordById(Long id)
	{
	    return tbAlarmRecordMapper.selectTbAlarmRecordById(id);
	}
	
	/**
     * 查询报警记录列表
     * 
     * @param tbAlarmRecord 报警记录信息
     * @return 报警记录集合
     */
	@Override
	public List<TbAlarmRecord> selectTbAlarmRecordList(TbAlarmRecord tbAlarmRecord)
	{
	    return tbAlarmRecordMapper.selectTbAlarmRecordList(tbAlarmRecord);
	}
	
    /**
     * 新增报警记录
     * 
     * @param tbAlarmRecord 报警记录信息
     * @return 结果
     */
	@Override
	public int insertTbAlarmRecord(TbAlarmRecord tbAlarmRecord)
	{
	    return tbAlarmRecordMapper.insertTbAlarmRecord(tbAlarmRecord);
	}
	
	/**
     * 修改报警记录
     * 
     * @param tbAlarmRecord 报警记录信息
     * @return 结果
     */
	@Override
	public int updateTbAlarmRecord(TbAlarmRecord tbAlarmRecord)
	{
	    return tbAlarmRecordMapper.updateTbAlarmRecord(tbAlarmRecord);
	}

	/**
     * 删除报警记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbAlarmRecordByIds(String ids)
	{
		return tbAlarmRecordMapper.deleteTbAlarmRecordByIds(Convert.toStrArray(ids));
	}

	/**
	 * 测试获取报警记录信息
	 *
	 * @param startTime 开始时间
	 * @param endTime   结束时间
	 * @return 结果
	 */
	public Long selectCountBetweenTheTime(String startTime, String endTime) {
		if (startTime == null || endTime == null) {
			return -1L;
		}
		return tbAlarmRecordMapper.selectCountBetweenTheTime(startTime,endTime);
	}

	/**
	 * 根据农场ID、地块ID、开始时间、结束时间统计报警数量
	 *
	 * @param farmId 农场ID
	 * @param plotId 地块ID
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 结果
	 */
	public Long selectCountBetweenTheTimeByPlotId(String farmId, Long plotId, String startTime, String endTime){
		if (farmId== null || plotId == null|| startTime == null || endTime == null) {
			return -1L;
		}
		return tbAlarmRecordMapper.selectCountBetweenTheTimeByPlotId(farmId, plotId,startTime,endTime);
	}
	/**
	 * 首页统计报警参数分布
	 * @param farmId
	 * @param createTimeStr
	 * @return
	 */
	public List<HashMap<String,Object>> selectByStatisticsList(String farmId,String createTimeStr){
		return tbAlarmRecordMapper.selectByStatisticsList(farmId, createTimeStr);
	}
	/**
	 * 首页统计  报警统计
	 * @param farmId 农场ID
	 * @param createTimeStr 查询开始日期
	 * @param createTimeEnd 查询结束日期
	 * @return
	 */
	public List<HashMap<String,Object>> selectByStatisticsDateList(String farmId,String createTimeStr,String createTimeEnd,Integer devType){
		return tbAlarmRecordMapper.selectByStatisticsDateList(farmId, createTimeStr, createTimeEnd,devType);
	}
}
