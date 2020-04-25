package org.zcy.agriculture.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.constants.DeviceConstants;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.enums.AlarmThresholdTypes;
import org.zcy.agriculture.enums.DevStatusEnum;
import org.zcy.agriculture.enums.DevTypeEnum;
import org.zcy.agriculture.enums.ThingsboardDeviceEnum;
import org.zcy.agriculture.mapper.TbResDeviceAttributesMapper;
import org.zcy.agriculture.mapper.TbResDeviceMapper;
import org.zcy.agriculture.param.MonitorDeviceInfoParam;
import org.zcy.agriculture.param.irrigation.IrrigationIOTParam;
import org.zcy.agriculture.service.ITbResDeviceService;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.HttpCodeMsg;
import org.zcy.agriculture.util.HttpUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.DeviceStatusStatisticsVo;
import org.zcy.agriculture.vo.DeviceTypeStatisticsVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 设备 服务层实现
 *
 * @author zh
 * @date 2019-06-21
 */
@Service
public class TbResDeviceServiceImpl extends BaseServiceImpl implements ITbResDeviceService {
    @Autowired
    private TbResDeviceMapper tbResDeviceMapper;

    @Autowired
    private TbResDeviceAttributesMapper tbResDeviceAttributesMapper;

    /**
     * 查询设备信息
     *
     * @param devId 设备ID
     * @return 设备信息
     */
    @Override
    public TbResDevice selectTbResDeviceById(Long devId) {
        return tbResDeviceMapper.selectTbResDeviceById(devId);
    }

    /**
     * 查询设备列表
     *
     * @param tbResDevice 设备信息
     * @return 设备集合
     */
    @Override
    public List<TbResDevice> selectTbResDeviceList(TbResDevice tbResDevice) {
        return tbResDeviceMapper.selectTbResDeviceList(tbResDevice);
    }


    /**
     * 判断设备在物联网端是否存在
     */
    public int isDeviceExistinDevice(String devNum) {
        String url = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1 + devNum + ThingsboardUrlConstants.DEVICE_STATUS_URL2;
        HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(url, getIOTToken());

        if (msg.getCode() == 401) { //token已过期，重新设置
            return ThingsboardDeviceEnum.TOKEN_ERR.getCode();
        }
        if (msg.getCode() == 200 && msg.getMsg() != null) {
            return ThingsboardDeviceEnum.EXIST.getCode();
        } else {
            return ThingsboardDeviceEnum.NOT_EXIST.getCode();
        }
    }

    /**
     * 新增设备
     *
     * @param tbResDevice 设备信息
     * @return 结果
     */
    @Override
    public int insertTbResDevice(TbResDevice tbResDevice) {
        String devNum = tbResDevice.getDevNum();
        if (devNum == null || devNum == "")
            return -1;
        if (tbResDeviceMapper.getCountFromTbResDeviceByDevNum(devNum) > 0)
            return -1;

        if ( tbResDevice.getDevType() == DevTypeEnum.WEATHER_STATION.getCode() && tbResDevice.getStatus()==null) { //气象站
            tbResDevice.setStatus(DevStatusEnum.READY.getCode()); //气象站默认设置为待机状态
        }

        int insertResult = tbResDeviceMapper.insertTbResDevice(tbResDevice);
        if (tbResDevice.getDevType() != null && tbResDevice.getDevType() == DevTypeEnum.CAMERA.getCode()) { //如果是摄像头类型，则设置默认值
            tbResDevice.setLastPhotoTime(new Date()); //上次拍照时间设置为当前时间
            if (tbResDevice.getShootingInterval() == null) {
                tbResDevice.setShootingInterval(DeviceConstants.DEFAULT_CAMERA_SHOOTING_INTERVAL); //设置默认拍照间隔
            }
        }

        if (insertResult > 0) {
            /**查询物联网端获取设备属性*/
            String url = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1 + tbResDevice.getDevNum() + ThingsboardUrlConstants.DEVICE_TIMESERIES_SUB_URL;
            HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(url, getIOTToken());
            ObjectMapper jsonMapper = new ObjectMapper();
            try {
                ArrayList<String> deviceList = jsonMapper.readValue(msg.getMsg(), ArrayList.class);
                for (String str : deviceList) {
                    AlarmThresholdTypes alarmThreshold = null;
                    try {
                        alarmThreshold = AlarmThresholdTypes.valueOf(str);
                    } catch (Exception e) {
                    }

                    if (alarmThreshold != null) {
                        /**将属性插入到数据库表里面去*/
                        TbResDeviceAttributes attributes = new TbResDeviceAttributes();
                        attributes.setDevId(tbResDevice.getDevId());
                        attributes.setAttributesName(alarmThreshold.getVal()); //根据枚举类型设置属性名称
                        attributes.setUnit(alarmThreshold.getUnit());           //设置单位
                        attributes.setThingsboardKey(str);
                        attributes.setTypesIndex(alarmThreshold.getCode());
                        int insertResult2 = tbResDeviceAttributesMapper.insertTbResDeviceAttributes(attributes);
                        if (insertResult2 < 1) { //属性插入失败了,需要回滚
                            tbResDeviceAttributesMapper.deleteTbResDeviceAttributesByDevId(tbResDevice.getDevId());
                            tbResDeviceMapper.deleteTbResDeviceById(tbResDevice.getDevId()); //把插入的设备删掉
                            return -1;
                        }
                    }
                }
                return 1;
            } catch (Exception e) {
                tbResDeviceMapper.deleteTbResDeviceById(tbResDevice.getDevId()); //报异常时候要把插入的设备删掉
                return -1;
            }
        }

        return insertResult;
    }

