package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物品表 tb_article
 *
 * @author llq lky.
 * @date 2019-07-29
 */
public class TbArticle extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 物品ID
     */
    private Long materialId;
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
     * 品牌
     */
    private String brand;
    /**
     * 计量单位id
     */
    private Long meterUnitId;
    /**
     * 计量单位Name
     */
    private String meterUnitName;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 单价对应的单位id
     */
    private Long priceUnitId;
    /**
     * 单价对应的单位name
     */
    private String priceUnitName;
    /**
     * 副单位ID1
     */
    private Long unitId1;
    /**
     * 副单位name1
     */
    private String unitName1;
    /**
     * 换算比例1
     */
    private Double conversionRatio1;
    /**
     * 副单位ID2
     */
    private Long unitId2;
    /**
     * 副单位name2
     */
    private String unitName2;
    /**
     * 换算比例2
     */
    private Double conversionRatio2;
    /**
     * 启用多单位，0为不启动，1为启动
     */
    private Integer multipleUnit;
    /**
     * 物品图片
     */
    private String image;
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
    /**
     * 库存数量
     */
    private Double quantity;
    /**
     * 库存成本
     */
    private BigDecimal cost;


    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getMeterUnitId() {
        return meterUnitId;
    }

    public void setMeterUnitId(Long meterUnitId) {
        this.meterUnitId = meterUnitId;
    }

    public String getMeterUnitName() {
        return meterUnitName;
    }

    public void setMeterUnitName(String meterUnitName) {
        this.meterUnitName = meterUnitName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getPriceUnitId() {
        return priceUnitId;
    }

    public void setPriceUnitId(Long priceUnitId) {
        this.priceUnitId = priceUnitId;
    }

    public String getPriceUnitName() {
        return priceUnitName;
    }

    public void setPriceUnitName(String priceUnitName) {
        this.priceUnitName = priceUnitName;
    }

    public Long getUnitId1() {
        return unitId1;
    }

    public void setUnitId1(Long unitId1) {
        this.unitId1 = unitId1;
    }

    public String getUnitName1() {
        return unitName1;
    }

    public void setUnitName1(String unitName1) {
        this.unitName1 = unitName1;
    }

    public Double getConversionRatio1() {
        return conversionRatio1;
    }

    public void setConversionRatio1(Double conversionRatio1) {
        this.conversionRatio1 = conversionRatio1;
    }

    public Long getUnitId2() {
        return unitId2;
    }

    public void setUnitId2(Long unitId2) {
        this.unitId2 = unitId2;
    }

    public String getUnitName2() {
        return unitName2;
    }

    public void setUnitName2(String unitName2) {
        this.unitName2 = unitName2;
    }

    public Double getConversionRatio2() {
        return conversionRatio2;
    }

    public void setConversionRatio2(Double conversionRatio2) {
        this.conversionRatio2 = conversionRatio2;
    }

    public Integer getMultipleUnit() {
        return multipleUnit;
    }

    public void setMultipleUnit(Integer multipleUnit) {
        this.multipleUnit = multipleUnit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return "TbArticle{" +
                "materialId=" + materialId +
                ", materialName='" + materialName + '\'' +
                ", materialTypeId=" + materialTypeId +
                ", materialTypeName='" + materialTypeName + '\'' +
                ", brand='" + brand + '\'' +
                ", meterUnitId=" + meterUnitId +
                ", meterUnitName='" + meterUnitName + '\'' +
                ", price=" + price +
                ", priceUnitId=" + priceUnitId +
                ", priceUnitName='" + priceUnitName + '\'' +
                ", unitId1=" + unitId1 +
                ", unitName1='" + unitName1 + '\'' +
                ", conversionRatio1=" + conversionRatio1 +
                ", unitId2=" + unitId2 +
                ", unitName2='" + unitName2 + '\'' +
                ", conversionRatio2=" + conversionRatio2 +
                ", multipleUnit=" + multipleUnit +
                ", image='" + image + '\'' +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                ", farmId='" + farmId + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
