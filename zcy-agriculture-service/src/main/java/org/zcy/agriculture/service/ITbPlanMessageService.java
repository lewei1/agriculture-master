package org.zcy.agriculture.service;

import java.util.List;

import org.zcy.agriculture.entity.TbPlanMessage;

/**
 * 农事记录消息 服务层
 * 
 * @author numberone
 * @date 2019-07-05
 */
public interface ITbPlanMessageService 
{
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
     * 删除农事记录消息信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlanMessageByIds(String ids);
	
}
