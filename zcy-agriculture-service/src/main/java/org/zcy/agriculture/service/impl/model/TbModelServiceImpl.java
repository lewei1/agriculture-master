package org.zcy.agriculture.service.impl.model;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.constants.FarmingDictEnum;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.enums.ModelTypeEnum;
import org.zcy.agriculture.mapper.*;
import org.zcy.agriculture.mapper.model.TbModelMapper;
import org.zcy.agriculture.mapper.model.TbModelPeriodFarmingMapper;
import org.zcy.agriculture.mapper.model.TbModelPeriodMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.model.ModelCropCategoryParam;
import org.zcy.agriculture.param.model.ModelOverviewParam;
import org.zcy.agriculture.param.model.ModelDetailParam;
import org.zcy.agriculture.service.impl.BaseServiceImpl;
import org.zcy.agriculture.service.model.ITbModelService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作物模型 服务层实现
 *
 * @author numberone
 * @date 2019-06-26
 */
@Service
public class TbModelServiceImpl extends BaseServiceImpl implements ITbModelService {

    @Autowired
    private TbModelMapper tbModelMapper;

    @Autowired
    private TbModelPeriodMapper tbModelPeriodMapper;

    @Autowired
    private TbModelPeriodFarmingMapper tbModelPeriodFarmingMapper;

    @Autowired
    private TbCropCategoryMapper tbCropCategoryMapper;

    @Autowired
    private TbFarmingTypeMapper tbFarmingTypeMapper;

    /**
     * 查询作物模型信息
     *
     * @param modelId 作物模型ID
     * @return 作物模型信息
     */
    @Override
    public TbModel selectTbModelById(Long modelId) {
        return tbModelMapper.selectTbModelById(modelId);
    }

    /**
     * 查询作物模型列表
     *
     * @param tbModel 作物模型信息
     * @return 作物模型集合
     */
    @Override
    public List<TbModel> selectTbModelList(TbModel tbModel) {
        return tbModelMapper.selectTbModelList(tbModel);
    }

    /**
     * 新增作物模型
     *
     * @param tbModel 作物模型信息
     * @return 结果
     */
    @Override
    public int insertTbModel(TbModel tbModel) {
        return tbModelMapper.insertTbModel(tbModel);
    }

    /**
     * 修改作物模型
     *
     * @param tbModel 作物模型信息
     * @return 结果
     */
    @Override
    public int updateTbModel(TbModel tbModel) {
        return tbModelMapper.updateTbModel(tbModel);
    }

