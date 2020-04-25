package org.zcy.agriculture.vo.model;

import java.io.Serializable;

/**
 *作物模型-概览-中栏列表和概览列表
 */
public class ModelOverviewVo implements Serializable {

    private String cropCategoryId;

    private String categoryName;

    private Integer modelNumber;

    private String samplePicture;

    public String getSamplePicture() {
        return samplePicture;
    }

    public void setSamplePicture(String samplePicture) {
        this.samplePicture = samplePicture;
    }

    public String getCropCategoryId() {
        return cropCategoryId;
    }

    public void setCropCategoryId(String cropCategoryId) {
        this.cropCategoryId = cropCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(Integer modelNumber) {
        this.modelNumber = modelNumber;
    }
}
