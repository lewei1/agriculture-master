package org.zcy.agriculture.param.model;

import org.zcy.agriculture.param.BaseParam;

/**
 * 作物模型-概览-条件参数
 */
public class ModelOverviewParam extends BaseParam {

    //作物名称
    private String categoryName;
    //种植标准
    private Long plantStandardId;
    //种植环境
    private Long plantEnvironmentId;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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
}
