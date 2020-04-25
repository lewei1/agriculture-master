package org.zcy.agriculture.vo.model;

import java.io.Serializable;
import java.util.List;

public class ModelCropCategoryDetailVo implements Serializable {

    //作物图片
    private String samplePicture;
    //作物名称
    private String categoryName;
    //作物模型详情
    List<ModelCropCategoryVo> modelCropCategoryVoList;

    public String getSamplePicture() {
        return samplePicture;
    }

    public void setSamplePicture(String samplePicture) {
        this.samplePicture = samplePicture;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ModelCropCategoryVo> getModelCropCategoryVoList() {
        return modelCropCategoryVoList;
    }

    public void setModelCropCategoryVoList(List<ModelCropCategoryVo> modelCropCategoryVoList) {
        this.modelCropCategoryVoList = modelCropCategoryVoList;
    }
}
