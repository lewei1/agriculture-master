package org.zcy.agriculture.param.irrigation;

import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.param.BaseParam;

import java.util.List;

public class IrrigationIOTParam extends BaseParam {

    private String method;

    private Object params;

    private List<TbResDevice> deviceList;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public List<TbResDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<TbResDevice> deviceList) {
        this.deviceList = deviceList;
    }
}
