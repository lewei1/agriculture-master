package org.zcy.agriculture.vo.device;

import java.io.Serializable;
import java.util.List;

public class CameraPhotoIOTReturnVo implements Serializable {

    private List<DevicePhotoVo> deviceList;

    public List<DevicePhotoVo> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<DevicePhotoVo> deviceList) {
        this.deviceList = deviceList;
    }
}
