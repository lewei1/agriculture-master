package org.zcy.agriculture.mapper;

import java.util.List;

import org.zcy.agriculture.entity.TbPlanMessage;
import org.zcy.agriculture.vo.TbPlanMessageVo;

/**
 * 农事记录消息 数据层
 * 
 * @author numberone
 * @date 2019-07-05
 */
public interface TbPlanMessageMapper {
	/**
	 * 查询农事记录消息信息
	 * 
	 * @param messageId 农事记录消息ID
	 * @return 农事记录消息信息
	 */
	public TbPlanMessage selectTbPlanMessageById(Long messageId);

	/**
	 * 查询农事记录消息列表
	 * 
	 * @param tbPlanMessage 农事记录消息信息
	 * @return 农事记录消息集合
	 */
	public List<TbPlanMessage> selectTbPlanMessageList(TbPlanMessage tbPlanMessage);

	/**
	 * 新增农事记录消息
	 * 
	 * @param tbPlanMessage 农事记录消息信息
	 * @return 结果
	 */
	public int insertTbPlanMessage(TbPlanMessage tbPlanMessage);

	/**
	 * 修改农事记录消息
	 * 
	 * @param tbPlanMessage 农事记录消息信息
	 * @return 结果
	 */
	public int updateTbPlanMessage(TbPlanMessage tbPlanMessage);

	/**
	 * 删除农事记录消息
	 * 
	 * @param messageId 农事记录消息ID
	 * @return 结果
	 */
	public int deleteTbPlanMessageById(Long messageId);

	/**
	 * 批量删除农事记录消息
	 * 
	 * @param messageIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbPlanMessageByIds(String[] messageIds);

	/**
	 * 查询农事计划聊天记录
	 * 
	 * @param planId
	 * @return
	 */
	public List<TbPlanMessageVo> selectByChatRecordList(Long planId,String farmId);

}