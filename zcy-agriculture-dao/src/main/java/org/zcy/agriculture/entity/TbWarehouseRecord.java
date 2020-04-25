package org.zcy.agriculture.entity;


import java.util.Date;

/**
 * 出入库记录表 tb_warehouse_record
 *
 * @author linlq lky.
 * @date 2019-06-28
 */
public class TbWarehouseRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long inId;
    /**
     * 出入库操作类型,(O - 出库，I - 入库)
     */
    private String inOutType;
    /**
     * 出入库类型id
     */
    private Long inOutTypeId;
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
     * 操作的仓库id
     */
    private Long warehouseId;
    /**
     * 农场ID
     */
    private String farmId;

    public Long getInId() {
        return inId;
    }

    public void setInId(Long inId) {
        this.inId = inId;
    }

    public String getInOutType() {
        return inOutType;
    }

    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }

    public Long getInOutTypeId() {
        return inOutTypeId;
    }

    public void setInOutTypeId(Long inOutTypeId) {
        this.inOutTypeId = inOutTypeId;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    @Override
    public String toString() {
        return "TbWarehouseRecord{" +
                "inId=" + inId +
                ", inOutType='" + inOutType + '\'' +
                ", inOutTypeId=" + inOutTypeId +
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
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                ", warehouseId=" + warehouseId +
                ", farmId='" + farmId + '\'' +
                '}';
    }
}
