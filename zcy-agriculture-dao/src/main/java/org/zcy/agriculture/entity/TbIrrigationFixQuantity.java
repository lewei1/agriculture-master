package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 灌溉分组定量表 tb_irrigation_fix_quantity
 *
 * @author numberone
 * @date 2019-07-01
 */
public class TbIrrigationFixQuantity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long fixQuantityId;
    /**
     * 设备id
     */
    private Long deviceId;
    /**
     * 传感器
     */
    private String sensor;
    /**
     * 大于或者小于,字典表数据
     */
    private Long contrast;
    /**
     * 对比条件
     */
    private Integer contrastCondition;
    /**
     * 打开或者关闭
     */
    private Long openClose;
    /**
     * 操作时间
     */
    private Integer actionTime;
    /**
     * 时间单位
     */
    private String timeUnit;
    /**
     * 定量状态,0-未激活，1-已激活， 2-删除
     */
    private Integer fixQuantityStatus;
    /**
     * 灌溉分组id
     */
    private Long groupId;

    public void setFixQuantityId(Long fixQuantityId) {
        this.fixQuantityId = fixQuantityId;
    }

    public Long getFixQuantityId() {
        return fixQuantityId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getSensor() {
        return sensor;
    }

    public void setContrast(Long contrast) {
        this.contrast = contrast;
    }

    public Long getContrast() {
        return contrast;
    }

    public void setContrastCondition(Integer contrastCondition) {
        this.contrastCondition = contrastCondition;
    }

    public Integer getContrastCondition() {
        return contrastCondition;
    }

    public void setOpenClose(Long openClose) {
        this.openClose = openClose;
    }

    public Long getOpenClose() {
        return openClose;
    }

    public void setActionTime(Integer actionTime) {
        this.actionTime = actionTime;
    }

    public Integer getActionTime() {
        return actionTime;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setFixQuantityStatus(Integer fixQuantityStatus) {
        this.fixQuantityStatus = fixQuantityStatus;
    }

    public Integer getFixQuantityStatus() {
        return fixQuantityStatus;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("fixQuantityId", getFixQuantityId())
                .append("deviceId", getDeviceId())
                .append("sensor", getSensor())
                .append("contrast", getContrast())
                .append("contrastCondition", getContrastCondition())
                .append("openClose", getOpenClose())
                .append("actionTime", getActionTime())
                .append("timeUnit", getTimeUnit())
                .append("fixQuantityStatus", getFixQuantityStatus())
                .append("groupId", getGroupId())
                .toString();
    }
}
