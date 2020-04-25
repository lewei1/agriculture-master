package org.zcy.agriculture.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbFarmingPlan;
import org.zcy.agriculture.param.FarmingPlanListParam;
import org.zcy.agriculture.vo.FarmingPlanSpecialVo;
import org.zcy.agriculture.vo.FarmingPlanVo;
import org.zcy.agriculture.vo.FarmingRecordVo;
import org.zcy.agriculture.vo.TbFarmingPlanVo;
import org.zcy.agriculture.vo.TbFarmingTypeVo;

/**
 * 农事计划（普通计划，智能计划） 数据层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface TbFarmingPlanMapper {
	/**
	 * 查询农事计划（普通计划，智能计划）信息
	 * 
	 * @param planId 农事计划（普通计划，智能计划）ID
	 * @return 农事计划（普通计划，智能计划）信息
	 */
	public TbFarmingPlan selectTbFarmingPlanById(Long planId);

	/**
	 * 查询农事计划（普通计划，智能计划）列表
	 * 
	 * @param tbFarmingPlan 农事计划（普通计划，智能计划）信息
	 * @return 农事计划（普通计划，智能计划）集合
	 */
	public List<TbFarmingPlan> selectTbFarmingPlanList(TbFarmingPlan tbFarmingPlan);

	/**
	 * 新增农事计划（普通计划，智能计划）
	 * 
	 * @param tbFarmingPlan 农事计划（普通计划，智能计划）信息
	 * @return 结果
	 */
	public int insertTbFarmingPlan(TbFarmingPlan tbFarmingPlan);

	/**
	 * 修改农事计划（普通计划，智能计划）
	 * 
	 * @param tbFarmingPlan 农事计划（普通计划，智能计划）信息
	 * @return 结果
	 */
	public int updateTbFarmingPlan(TbFarmingPlan tbFarmingPlan);

	/**
	 * 删除农事计划（普通计划，智能计划）
	 * 
	 * @param planId 农事计划（普通计划，智能计划）ID
	 * @return 结果
	 */
	public int deleteTbFarmingPlanById(Long planId);

	/**
	 * 批量删除农事计划（普通计划，智能计划）
	 * 
	 * @param planIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbFarmingPlanByIds(String[] planIds);

	/**
	 * 根据子地块 农场 时间查询计划数据
	 * 
	 * @param tbFarmingPlan
	 * @return
	 */
	public List<FarmingPlanVo> queryListByDate(@Param("list") Set<Long> subPlotIds, @Param("param") FarmingPlanListParam param);

	/**
	 * 农事计划列表
	 * 
	 * @param fpsv
	 * @return
	 */
	public List<FarmingPlanSpecialVo> selectBytjList(FarmingPlanSpecialVo fpsv);

	/**
	 * 今天计划
	 * 
	 * @param tbFarmingPlanVo
	 * @return
	 */
	public List<TbFarmingPlanVo> selectByTodayList(TbFarmingPlanVo tbFarmingPlanVo);

	/**
	 * 全部计划
	 * 
	 * @param farmId
	 * @return
	 */
	public List<TbFarmingTypeVo> selectByAllList(TbFarmingPlanVo tbFarmingPlanVo);

	/**
	 * 计划列表
	 * 
	 * @param tbFarmingPlanVo
	 * @return
	 */
	public List<TbFarmingPlanVo> selectByPlanList(TbFarmingPlanVo tbFarmingPlanVo);

	/**
	 * 农事记录 列表
	 * 
	 * @param farmingRecordVo
	 * @return
	 */
	public List<FarmingRecordVo> selectByLsRecordList(FarmingRecordVo farmingRecordVo);

	/**
	 * 农事记录 总条数
	 * 
	 * @param farmingRecordVo
	 * @return
	 */
	public int selectByLsRecordCou(FarmingRecordVo farmingRecordVo);

	/**
	 * 农事记录 头部汇总
	 * 
	 * @param farmingRecordVo
	 * @return
	 */
	public List<FarmingRecordVo> selectByStatisticsCou(FarmingRecordVo farmingRecordVo);

	/**
	 * 智能计划列表
	 * 
	 * @param farmingRecordVo
	 * @return
	 */
	public List<FarmingRecordVo> selectBySmartPlanList(TbFarmingPlanVo tbFarmingPlanVo);

	/**
	 * 今日任务数，今日完成任务数
	 * 
	 * @param today
	 * @param plotId
	 * @return
	 */
	public HashMap<String, Object> selectByStatisticsToday(String today, Long plotId);

	/**
	 * 首页农事展示状况
	 * 
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String, Object>> selectByHomeList(String farmId);

	/**
	 * 首页任务完成情况
	 * 
	 * @param userId
	 * @return
	 */
	public HashMap<String, Object> selectByHomeTaskCompletion(Long userId,String farmId);

	/**
	 * 根据农场ID 和 用户ID查询 任务
	 * 
	 * @param farmId
	 * @param userCode
	 * @return
	 */
	public int selectByFarmTask(String farmId, Long userCode);
	/**
	 * H5详情页面  任务
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String, Object>> selectByH5Task(String farmId);
	/**
	 * 更新智能计划到期   已完成
	 * @param endTime
	 * @return
	 */
	public int updateByFarmingPlanComplete(String endTime);
}