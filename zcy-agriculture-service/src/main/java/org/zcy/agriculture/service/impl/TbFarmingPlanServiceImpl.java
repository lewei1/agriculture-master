package org.zcy.agriculture.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.PlanTypeEnum;
import org.zcy.agriculture.constants.PlotConstants;
import org.zcy.agriculture.entity.TbAgriculturalMachine;
import org.zcy.agriculture.entity.TbFarmingPlan;
import org.zcy.agriculture.entity.TbFarmingPlanImplement;
import org.zcy.agriculture.entity.TbFarmingType;
import org.zcy.agriculture.entity.TbModelPeriodFarming;
import org.zcy.agriculture.entity.TbPlanImg;
import org.zcy.agriculture.entity.TbPlanMaterial;
import org.zcy.agriculture.entity.TbPlanParticipant;
import org.zcy.agriculture.entity.TbPlanPlot;
import org.zcy.agriculture.entity.TbSubPlot;
import org.zcy.agriculture.entity.TbUseRecord;
import org.zcy.agriculture.entity.TbUser;
import org.zcy.agriculture.mapper.TbAgriculturalMachineMapper;
import org.zcy.agriculture.mapper.TbFarmingPlanImplementMapper;
import org.zcy.agriculture.mapper.TbFarmingPlanMapper;
import org.zcy.agriculture.mapper.TbFarmingTypeMapper;
import org.zcy.agriculture.mapper.model.TbModelMapper;
import org.zcy.agriculture.mapper.model.TbModelPeriodFarmingMapper;
import org.zcy.agriculture.mapper.TbPlanImgMapper;
import org.zcy.agriculture.mapper.TbPlanMaterialMapper;
import org.zcy.agriculture.mapper.TbPlanMessageMapper;
import org.zcy.agriculture.mapper.TbPlanParticipantMapper;
import org.zcy.agriculture.mapper.TbPlanPlotMapper;
import org.zcy.agriculture.mapper.TbPlantingMapper;
import org.zcy.agriculture.mapper.TbUseRecordMapper;
import org.zcy.agriculture.mapper.TbUserMapper;
import org.zcy.agriculture.mapper.plot.TbPlotMapper;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.AddFarmingPlanParam;
import org.zcy.agriculture.param.AddPloatUserParam;
import org.zcy.agriculture.param.AddStandardPlanParam;
import org.zcy.agriculture.param.FarmingPlanListParam;
import org.zcy.agriculture.service.ITbFarmingPlanService;
import org.zcy.agriculture.service.ITbMessageRecordService;
import org.zcy.agriculture.service.ITbPlanPlotService;
import org.zcy.agriculture.service.ITbWarehouseDynamicService;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.FarmingPlanListVo;
import org.zcy.agriculture.vo.FarmingPlanPloatVo;
import org.zcy.agriculture.vo.FarmingPlanSpecialVo;
import org.zcy.agriculture.vo.FarmingPlanVo;
import org.zcy.agriculture.vo.FarmingRecordVo;
import org.zcy.agriculture.vo.TbFarmingPlanImplementVo;
import org.zcy.agriculture.vo.TbFarmingPlanVo;
import org.zcy.agriculture.vo.TbFarmingTypeVo;
import org.zcy.agriculture.vo.TbPlanMaterialVo;
import org.zcy.agriculture.vo.TbPlanMessageVo;
import org.zcy.agriculture.vo.TbPlantingVo;
import org.zcy.agriculture.vo.TbPlotVo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * 农事计划（普通计划，智能计划） 服务层实现
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbFarmingPlanServiceImpl implements ITbFarmingPlanService {
	@Autowired
	private TbFarmingPlanMapper tbFarmingPlanMapper;
	@Autowired
	private TbPlanPlotMapper tbPlanPlotMapper;
	@Autowired
	private TbPlanMaterialMapper tbPlanMaterialMapper;
	@Autowired
	private TbPlanImgMapper tbPlanImgMapper;
	@Autowired
	private TbPlanParticipantMapper tbPlanParticipantMapper;
	@Autowired
	private TbFarmingPlanImplementMapper tbFarmingPlanImplementMapper;
	@Autowired
	private TbPlantingMapper tbPlantingMapper;
	@Autowired
	private TbModelPeriodFarmingMapper tbModelPeriodFarmingMapper;
	@Autowired
	private TbPlotMapper tbPlotMapper;
	@Autowired
	private TbPlanMessageMapper tbPlanMessageMapper;
	@Autowired
	private ITbMessageRecordService tbMessageRecordService;
	@Autowired
	private ITbWarehouseDynamicService tbWarehouseDynamicService;
	@Autowired
	private TbAgriculturalMachineMapper tbAgriculturalMachineMapper;
	@Autowired
	private ITbPlanPlotService tbPlanPlotService;
	@Autowired
	private TbUseRecordMapper tUseRecordMapper;
	@Autowired
	private TbFarmingTypeMapper tFarmingTypeMapper;
	@Autowired
	private TbUserMapper tUserMapper;
	@Autowired
	private TbModelMapper tbModelMapper;

	/**
	 * 查询农事计划（普通计划，智能计划）信息
	 * 
	 * @param planId 农事计划（普通计划，智能计划）ID
	 * @return 农事计划（普通计划，智能计划）信息
	 */
	@Override
	public TbFarmingPlan selectTbFarmingPlanById(Long planId) {
		return tbFarmingPlanMapper.selectTbFarmingPlanById(planId);
	}

	/**
	 * 查询农事计划（普通计划，智能计划）列表
	 * 
	 * @param tbFarmingPlan 农事计划（普通计划，智能计划）信息
	 * @return 农事计划（普通计划，智能计划）集合
	 */
	@Override
	public List<TbFarmingPlan> selectTbFarmingPlanList(TbFarmingPlan tbFarmingPlan) {
		return tbFarmingPlanMapper.selectTbFarmingPlanList(tbFarmingPlan);
	}

	/**
	 * 新增农事计划（普通计划，智能计划）
	 * 
	 * @param param 农事计划（普通计划，智能计划）信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public void insertTbFarmingPlan(AddFarmingPlanParam param) {
		if (param.getPlanId() == null) {// 新增
			List<AddPloatUserParam> ps = param.getPantUsers();
			// 插入地块关联表
			List<TbPlanPlot> plots = Lists.newArrayList();
			List<TbPlanParticipant> pants = Lists.newArrayList();
			String userId = "";
			String userName = "";
			Date startTime = new Date();
			Date costTime = new Date();
			for (AddPloatUserParam userParam : ps) {
				// 设置计划主表数据
				TbFarmingPlan plan = new TbFarmingPlan();
				plan.setPlanType(PlanTypeEnum.ORDINARY.getCode());
				BeanUtils.copyProperties(param, plan);
				// 判断执行条件是否为空 如果不为空 就是智能计划
				if (null != param.getImplement()) {
					plan.setStartTime(DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", DateUtils.getDate() + " " + param.getStartTimeZn()));
					plan.setEndTime(DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", DateUtils.getDate() + " " + param.getEndTimeZn()));
					plan.setIsLntelligence(1);
					plan.setPlanType(PlanTypeEnum.INTELLIGENCE.getCode());
//					plan.setPlanRepeatData(plan.getPlanRepeat() == 1?1:null);//如果是智能计划，选择重复的时候 只能是1天
				} else {
					plan.setWorkHours(0d);
				}
				startTime = plan.getStartTime();
				costTime = plan.getEndTime();

				// 如果创建以完成的计划
				if (param.getPlanStatus() != null && param.getPlanStatus() == NormalOrDeleteEnum.CLOSE.getCode()) {
					plan.setUpdateBy(param.getCreateBy());
					plan.setUpdateTime(plan.getEndTime());
				}
				tbFarmingPlanMapper.insertTbFarmingPlan(plan);
				if (plan.getPlanRepeat() == 1) {
					// 如果是重复
					TbFarmingPlan t = new TbFarmingPlan();
					t.setPlanId(plan.getPlanId());
					t.setPlanRepeatPlanId(plan.getPlanId());
					tbFarmingPlanMapper.updateTbFarmingPlan(t);
				}
				Long planId = plan.getPlanId();
				// 插入执行条件表
				if (null != param.getImplement()) {
					TbFarmingPlanImplement implement = param.getImplement();
					implement.setPlanId(planId);
					tbFarmingPlanImplementMapper.insertTbFarmingPlanImplement(implement);
				}
				// 插入使用物品表数据
				if (!ValidationUtil.isEmpty(param.getMaterials()) && param.getMaterials().size() > 0) {
					List<TbPlanMaterial> materials = param.getMaterials();
					for (TbPlanMaterial tbPlanMaterial : materials) {
						tbPlanMaterial.setPlanId(planId);
						tbWarehouseDynamicService.save(plan.getFarmId(), tbPlanMaterial.getMaterialId(), tbPlanMaterial.getMaterialAmount() + "" + tbPlanMaterial.getMaterialUnit(), param.getCreateBy());
					}
					tbPlanMaterialMapper.insertBatchTbPlanMaterial(materials);
				}
				// 插入计划对应的图片表
				if (!ValidationUtil.isEmpty(param.getImgs()) && param.getImgs().size() > 0) {
					List<TbPlanImg> imgs = param.getImgs();
					for (TbPlanImg tbPlanImg : imgs) {
						tbPlanImg.setPlanId(planId);
					}
					tbPlanImgMapper.insertBatchTbPlanImg(imgs);
				}

				TbPlanPlot tbPlanPlot = new TbPlanPlot();
				tbPlanPlot.setPlanId(planId);
				tbPlanPlot.setSubPlotId(userParam.getSubPlotId());
				tbPlanPlot.setPlotId(userParam.getPlotId());
				plots.add(tbPlanPlot);

				if (userParam.getResponserId() != null) {
					TbPlanParticipant tbPlanParticipant = new TbPlanParticipant();
					tbPlanParticipant.setPlanId(planId);
					tbPlanParticipant.setSubPlotId(userParam.getSubPlotId());
					tbPlanParticipant.setResponserId(userParam.getResponserId());
					pants.add(tbPlanParticipant);
				}

				if (userParam.getResponserId() != null && userId.indexOf("," + userParam.getResponserId() + ",") == -1) {
					userId += "," + userParam.getResponserId() + ",";
					// 插入新消息
					tbMessageRecordService.save(param.getCreateBy(), userParam.getResponserId(), param.getFarmingTypeId(), null, param.getFarmId());

					// (普通计划添加，智能计划满足条件后添加)
					if (null == param.getImplement()) {
						// 插入农机人员
						TbUser tb = tUserMapper.selectVbUserByCode(userParam.getResponserId(), param.getFarmId());
						userName = userName + (tb != null ? ("," + tb.getNickName()) : "");
					}
				}
				if (StringUtils.isNotEmpty(userParam.getParticipantIds())) {
					List<String> us = Lists.newArrayList(userParam.getParticipantIds().split(","));
					for (String uid : us) {
						if (StringUtils.isEmpty(uid)) {
							continue;
						}
						TbPlanParticipant tbPlanParticipant = new TbPlanParticipant();
						tbPlanParticipant.setPlanId(planId);
						tbPlanParticipant.setSubPlotId(userParam.getSubPlotId());
						tbPlanParticipant.setParticipantId(Long.parseLong(uid));
						pants.add(tbPlanParticipant);

						if (userId.indexOf("," + uid + ",") == -1) {
							// 插入新消息
							tbMessageRecordService.save(param.getCreateBy(), Long.parseLong(uid), param.getFarmingTypeId(), null, param.getFarmId());

							// (普通计划添加，智能计划满足条件后添加)
							if (null == param.getImplement()) {
								// 插入农机人员
								TbUser tb = tUserMapper.selectVbUserByCode(Long.parseLong(uid), param.getFarmId());
								userName = userName + (tb != null ? ("," + tb.getNickName()) : "");
							}
						}
					}
				}
			}
			tbPlanPlotMapper.insertBatchTbPlanPlot(plots);
			if (pants.size() > 0) {
				// 插入地块人员信息
				tbPlanParticipantMapper.insertBatchTbPlanParticipant(pants);
			}

			// 创建使用农机记录(普通计划添加，智能计划满足条件后添加) 完成后才有农机使用记录
			if (StringUtils.isNotEmpty(param.getMachineId()) && null == param.getImplement() && param.getPlanStatus() == NormalOrDeleteEnum.CLOSE.getCode()) {
				String machineIds[] = param.getMachineId().split(",");
				TbFarmingType ft = tFarmingTypeMapper.selectTbFarmingTypeById(param.getFarmingTypeId());
				for (String machineId : machineIds) {
					TbUseRecord ur = new TbUseRecord();
					ur.setPlan(ft.getFarmingTypeName());
					ur.setMachineId(Long.parseLong(machineId));
					ur.setPerson(StringUtils.isNotEmpty(userName) ? userName.substring(1) : "");
					long t1 = (costTime.getTime() - startTime.getTime()) / 86400000;
					long t2 = (costTime.getTime() - startTime.getTime()) % 86400000;
					if ((int) t1 == 0) {
						ur.setCostTime((int) t2 / 3600000 + "小时");
					} else {
						ur.setCostTime(t1 + "天");
					}
					tUseRecordMapper.insertTbUseRecord(ur);
				}
			}
		} else {
			if (param.getUpdateType() == 1) {// 修改 执行条件
				if (null != param.getImplement()) {
					// 插入执行条件表
					TbFarmingPlanImplement implement = param.getImplement();
					implement.setPlanId(param.getPlanId());
					// 先删除
					tbFarmingPlanImplementMapper.deleteTbFarmingPlanImplementByPlanId(param.getPlanId());
					// 然后重新保存
					tbFarmingPlanImplementMapper.insertTbFarmingPlanImplement(implement);
				}
				if (param.getWorkHours() != null) {
					// 设置计划主表数据
					TbFarmingPlan plan = new TbFarmingPlan();
					plan.setPlanId(param.getPlanId());
					plan.setWorkHours(param.getWorkHours());
					tbFarmingPlanMapper.updateTbFarmingPlan(plan);
				}
			} else if (param.getUpdateType() == 2 && !ValidationUtil.isEmpty(param.getMaterials()) && param.getMaterials().size() > 0) {// 修改 物品
				// 插入使用物品表数据
				List<TbPlanMaterial> materials = param.getMaterials();
				for (TbPlanMaterial tbPlanMaterial : materials) {
					tbPlanMaterial.setPlanId(param.getPlanId());
				}
				// 先删除
				tbPlanMaterialMapper.deleteTbPlanMaterialByPlanId(param.getPlanId());
				// 然后重新保存
				tbPlanMaterialMapper.insertBatchTbPlanMaterial(materials);
			} else if (param.getUpdateType() == 3 && !ValidationUtil.isEmpty(param.getImgs()) && param.getImgs().size() > 0) {
				// 插入计划对应的图片表
				List<TbPlanImg> imgs = param.getImgs();
				for (TbPlanImg tbPlanImg : imgs) {
					tbPlanImg.setPlanId(param.getPlanId());
				}
				// 先删除
				tbPlanImgMapper.deleteTbPlanImgByPlanId(param.getPlanId());
				// 然后重新保存
				tbPlanImgMapper.insertBatchTbPlanImg(imgs);
			} else if (param.getUpdateType() == 4 && !ValidationUtil.isEmpty(param.getPantUsers()) && param.getPantUsers().size() > 0) {
				List<AddPloatUserParam> ps = param.getPantUsers();
				// 跟新人员
				List<TbPlanParticipant> pants = Lists.newArrayList();
				for (AddPloatUserParam userParam : ps) {
					if (userParam.getResponserId() != null) {
						TbPlanParticipant tbPlanParticipant = new TbPlanParticipant();
						tbPlanParticipant.setPlanId(param.getPlanId());
						tbPlanParticipant.setSubPlotId(userParam.getSubPlotId());
						tbPlanParticipant.setResponserId(userParam.getResponserId());
						pants.add(tbPlanParticipant);
					}

					List<String> us = Lists.newArrayList(userParam.getParticipantIds().split(","));
					for (String uid : us) {
						if (StringUtils.isEmpty(uid)) {
							continue;
						}
						TbPlanParticipant tbPlanParticipant = new TbPlanParticipant();
						tbPlanParticipant.setPlanId(param.getPlanId());
						tbPlanParticipant.setSubPlotId(userParam.getSubPlotId());
						tbPlanParticipant.setParticipantId(Long.parseLong(uid));
						pants.add(tbPlanParticipant);
					}
					tbPlanParticipantMapper.deleteTbPlanParticipantBySubPlotId(userParam.getSubPlotId(), param.getPlanId());
				}
				if (pants.size() > 0) {
					// 插入地块人员信息
					tbPlanParticipantMapper.insertBatchTbPlanParticipant(pants);
				}

			} else if (param.getUpdateType() == 5) {
				// 修改
				// 设置计划主表数据
				TbFarmingPlan plan = new TbFarmingPlan();
				BeanUtils.copyProperties(param, plan);
				plan.setUpdateTime(new Date());
				plan.setUpdateBy(param.getUpdateBy());
				tbFarmingPlanMapper.updateTbFarmingPlan(plan);
			}
		}
	}

	/**
	 * 重复 ，完成计划
	 * 
	 * @param fpInfo 农事计划（普通计划）信息
	 * @return 结果
	 */
	@Transactional
	public synchronized void planComplete(TbFarmingPlan fpInfo, Long planId) {
		tbFarmingPlanMapper.insertTbFarmingPlan(fpInfo);
		// 复制物品使用表
		tbPlanMaterialMapper.copyPlanMaterial(planId, fpInfo.getPlanId());
		// 复制图片关联表
		tbPlanImgMapper.copyPlanImg(planId, fpInfo.getPlanId());

		// 复制地块关联表
		tbPlanPlotMapper.copyPlanPloat(planId, fpInfo.getPlanId(), null);
		// 复制人员关联表
		tbPlanParticipantMapper.copyPlanParticipant(planId, fpInfo.getPlanId(), null);

		TbFarmingPlan f = new TbFarmingPlan();
		f.setPlanId(planId);
		f.setPlanStatus(NormalOrDeleteEnum.CLOSE.getCode());
		f.setUpdateBy(fpInfo.getUpdateBy());
		f.setUpdateTime(fpInfo.getUpdateTime());
		f.setFarmId(fpInfo.getFarmId());
		updateTbFarmingPlan(f);

		// 创建使用农机记录
		if (StringUtils.isNotEmpty(fpInfo.getMachineId())) {
			String machineIds[] = fpInfo.getMachineId().split(",");
			TbFarmingType ft = tFarmingTypeMapper.selectTbFarmingTypeById(fpInfo.getFarmingTypeId());
			for (String machineId : machineIds) {
				TbUseRecord ur = new TbUseRecord();
				ur.setPlan(ft.getFarmingTypeName());
				ur.setMachineId(Long.parseLong(machineId));

				long t1 = (fpInfo.getEndTime().getTime() - fpInfo.getStartTime().getTime()) / 86400000;
				long t2 = (fpInfo.getEndTime().getTime() - fpInfo.getStartTime().getTime()) % 86400000;
				if ((int) t1 == 0) {
					ur.setCostTime((int) t2 / 3600000 + "小时");
				} else {
					ur.setCostTime(t1 + "天");
				}

				// 目前一个子地块 对应一个计划
				TbPlanParticipant tbPlanParticipant = new TbPlanParticipant();
				tbPlanParticipant.setPlanId(planId);
				List<TbPlanParticipant> ppList = tbPlanParticipantMapper.selectTbPlanParticipantList(tbPlanParticipant);
				String userName = "";
				if (ppList != null && ppList.size() > 0) {
					for (TbPlanParticipant pp : ppList) {
						if (pp.getResponserId() != null) {
							// 插入农机人员
							TbUser tb = tUserMapper.selectVbUserByCode(pp.getResponserId(), fpInfo.getFarmId());
							userName = userName + (tb != null ? ("," + tb.getNickName()) : "");
						}
						if (pp.getParticipantId() != null) {
							// 插入农机人员
							TbUser tb = tUserMapper.selectVbUserByCode(pp.getResponserId(), fpInfo.getFarmId());
							userName = userName + (tb != null ? ("," + tb.getNickName()) : "");
						}
					}
				}
				ur.setPerson(StringUtils.isNotEmpty(userName) ? userName.substring(1) : "");
				tUseRecordMapper.insertTbUseRecord(ur);
			}
		}
	}

	/**
	 * 修改农事计划（普通计划，智能计划）
	 * 
	 * @param tbFarmingPlan 农事计划（普通计划，智能计划）信息
	 * @return 结果
	 */
	@Transactional
	public int updateTbFarmingPlan(TbFarmingPlan tbFarmingPlan) {
		if (tbFarmingPlan.getPlanStatus() != null && tbFarmingPlan.getPlanStatus() == 2) {
			// 插入新消息
			tbMessageRecordService.save(tbFarmingPlan.getUpdateBy(), null, null, tbFarmingPlan.getPlanId(), tbFarmingPlan.getFarmId());
			tbFarmingPlan.setFarmId(null);// 农场ID 不更新 数据

			TbFarmingPlan plan = tbFarmingPlanMapper.selectTbFarmingPlanById(tbFarmingPlan.getPlanId());
			// 创建使用农机记录 (普通计划)
			if (StringUtils.isNotEmpty(plan.getMachineId()) && plan.getIsLntelligence() == NormalOrDeleteEnum.NORMAL.getCode()) {
				String machineIds[] = plan.getMachineId().split(",");
				TbFarmingType ft = tFarmingTypeMapper.selectTbFarmingTypeById(plan.getFarmingTypeId());
				for (String machineId : machineIds) {
					TbUseRecord ur = new TbUseRecord();
					ur.setPlan(ft.getFarmingTypeName());
					ur.setMachineId(Long.parseLong(machineId));

					long t1 = (tbFarmingPlan.getUpdateTime().getTime() - plan.getStartTime().getTime()) / 86400000;
					long t2 = (tbFarmingPlan.getUpdateTime().getTime() - plan.getStartTime().getTime()) % 86400000;
					if ((int) t1 == 0) {
						ur.setCostTime((int) t2 / 3600000 + "小时");
					} else {
						ur.setCostTime(t1 + "天");
					}

					// 目前一个子地块 对应一个计划
					TbPlanParticipant tbPlanParticipant = new TbPlanParticipant();
					tbPlanParticipant.setPlanId(tbFarmingPlan.getPlanId());
					List<TbPlanParticipant> ppList = tbPlanParticipantMapper.selectTbPlanParticipantList(tbPlanParticipant);
					String userName = "";
					if (ppList != null && ppList.size() > 0) {
						for (TbPlanParticipant pp : ppList) {
							if (pp.getResponserId() != null) {
								// 插入农机人员
								TbUser tb = tUserMapper.selectVbUserByCode(pp.getResponserId(), plan.getFarmId());
								userName = userName + (tb != null ? ("," + tb.getNickName()) : "");
							}
							if (pp.getParticipantId() != null) {
								// 插入农机人员
								TbUser tb = tUserMapper.selectVbUserByCode(pp.getResponserId(), plan.getFarmId());
								userName = userName + (tb != null ? ("," + tb.getNickName()) : "");
							}
						}
					}
					ur.setPerson(StringUtils.isNotEmpty(userName) ? userName.substring(1) : "");
					tUseRecordMapper.insertTbUseRecord(ur);
				}
			}
		}
		return tbFarmingPlanMapper.updateTbFarmingPlan(tbFarmingPlan);
	}

	/**
	 * 删除农事计划（普通计划，智能计划）对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteTbFarmingPlanByIds(String ids) {
		return tbFarmingPlanMapper.deleteTbFarmingPlanByIds(Convert.toStrArray(ids));
	}

	/**
	 * 达到条件后将智能计划转换成普通计划
	 */
	@Override
	@Transactional
	public AjaxResult changePlain(Long implementId) {
		// 校验入参是否为空
		if (null == implementId) {
			return AjaxResult.error("参数不能为空");
		}
		// 查询执行计划数据
		TbFarmingPlanImplement im = tbFarmingPlanImplementMapper.selectTbFarmingPlanImplementById(implementId);
//		if (null == im || im.getStatus() != 0) {
//			return AjaxResult.error("不存在可执行计划");
//		}
		// 查询农事计划主表
		TbFarmingPlan plan = tbFarmingPlanMapper.selectTbFarmingPlanById(im.getPlanId());
		if (null == plan) {
			return AjaxResult.error("不存在智能计划");
		} else if (plan.getPlanStatus() != 0) {
			return AjaxResult.error("不存在可执行计划");
		}
		Long dates = plan.getStartTime().getTime() / 86400000;
		Long times = plan.getStartTime().getTime() % 86400000;
		Long datee = plan.getEndTime().getTime() / 86400000;
		Long timee = plan.getEndTime().getTime() % 86400000;
		Integer planRepeat = plan.getPlanRepeat();
		Long planId = plan.getPlanId();
		// 将添加方式改为智能计划添加
		plan.setPlanType(PlanTypeEnum.ORDINARY.getCode());
		plan.setIsLntelligence(0);
		// 执行时间为当前时间，结束时间为当前直接加上工时
		Date startDate = DateUtils.getNowDate();
		plan.setStartTime(startDate);
		plan.setEndTime(DateUtils.addDateHour(startDate, plan.getWorkHours()));
		plan.setPlanId(null);
		plan.setCreateTime(new Date());
		plan.setPlanRepeat(0);
		tbFarmingPlanMapper.insertTbFarmingPlan(plan);
		Long newPlanId = plan.getPlanId();
		// 复制地块关联表
		tbPlanPlotMapper.copyPlanPloat(planId, newPlanId, null);
		// 复制人员关联表
		tbPlanParticipantMapper.copyPlanParticipant(planId, newPlanId, null);
		// 复制物品使用表
		tbPlanMaterialMapper.copyPlanMaterial(planId, newPlanId);
		// 复制图片关联表
		tbPlanImgMapper.copyPlanImg(planId, newPlanId);
		// 是否给相关人员推送消息数据
		// 将计划数据状态变成已经执行
		TbFarmingPlanImplement up = new TbFarmingPlanImplement();
		up.setId(im.getId());
		up.setStatus(2);
		tbFarmingPlanImplementMapper.updateTbFarmingPlanImplement(up);

		if (planRepeat != null && planRepeat == 1) {// 如果是选择 重复 修改时间
			TbFarmingPlan planUp = new TbFarmingPlan();
			planUp.setPlanId(im.getPlanId());
			planUp.setStartTime(new Date((datee + plan.getPlanRepeatData()) * 86400000 + times));
			planUp.setEndTime(new Date((datee + datee + plan.getPlanRepeatData() - dates) * 86400000 + timee));
			// 如果是重复， 调整开始时间 结束时间
			updateTbFarmingPlan(planUp);
		} else {// 如果是选择 不重复 直接完成
				// 将主表数据状态也变成已经执行
			TbFarmingPlan planUp = new TbFarmingPlan();
			planUp.setPlanId(planId);
			planUp.setPlanStatus(2);
			planUp.setUpdateTime(new Date());
			updateTbFarmingPlan(planUp);
		}

		return AjaxResult.success();
	}

	/**
	 * 添加标准农事计划
	 */
	@Override
	@Transactional
	public AjaxResult addStandardPlan(AddStandardPlanParam param) {
		// 1.查询地块的种植时间 、负责人（根据子地块ID批量查询种植时间）
		Set<Long> subPlotIds = Sets.newHashSet();
		for (AddPloatUserParam ploatUser : param.getPloats()) {
			subPlotIds.add(ploatUser.getSubPlotId());
		}
		List<TbPlantingVo> plantIngList = tbPlantingMapper.selectListBySubPlotIds(subPlotIds, param.getFarmId());

		if (subPlotIds.size() != plantIngList.size()) {
			return AjaxResult.error("添加失败:地块种植信息错误");
		}
		// 2.然后根据流程ID获取数据
		List<Long> farmingIds = param.getFarmingIds();
		List<TbModelPeriodFarming> list = tbModelPeriodFarmingMapper.selectTbModelPeriodFarmingListByIds(farmingIds);
		tbModelMapper.updateTbModelByModelUseTime(farmingIds);// 使用模型次数 +1
		String u = "";
		// 3.然后组装普通计划数据
		for (TbPlantingVo tbPlantingVo : plantIngList) {
			for (TbModelPeriodFarming pf : list) {
				// 4.插入计划表，插入地块关联表
				TbFarmingPlan plan = new TbFarmingPlan();
				plan.setPlanType(PlanTypeEnum.STANDARD.getCode());
				plan.setFarmingTypeId(pf.getFarmingTypeId());
				plan.setRemark(pf.getFarmingRequirements());
				plan.setDataId(tbPlantingVo.getPlantingId());// 种植ID
				plan.setCreateBy(param.getCreateBy());
				plan.setFarmId(param.getFarmId());
				// 根据种植时间和类型判断农事计划开始时间
				int plantDay = pf.getPlantDay();
				if (pf.getPlantType().equals(PlotConstants.PLANT_TYPE_FRONT)) {
					plantDay = plantDay * -1;
				}
				Date startDate = DateUtils.addDateDay(tbPlantingVo.getPlantTime(), plantDay);
				// 开始时间为 00:00:00 结束时间为23:59:59
				Date startTime = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, (DateUtils.dateTime(startDate) + " 00:00:00"));
				plan.setStartTime(startTime);
				Date endTime = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, (DateUtils.dateTime(startDate) + " 23:59:59"));
				plan.setEndTime(endTime);
				tbFarmingPlanMapper.insertTbFarmingPlan(plan);
				// 地块与农机计划关联表
				Long planId = plan.getPlanId();
				TbPlanPlot tbPlanPlot = new TbPlanPlot();
				tbPlanPlot.setPlanId(planId);
				tbPlanPlot.setSubPlotId(tbPlantingVo.getSubPlotId());
				tbPlanPlot.setPlotId(tbPlantingVo.getPlotId());
				tbPlanPlotMapper.insertTbPlanPlot(tbPlanPlot);

				if (tbPlantingVo.getSubPlotUserId() != null) {
					// 新增农事任务人员表
					TbPlanParticipant tbPlanParticipant = new TbPlanParticipant();
					tbPlanParticipant.setPlanId(planId);
					tbPlanParticipant.setSubPlotId(tbPlantingVo.getSubPlotId());
					tbPlanParticipant.setResponserId(tbPlantingVo.getSubPlotUserId());
					tbPlanParticipantMapper.insertTbPlanParticipant(tbPlanParticipant);
					if (u.indexOf("," + tbPlantingVo.getSubPlotUserId() + "" + pf.getFarmingTypeId() + ",") == -1) {
						u += "," + tbPlantingVo.getSubPlotUserId() + "" + pf.getFarmingTypeId() + ",";
						// 插入新消息
						tbMessageRecordService.save(param.getCreateBy(), tbPlantingVo.getSubPlotUserId(), pf.getFarmingTypeId(), null, param.getFarmId());
					}
				}
			}
		}

		return AjaxResult.success();
	}

	@Override
	public FarmingPlanListVo queryList(FarmingPlanListParam param) {
		FarmingPlanListVo returnVo = new FarmingPlanListVo();

		// 查询已种植的地块信息
		List<TbPlotVo> plotList = tbPlotMapper.selectTbPlotResultBySubPlot(param.getFarmId(), 0);

		// 组装地块信息
		List<FarmingPlanPloatVo> planPloatList = Lists.newArrayList();

		Set<Long> subPlotIds = Sets.newHashSet();
		for (TbPlotVo tbPlotVo : plotList) {
			if (null != tbPlotVo.getSubPlot()) {
				for (TbSubPlot tbSubPlot : tbPlotVo.getSubPlot()) {
					FarmingPlanPloatVo plan = new FarmingPlanPloatVo();
					plan.setPlotId(tbPlotVo.getPlotId());
					plan.setPlotName(tbPlotVo.getPlotName());
					plan.setSubPlotName(tbSubPlot.getSubPlotName());
					plan.setSubPlotId(tbSubPlot.getSubPlotId());
					planPloatList.add(plan);
					subPlotIds.add(tbSubPlot.getSubPlotId());
				}
			}
		}
		if (subPlotIds.size() == 0) {
			return returnVo;
		}
		// 查询子地块列表下的所有农事计划列表，并将数量统计
		List<FarmingPlanVo> farmingPlanList = tbFarmingPlanMapper.queryListByDate(subPlotIds, param);
		// 数据子地块计划分组并且更新状态和统计数量
		if (null != farmingPlanList && farmingPlanList.size() > 0) {
			Map<String, List<FarmingPlanVo>> map = Maps.newHashMap();
			/** 全部计划数量 */
			Integer allPlansNum = farmingPlanList.size();
			/** 待指派数量 */ // 待指派需要根据接口额外查询 // 查询人员为空的数据
			Integer assignPlansNum = 0;
			/** 完成计划数量 */
			Integer overPlansNum = 0;
			/** 逾期计划数量 */
			Integer overduePlansNum = 0;
			Date nowDate = new Date();
			for (FarmingPlanVo tbFarmingPlan : farmingPlanList) {
				String subPlotIdStr = String.valueOf(tbFarmingPlan.getSubPlotId());
				List<FarmingPlanVo> list = map.get(subPlotIdStr);
				if (null == list) {
					list = Lists.newArrayList();
				}
				// 如果是重复的要判断今天是否已经完成

				// 0待完成(当前时间还没到)，1待完成(当前时间在时间段内，绿色), 2已经完成 , 3已逾期
				// 当状态为0时 需要判断是0,1,3
				if (tbFarmingPlan.getPlanStatus() == 2) {
					overPlansNum++;
				}
				if (tbFarmingPlan.getPlanStatus() == 0) {
					// 当前时间大于结束时间 ， 已经逾期 3
					if (DateUtils.comperDate(tbFarmingPlan.getEndTime(), nowDate)) {
						tbFarmingPlan.setPlanStatus(3);
						overduePlansNum++;
					}
					// 当前时间小于开始时间 ，时间还没到 1
					if (DateUtils.comperDate(nowDate, tbFarmingPlan.getStartTime())) {
						tbFarmingPlan.setPlanStatus(1);
					}
				}

				list.add(tbFarmingPlan);
				map.put(subPlotIdStr, list);
			}
			returnVo.setAllPlansNum(allPlansNum);
			returnVo.setAssignPlansNum(assignPlansNum);
			returnVo.setOverduePlansNum(overduePlansNum);
			returnVo.setOverPlansNum(overPlansNum);
			// 将计划信息赋值给地块信息
			for (FarmingPlanPloatVo farmingPlanPloatVo : planPloatList) {
				String subPlotIdStr = String.valueOf(farmingPlanPloatVo.getSubPlotId());
				List<FarmingPlanVo> list = map.get(subPlotIdStr);
				if (null != list) {
					farmingPlanPloatVo.setPlans(list);
				}
			}
		}
		returnVo.setPloats(planPloatList);
		return returnVo;
	}

	/**
	 * 农事计划列表
	 * 
	 * @param fpsv
	 * @return
	 */
	public HashMap<String, Object> selectBytjList(FarmingPlanSpecialVo fpsv) {
		HashMap<String, Object> map = new HashMap<>();

		List<FarmingPlanSpecialVo> fpsvList = tbFarmingPlanMapper.selectBytjList(fpsv);
		map.put("fpsvList", fpsvList);

		Integer allPlansNum = 0;// 全部计划数量
		Integer assignPlansNum = 0;// 待指派数量 待指派需要根据接口额外查询 // 查询人员为空的数据
		Integer overPlansNum = 0;// 完成计划数量
		Integer overduePlansNum = 0;// 逾期计划数量
		if (fpsvList != null && fpsvList.size() > 0) {
			for (FarmingPlanSpecialVo f : fpsvList) {
				f.setCompleteCou(0);
				f.setTotalCou(0);
				if (f.getFp() != null && f.getFp().size() > 0) {
					f.setTotalCou(f.getFp().size());
					for (TbFarmingPlanVo fp : f.getFp()) {
						f.setCompleteCou(fp.getPlanStatus() == 2 ? f.getCompleteCou() + 1 : f.getCompleteCou());
						allPlansNum += 1;
						if (fp.getHangAir() == null || fp.getHangAir() == 0) {
							assignPlansNum += 1;
						}
						if (fp.getPlanStatus() == 2) {
							overPlansNum += 1;
						}

						// 0待完成(当前时间还没到)，1待完成(当前时间在时间段内，绿色), 2已经完成 , 3已逾期
						if (fp.getPlanStatus() == 0) {
							Date d = new Date();
							if (d.getTime() < fp.getStartTime().getTime()) {
								fp.setPlanStatus(0);
							} else if (d.getTime() < fp.getEndTime().getTime()) {
								fp.setPlanStatus(1);
							} else {
								fp.setPlanStatus(3);
								overduePlansNum += 1;
							}
						}
					}
				}
			}
		}
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("allPlansNum", allPlansNum);
		map1.put("assignPlansNum", assignPlansNum);
		map1.put("overPlansNum", overPlansNum);
		map1.put("overduePlansNum", overduePlansNum);
		map.put("statistics", map1);
		return map;
	}

	/**
	 * 计划详情
	 * 
	 * @param planId
	 * @return
	 */
	public HashMap<String, Object> getInfo(Long planId, Long subPlotId) {
		HashMap<String, Object> map = Maps.newHashMap();
		// 查询农事计划
		TbFarmingPlan fp = tbFarmingPlanMapper.selectTbFarmingPlanById(planId);
		TbFarmingPlanVo fpVo = new TbFarmingPlanVo();
		BeanUtils.copyProperties(fp, fpVo);
		if (StringUtils.isNotEmpty(fp.getMachineId())) {
			String tMachineIds[] = fp.getMachineId().split(",");
			fpVo.setMachineIds(tMachineIds);
			String tMachineNames[] = new String[tMachineIds.length];
			for (int i = 0; i < tMachineIds.length; i++) {
				TbAgriculturalMachine am = tbAgriculturalMachineMapper.selectTbAgriculturalMachineById(Long.parseLong(tMachineIds[i]));
				if (am != null) {
					tMachineNames[i] = am.getName();
				} else {
					tMachineIds[i] = null;
				}
			}
			fpVo.setMachineNames(tMachineNames);
		}
		// 根据农事计划ID查询 负责人 和参与者
		map.put("planParticipant", tbPlanParticipantMapper.selectByPlanParticipantList(fp.getFarmId(), planId, subPlotId));
		// 根据农事计划ID查询 地块ID
		map.put("pantUsers", tbPlanPlotService.selectByPlanIdGetPlotId(planId));

		fpVo.setMachineId(null);
		map.put("fp", fpVo);

		// 查询农事计划图片
		TbPlanImg tbPlanImg = new TbPlanImg();
		tbPlanImg.setPlanId(planId);
		map.put("fpImg", tbPlanImgMapper.selectTbPlanImgList(tbPlanImg));

		// 查询农事计划物品
		TbPlanMaterialVo tbPlanMaterial = new TbPlanMaterialVo();
		tbPlanMaterial.setPlanId(planId);
		map.put("fpMaterial", tbPlanMaterialMapper.selectByPlanIdList(tbPlanMaterial));

		// 判断是否为智能计划 1是
		if (fp.getIsLntelligence() == 1) {
			// 查询可执行条件
			TbFarmingPlanImplement tbFarmingPlanImplement = new TbFarmingPlanImplement();
			tbFarmingPlanImplement.setPlanId(planId);
			map.put("fpPlanImplement", tbFarmingPlanImplementMapper.selectTbFarmingPlanImplementList(tbFarmingPlanImplement));
		} else {
			// 如果不是智能计划，查询聊天信息
			List<TbPlanMessageVo> pmList = tbPlanMessageMapper.selectByChatRecordList(planId, fp.getFarmId());
			if (pmList != null && pmList.size() > 0) {
				pmList.forEach(p -> {
					if (StringUtils.isNotEmpty(p.getImgUrl())) {
						p.setImgUrls(p.getImgUrl().split(","));
					}
					p.setImgUrl(null);
				});
			}
			map.put("fpChatRecord", pmList);
		}

		return map;
	}

	/**
	 * 今天计划
	 * 
	 * @param tbFarmingPlanVo
	 * @return
	 */
	public List<TbFarmingPlanVo> selectByTodayList(TbFarmingPlanVo tbFarmingPlanVo) {
		List<TbFarmingPlanVo> fpList = tbFarmingPlanMapper.selectByTodayList(tbFarmingPlanVo);
		if (fpList != null && fpList.size() > 0) {
			fpList.forEach(fp -> {
				// 0待完成(当前时间还没到)，1待完成(当前时间在时间段内，绿色), 2已经完成 , 3已逾期
				if (fp.getPlanStatus() == 0) {
					Date d = new Date();
					if (d.getTime() < fp.getStartTime().getTime()) {
						fp.setPlanStatus(0);
					} else if (d.getTime() < fp.getEndTime().getTime()) {
						fp.setPlanStatus(1);
					} else {
						fp.setPlanStatus(3);
					}
				}
			});
		}
		return fpList;
	}

	/**
	 * 全部计划
	 * 
	 * @param tbFarmingPlanVo
	 * @return
	 */
	public List<TbFarmingTypeVo> selectByAllList(TbFarmingPlanVo tbFarmingPlanVo) {
		List<TbFarmingTypeVo> res = tbFarmingPlanMapper.selectByAllList(tbFarmingPlanVo);
		if (res != null && res.size() > 0) {
			res.forEach(t -> {
				t.setTotalCou(0);
				t.setCompleteCou(0);
				if (t.getFp() != null && t.getFp().size() > 0) {
					t.setTotalCou(t.getFp().size());
					t.getFp().forEach(f -> {
						t.setCompleteCou(f.getPlanStatus() == 2 ? t.getCompleteCou() + 1 : t.getCompleteCou());

						// 0待完成(当前时间还没到)，1待完成(当前时间在时间段内，绿色), 2已经完成 , 3已逾期
						if (f.getPlanStatus() == 0) {
							Date d = new Date();
							if (d.getTime() < f.getStartTime().getTime()) {
								f.setPlanStatus(0);
							} else if (d.getTime() < f.getEndTime().getTime()) {
								f.setPlanStatus(1);
							} else {
								f.setPlanStatus(3);
							}
						}
					});
				}
			});
		}
		return res;
	}

	/**
	 * 计划列表
	 * 
	 * @param tbFarmingPlanVo
	 * @return
	 */
	public List<TbFarmingPlanVo> selectByPlanList(TbFarmingPlanVo tbFarmingPlanVo) {
		List<TbFarmingPlanVo> fpList = tbFarmingPlanMapper.selectByPlanList(tbFarmingPlanVo);
		if (fpList != null && fpList.size() > 0) {
			fpList.forEach(fp -> {
				// 0待完成(当前时间还没到)，1待完成(当前时间在时间段内，绿色), 2已经完成 , 3已逾期
				if (fp.getPlanStatus() == 0) {
					Date d = new Date();
					if (d.getTime() < fp.getStartTime().getTime()) {
						fp.setPlanStatus(0);
					} else if (d.getTime() < fp.getEndTime().getTime()) {
						fp.setPlanStatus(1);
					} else {
						fp.setPlanStatus(3);
					}
				}
			});
		}
		return fpList;
	}

	/**
	 * 农事记录 列表
	 * 
	 * @param farmingRecordVo
	 * @return
	 */
	public List<FarmingRecordVo> selectByLsRecordList(FarmingRecordVo farmingRecordVo) {
		List<FarmingRecordVo> frList = tbFarmingPlanMapper.selectByLsRecordList(farmingRecordVo);
		if (frList != null && frList.size() > 0) {
			frList.forEach(f -> {
				String user = "";
				String name = "";
				List<HashMap<String, Object>> userList = Lists.newArrayList();
				f.setUsers(userList);
				if (StringUtils.isNotEmpty(f.getUserName())) {
					String[] users = f.getUserName().split(";");
					for (String u : users) {
						if (StringUtils.isNotEmpty(u) && !user.contains(u)) {
							user += u;
							HashMap<String, Object> m1 = Maps.newHashMap();
							m1.put("userName", u.split("\\|")[0]);
							name += "," + u.split("\\|")[0];
							m1.put("headUrl", u.indexOf("|") >= 0 ? u.split("\\|")[1] : null);
							userList.add(m1);
						}
					}
					f.setUserName(StringUtils.isNotEmpty(name) ? name.substring(1) : null);
				}

//				 农博的工时计算
//				 普通计划完成后产生的工时：
//				 1、若计划在时间范围内完成，自计划开始时间到完成时间计算工时，工时若小于24小时，则工时按小时计算，如23.4计算为23；若工时大于一天，则工时按天数计算，如1.5天算1天
//				 2、逾期的计划完成后，工时时间为计划结束时间-开始时间，结果小于24小时，按小时计算，若大于24小时，按天数算
//				 3、农事记录的工时计算参考普通计划
				long w = f.getUpdateTime() == null || f.getEndTime().getTime() < f.getUpdateTime().getTime() || f.getUpdateTime().getTime() < f.getStartTime().getTime() ? f.getEndTime().getTime()
						: f.getUpdateTime().getTime();
				long s = f.getStartTime().getTime();
				long t1 = (w - s) / 86400000;
				long t2 = (w - s) % 86400000;
				if ((int) t1 == 0) {
					f.setWorkingHours((int) t2 / 3600000 + "小时");
				} else {
					f.setWorkingHours(t1 + "天");
				}

				// 0待完成(当前时间还没到)，1待完成(当前时间在时间段内，绿色), 2已经完成 , 3已逾期
				if (f.getPlanStatus() == 0) {
					Date d = new Date();
					if (d.getTime() < f.getStartTime().getTime()) {
						f.setPlanStatus(0);
					} else if (d.getTime() < f.getEndTime().getTime()) {
						f.setPlanStatus(1);
					} else {
						f.setPlanStatus(3);
					}
				}

			});
		}
		return frList;
	}

	/**
	 * 农事记录 头部汇总
	 * 
	 * @param farmingRecordVo
	 * @return
	 */
	public HashMap<String, Object> selectByStatisticsCou(FarmingRecordVo farmingRecordVo) {
		List<FarmingRecordVo> fr = tbFarmingPlanMapper.selectByStatisticsCou(farmingRecordVo);
		HashMap<String, Object> m2 = Maps.newHashMap();
		m2.put("sfTotal", 0);
		m2.put("yyTotal", 0);
		m2.put("lzTotal", 0);
		if (fr != null && fr.size() > 0) {
			for (FarmingRecordVo f : fr) {
				// 记录类型（1施肥记录，2用药记录，3劳作记录）
				switch (f.getTpyeRecord()) {
				case 1:
					m2.put("sfTotal", f.getMsgCou());
					break;
				case 2:
					m2.put("yyTotal", f.getMsgCou());
					break;
				case 3:
					m2.put("lzTotal", f.getMsgCou());
					break;
				}
			}
		}
		return m2;
	}

	/**
	 * 农事记录 总条数
	 * 
	 * @param farmingRecordVo
	 * @return
	 */
	public int selectByLsRecordCou(FarmingRecordVo farmingRecordVo) {
		return tbFarmingPlanMapper.selectByLsRecordCou(farmingRecordVo);
	}

	/**
	 * 智能计划列表
	 * 
	 * @param tbFarmingPlanVo
	 * @return
	 */
	public List<FarmingRecordVo> selectBySmartPlanList(TbFarmingPlanVo tbFarmingPlanVo) {
		List<FarmingRecordVo> res = tbFarmingPlanMapper.selectBySmartPlanList(tbFarmingPlanVo);
		if (res != null && res.size() > 0) {
			for (FarmingRecordVo f : res) {
				String user = "";
				List<HashMap<String, Object>> userList = Lists.newArrayList();
				f.setUsers(userList);
				if (StringUtils.isNotEmpty(f.getUserName())) {
					String[] users = f.getUserName().split(";");
					for (String u : users) {
						if (StringUtils.isNotEmpty(u) && !user.contains(u)) {
							user += u;
							HashMap<String, Object> m1 = Maps.newHashMap();
							m1.put("userName", u.split("\\|")[0]);
							m1.put("headUrl", u.indexOf("|") > 1 ? u.split("\\|")[1] : null);
							m1.put("planUserId", u.split("\\|").length > 2 ? u.split("\\|")[2] : null);
							m1.put("userCode", u.split("\\|").length > 3 ? u.split("\\|")[3] : null);
							userList.add(m1);
						}
					}
					f.setUserName(null);
				}
				if (StringUtils.isNotEmpty(f.getDevName())) {
					// 条件 0 大于 1大于等于 2小于 3小于等于 4 等于
					switch (f.getConditionVar()) {
					case 0:
						f.setDevName(f.getDevName() + "大于" + f.getDataVar());
						break;
					case 1:
						f.setDevName(f.getDevName() + "大于等于" + f.getDataVar());
						break;
					case 2:
						f.setDevName(f.getDevName() + "小于" + f.getDataVar());
						break;
					case 3:
						f.setDevName(f.getDevName() + "小于等于" + f.getDataVar());
						break;
					case 4:
						f.setDevName(f.getDevName() + "等于" + f.getDataVar());
						break;
					default:
						f.setDevName("");
						break;
					}
				} else {
					f.setDevName("设备已移除");
				}
			}
		}
		return res;
	}

	/**
	 * 查询未调用过的 且在传入的时间 是 在开启到结束时间内的 智能计划 （此方法在定时器里调用）
	 * 
	 * @return
	 */
	public List<TbFarmingPlanImplementVo> selectByIntelligentPlanningList() {
		String res = DateUtils.getTime();
		// 查询 是重复的 智能计划，且过了智能结束时间， 按重复天数推迟
		List<TbFarmingPlanImplementVo> fpiList = tbFarmingPlanImplementMapper.selectByIntelligentPlanningList(res, 2);
		if (fpiList != null && fpiList.size() > 0) {
			for (TbFarmingPlanImplementVo fpi : fpiList) {
				TbFarmingPlan planUp = new TbFarmingPlan();
				planUp.setPlanId(fpi.getPlanId());
				Long dates = fpi.getStartTime().getTime() / 86400000;
				Long times = fpi.getStartTime().getTime() % 86400000;
				Long datee = fpi.getEndTime().getTime() / 86400000;
				Long timee = fpi.getEndTime().getTime() % 86400000;
				planUp.setStartTime(new Date((datee + fpi.getPlanRepeatData()) * 86400000 + times));
				planUp.setEndTime(new Date((datee + datee + fpi.getPlanRepeatData() - dates) * 86400000 + timee));
				// 如果是重复， 调整开始时间 结束时间
				updateTbFarmingPlan(planUp);
			}
		}
		// 判断智能计划 未执行的 ， 并不重复的 假如到期 修改以完成
		tbFarmingPlanMapper.updateByFarmingPlanComplete(res);
		// 查询当前时间内，需要判断的智能计划
		fpiList = tbFarmingPlanImplementMapper.selectByIntelligentPlanningList(res, 1);
		return fpiList;
	}

	/**
	 * 今日任务数，今日完成任务数
	 * 
	 * @param plotId
	 * @return
	 */
	public HashMap<String, Object> selectByStatisticsToday(Long plotId) {
		List<HashMap<String, Object>> list = Lists.newArrayList();
		HashMap<String, Object> m = Maps.newHashMap();
		HashMap<String, Object> m1 = null;
		HashMap<String, Object> l = null;
		int date = 30;
		// 获取当前日期
		Calendar ca = Calendar.getInstance();

		boolean isHasOne = false;
		ca.add(Calendar.DATE, -date + 1);
		for (int i = 0; i < date; i++) {
			m1 = Maps.newHashMap();
			String mm = DateUtils.parseDateToStr("MM-dd", ca.getTime());
			m1.put("dateStr", mm);
			m1.put("total", 0);
			l = tbFarmingPlanMapper.selectByStatisticsToday(DateUtils.dateTime(ca.getTime()), plotId);
			if (l != null && l.get("total") != null) {
				m1.put("total", l.get("total"));
				if ((Long)l.get("total") > 0) {
					isHasOne = true;
				}
			}
			ca.add(Calendar.DATE, 1);
			list.add(m1);
		}
		if (!isHasOne) { //如果过去30天所有天的数据都为零则返回前端一个空的集合，方便展示
			list.clear();
		}
		m.put("list", list);

		Double todayTotal = l == null || l.get("total") == null ? 0d : Double.parseDouble(l.get("total").toString());
		Double todayCompleteTotal = l == null || l.get("todayTotal") == null ? 0d : Double.parseDouble(l.get("todayTotal").toString());
		m.put("todayTotal", todayTotal);
		if (todayCompleteTotal == 0) {
			m.put("completionRatio", "0%");
		} else {
			m.put("completionRatio", (todayTotal == 0 ? "0" : String.format("%.2f", todayCompleteTotal * 100 / todayTotal)) + "%");
		}

		return m;
	}

	/**
	 * 首页农事展示状况
	 * 
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String, Object>> selectByHomeList(String farmId) {
		return tbFarmingPlanMapper.selectByHomeList(farmId);
	}

	/**
	 * 首页任务完成情况
	 * 
	 * @param userId
	 * @return
	 */
	public HashMap<String, Object> selectByHomeTaskCompletion(Long userId, String farmId) {
		return tbFarmingPlanMapper.selectByHomeTaskCompletion(userId, farmId);
	}

	/**
	 * 根据农场ID 和 用户ID查询 任务
	 * 
	 * @param farmId
	 * @param userCode
	 * @return
	 */
	public int selectByFarmTask(String farmId, Long userCode) {
		return tbFarmingPlanMapper.selectByFarmTask(farmId, userCode);
	}

	/**
	 * H5详情页面 任务
	 * 
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String, Object>> selectByH5Task(String farmId) {
		List<HashMap<String, Object>> list = tbFarmingPlanMapper.selectByH5Task(farmId);
		if (list != null && list.size() > 0) {
			for (HashMap<String, Object> m : list) {
				List<HashMap<String, Object>> l = Lists.newArrayList();
				if (m.get("messageContent") != null && StringUtils.isNotEmpty(m.get("messageContent").toString())) {
					String messageContents[] = m.get("messageContent").toString().split(";");
					for (String messageContent : messageContents) {
						HashMap<String, Object> h = Maps.newHashMap();
						h.put("messageContent", messageContent.split("\\|")[0]);
						h.put("img", messageContent.split("\\|").length > 1 ? messageContent.split("\\|")[1].split(",") : "");
						l.add(h);
					}
				}
				m.put("message", l);
				m.remove("messageContent");
			}
		}
		return list;
	}
}
