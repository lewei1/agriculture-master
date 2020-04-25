package org.zcy.agriculture.vo.model;

import org.zcy.agriculture.vo.BaseVo;

/**
 * 作物模型-通过作物种类查询的模型列表
 */
public class ModelCropCategoryVo extends BaseVo {

    private Long modelId;

    private String modelName;

    private Long plantStandardId;

    private String plantStandard;

    private Long plantEnvironmentId;

    private String plantEnvironment;

    private String modelSource;

    private Integer modelUseTime;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Long getPlantStandardId() {
        return plantStandardId;
    }

    public void setPlantStandardId(Long plantStandardId) {
        this.plantStandardId = plantStandardId;
    }

    public String getPlantStandard() {
        return plantStandard;
    }

    public void setPlantStandard(String plantStandard) {
        this.plantStandard = plantStandard;
    }

    public Long getPlantEnvironmentId() {
        return plantEnvironmentId;
    }

    public void setPlantEnvironmentId(Long plantEnvironmentId) {
        this.plantEnvironmentId = plantEnvironmentId;
    }

    public String getPlantEnvironment() {
        return plantEnvironment;
    }

    public void setPlantEnvironment(String plantEnvironment) {
        this.plantEnvironment = plantEnvironment;
    }

    public String getModelSource() {
        return modelSource;
    }

    public void setModelSource(String modelSource) {
        this.modelSource = modelSource;
    }

    public Integer getModelUseTime() {
        return modelUseTime;
    }

    public void setModelUseTime(Integer modelUseTime) {
        this.modelUseTime = modelUseTime;
    }
}
