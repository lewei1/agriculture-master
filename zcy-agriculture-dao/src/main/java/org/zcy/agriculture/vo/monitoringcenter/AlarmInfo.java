package org.zcy.agriculture.vo.monitoringcenter;

import org.zcy.agriculture.vo.AlarmDayNumVo;

import java.util.List;

public class AlarmInfo {
    Long   todayAlarmNum; //今日报警数量
    String  dayToDayRatio;//日环比
    List<AlarmDayNumVo> list;

    public Long getTodayAlarmNum() {
        return todayAlarmNum;
    }

    public void setTodayAlarmNum(Long todayAlarmNum) {
        this.todayAlarmNum = todayAlarmNum;
    }

    public String getDayToDayRatio() {
        return dayToDayRatio;
    }

    public void setDayToDayRatio(String dayToDayRatio) {
        this.dayToDayRatio = dayToDayRatio;
    }

    public List<AlarmDayNumVo> getList() {
        return list;
    }

    public void setList(List<AlarmDayNumVo> list) {
        this.list = list;
    }
}
