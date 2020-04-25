package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 灌溉设备图片表 tb_irrigation_device_img
 *
 * @author numberone
 * @date 2019-07-22
 */
public class TbIrrigationDeviceImg extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long imgId;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 灌溉设备id
     */
    private Long deviceId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 农场id,UUID
     */
    private String farmId;

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() {
        return deviceId;
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

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmId() {
        return farmId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("imgId", getImgId())
                .append("imgUrl", getImgUrl())
                .append("deviceId", getDeviceId())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("farmId", getFarmId())
                .toString();
    }
}
