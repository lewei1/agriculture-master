package org.zcy.agriculture.param.irrigation;

import org.zcy.agriculture.entity.TbIrrigationLog;

import java.util.Date;

public class IrrigationLogParam extends TbIrrigationLog {

    /**
     * 开始日期字符串
     */
    private Date startDate;
    /**
     * 结束日期字符串
     */
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