    @Override
    public int getTbResDeviceAttributeFromThingsboard(TbResDevice tbResDevice) {
        if (tbResDevice.getDevId() == null) {
            return -1;
        }

        TbResDevice dev = tbResDeviceMapper.selectTbResDeviceById(tbResDevice.getDevId());
        String devNum = dev.getDevNum();
        if (devNum == null || dev.getDevNum() == "")
            return -1;

        /**查询物联网端获取设备属性*/
        String url = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1 + devNum + ThingsboardUrlConstants.DEVICE_TIMESERIES_SUB_URL;
        HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(url, getIOTToken());
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            ArrayList<String> deviceList = jsonMapper.readValue(msg.getMsg(), ArrayList.class);
            for (String str : deviceList) {
                AlarmThresholdTypes alarmThreshold = null;
                try {
                    alarmThreshold = AlarmThresholdTypes.valueOf(str);
                } catch (Exception e) {
                }

                if (alarmThreshold != null) {
                    /**将属性插入到数据库表里面去*/
                    TbResDeviceAttributes attributes = new TbResDeviceAttributes();
                    attributes.setDevId(tbResDevice.getDevId());
                    attributes.setAttributesName(alarmThreshold.getVal()); //根据枚举类型设置属性名称
                    attributes.setUnit(alarmThreshold.getUnit());           //设置单位
                    attributes.setThingsboardKey(str);
                    attributes.setTypesIndex(alarmThreshold.getCode());
                    int insertResult2 = tbResDeviceAttributesMapper.insertTbResDeviceAttributes(attributes);
                    if (insertResult2 < 1) { //属性插入失败了,需要回滚
                        tbResDeviceAttributesMapper.deleteTbResDeviceAttributesByDevId(tbResDevice.getDevId());
                        return -1;
                    }
                }
            }
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 修改设备
     *
     * @param tbResDevice 设备信息
     * @return 结果
     */
    @Override
    public int updateTbResDevice(TbResDevice tbResDevice) {
        return tbResDeviceMapper.updateTbResDevice(tbResDevice);
    }

    /**
     * 删除设备对象
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbResDeviceById(Long id) {
        /**根据设备ID删除设备，设备除成功则同时删除掉设备属性表里数据*/
        int result = tbResDeviceMapper.deleteTbResDeviceById(id);
        if (result == 1) {
            tbResDeviceAttributesMapper.deleteTbResDeviceAttributesByDevId(id);
        }
        return result;
    }

    /**
     * 获取设备状态信息（各种状态数量）
     *
     * @param farmID 农场ID
     * @return 结果
     */
    public DeviceStatusStatisticsVo deviceStatusStatistics(String farmID) {
        DeviceStatusStatisticsVo dev = new DeviceStatusStatisticsVo();

        TbResDevice device = new TbResDevice();
        if (farmID != "") {
            device.setFarmId(farmID);
        }

        /**报警的设备数量*/
        device.setStatus(0);
        Long alarmingNum = tbResDeviceMapper.getCountFromTbResDevice(device);
        dev.setAlarmingNum(alarmingNum);

        /**正运行的设备数量*/
        device.setStatus(1);
        Long runingNum = tbResDeviceMapper.getCountFromTbResDevice(device);
        dev.setRuningNum(runingNum);

        /**断开连接的设备数量*/
        device.setStatus(2);
        Long disconnectedNum = tbResDeviceMapper.getCountFromTbResDevice(device);
        dev.setDisconnectedNum(disconnectedNum);

        /**待机的设备数量*/
        device.setStatus(3);
        Long standByNum = tbResDeviceMapper.getCountFromTbResDevice(device);
        dev.setStandbyNum(standByNum);
        return dev;
    }

