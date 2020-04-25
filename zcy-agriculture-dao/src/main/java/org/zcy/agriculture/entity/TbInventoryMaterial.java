package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 仓库和物品关联(库存)表 tb_inventory_material
 *
 * @author linlq
 * @date 2019-07-02
 */
public class TbInventoryMaterial extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long warehouseMaterialId;
    /**
     * 数量
     */
    private Double quantity;
    /**
     * 报警库存
     */
    private Double alarmStock;
    /**
     * 物品id
     */
    private Long materialId;
    /**
     * 仓库ID
     */
    private Long warehouseId;
    /**
     * 农场id,UUID
     */
    private String farmId;
    /**
     * 物品名称
     */
    private String materialName;
    /**
     * 物品类型ID
     */
    private Long materialTypeId;
    /**
     * 物品类型名称
     */
    private String materialTypeName;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 单价的单位
     */
    private String priceUnitName;
    /**
     * 最小单位id
     */
    private Long unit;

    /**
     * 最小单位名称
     */
    private String unitName;
    /**
     * 总价
     */
    private BigDecimal totalPrice;


    public Long getWarehouseMaterialId() {
        return warehouseMaterialId;
    }

    public void setWarehouseMaterialId(Long warehouseMaterialId) {
        this.warehouseMaterialId = warehouseMaterialId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getAlarmStock() {
        return alarmStock;
    }

    public void setAlarmStock(Double alarmStock) {
        this.alarmStock = alarmStock;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
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

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getUnit() {
        return unit;
    }

    public void setUnit(Long unit) {
        this.unit = unit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Long materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getPriceUnitName() {
        return priceUnitName;
    }

    public void setPriceUnitName(String priceUnitName) {
        this.priceUnitName = priceUnitName;
    }

    @Override
    public String toString() {
        return "TbInventoryMaterial{" +
                "warehouseMaterialId=" + warehouseMaterialId +
                ", quantity=" + quantity +
                ", alarmStock=" + alarmStock +
                ", materialId=" + materialId +
                ", warehouseId=" + warehouseId +
                ", farmId='" + farmId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialTypeId=" + materialTypeId +
                ", materialTypeName='" + materialTypeName + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", price=" + price +
                ", priceUnitName='" + priceUnitName + '\'' +
                ", unit=" + unit +
                ", unitName='" + unitName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
