package org.zcy.agriculture.param;

import org.zcy.agriculture.entity.TbResDevice;

import java.util.List;

public class CameraIOTParam {
    private String method;

    private List<TbResDevice> deviceList;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<TbResDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<TbResDevice> deviceList) {
        this.deviceList = deviceList;
    }
}
