package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 物品-单位表 tb_unit_type
 *
 * @author linlq
 * @date 2019-06-21
 */
public class TbUnitType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 单位ID
     */
    private Long materialUnitId;
    /**
     * 农场id,UUID
     */
    private String farmId;
    /**
     * 单位类型
     */
    private String typeName;
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
     * 物品ID
     */
    private Long materialId;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public void setMaterialUnitId(Long materialUnitId) {
        this.materialUnitId = materialUnitId;
    }

    public Long getMaterialUnitId() {
        return materialUnitId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
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

    @Override
    public String toString() {
        return "TbUnitType{" +
                "materialUnitId=" + materialUnitId +
                ", farmId='" + farmId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                ", materialId=" + materialId +
                '}';
    }
}
