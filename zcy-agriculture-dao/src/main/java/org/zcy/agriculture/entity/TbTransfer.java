package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 调拨记录表 tb_transfer
 *
 * @author linlq
 * @date 2019-07-02
 */
public class TbTransfer extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long transferId;
    /**
     * 调入仓库id
     */
    private Long inWarehouseId;
    /**
     * 调出仓库id
     */
    private Long outWarehouseId;
    /**
     * 单号
     */
    private String number;
    /**
     * 物品id
     */
    private Long materialId;
    /**
     * 物品名称
     */
    private String article;
    /**
     * 物品类型id
     */
    private Long materialTypeId;
    /**
     * 物品类型名称
     */
    private String materialTypeName;
    /**
     * 方式
     */
    private String method;
    /**
     * 单位id
     */
    private Long unit;

    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 数量
     */
    private Double quantity;
    /**
     * 操作人id
     */
    private String operatorId;
    /**
     * 操作人name
     */
    private String operatorName;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 状态，0为在途，1为已调拨
     */
    private Integer status;
    /**
     * 操作，0显示入库，1不显示
     */
    private Integer operate;
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
     * 农场ID
     */
    private String farmId;

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public Long getInWarehouseId() {
        return inWarehouseId;
    }

    public void setInWarehouseId(Long inWarehouseId) {
        this.inWarehouseId = inWarehouseId;
    }

    public Long getOutWarehouseId() {
        return outWarehouseId;
    }

    public void setOutWarehouseId(Long outWarehouseId) {
        this.outWarehouseId = outWarehouseId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Long getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Long materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getUnit() {
        return unit;
    }

    public void setUnit(Long unit) {
        this.unit = unit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    @Override
    public String toString() {
        return "TbTransfer{" +
                "transferId=" + transferId +
                ", inWarehouseId=" + inWarehouseId +
                ", outWarehouseId=" + outWarehouseId +
                ", number='" + number + '\'' +
                ", materialId=" + materialId +
                ", article='" + article + '\'' +
                ", materialTypeId=" + materialTypeId +
                ", materialTypeName='" + materialTypeName + '\'' +
                ", method='" + method + '\'' +
                ", unit=" + unit +
                ", unitName='" + unitName + '\'' +
                ", quantity=" + quantity +
                ", operatorId='" + operatorId + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status=" + status +
                ", operate=" + operate +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                ", farmId='" + farmId + '\'' +
                '}';
    }
}
