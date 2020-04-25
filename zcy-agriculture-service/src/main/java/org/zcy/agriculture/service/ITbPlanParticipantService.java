package org.zcy.agriculture.service;

import java.util.List;
import java.util.Map;

import org.zcy.agriculture.entity.TbPlanParticipant;

/**
 * 农事计划参与者(包括负责人和成员) 服务层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbPlanParticipantService 
{
	/**
     * 查询农事计划参与者(包括负责人和成员)信息
     * 
     * @param planUserId 农事计划参与者(包括负责人和成员)ID
     * @return 农事计划参与者(包括负责人和成员)信息
     */
	public TbPlanParticipant selectTbPlanParticipantById(Long planUserId);
	
	/**
     * 查询农事计划参与者(包括负责人和成员)列表
     * 
     * @param tbPlanParticipant 农事计划参与者(包括负责人和成员)信息
     * @return 农事计划参与者(包括负责人和成员)集合
     */
	public List<TbPlanParticipant> selectTbPlanParticipantList(TbPlanParticipant tbPlanParticipant);
	
	/**
     * 新增农事计划参与者(包括负责人和成员)
     * 
     * @param tbPlanParticipant 农事计划参与者(包括负责人和成员)信息
     * @return 结果
     */
	public int insertTbPlanParticipant(TbPlanParticipant tbPlanParticipant);
	
	/**
     * 修改农事计划参与者(包括负责人和成员)
     * 
     * @param tbPlanParticipant 农事计划参与者(包括负责人和成员)信息
     * @return 结果
     */
	public int updateTbPlanParticipant(TbPlanParticipant tbPlanParticipant);
		
	/**
     * 删除农事计划参与者(包括负责人和成员)信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlanParticipantByIds(String ids);

	/**
	 * 根据农场ID和userCode删除记录
	 */
	void deleteByFarmIdAndUserCode(Map map);
	
}
