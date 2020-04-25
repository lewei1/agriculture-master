package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 作物模型表 tb_model
 *
 * @author numberone
 * @date 2019-06-27
 */
public class TbModel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long modelId;
    /**
     * 模型名称
     */
    private String modelName;
    /**
     * 模型类型
     */
    private String modelType;
    /**
     * 作物种类id
     */
    private Long cropCategoryId;
    /**
     * 作物标准id
     */
    private Long plantStandardId;
    /**
     * 作物环境id
     */
    private Long plantEnvironmentId;
    /**
     * 模型状态
     */
    private Integer modelStatus;
    /**
     * 模型使用次数
     */
    private Integer modelUseTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     *
     */
    private String farmId;


    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setCropCategoryId(Long cropCategoryId) {
        this.cropCategoryId = cropCategoryId;
    }

    public Long getCropCategoryId() {
        return cropCategoryId;
    }

    public void setPlantStandardId(Long plantStandardId) {
        this.plantStandardId = plantStandardId;
    }

    public Long getPlantStandardId() {
        return plantStandardId;
    }

    public void setPlantEnvironmentId(Long plantEnvironmentId) {
        this.plantEnvironmentId = plantEnvironmentId;
    }

    public Long getPlantEnvironmentId() {
        return plantEnvironmentId;
    }

    public void setModelStatus(Integer modelStatus) {
        this.modelStatus = modelStatus;
    }

    public Integer getModelStatus() {
        return modelStatus;
    }

    public void setModelUseTime(Integer modelUseTime) {
        this.modelUseTime = modelUseTime;
    }

    public Integer getModelUseTime() {
        return modelUseTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmId() {
        return farmId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("modelId", getModelId())
                .append("modelName", getModelName())
                .append("cropCategoryId", getCropCategoryId())
                .append("plantStandardId", getPlantStandardId())
                .append("plantEnvironmentId", getPlantEnvironmentId())
                .append("modelStatus", getModelStatus())
                .append("modelUseTime", getModelUseTime())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("farmId", getFarmId())
                .toString();
    }
}
