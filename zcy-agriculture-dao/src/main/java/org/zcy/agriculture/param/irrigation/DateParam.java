package org.zcy.agriculture.param.irrigation;

import org.zcy.agriculture.entity.TbIrrigationLog;

public class DateParam {

    /**
     * 开始日期字符串
     */
    private String startDateStr;
    /**
     * 结束日期字符串
     */
    private String endDateStr;

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }
}
