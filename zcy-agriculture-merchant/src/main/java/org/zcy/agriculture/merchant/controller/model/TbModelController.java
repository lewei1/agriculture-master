package org.zcy.agriculture.merchant.controller.model;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.TbCropCategory;
import org.zcy.agriculture.entity.TbModel;
import org.zcy.agriculture.entity.TbModelPeriodAndFarmings;
import org.zcy.agriculture.entity.TbModelPeriodFarming;
import org.zcy.agriculture.enums.ModelTypeEnum;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.param.model.ModelCropCategoryParam;
import org.zcy.agriculture.param.model.ModelOverviewParam;
import org.zcy.agriculture.param.model.ModelDetailParam;
import org.zcy.agriculture.service.ITbCropCategoryService;
import org.zcy.agriculture.service.model.ITbModelService;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.model.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 作物模型 信息操作处理
 *
 * @author numberone
 * @date 2019-06-26
 */
@Controller
@RequestMapping("/api/model")
public class TbModelController extends BaseController {

    @Autowired
    private ITbModelService tbModelService;

    @Autowired
    private ITbCropCategoryService tbCropCategoryService;

    /**
     * 获取select下拉框列表集合
     * @return
     */
    @GetMapping("/selectList")
    @ResponseBody
    public AjaxResult getSelectList() {
        ModelSelectDataVo vo = tbModelService.selectAddPageList(getFarmUUID());
        return success(vo);
    }

    /**
     * 统计
     * @return
     */
    @GetMapping("/statistics")
    @ResponseBody
    public AjaxResult statistics() {
        ModelStatisticsVo statisticsVo = tbModelService.selectModelStatistics(getFarmUUID());
        return success(statisticsVo);
    }


    /**
     * 边栏+概览列表
     */
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(@RequestBody ModelOverviewParam param) {
        param.setFarmId(getFarmUUID());
        List<ModelOverviewVo> list = tbModelService.selectModelOverviewList(param);
        return success(list);
    }

    /**
     * 通过参数查询模型
     * @param param
     * @return
     */
    @PostMapping("/cropModelList")
    @ResponseBody
    public AjaxResult cropModelList(@RequestBody ModelCropCategoryParam param) {

        //校验
        AjaxResult validation = validation(param);
        if(AjaxResult.getResultStatus(validation) != RequestStatus.SUCCESS.getStatus())
            return validation;

        param.setFarmId(getFarmUUID());
        //用于判断是否是我的模型
        param.setCurrentLoginId(getFarmUserCode());
        List<ModelCropCategoryVo> list = tbModelService.selectModelListByCropCategoryId(param);
        if(ValidationUtil.isEmpty(list))
            list = Lists.newArrayList();

        ModelCropCategoryDetailVo vo = new ModelCropCategoryDetailVo();
        vo.setModelCropCategoryVoList(list);

        TbCropCategory cropCategory = tbCropCategoryService.selectTbCropCategoryById(param.getCropCategoryId());
        if(!ValidationUtil.isEmpty(cropCategory)) {
            vo.setCategoryName(cropCategory.getCategoryName());
            vo.setSamplePicture(cropCategory.getSamplePicture());
        }

        return success(vo);
    }

