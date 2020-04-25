package org.zcy.agriculture.param.plot;

import org.zcy.agriculture.entity.TbPlotReport;
import org.zcy.agriculture.entity.TbReportImg;

import java.util.List;

public class PlotReportDetailParam extends TbPlotReport {

    private List<TbReportImg> reportImgList;

    public List<TbReportImg> getReportImgList() {
        return reportImgList;
    }

    public void setReportImgList(List<TbReportImg> reportImgList) {
        this.reportImgList = reportImgList;
    }
}