    /**
     * 获取各种设备数量信息
     *
     * @param farmID 农场ID
     * @return 结果
     */
    public DeviceTypeStatisticsVo deviceTypeStatistics(String farmID) {
        DeviceTypeStatisticsVo dev = new DeviceTypeStatisticsVo();

        TbResDevice device = new TbResDevice();
        if (farmID != "") {
            device.setFarmId(farmID);
        }
        device.setDevType(0);
        /**检测设备数量*/
        Long monitoringNum = tbResDeviceMapper.getCountFromTbResDevice(device);
        dev.setMonitoringNum(monitoringNum);

        device.setDevType(1);
        /**气象站数量*/
        Long weatherStationNum = tbResDeviceMapper.getCountFromTbResDevice(device);
        dev.setWeatherStationNum(weatherStationNum);

        device.setDevType(2);
        /**断开连接的设备数量*/
        Long irrigationNum = tbResDeviceMapper.getCountFromTbResDevice(device);
        dev.setIrrigationNum(irrigationNum);

        device.setDevType(3);
        /**控制设备数量*/
        Long controllingNum = tbResDeviceMapper.getCountFromTbResDevice(device);
        dev.setControllingNum(controllingNum);

        device.setDevType(4);
        /**摄像头数量*/
        Long cameraNum = tbResDeviceMapper.getCountFromTbResDevice(device);
        dev.setCameraNum(cameraNum);
        return dev;
    }

    /**
     * 根据传入的TbResDevice类型对象来获取对应的数据
     *
     * @return 统计结果
     */
    public Long getCountFromTbResDevice(TbResDevice tbResDevice) {
        return tbResDeviceMapper.getCountFromTbResDevice(tbResDevice);
    }

    /**
     * 根据传入的TbResDevice类型对象来获取对应的数据
     *
     * @return 统计结果
     */
    @Override
    public Long getCountFromTbResDeviceByDevNum(String devNum) {
        return tbResDeviceMapper.getCountFromTbResDeviceByDevNum(devNum);
    }

//    public void getDeviceAttributesDataFromThingsboard(TbResDevice tbResDevice) {
//        String attributesDataURL = ThingsboardUrlConstants.ROOT_URL +  ThingsboardUrlConstants.DEVICE_STATUS_URL1;
//        String attributes = "keys=";
//
//        if (tbResDevice.getDevId()!=  null) {
//            tbResDevice = tbResDeviceMapper.selectTbResDeviceById(tbResDevice.getDevId());
//            /**获取设备序列号*/
//            String devNum = tbResDevice.getDevNum();
//
//            if (devNum!= null && devNum != "") {
//                TbResDeviceAttributesVo attribute = new TbResDeviceAttributesVo();
//                /**根据设备ID遍历tb_res_device_attributes表*/
//                attribute.setDevId(tbResDevice.getDevId());
//                List<TbResDeviceAttributes> list = tbResDeviceAttributesMapper.selectTbResDeviceAttributesList(attribute);
//
//                /**构造属性字符串*/
//                boolean flag = true;
//                for (TbResDeviceAttributes temp : list) {
//                    String thingsboardKey = temp.getThingsboardKey();
//                    if (thingsboardKey!= null && thingsboardKey != "") {
//                        if (flag) { //如果是第一个单词前面就不需要加逗号
//                            attributes += thingsboardKey;
//                            flag = false;
//                        } else {
//                            attributes = attributes + "," + thingsboardKey;
//                        }
//                    }
//                }
//
//                attributesDataURL = attributesDataURL + devNum +  ThingsboardUrlConstants.DEVICE_VALUES_TIMESERIES_SUB_URL+"?" + attributes +"&startTs="+ (System.currentTimeMillis()-72000000) + "&endTs="+ System.currentTimeMillis();
//                HttpCodeMsg  msg = HttpUtils.doGetAndReturnCodeMsg(attributesDataURL, getIOTToken());
//                System.out.println("获取设备属性 msg:"+ msg.getCode()+"msg.getMsg():"+ msg.getMsg());
//                /**以下是解析JSON数据呢*/
//
//                JSONObject jsonObject = JSONObject.parseObject(msg.getMsg());
//                Map<String, Object> map = jsonObject.getInnerMap();
//                Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
//                while (entries.hasNext()) {
//                    Map.Entry<String, Object> entry = entries.next();
//
//                    JSONArray tr = jsonObject.getJSONArray(entry.getKey());
//                    for(int i = 0 ;i<tr.size();i++){ //对数组元素进行遍历
//                        JSONObject jsonObjectTemp = JSONObject.parseObject(tr.getString(i));
//                        ThingsboardDeviceAtrributeVo tokenVo = JSONObject.toJavaObject(jsonObjectTemp, ThingsboardDeviceAtrributeVo.class);
//                        System.out.println("ts:"+tokenVo.getTs()); //获取到的
//                        System.out.println("value:"+tokenVo.getValue());
//                    }
//                }
//
//            }
//        }
//    }

