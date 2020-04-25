package org.zcy.agriculture.vo.model;

import org.zcy.agriculture.entity.TbModel;
import org.zcy.agriculture.entity.TbModelPeriodAndFarmings;

import java.util.List;

/**
 * 作物模型-详情
 */
public class ModelDetailVo extends TbModel {

    private String categoryName;

    private String plantStandard;

    private String plantEnvironment;

    private String modelSource;

    private List<TbModelPeriodAndFarmings> periodAndFarmingsList;


    public String getPlantStandard() {
        return plantStandard;
    }

    public void setPlantStandard(String plantStandard) {
        this.plantStandard = plantStandard;
    }

    public String getPlantEnvironment() {
        return plantEnvironment;
    }

    public void setPlantEnvironment(String plantEnvironment) {
        this.plantEnvironment = plantEnvironment;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<TbModelPeriodAndFarmings> getPeriodAndFarmingsList() {
        return periodAndFarmingsList;
    }

    public void setPeriodAndFarmingsList(List<TbModelPeriodAndFarmings> periodAndFarmingsList) {
        this.periodAndFarmingsList = periodAndFarmingsList;
    }

    public String getModelSource() {
        return modelSource;
    }

    public void setModelSource(String modelSource) {
        this.modelSource = modelSource;
    }
}
