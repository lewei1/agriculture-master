package org.zcy.agriculture.vo.irrigation;

import org.zcy.agriculture.entity.TbResDevice;

import java.io.Serializable;
import java.util.List;

public class IrrigationReturnIOTVo implements Serializable {

    private List<TbResDevice> deviceList;

    public List<TbResDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<TbResDevice> deviceList) {
        this.deviceList = deviceList;
    }
}
