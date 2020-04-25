package org.zcy.agriculture.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.zcy.agriculture.entity.TbAlarmRecord;

/**
 * 报警记录 数据层
 * 
 * @author zh
 * @date 2019-07-02
 */
public interface TbAlarmRecordMapper 
{
	/**
     * 查询报警记录信息
     * 
     * @param id 报警记录ID
     * @return 报警记录信息
     */
	public TbAlarmRecord selectTbAlarmRecordById(Long id);
	
	/**
     * 查询报警记录列表
     * 
     * @param tbAlarmRecord 报警记录信息
     * @return 报警记录集合
     */
	public List<TbAlarmRecord> selectTbAlarmRecordList(TbAlarmRecord tbAlarmRecord);
	
	/**
     * 新增报警记录
     * 
     * @param tbAlarmRecord 报警记录信息
     * @return 结果
     */
	public int insertTbAlarmRecord(TbAlarmRecord tbAlarmRecord);
	
	/**
     * 修改报警记录
     * 
     * @param tbAlarmRecord 报警记录信息
     * @return 结果
     */
	public int updateTbAlarmRecord(TbAlarmRecord tbAlarmRecord);
	
	/**
     * 删除报警记录
     * 
     * @param id 报警记录ID
     * @return 结果
     */
	public int deleteTbAlarmRecordById(Long id);
	
	/**
     * 批量删除报警记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbAlarmRecordByIds(String[] ids);

	/**
	 * 根据开始时间、结束时间统计报警数量
	 *
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 结果
	 */
	public Long selectCountBetweenTheTime(@Param("startTime") String startTime,@Param("endTime") String endTime);

	/**
	 * 根据农场ID、地块ID、开始时间、结束时间统计报警数量
	 *
	 * @param farmId 农场ID
	 * @param plotId 地块ID
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 结果
	 */
	public Long selectCountBetweenTheTimeByPlotId(@Param("farmId") String farmId,@Param("plotId") Long plotId,@Param("startTime") String startTime,@Param("endTime") String endTime);

	/**
	 * 首页统计报警参数分布
	 * @param farmId
	 * @param createTimeStr
	 * @return
	 */
	public List<HashMap<String,Object>> selectByStatisticsList(String farmId,String createTimeStr);
	/**
	 * 首页统计  报警统计
	 * @param farmId 农场ID
	 * @param createTimeStr 查询开始日期
	 * @param createTimeEnd 查询结束日期
	 * @return
	 */
	public List<HashMap<String,Object>> selectByStatisticsDateList(String farmId,String createTimeStr,String createTimeEnd,Integer devType);
}