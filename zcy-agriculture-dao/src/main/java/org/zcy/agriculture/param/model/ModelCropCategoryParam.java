package org.zcy.agriculture.param.model;

import org.zcy.agriculture.param.BaseParam;

public class ModelCropCategoryParam extends BaseParam {

    //来源
    private String modelType;
    //作物种类id
    private Long cropCategoryId;
    //当前登录人id
    private Long currentLoginId;
    //种植标准
    private Long plantStandardId;
    //种植环境
    private Long plantEnvironmentId;

    public Long getPlantStandardId() {
        return plantStandardId;
    }

    public void setPlantStandardId(Long plantStandardId) {
        this.plantStandardId = plantStandardId;
    }

    public Long getPlantEnvironmentId() {
        return plantEnvironmentId;
    }

    public void setPlantEnvironmentId(Long plantEnvironmentId) {
        this.plantEnvironmentId = plantEnvironmentId;
    }

    public void setCropCategoryId(Long cropCategoryId) {
        this.cropCategoryId = cropCategoryId;
    }

    public Long getCurrentLoginId() {
        return currentLoginId;
    }

    public void setCurrentLoginId(Long currentLoginId) {
        this.currentLoginId = currentLoginId;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public Long getCropCategoryId() {
        return cropCategoryId;
    }

}