    /**
     * 删除作物模型对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbModelByIds(String ids) {
        return tbModelMapper.deleteTbModelByIds(Convert.toStrArray(ids));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertTbModelDetail(ModelDetailParam detailParam) throws Exception {
        int result;
        //主表
        try {
            TbModel tbModel = new TbModel();
            BeanUtils.copyBeanProp(tbModel, detailParam);
            result = tbModelMapper.insertTbModel(tbModel);
            if (result < 0)
                return result;
            //阶段表
            TbModelPeriod tbModelPeriod = new TbModelPeriod();
            List<TbModelPeriodAndFarmings> periodAndFarmingsList = detailParam.getPeriodAndFarmingsList();
            if (!ValidationUtil.isEmpty(periodAndFarmingsList)) {
                for (TbModelPeriodAndFarmings periodAndFarmings : periodAndFarmingsList) {
                    periodAndFarmings.setModelId(tbModel.getModelId());
                    BeanUtils.copyBeanProp(tbModelPeriod, periodAndFarmings);
                    result = tbModelPeriodMapper.insertTbModelPeriod(tbModelPeriod);
                    if (result < 0)
                        return result;
                    //阶段农事表
                    List<TbModelPeriodFarming> farmingList = periodAndFarmings.getFarmingList();
                    String regEx = "[^0-9]";
                    Pattern p = Pattern.compile(regEx);

                    if (!ValidationUtil.isEmpty(farmingList)) {
                        for (TbModelPeriodFarming farming : farmingList) {
                            farming.setPeriodId(tbModelPeriod.getPeriodId());

                            //定植前100天
                            String farmingTime = farming.getFarmingTime();
                            if (!ValidationUtil.isEmpty(farmingTime)) {
                                //解析农事时间farmingTime字符串
                                Matcher m = p.matcher(farmingTime);
                                //定植当天
                                if(farmingTime.trim().length() == 4 && "定植当天".equals(farmingTime.trim())) {
                                    farming.setPlantType(farmingTime);
                                    farming.setPlantDay(0);
                                }else {
                                    //定植前/后x天
                                    farming.setPlantType(farmingTime.substring(0, 3));
                                    farming.setPlantDay(Integer.valueOf(m.replaceAll("").trim()));
                                }
                            }
                            result = tbModelPeriodFarmingMapper.insertTbModelPeriodFarming(farming);
                            if (result < 0)
                                return result;
                        }
                    }
                }

            }
        } catch (Exception e) {
            throw new Exception();
        }
        return result;
    }


    @Override
    public ModelSelectDataVo selectAddPageList(String farmId) {
        ModelSelectDataVo vo = new ModelSelectDataVo();
        //作物种类
        TbCropCategory categoty = new TbCropCategory();
        categoty.setCategoryStatus(NormalOrDeleteEnum.NORMAL.getCode());
        categoty.setFarmId(farmId);
        List<TbCropCategory> cropCategoryList = tbCropCategoryMapper.selectTbCropCategoryList(categoty);
        if (!ValidationUtil.isEmpty(cropCategoryList)) {
            vo.setCropCategoryList(cropCategoryList);
        }
        //种植标准
        List<SysDictData> plantStandardList = getDictList(FarmingDictEnum.PLANT_STANDARD.getVal());
        if (!ValidationUtil.isEmpty(plantStandardList)) {
            vo.setPlantStandardList(plantStandardList);
        }
        //种植环境
        List<SysDictData> plantEnvironmentList = getDictList(FarmingDictEnum.PLANT_ENVIRONMENT.getVal());
        if (!ValidationUtil.isEmpty(plantEnvironmentList)) {
            vo.setPlantEnvironmentList(plantEnvironmentList);
        }
        //农事类型
        TbFarmingType type = new TbFarmingType();
        type.setFarmingTypeStatus(NormalOrDeleteEnum.NORMAL.getCode());
        List<TbFarmingType> farmingTypeList = tbFarmingTypeMapper.selectTbFarmingTypeList(type);
        if (!ValidationUtil.isEmpty(farmingTypeList)) {
            vo.setFarmingTypeList(farmingTypeList);
        }

        return vo;
    }

    @Override
    public List<ModelOverviewVo> selectModelOverviewList(ModelOverviewParam param) {
        return tbModelMapper.selectModelOverviewList(param);
    }

    @Override
    public ModelStatisticsVo selectModelStatistics(String farmId) {
        return tbModelMapper.selectModelStatistics(farmId);
    }

    @Override
    public List<ModelCropCategoryVo> selectModelListByCropCategoryId(ModelCropCategoryParam param) {
        List<ModelCropCategoryVo> list = Lists.newArrayList();

        TbModel tbModel = new TbModel();
        BeanUtils.copyBeanProp(tbModel, param);

        List<TbModel> modelList = tbModelMapper.selectTbModelList(tbModel);
        if (!ValidationUtil.isEmpty(modelList)) {
            for (TbModel model : modelList) {
                ModelCropCategoryVo vo = new ModelCropCategoryVo();
                
                vo.setModelId(model.getModelId());
                vo.setModelName(model.getModelName());
                SysDictData ps = getDictData(model.getPlantStandardId());
                if (!ValidationUtil.isEmpty(ps)) {
                    vo.setPlantStandardId(ps.getDictCode());
                    vo.setPlantStandard(ps.getDictLabel());
                }

                SysDictData pe = getDictData(model.getPlantEnvironmentId());
                if(!ValidationUtil.isEmpty(pe)) {
                    vo.setPlantEnvironmentId(pe.getDictCode());
                    vo.setPlantEnvironment(pe.getDictLabel());
                }

                //判断是不是我的模型
                Long createBy = model.getCreateBy();
                if(!ValidationUtil.isEmpty(createBy) && createBy.equals(param.getCurrentLoginId())) {
                    vo.setModelSource(ModelTypeEnum.MODEL_MY.getVal());
                }else {
                    vo.setModelSource(ModelTypeEnum.MODEL_FARM.getVal());
                }

                vo.setModelUseTime(model.getModelUseTime());
                list.add(vo);
            }
        }

        return list;
    }

    @Override
    public ModelDetailVo selectModelDetailById(Long modelId, String farmId) {

        ModelDetailVo vo = tbModelMapper.selectModelDetail(modelId, farmId);
        //处理定植当天0天的bug
        for(TbModelPeriodAndFarmings periodAndFarmings : vo.getPeriodAndFarmingsList()) {
            List<TbModelPeriodFarming> farmingList = periodAndFarmings.getFarmingList();
            for (TbModelPeriodFarming periodFarming : farmingList) {
                if("定植当天0天".equals(periodFarming.getFarmingTime())) {
                    periodFarming.setFarmingTime("定植当天");
                }
            }
        }
        if(!ValidationUtil.isEmpty(vo)) {
            //种植标准
            if(!ValidationUtil.isEmpty(vo.getPlantStandardId())) {
                vo.setPlantStandard(getDictData(vo.getPlantStandardId()).getDictLabel());
            }
            //种植环境
            if(!ValidationUtil.isEmpty(vo.getPlantEnvironmentId())) {
                vo.setPlantEnvironment(getDictData(vo.getPlantEnvironmentId()).getDictLabel());
            }
        }
        return vo;
    }
    /**
     * 根据种植作物，作物标准， 作物环境查询模型
     * @param cropCategoryId
     * @param plantStandardId
     * @param plantEnvironmentId
     * @param farmId
     * @return
     */
    public List<HashMap<String,Object>> selectByCropCategoryList(Long modelId,String farmId){
    	return tbModelPeriodMapper.selectByCropCategoryList(modelId, farmId);
    }
}
