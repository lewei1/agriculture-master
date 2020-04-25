package org.zcy.agriculture.vo;

import java.io.Serializable;

/**
 * 设备状态统计信息
 *
 * @author zh
 * @date 2019-06-24
 */

public class DeviceStatusStatisticsVo extends BaseVo {
    /** 正在报警*/
    private Long alarmingNum;
    /** 运行中*/
    private Long runingNum;
    /** 已断开*/
    private Long disconnectedNum;
    /** 待机中*/
    private Long standbyNum;
    /** 总计*/
    private Long totalNum;

    public Long getAlarmingNum() {
        return alarmingNum;
    }

    public void setAlarmingNum(Long alarmingNum) {
        this.alarmingNum = alarmingNum;
    }

    public Long getRuningNum() {
        return runingNum;
    }

    public void setRuningNum(Long runingNum) {
        this.runingNum = runingNum;
    }

    public Long getDisconnectedNum() {
        return disconnectedNum;
    }

    public void setDisconnectedNum(Long disconnectedNum) {
        this.disconnectedNum = disconnectedNum;
    }

    public Long getStandbyNum() {
        return standbyNum;
    }

    public void setStandbyNum(Long standbyNum) {
        this.standbyNum = standbyNum;
    }

    /*总计（全部设备）*/
    public Long getTotalNum() {
        return alarmingNum + runingNum + disconnectedNum + standbyNum;
    }
}
