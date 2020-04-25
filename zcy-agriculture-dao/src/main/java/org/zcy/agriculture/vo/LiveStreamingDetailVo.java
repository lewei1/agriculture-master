package org.zcy.agriculture.vo;

import org.zcy.agriculture.param.LiveStreamingParam;
import java.util.List;

/**
 * 视频直播 详情
 */
public class LiveStreamingDetailVo extends LiveStreamingParam {

    /** 摄像头图像 */
    private String cameraPic;
    /** 地块名称 */
    private String  plotName;

    public String getCameraPic() {
        return cameraPic;
    }

    public void setCameraPic(String cameraPic) {
        this.cameraPic = cameraPic;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }
}
