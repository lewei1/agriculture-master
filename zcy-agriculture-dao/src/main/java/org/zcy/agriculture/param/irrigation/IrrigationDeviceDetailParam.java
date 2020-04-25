package org.zcy.agriculture.param.irrigation;

import org.zcy.agriculture.entity.TbResDevice;

public class IrrigationDeviceDetailParam extends TbResDevice {

    private String imgUrl;

    private Long createBy;

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
