package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 农机表 tb_agricultural_machine
 *
 * @author linlq
 * @date 2019-06-21
 */
public class TbAgriculturalMachine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 农机ID
     */
    private Long machineId;
    /**
     * 农场ID
     */
    private String farmId;
    /**
     * 农机类型ID
     */
    private Long machineTypeId;
    /**
     * 农机名称
     */
    private String name;
    /**
     * 农机型号
     */
    private String model;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 图片
     */
    private String image;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 农机类型名称
     */
    private String machineTypeName;

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setMachineTypeId(Long machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    public Long getMachineTypeId() {
        return machineTypeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getMachineTypeName() {
        return machineTypeName;
    }

    public void setMachineTypeName(String machineTypeName) {
        this.machineTypeName = machineTypeName;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("machineId", getMachineId())
                .append("farmId", getFarmId())
                .append("machineTypeId", getMachineTypeId())
                .append("name", getName())
                .append("model", getModel())
                .append("price", getPrice())
                .append("image", getImage())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("machineTypeName", getMachineTypeName())
                .toString();
    }
}
