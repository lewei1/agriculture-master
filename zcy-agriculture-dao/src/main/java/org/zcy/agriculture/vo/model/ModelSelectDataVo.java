package org.zcy.agriculture.vo.model;

import org.zcy.agriculture.entity.SysDictData;
import org.zcy.agriculture.entity.TbCropCategory;
import org.zcy.agriculture.entity.TbFarmingType;
import org.zcy.agriculture.vo.BaseVo;

import java.util.List;

/**
 * 作物模型-添加模型-下拉框数据集合
 */
public class ModelSelectDataVo extends BaseVo {

    private List<TbCropCategory> cropCategoryList;

    private List<SysDictData> plantStandardList;

    private List<SysDictData> plantEnvironmentList;

    private List<TbFarmingType> farmingTypeList;

    public List<TbCropCategory> getCropCategoryList() {
        return cropCategoryList;
    }

    public void setCropCategoryList(List<TbCropCategory> cropCategoryList) {
        this.cropCategoryList = cropCategoryList;
    }

    public List<SysDictData> getPlantStandardList() {
        return plantStandardList;
    }

    public void setPlantStandardList(List<SysDictData> plantStandardList) {
        this.plantStandardList = plantStandardList;
    }

    public List<SysDictData> getPlantEnvironmentList() {
        return plantEnvironmentList;
    }

    public void setPlantEnvironmentList(List<SysDictData> plantEnvironmentList) {
        this.plantEnvironmentList = plantEnvironmentList;
    }

    public List<TbFarmingType> getFarmingTypeList() {
        return farmingTypeList;
    }

    public void setFarmingTypeList(List<TbFarmingType> farmingTypeList) {
        this.farmingTypeList = farmingTypeList;
    }
}
