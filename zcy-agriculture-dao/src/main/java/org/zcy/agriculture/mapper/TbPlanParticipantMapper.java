package org.zcy.agriculture.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbPlanParticipant;
import org.zcy.agriculture.vo.TbFarmingPlanVo;

/**
 * 农事计划参与者(包括负责人和成员) 数据层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface TbPlanParticipantMapper {
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
	 * 批量新增
	 * 
	 * @param list
	 * @return
	 */
	public int insertBatchTbPlanParticipant(List<TbPlanParticipant> list);

	/**
	 * 根据计划ID复制数据
	 * 
	 * @param tbPlanPlot
	 * @return
	 */
	public int copyPlanParticipant(@Param("planId") Long planId, @Param("newPlanId") Long newPlanId, @Param("subPlotId") Long subPlotId);

	/**
	 * 修改农事计划参与者(包括负责人和成员)
	 * 
	 * @param tbPlanParticipant 农事计划参与者(包括负责人和成员)信息
	 * @return 结果
	 */
	public int updateTbPlanParticipant(TbPlanParticipant tbPlanParticipant);

	/**
	 * 删除农事计划参与者(包括负责人和成员)
	 * 
	 * @param planUserId 农事计划参与者(包括负责人和成员)ID
	 * @return 结果
	 */
	public int deleteTbPlanParticipantById(Long planUserId);

	/**
	 * 批量删除农事计划参与者(包括负责人和成员)
	 * 
	 * @param planUserIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbPlanParticipantByIds(String[] planUserIds);
	/**
	 * 根据子地块和计划ID删除成员
	 * @param subPlotId
	 * @param planId
	 * @return
	 */
	public int deleteTbPlanParticipantBySubPlotId(Long subPlotId,Long planId);
	
	/**
	 * 根据农事计划ID查询 负责人 和参与者
	 * @param farmId
	 * @param planId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByPlanParticipantList(String farmId,Long planId,Long subPlotId);


	/**
	 * 根据农场ID和userCode删除记录
	 */
	void deleteByFarmIdAndUserCode(Map map);
}