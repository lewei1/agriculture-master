package org.zcy.agriculture.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbMessageRecord;
import org.zcy.agriculture.mapper.TbMessageRecordMapper;
import org.zcy.agriculture.service.ITbMessageRecordService;

import com.google.common.collect.Maps;

/**
 * 消息记录 服务层实现
 * 
 * @author numberone
 * @date 2019-07-11
 */
@Service
public class TbMessageRecordServiceImpl implements ITbMessageRecordService {
	@Autowired
	private TbMessageRecordMapper tbMessageRecordMapper;

	/**
	 * 查询消息记录信息
	 * 
	 * @param messageId 消息记录ID
	 * @return 消息记录信息
	 */
	@Override
	public TbMessageRecord selectTbMessageRecordById(Long messageId) {
		return tbMessageRecordMapper.selectTbMessageRecordById(messageId);
	}

	/**
	 * 查询消息记录列表
	 * 
	 * @param tbMessageRecord 消息记录信息
	 * @return 消息记录集合
	 */

	@Override
	public List<TbMessageRecord> selectTbMessageRecordList(TbMessageRecord tbMessageRecord) {
		return tbMessageRecordMapper.selectTbMessageRecordList(tbMessageRecord);
	}

	/**
	 * @param map 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbMessageRecordByIds(Map<String, Object> map) {
		return tbMessageRecordMapper.deleteTbMessageRecordByIds(map);
	}

	/**
	 * 新增消息记录
	 * 
	 * @param tbMessageRecord 消息记录信息
	 * @return 结果
	 */
	@Override
	public int insertTbMessageRecord(TbMessageRecord tbMessageRecord) {
		return tbMessageRecordMapper.insertTbMessageRecord(tbMessageRecord);
	}

	/**
	 * 修改消息记录
	 * 
	 * @param tbMessageRecord 消息记录信息
	 * @return 结果
	 */
	@Override
	public int updateTbMessageRecord(TbMessageRecord tbMessageRecord) {
		return tbMessageRecordMapper.updateTbMessageRecord(tbMessageRecord);
	}

	/**
	 * 修改消息状态
	 *
	 * @param tbMessageRecord 消息记录信息
	 * @return 结果
	 */
	@Override
	public int updateMessageStatus(TbMessageRecord tbMessageRecord) {
		return tbMessageRecordMapper.updateMessageStatus(tbMessageRecord);
	}

	/**
	 * 插入消息
	 * 
	 * @param planId        计划ID
	 * @param sendUserId    发送方用户id
	 * @param receiveUserId 接收方用户id
	 * @param farmingTypeId 农事计划类型名称
	 */
	@Transactional
	public void save(Long sendUserId, Long receiveUserId, Long farmingTypeId, Long planId,String farmId) {
		HashMap<String, Object> m = Maps.newHashMap();
		m.put("sendUserId", sendUserId);
		m.put("receiveUserId", receiveUserId);
		m.put("farmingTypeId", farmingTypeId);
		m.put("messageSubject", planId == null ? "新任务" : "新消息");
		m.put("planId", planId);
		m.put("farmId", farmId);
		tbMessageRecordMapper.insertByMap(m);
	}

	/**
	 * 批量修改消息状态为已读
	 *
	 * @param map 需要修改的数据ID
	 * @return 结果
	 */
	@Override
	public int updateMessageStatusByIds(Map<String, Object> map) {
		return tbMessageRecordMapper.updateMessageStatusByIds(map);
	}
	/**
	 * 首页统计消息
	 * @param userId
	 * @return
	 */
	public List<HashMap<String,Object>> selectBystatisticsType(Long userId,String farmId){
		return tbMessageRecordMapper.selectBystatisticsType(userId,farmId);
	}
	/**
	 * 首页统计  报警统计
	 * @param farmId
	 * @param createTimeStr
	 * @param createTimeEnd
	 * @param type
	 * @return
	 */
	public List<HashMap<String,Object>> selectByStatisticsDateList(String farmId,String createTimeStr,String createTimeEnd,int type){
		return tbMessageRecordMapper.selectByStatisticsDateList(farmId, createTimeStr, createTimeEnd, type);
	}
	/**
	 * 首页统计  报警时间分布
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByStatisticsTimeList(String farmId,String createTimeStr){
		return tbMessageRecordMapper.selectByStatisticsTimeList(farmId,createTimeStr);
	}
}
