package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 灌溉分组定时表 tb_irrigation_timing
 *
 * @author numberone
 * @date 2019-07-01
 */
public class TbIrrigationTiming extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long timingId;

    /**
     * 几点打开
     */
    private String openTime;
    /**
     * 打开多长时间
     */
    private Integer openPeriod;
    /**
     * 时间单位
     */
    private String timeUnit;
    /**
     * 是否循环0-循环，1-不循环
     */
    private Integer loopOrNot;
    /**
     * 循环几次
     */
    private Integer loopCount;
    /**
     * 间隔多久
     */
    private Integer spacingTime;
    /**
     * 间隔时间单位
     */
    private String spacingPeriod;
    /**
     * 定时状态0-未激活，1-已激活， 2-删除
     */
    private Integer timingStatus;
    /**
     * 灌溉分组id
     */
    private Long groupId;

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public void setTimingId(Long timingId) {
        this.timingId = timingId;
    }

    public Long getTimingId() {
        return timingId;
    }


    public void setOpenPeriod(Integer openPeriod) {
        this.openPeriod = openPeriod;
    }

    public Integer getOpenPeriod() {
        return openPeriod;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setLoopOrNot(Integer loopOrNot) {
        this.loopOrNot = loopOrNot;
    }

    public Integer getLoopOrNot() {
        return loopOrNot;
    }

    public void setLoopCount(Integer loopCount) {
        this.loopCount = loopCount;
    }

    public Integer getLoopCount() {
        return loopCount;
    }

    public void setSpacingTime(Integer spacingTime) {
        this.spacingTime = spacingTime;
    }

    public Integer getSpacingTime() {
        return spacingTime;
    }

    public void setSpacingPeriod(String spacingPeriod) {
        this.spacingPeriod = spacingPeriod;
    }

    public String getSpacingPeriod() {
        return spacingPeriod;
    }

    public void setTimingStatus(Integer timingStatus) {
        this.timingStatus = timingStatus;
    }

    public Integer getTimingStatus() {
        return timingStatus;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("timingId", getTimingId())
                .append("openTime", getOpenTime())
                .append("openPeriod", getOpenPeriod())
                .append("timeUnit", getTimeUnit())
                .append("loopOrNot", getLoopOrNot())
                .append("loopCount", getLoopCount())
                .append("spacingTime", getSpacingTime())
                .append("spacingPeriod", getSpacingPeriod())
                .append("timingStatus", getTimingStatus())
                .append("groupId", getGroupId())
                .toString();
    }
}
