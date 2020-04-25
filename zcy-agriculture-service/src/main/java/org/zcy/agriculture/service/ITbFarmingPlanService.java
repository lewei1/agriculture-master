package org.zcy.agriculture.service;

import java.util.HashMap;
import java.util.List;

import org.zcy.agriculture.entity.TbFarmingPlan;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.param.AddFarmingPlanParam;
import org.zcy.agriculture.param.AddStandardPlanParam;
import org.zcy.agriculture.param.FarmingPlanListParam;
import org.zcy.agriculture.vo.FarmingPlanListVo;
import org.zcy.agriculture.vo.FarmingPlanSpecialVo;
import org.zcy.agriculture.vo.FarmingRecordVo;
import org.zcy.agriculture.vo.TbFarmingPlanImplementVo;
import org.zcy.agriculture.vo.TbFarmingPlanVo;
import org.zcy.agriculture.vo.TbFarmingTypeVo;

/**
 * 农事计划（普通计划，智能计划） 服务层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbFarmingPlanService {
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
	public void insertTbFarmingPlan(AddFarmingPlanParam param);

	/**
	 * 修改农事计划（普通计划，智能计划）
	 * 
	 * @param tbFarmingPlan 农事计划（普通计划，智能计划）信息
	 * @return 结果
	 */
	public int updateTbFarmingPlan(TbFarmingPlan tbFarmingPlan);

	/**
	 * 删除农事计划（普通计划，智能计划）信息
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbFarmingPlanByIds(String ids);

	/**
	 * 将智能计划转化成普通计划
	 * 
	 * @param implementId
	 * @return
	 */
	public AjaxResult changePlain(Long implementId);

	/**
	 * 添加标准计划
	 * 
	 * @param param
	 * @return
	 */
	public AjaxResult addStandardPlan(AddStandardPlanParam param);

	/**
	 * 获取农事计划页面数据
	 * 
	 * @param param
	 * @return
	 */
	public FarmingPlanListVo queryList(FarmingPlanListParam param);

	/**
	 * 农事计划列表
	 * 
	 * @param fpsv
	 * @return
	 */
	public HashMap<String, Object> selectBytjList(FarmingPlanSpecialVo fpsv);

	/**
	 * 计划详情
	 * 
	 * @param planId
	 * @return
	 */
	public HashMap<String, Object> getInfo(Long planId,Long subPlotId);

	/**
	 * 重复 ，完成计划
	 * 
	 * @param tbFarmingPlan 农事计划（普通计划，智能计划）信息
	 * @return 结果
	 */
	public void planComplete(TbFarmingPlan fpInfo, Long planId);

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
	 * 农事记录 总条数
	 * 
	 * @param farmingRecordVo
	 * @return
	 */
	public int selectByLsRecordCou(FarmingRecordVo farmingRecordVo);

	/**
	 * 农事记录 列表
	 * 
	 * @param farmingRecordVo
	 * @return
	 */
	public List<FarmingRecordVo> selectByLsRecordList(FarmingRecordVo farmingRecordVo);

	/**
	 * 农事记录 头部汇总
	 * 
	 * @param farmingRecordVo
	 * @return
	 */
	public HashMap<String, Object> selectByStatisticsCou(FarmingRecordVo farmingRecordVo);

	/**
	 * 智能计划列表
	 * 
	 * @param tbFarmingPlanVo
	 * @return
	 */
	public List<FarmingRecordVo> selectBySmartPlanList(TbFarmingPlanVo tbFarmingPlanVo);

	/**
	 * 今日任务数，今日完成任务数
	 *
	 * @param plotId
	 * @return
	 */
	public HashMap<String, Object> selectByStatisticsToday(Long plotId);

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
	 * 查询未调用过的 且在传入的时间 是 在开启到结束时间内的 智能计划
	 * 
	 * @param today
	 * @return
	 */
	public List<TbFarmingPlanImplementVo> selectByIntelligentPlanningList();
	/**
	 * H5详情页面  任务
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String, Object>> selectByH5Task(String farmId);
}
