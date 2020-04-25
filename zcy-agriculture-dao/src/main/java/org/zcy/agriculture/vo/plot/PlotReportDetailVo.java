package org.zcy.agriculture.vo.plot;

import org.zcy.agriculture.entity.TbPlotReport;
import org.zcy.agriculture.entity.TbReportImg;

import java.util.List;

public class PlotReportDetailVo extends TbPlotReport {

    private String firstImgUrl;

    private List<TbReportImg> imgList;

    public List<TbReportImg> getImgList() {
        return imgList;
    }

    public void setImgList(List<TbReportImg> imgList) {
        this.imgList = imgList;
    }

    public String getFirstImgUrl() {
        return firstImgUrl;
    }

    public void setFirstImgUrl(String firstImgUrl) {
        this.firstImgUrl = firstImgUrl;
    }
}
