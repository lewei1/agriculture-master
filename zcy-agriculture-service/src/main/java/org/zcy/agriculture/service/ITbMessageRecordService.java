package org.zcy.agriculture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zcy.agriculture.entity.TbMessageRecord;

/**
 * 消息记录 服务层
 * 
 * @author lky
 * @date 2019-07-11
 */
public interface ITbMessageRecordService {
	/**
	 * 查询消息记录信息
	 * 
	 * @param messageId 消息记录ID
	 * @return 消息记录信息
	 */
	public TbMessageRecord selectTbMessageRecordById(Long messageId);

	/**
	 * 查询消息记录列表
	 * 
	 * @param tbMessageRecord 消息记录信息
	 * @return 消息记录集合
	 */
	public List<TbMessageRecord> selectTbMessageRecordList(TbMessageRecord tbMessageRecord);

	/**
	 * 新增消息记录
	 * 
	 * @param tbMessageRecord 消息记录信息
	 * @return 结果
	 */
	public int insertTbMessageRecord(TbMessageRecord tbMessageRecord);

	/**
	 * 修改消息记录
	 * 
	 * @param tbMessageRecord 消息记录信息
	 * @return 结果
	 */
	public int updateTbMessageRecord(TbMessageRecord tbMessageRecord);

	/**
	 * 删除消息记录信息
	 * 
	 * @param map 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbMessageRecordByIds(Map<String, Object> map);

	/**
	 * 修改消息状态
	 *
	 * @param tbMessageRecord 消息记录信息
	 * @return 结果
	 */
	public int updateMessageStatus(TbMessageRecord tbMessageRecord);

	/**
	 * 插入消息
	 * 
	 * @param planId        计划ID
	 * @param sendUserId    发送方用户id
	 * @param receiveUserId 接收方用户id
	 * @param farmingTypeId 农事计划类型名称
	 */
	public void save(Long sendUserId, Long receiveUserId, Long farmingTypeId, Long planId,String farmId);

	/**
	 * 批量修改消息状态为已读
	 *
	 * @param map 需要修改的数据ID
	 * @return 结果
	 */
	public int updateMessageStatusByIds(Map<String, Object> map);
	/**
	 * 首页统计消息
	 * @param userId
	 * @return
	 */
	public List<HashMap<String,Object>> selectBystatisticsType(Long userId,String farmId);
	/**
	 * 首页统计  报警统计
	 * @param farmId
	 * @param createTimeStr
	 * @param createTimeEnd
	 * @param type
	 * @return
	 */
	public List<HashMap<String,Object>> selectByStatisticsDateList(String farmId,String createTimeStr,String createTimeEnd,int type);
	/**
	 * 首页统计  报警时间分布
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByStatisticsTimeList(String farmId,String createTimeStr);
}
