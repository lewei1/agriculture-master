package org.zcy.agriculture.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.enums.DevTypeEnum;
import org.zcy.agriculture.mapper.TbAlarmRecordMapper;
import org.zcy.agriculture.mapper.TbResDeviceMapper;
import org.zcy.agriculture.mapper.TbSubPlotMapper;
import org.zcy.agriculture.param.MonitorDeviceInfoParam;
import org.zcy.agriculture.service.IMonitorCenterService;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.HttpCodeMsg;
import org.zcy.agriculture.util.HttpUtils;
import org.zcy.agriculture.vo.AlarmDayNumVo;
import org.zcy.agriculture.vo.monitoringcenter.AlarmInfo;
import org.zcy.agriculture.vo.monitoringcenter.PlotAndDeviceDetailsVo;

@Service
public class IMonitorCenterServiceImpl extends BaseServiceImpl implements IMonitorCenterService {

    @Autowired
    private TbAlarmRecordMapper tbAlarmRecordMapper;

    @Autowired
    private TbSubPlotMapper subPlotMapper;

    @Autowired
    private TbResDeviceMapper deviceMapper;


    public PlotAndDeviceDetailsVo getPlotAndDeviceDetailsInfo(String farmId, Long plotId) {
        PlotAndDeviceDetailsVo vo = new PlotAndDeviceDetailsVo();

        /*****************以下是取过去30天报警数据以及环比数据*********************/
        AlarmInfo alarmInfo = new AlarmInfo();
        Long lastNumber = 30L;      //过去多少天（这里取30天的）
        Long todayAlarmNum = 0L;    //今日报警数量
        Long yesTodayAlarmNum = 0L; //昨天报警数量
        List<AlarmDayNumVo> listAlarm = new ArrayList<AlarmDayNumVo>();

        boolean isHasOne = false;

        /**以下代码是依次统计过去每一天有多少条报警记录*/
        for (int i = 0; i < lastNumber; i++) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("MM-dd");
                DateFormat dateFormat2 = new SimpleDateFormat("YYYY-MM-dd 00:00:00");
                Date dateStart = DateUtils.getLastDayByIndex(i); //每天开始的时间字符串
                Date dateEnd = DateUtils.getLastDayByIndex(i - 1); //每天结束的时间字符串
                Long number = tbAlarmRecordMapper.selectCountBetweenTheTimeByPlotId(farmId, plotId, dateFormat2.format(dateStart), dateFormat2.format(dateEnd));
                String dateStr = dateFormat.format(dateStart);
                AlarmDayNumVo alarmDayNumVo = new AlarmDayNumVo();
                alarmDayNumVo.setDateStr(dateStr); //时间字符串
                alarmDayNumVo.setAlarmNum(number); //报警数
                listAlarm.add(alarmDayNumVo);

                if (i == 0) {
                    todayAlarmNum = number;
                } else if (i == 1) {
                    yesTodayAlarmNum = number;
                }

                if (number > 0) {
                    isHasOne = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!isHasOne) { //如果过去30天所有天的数据都为零则返回前端一个空的集合，方便展示
            listAlarm.clear();
        }

        alarmInfo.setList(listAlarm);
        alarmInfo.setTodayAlarmNum(todayAlarmNum);
        alarmInfo.setDayToDayRatio("0%");
        if (yesTodayAlarmNum != 0) {
            //计算今日环比
            float num = (float) (todayAlarmNum - yesTodayAlarmNum) / yesTodayAlarmNum*100;

            alarmInfo.setDayToDayRatio(String.format("%.2f",num) + "%");
        } else if (yesTodayAlarmNum == 0 && todayAlarmNum != 0) {
            alarmInfo.setDayToDayRatio("100%");
        }
        vo.setAlarmInfo(alarmInfo); //汇总到vo对象里
        /*********过去30天报警数据统计结束*********/

        /**统计子地块信息*/
        vo.setSubPlotPersonNum(subPlotMapper.getSubPlotPersonNumByPlotId(plotId));  //管理人员数量
        vo.setSubPlotNum(subPlotMapper.getSubPlotNumberByPlotId(plotId));           //子地块数

        /**获取监测设备数量*/
        TbResDevice device = new TbResDevice();               //构造一个TbResDevice类型对象device
        device.setDevType(DevTypeEnum.MONITOR_DEV.getCode()); //设置device设备类型为检测设备
        device.setPlotId(plotId);                             //设置地块ID
        device.setFarmId(farmId);                             //设置农场ID
        vo.setMonitorDevNum(deviceMapper.getCountFromTbResDevice(device)); //检测设备数量
        return vo;
    }