    /**
     * 新增或修改保存作物模型
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody ModelDetailParam detailParam) {
        int result = -1;
        try {


            if(ValidationUtil.isEmpty(detailParam.getModelId())) {
                //新增校验
                AjaxResult validation = validation(detailParam);
                if( AjaxResult.getResultStatus(validation) == RequestStatus.FAILED.getStatus()) {
                    return validation;
                }
                detailParam.setFarmId(getFarmUUID());
                detailParam.setCreateBy(getFarmUserCode());
                detailParam.setModelType(ModelTypeEnum.MODEL_FARM.getCode());
                result = tbModelService.insertTbModelDetail(detailParam);

            }else {
                //只能更新模型名称，种植标准，种植环境
                if(ValidationUtil.isEmpty(detailParam.getModelName()))
                    return error("模型名称"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                if(ValidationUtil.isEmpty(detailParam.getPlantStandardId()))
                    return error("种植标准"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                if(ValidationUtil.isEmpty(detailParam.getPlantEnvironmentId()))
                    return error("种植环境"+ ValidationConstants.SUFFIX_NOT_EMPTY);

                TbModel model = new TbModel();
                model.setModelId(detailParam.getModelId());
                model.setModelName(detailParam.getModelName());
                model.setPlantStandardId(detailParam.getPlantStandardId());
                model.setPlantEnvironmentId(detailParam.getPlantEnvironmentId());

                //判断模型是否相同,同一农场下模型不能相同
                List<TbModel> models = tbModelService.selectTbModelList(model);
                if(!ValidationUtil.isEmpty(models))
                    return error("模型名称"+ ValidationConstants.SUFFIX_HAS_EXIST);

                model.setFarmId(getFarmUUID());
                model.setUpdateBy(getFarmUserCode());
                model.setUpdateTime(new Date());

                result = tbModelService.updateTbModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toAjax(result);
    }

    /**
     * 删除作物模型
     */
    @GetMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long modelId) {
        if(ValidationUtil.isEmpty(modelId)) {
            return error("作物模型id"+ValidationConstants.SUFFIX_NOT_EMPTY);
        }

        TbModel model = new TbModel();
        model.setModelId(modelId);
        model.setModelStatus(NormalOrDeleteEnum.DELETE.getCode());
        model.setFarmId(getFarmUUID());
        model.setUpdateBy(getFarmUserCode());
        model.setUpdateTime(new Date());

        return toAjax(tbModelService.updateTbModel(model));
    }

    /**
     * 模型详情
     * @param modelId
     * @return
     */
    @GetMapping("/detail")
    @ResponseBody
    public AjaxResult detail(Long modelId) {
        if(ValidationUtil.isEmpty(modelId)) {
            return error("作物模型id"+ValidationConstants.SUFFIX_NOT_EMPTY);
        }
        ModelDetailVo vo = tbModelService.selectModelDetailById(modelId, getFarmUUID());
        Long farmUserCode = this.getFarmUserCode();
        if(farmUserCode.longValue()==vo.getCreateBy().longValue()){
            vo.setModelSource(ModelTypeEnum.MODEL_MY.getVal());
        }else {
            vo.setModelSource(ModelTypeEnum.MODEL_FARM.getVal());
        }
        return success(vo);
    }


    @Override
    protected AjaxResult validation(Object object) {
        if(object instanceof ModelDetailParam) {
            ModelDetailParam param = (ModelDetailParam)object;

            if(ValidationUtil.isEmpty(param.getCropCategoryId()))
                return error("种植作物"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            if(ValidationUtil.isEmpty(param.getPlantStandardId()))
                return error("种植标准"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            if(ValidationUtil.isEmpty(param.getPlantEnvironmentId()))
                return error("种植环境"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            if(ValidationUtil.isEmpty(param.getModelName()))
                return error("模型名称"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            else {
                //判断当前农场的模型名称是否重复
                TbModel model = new TbModel();
                model.setFarmId(getFarmUUID());
                model.setModelStatus(NormalOrDeleteEnum.NORMAL.getCode());
                model.setModelName(param.getModelName());
                List<TbModel> models = tbModelService.selectTbModelList(model);
                if(!ValidationUtil.isEmpty(models))
                    return error("模型名称"+ ValidationConstants.SUFFIX_HAS_EXIST);
            }

            List<TbModelPeriodAndFarmings> periodAndFarmingsList = param.getPeriodAndFarmingsList();
            if(ValidationUtil.isEmpty(periodAndFarmingsList))
                return error("作物阶段"+ ValidationConstants.SUFFIX_LACK_PARAMS);
            else {
                for(TbModelPeriodAndFarmings farmings : periodAndFarmingsList) {
                    if(ValidationUtil.isEmpty(farmings.getPeriodName()))
                        return error("阶段名"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                    if(ValidationUtil.isEmpty(farmings.getStartTime()))
                        return error("开始时间"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                    if(ValidationUtil.isEmpty(farmings.getEndTime()))
                        return error("结束时间"+ ValidationConstants.SUFFIX_NOT_EMPTY);

                    List<TbModelPeriodFarming> farmingList = farmings.getFarmingList();
                    if(ValidationUtil.isEmpty(farmingList))
                        return error("农事信息"+ ValidationConstants.SUFFIX_LACK_PARAMS);
                    else {
                        for (TbModelPeriodFarming f : farmingList) {
                            if(ValidationUtil.isEmpty(f.getFarmingTime()))
                                return error("（农事）时间"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                            if(ValidationUtil.isEmpty(f.getFarmingTime()))
                                return error("农事类型"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                        }
                    }
                }
            }
        }else if(object instanceof ModelCropCategoryParam) {

            ModelCropCategoryParam conditionParam = (ModelCropCategoryParam)object;
            if(ValidationUtil.isEmpty(conditionParam.getCropCategoryId()))
                return error("作物种类id"+ValidationConstants.SUFFIX_NOT_EMPTY);

        }
        return success();
    }
    
    /**
     * 农事计划  根据种植作物，作物标准， 作物环境查询模型
     * @param modelId
     * @return
     */
    @GetMapping("/getModelFarming")
    @ResponseBody
    public AjaxResult getModelFarming(Long modelId) {
        if(modelId == null) {
            return error(RequestStatus.PARAM_REQUIRED.getMessage());
        }
        List<HashMap<String,Object>> list = tbModelService.selectByCropCategoryList(modelId, getFarmUUID());
        return success(list);
    }
    
}