    /**
     * 根据传入设备属性查询设备当前值并返回消息
     *
     * @param devNum       设备序列号
     * @param attributeStr 设备传感器列表
     * @return 字典数据集合信息
     */
    @Override
    public String getDeviceAttributeNowDataFromThingsboard(String devNum, String attributeStr) {
        String attributesDataURL = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1;
        String attributes = "keys=";

        if (devNum != null && devNum != "") {
            try {
                attributes += attributeStr;
                attributesDataURL = attributesDataURL + devNum + ThingsboardUrlConstants.DEVICE_VALUES_TIMESERIES_SUB_URL + "?" + attributes;

                HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(attributesDataURL, getIOTToken());
//                System.out.println("attributesDataURL:"+attributesDataURL);
//                System.out.println("getIOTToken():"+getIOTToken());

                return msg.getMsg();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    /**
     * 获取设备历史数据
     *
     * @param devNum 设备序列号
     * @param attribut 设备属性
     * @return 物联网端查询结果
     */
    @Override
    public String deviceAttributeWithRecent24Hours(String devNum,String attribut) {
        /**请求的物联网地址URL*/
        String attributesDataURL = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1;
        /**属性集*/
        String attributes = "keys=";
        /**interval这个变量的作用是用来表示从物联网端获取的数据相邻俩条数据间的间隔*/
        Long interval = 3600000L;

            if (devNum != null && devNum != "") {
                try {
                    /**构造开始时间和结束时间的时间戳，以便请求物联网端*/
                    Long dateEnd = System.currentTimeMillis();
                    Long dateStart =dateEnd-24*3600000;

                    if (dateEnd > new Date().getTime()) {
                        dateEnd = new Date().getTime();
                    }

                    attributes += attribut;
                    attributesDataURL = attributesDataURL + devNum + ThingsboardUrlConstants.DEVICE_VALUES_TIMESERIES_SUB_URL + "?" + attributes + "&startTs=" + dateStart + "&endTs=" + dateEnd + "&interval=" + interval + "&agg=AVG";
                    HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(attributesDataURL, getIOTToken());

                    System.out.println("attributesDataURL:"+attributesDataURL);

                    return msg.getMsg();
                } catch (Exception e) {
                }
        }

        return "";
    }


    /**
     * 首页设备统计
     *
     * @param farmId
     * @return
     */
    public List<HashMap<String, Object>> selectByDeviceType(String farmId) {
        return tbResDeviceMapper.selectByDeviceType(farmId);
    }

    /**
     * 控制灌溉设备的状态
     *
     * @param resDevice
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateIrrigationDevice(TbResDevice resDevice) throws Exception {
        int result;

        try {

            IrrigationIOTParam param = new IrrigationIOTParam();
            param.setMethod(ThingsboardUrlConstants.DEVICE_CONTROL_METHOD);
            param.setParams(new Object());

            List<TbResDevice> deviceList = Lists.newArrayList();
            TbResDevice device = new TbResDevice();
            device.setDevNum(resDevice.getDevNum());
            device.setStatus(resDevice.getStatus());

            deviceList.add(device);
            param.setDeviceList(deviceList);

            //操作物联网设备
            List<TbResDevice> resDeviceList = processIOTDevice(param);
            //判断物联网返回的所有设备的状态
            if (!ValidationUtil.isEmpty(resDeviceList)) {
                for (TbResDevice d : resDeviceList) {
                    if (d.getStatus() != resDevice.getStatus())
                        return -1;
                }
            }

            result = tbResDeviceMapper.updateTbResDevice(resDevice);
        } catch (Exception e) {
            throw new Exception();
        }

        return result;
    }
    /**
	 * 根据地块 和类型查询 设备 
	 * @param map
	 * @return
	 */
	public List<TbResDevice>selectByDeviceList(HashMap<String,Object> map){
		return tbResDeviceMapper.selectByDeviceList(map);
	}
}
