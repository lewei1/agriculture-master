package org.zcy.agriculture.vo.monitoringcenter;

import java.util.Map;

public class DeviceMaxMinMsgVo {
    String devName;
    Map max;
    Map min;
    Map now;
    Map avg;

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public Map getMax() {
        return max;
    }

    public void setMax(Map max) {
        this.max = max;
    }

    public Map getMin() {
        return min;
    }

    public void setMin(Map min) {
        this.min = min;
    }

    public Map getNow() {
        return now;
    }

    public void setNow(Map now) {
        this.now = now;
    }

    public Map getAvg() {
        return avg;
    }

    public void setAvg(Map avg) {
        this.avg = avg;
    }
}