    /**
     * 根据传入条件查询设备的传感器属性历史最大值/最小值
     *
     * @param param 包含开始日期/截止日期等字符串
     * @param flag 历史值（0最小值，1最大值，2平均值）
     * @return 字典数据集合信息
     */
    @Override
    public String deviceNowOrHistoryMaxMinAttribute(MonitorDeviceInfoParam param, int flag) {
        /**请求的物联网地址URL*/
        String attributesDataURL = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1;
        /**属性集（传感器）*/
        String attributes = "keys=";
        /**是取历史最大值还是最小值，为MIN表示最小值，为MAX表示最大值*/
        String maxOrMinStr = "MIN";

        switch (flag){
            case 0:
                maxOrMinStr = "MIN"; //AVG
                break;
            case 1:
                maxOrMinStr = "MAX"; //AVG
                break;
            case 2:
                maxOrMinStr = "AVG";
        }

        if (param.getDevNum() != null) {
            /**获取设备序列号*/
            String devNum = param.getDevNum();

            if (devNum != null && devNum != "") {
                try {
                    /**构造开始时间和结束时间的时间戳，以便请求物联网端*/
                    Long dateStart = DateUtils.dateTime(DateUtils.YYYY_MM_DD, param.getStartDateStr()).getTime();
                    Long dateEnd = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, param.getEndDateStr() + " 23:59:59").getTime();
                    attributes += param.getAttributes(); //获取设备传感器
                    attributesDataURL = attributesDataURL + devNum + ThingsboardUrlConstants.DEVICE_VALUES_TIMESERIES_SUB_URL + "?" + attributes + "&startTs=" + dateStart + "&endTs=" + dateEnd + "&interval=" + (dateEnd - dateStart) + "&agg=" + maxOrMinStr;
                    HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(attributesDataURL, getIOTToken());

                    return msg.getMsg();
                } catch (Exception e) {
                }
            }
        }

        return "";
    }

    /**
     * 获取设备历史数据
     *
     * @param param （包含开始日期，结束日期，以及统计的时间间隔）
     * @return 物联网端查询结果
     */
    @Override
    public String deviceAttributeHistoryInfo(MonitorDeviceInfoParam param) {
        /**请求的物联网地址URL*/
        String attributesDataURL = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1;
        /**属性集*/
        String attributes = "keys=";
        /**interval这个变量的作用是用来表示从物联网端获取的数据相邻俩条数据间的间隔*/
        Long interval;

        if (param.getDevNum() != null) {
            /**获取设备序列号*/
            String devNum = param.getDevNum();

            if (devNum != null && devNum != "") {
                try {
                    /**构造开始时间和结束时间的时间戳，以便请求物联网端*/
                    Long dateStart = DateUtils.dateTime(DateUtils.YYYY_MM_DD, param.getStartDateStr() + " 00:00:00").getTime();
                    Long dateEnd = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, param.getEndDateStr() + " 23:59:59").getTime();
                    if (dateEnd > new Date().getTime()) {
                        dateEnd = new Date().getTime();
                    }

                    /**传过来的时间间隔是以秒为单位的，所以来计算时候要乘以1000*/
                    if (param.getIntervalSecond() * 1000 > dateEnd - dateStart) { //interval的值不能大于起止时间
                        interval = dateEnd - dateStart;
                    } else {
                        interval = param.getIntervalSecond() * 1000;
                    }

                    attributes += param.getAttributes();
                    attributesDataURL = attributesDataURL + devNum + ThingsboardUrlConstants.DEVICE_VALUES_TIMESERIES_SUB_URL + "?" + attributes + "&startTs=" + dateStart + "&endTs=" + dateEnd + "&interval=" + interval + "&agg=AVG";
                    HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(attributesDataURL, getIOTToken());

                    return msg.getMsg();
                } catch (Exception e) {
                }
            }
        }

        return "";
    }

    /**
     * 获取设备历史数据
     *
     * @param param （包含开始日期，结束日期）
     * @return 物联网端查询结果
     */
    @Override
    public String deviceAttributeHistoryInfoWithPage(MonitorDeviceInfoParam param,int pageNum,int pageSize) {
        /**请求的物联网地址URL*/
        String attributesDataURL = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1;
        /**属性集*/
        String attributes = "keys=";

        if (param.getDevNum() != null) {
            /**获取设备序列号*/
            String devNum = param.getDevNum();

            if (devNum != null && devNum != "") {
                try {
                    /**构造开始时间和结束时间的时间戳，以便请求物联网端*/
                    Long dateStart = DateUtils.dateTime(DateUtils.YYYY_MM_DD, param.getStartDateStr() + " 00:00:00").getTime();
                    Long dateEnd = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, param.getEndDateStr() + " 23:59:59").getTime();
                    if (dateEnd > new Date().getTime()) {
                        dateEnd = new Date().getTime();
                    }

                    attributes += param.getAttributes();
                    attributesDataURL = attributesDataURL + devNum + ThingsboardUrlConstants.DEVICE_VALUES_TIMESERIES_SUB_URL + "?" + attributes + "&startTs="
                            + dateStart + "&endTs=" + dateEnd + "&from="+ pageNum +"&limit=" + pageSize;
                    HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(attributesDataURL, getIOTToken());

                    return msg.getMsg();
                } catch (Exception e) {
                }
            }
        }

        return "";
    }
}
