package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbPlanMessage;
import org.zcy.agriculture.mapper.TbPlanMessageMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbPlanMessageService;

/**
 * 农事记录消息 服务层实现
 * 
 * @author numberone
 * @date 2019-07-05
 */
@Service
public class TbPlanMessageServiceImpl implements ITbPlanMessageService 
{
	@Autowired
	private TbPlanMessageMapper tbPlanMessageMapper;

	/**
     * 查询农事记录消息信息
     * 
     * @param messageId 农事记录消息ID
     * @return 农事记录消息信息
     */
    @Override
	public TbPlanMessage selectTbPlanMessageById(Long messageId)
	{
	    return tbPlanMessageMapper.selectTbPlanMessageById(messageId);
	}
	
	/**
     * 查询农事记录消息列表
     * 
     * @param tbPlanMessage 农事记录消息信息
     * @return 农事记录消息集合
     */
	@Override
	public List<TbPlanMessage> selectTbPlanMessageList(TbPlanMessage tbPlanMessage)
	{
	    return tbPlanMessageMapper.selectTbPlanMessageList(tbPlanMessage);
	}
	
    /**
     * 新增农事记录消息
     * 
     * @param tbPlanMessage 农事记录消息信息
     * @return 结果
     */
	@Override
	public int insertTbPlanMessage(TbPlanMessage tbPlanMessage)
	{
	    return tbPlanMessageMapper.insertTbPlanMessage(tbPlanMessage);
	}
	
	/**
     * 修改农事记录消息
     * 
     * @param tbPlanMessage 农事记录消息信息
     * @return 结果
     */
	@Override
	public int updateTbPlanMessage(TbPlanMessage tbPlanMessage)
	{
	    return tbPlanMessageMapper.updateTbPlanMessage(tbPlanMessage);
	}

	/**
     * 删除农事记录消息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbPlanMessageByIds(String ids)
	{
		return tbPlanMessageMapper.deleteTbPlanMessageByIds(Convert.toStrArray(ids));
	}
	
}
