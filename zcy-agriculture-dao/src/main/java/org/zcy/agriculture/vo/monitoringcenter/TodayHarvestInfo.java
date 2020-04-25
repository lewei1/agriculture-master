package org.zcy.agriculture.vo.monitoringcenter;

import java.util.List;

public class TodayHarvestInfo {
    Long harvestNum;//今日采收数量
    Float dayToDayRatio;//日环比
    List<HarvestTimesNum> list;

    public Long getHarvestNum() {
        return harvestNum;
    }

    public void setHarvestNum(Long harvestNum) {
        this.harvestNum = harvestNum;
    }

    public Float getDayToDayRatio() {
        return dayToDayRatio;
    }

    public void setDayToDayRatio(Float dayToDayRatio) {
        this.dayToDayRatio = dayToDayRatio;
    }

    public List<HarvestTimesNum> getList() {
        return list;
    }

    public void setList(List<HarvestTimesNum> list) {
        this.list = list;
    }
}
