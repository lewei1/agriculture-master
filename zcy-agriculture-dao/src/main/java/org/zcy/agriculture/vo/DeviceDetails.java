package org.zcy.agriculture.vo;

import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.vo.device.DeviceAttributesVo;
import org.zcy.agriculture.vo.device.TbResDeviceAttributesExtraVo;
import org.zcy.agriculture.vo.monitoringcenter.AlarmThresholdKeyNameVo;

import java.util.List;
import java.util.Map;

/**
 * 设备详情（包含设备基本信息和接口属性）
 *
 * @author zh
 * @date 2019-06-25
 */
public class DeviceDetails extends TbResDevice {
   // private TbResDevice device;

    /** 地块名称 */
    private String plotName;

    List<TbResDeviceAttributesExtraVo> listAttributes;

    public List<AlarmThresholdKeyNameVo> getListThingsboardKeys() {
        return listThingsboardKeys;
    }

    public void setListThingsboardKeys(List<AlarmThresholdKeyNameVo> listThingsboardKeys) {
        this.listThingsboardKeys = listThingsboardKeys;
    }

    List<AlarmThresholdKeyNameVo> listThingsboardKeys;

    private Map tsData;

    public List<TbResDeviceAttributesExtraVo> getListAttributes() {
        return listAttributes;
    }

    public void setListAttributes(List<TbResDeviceAttributesExtraVo> listAttributes) {
        this.listAttributes = listAttributes;
    }

//    public String getRealTimeData() {
//        return realTimeData;
//    }
//
//    public void setRealTimeData(String realTimeData) {
//        this.realTimeData = realTimeData;
//    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public Map getTsData() {
        return tsData;
    }

    public void setTsData(Map tsData) {
        this.tsData = tsData;
    }
}
