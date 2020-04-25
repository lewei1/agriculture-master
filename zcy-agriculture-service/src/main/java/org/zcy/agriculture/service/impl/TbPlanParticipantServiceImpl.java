package org.zcy.agriculture.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbPlanParticipant;
import org.zcy.agriculture.mapper.TbPlanParticipantMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbPlanParticipantService;

/**
 * 农事计划参与者(包括负责人和成员) 服务层实现
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbPlanParticipantServiceImpl implements ITbPlanParticipantService 
{
	@Autowired
	private TbPlanParticipantMapper tbPlanParticipantMapper;

	/**
     * 查询农事计划参与者(包括负责人和成员)信息
     * 
     * @param planUserId 农事计划参与者(包括负责人和成员)ID
     * @return 农事计划参与者(包括负责人和成员)信息
     */
    @Override
	public TbPlanParticipant selectTbPlanParticipantById(Long planUserId)
	{
	    return tbPlanParticipantMapper.selectTbPlanParticipantById(planUserId);
	}
	
	/**
     * 查询农事计划参与者(包括负责人和成员)列表
     * 
     * @param tbPlanParticipant 农事计划参与者(包括负责人和成员)信息
     * @return 农事计划参与者(包括负责人和成员)集合
     */
	@Override
	public List<TbPlanParticipant> selectTbPlanParticipantList(TbPlanParticipant tbPlanParticipant)
	{
	    return tbPlanParticipantMapper.selectTbPlanParticipantList(tbPlanParticipant);
	}
	
    /**
     * 新增农事计划参与者(包括负责人和成员)
     * 
     * @param tbPlanParticipant 农事计划参与者(包括负责人和成员)信息
     * @return 结果
     */
	@Override
	public int insertTbPlanParticipant(TbPlanParticipant tbPlanParticipant)
	{
	    return tbPlanParticipantMapper.insertTbPlanParticipant(tbPlanParticipant);
	}
	
	/**
     * 修改农事计划参与者(包括负责人和成员)
     * 
     * @param tbPlanParticipant 农事计划参与者(包括负责人和成员)信息
     * @return 结果
     */
	@Override
	public int updateTbPlanParticipant(TbPlanParticipant tbPlanParticipant)
	{
	    return tbPlanParticipantMapper.updateTbPlanParticipant(tbPlanParticipant);
	}

	/**
     * 删除农事计划参与者(包括负责人和成员)对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbPlanParticipantByIds(String ids)
	{
		return tbPlanParticipantMapper.deleteTbPlanParticipantByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据农场ID和userCode删除记录
	 */
	@Override
	public void deleteByFarmIdAndUserCode(Map map) {
		tbPlanParticipantMapper.deleteByFarmIdAndUserCode(map);
	}

}
