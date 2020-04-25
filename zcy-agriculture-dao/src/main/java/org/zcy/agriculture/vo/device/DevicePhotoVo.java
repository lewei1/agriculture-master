package org.zcy.agriculture.vo.device;

public class DevicePhotoVo {
    /** 设备序列号 */
    private String devNum;
    /** 图片地址 */
    private String picUrl;

    private String status;

    public String getDevNum() {
        return devNum;
    }

    public void setDevNum(String devNum) {
        this.devNum = devNum;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
