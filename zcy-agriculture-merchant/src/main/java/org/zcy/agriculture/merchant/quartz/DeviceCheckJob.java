package org.zcy.agriculture.merchant.quartz;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.zcy.agriculture.constants.DayAlarmDefaultConstants;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.enums.AlarmThresholdDefaultVlue;
import org.zcy.agriculture.enums.AlarmThresholdTypes;
import org.zcy.agriculture.enums.DevStatusEnum;
import org.zcy.agriculture.enums.DevTypeEnum;
import org.zcy.agriculture.mapper.*;
import org.zcy.agriculture.mapper.farm.TbFarmMapper;
import org.zcy.agriculture.mapper.plot.TbPlotMapper;
import org.zcy.agriculture.service.ITbFarmingPlanService;
import org.zcy.agriculture.service.ITbResDeviceService;
import org.zcy.agriculture.util.HttpCodeMsg;
import org.zcy.agriculture.util.HttpUtils;
import org.zcy.agriculture.vo.TbFarmingPlanImplementVo;
import org.zcy.agriculture.vo.ThingsboardDeviceAtrributeVo;

import java.sql.Time;
import java.util.*;
import java.util.Map.Entry;

public class DeviceCheckJob implements Job {
    public static Logger logger = LogManager.getLogger(DeviceCheckJob.class);
    @Autowired
    private ITbResDeviceService tbResDeviceService;

    @Autowired
    private TbAlarmThresholdMapper alarmThresholdMapper;

    @Autowired
    private TbAlarmDayTimeMapper alarmDayTimeMapper;

    @Autowired
    private TbAlarmRecordMapper alarmRecordMapperr;

    @Autowired
    private TbMessageRecordMapper messageRecordMapper;

    @Autowired
    private TbFarmMapper farmMapper;

    @Autowired
    private TbPlotMapper plotMapper;

    @Autowired
    private ITbFarmingPlanService tbFarmingPlanService;

    @SuppressWarnings("unchecked")
	@Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //	查询在当前时间内 智能计划
        List<TbFarmingPlanImplementVo> fpList = tbFarmingPlanService.selectByIntelligentPlanningList();
        
        TbResDevice tbResDevice = new TbResDevice();
        /**遍历设备列表，依次请求设备并请求物联网端接口获取出设备最新数据并跟后台设置的阈值进行比较匹配*/
        List<TbResDevice> listDevice = tbResDeviceService.selectTbResDeviceList(tbResDevice);
        for (TbResDevice d : listDevice) {
            String attributesDataURL = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1; //定义物联网端定义的获取设备最近数据的URL变量

            String devNum = d.getDevNum(); // 获取设备序列号
            if (d.getDevId() != null && devNum != null && devNum != "" && d.getPlotId() != null && d.getFarmId() != null) { //设备序列号必须非空并且地块ID非空
                TbFarm farm = farmMapper.selectTbFarmById(d.getFarmId());
                TbPlot plot = plotMapper.selectTbPlotById(d.getPlotId());
                Integer disconnectSeconds = 600*1000; //这个变量表示设备多长时间没上传数据则断网了
                if (d.getDevType()!= null){
                    try {
                        disconnectSeconds = DevTypeEnum.getDisconnectsByCode(d.getDevType())*1000;
                    }catch (Exception e){}
                }


                /**构造访问物联网获取设备实时数据的完整URL（devNum是设备序列号）*/
                attributesDataURL = attributesDataURL + devNum + ThingsboardUrlConstants.DEVICE_VALUES_TIMESERIES_SUB_URL + "?";
                HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(attributesDataURL, tbResDeviceService.getIOTToken()); //返回的MSG

                boolean deviceIsAlarming = false; //设备是否报警
                boolean deviceIsDisConnect = true; //设备已断线
                try {
                    if (msg.getCode() == 200 && msg.getMsg() != null) {
                        /**解析返回的MSG*/
                    	Map<String, JSONArray> map= JSONObject.parseObject(msg.getMsg(),Map.class);
                        for(Entry<String, JSONArray> entry : map.entrySet()){
                            JSONArray tr = entry.getValue();
                            try {
                                System.out.println("entry.getKey():"+entry.getKey()+",Code:" + AlarmThresholdTypes.valueOf(entry.getKey()).getCode());
                                
                                for (int i = 0; i < tr.size(); i++) { //对数组元素进行遍历
                                    JSONObject jsonObjectTemp = JSONObject.parseObject(tr.getString(i));
                                    ThingsboardDeviceAtrributeVo tokenVo = JSONObject.toJavaObject(jsonObjectTemp, ThingsboardDeviceAtrributeVo.class);
                                    Long tsValue = Long.parseLong(tokenVo.getTs());
                                    Float value = Float.parseFloat(tokenVo.getValue());
                                  
                                    /**如果是报警类型的数据*/
                                    if (AlarmThresholdTypes.valueOf(entry.getKey()).getCode() <= AlarmThresholdTypes.PH.getCode()) {
                                        /**时间上做个拦截，只有最近5分钟（300000毫秒）的数据才去跟阈值做比较*/
                                        if (System.currentTimeMillis() - tsValue <= 300000) {
                                            deviceIsDisConnect = false;

                                            /**根据农场和地块ID获取报警阈值*/
                                            TbAlarmThreshold alarmThreshold = getAlarmThresholdByPlot(d.getFarmId(), d.getPlotId(), AlarmThresholdTypes.valueOf(entry.getKey()).getCode());
                                            /**获取白天报警时间段*/
                                            TbAlarmDayTime alarmDayTime = getAlarmDayTime(d.getFarmId(), d.getPlotId());
                                            /**判断当前时间是否为白天报警范围*/
                                            boolean isNowDay = isDay(alarmDayTime.getDayStartTime(), alarmDayTime.getDayEndTime());

                                            /**构造报警记录*/
                                            TbAlarmRecord tbAlarmRecord = new TbAlarmRecord();
                                            tbAlarmRecord.setDevId(d.getDevId());     //设备ID
                                            tbAlarmRecord.setDevType(d.getDevType()); //设备类型
                                            tbAlarmRecord.setFarmId(d.getFarmId());   //农场ID
                                            tbAlarmRecord.setPlotId(d.getPlotId());   //地块ID
                                            tbAlarmRecord.setRealValue(value);        //从物联网端获取的实时数据
                                            tbAlarmRecord.setTypesIndex(AlarmThresholdTypes.valueOf(entry.getKey()).getCode()); //报警阈值类型Index
                                            tbAlarmRecord.setCreateTime(new Date()); //创建时间

                                            TbMessageRecord messageRecord = new TbMessageRecord();
                                            messageRecord.setCreateTime(new Date());
                                            messageRecord.setMessageType(0);
                                            messageRecord.setFarmId(d.getFarmId());
                                            messageRecord.setMessageStatus(0);

                                            if (isNowDay) { //如果是白天，就用白天的阈值去判断做相应操作吧
                                                /**在白天报警阈值范围内就报警*/
                                                if (value < alarmThreshold.getDayMinValue() || value > alarmThreshold.getDayMaxValue()) {
                                                    if (value < alarmThreshold.getDayMinValue()) {
                                                        tbAlarmRecord.setLessOrMore(0); //小于阈值则设为0

                                                        messageRecord.setMessageSubject(AlarmThresholdTypes.valueOf(entry.getKey()).getVal() + "过低报警");//主题
                                                        messageRecord.setMessageContent("当前农场【"+farm.getFarmName()+"】的【" + plot.getPlotName()+"】"+ AlarmThresholdTypes.valueOf(entry.getKey()).getVal()+"为"+
                                                                value+AlarmThresholdTypes.valueOf(entry.getKey()).getUnit()+"低于阈值");
                                                    } else {
                                                        tbAlarmRecord.setLessOrMore(1); //大于阈值则设为1

                                                        messageRecord.setMessageSubject(AlarmThresholdTypes.valueOf(entry.getKey()).getVal() + "过高报警");//主题
                                                        messageRecord.setMessageContent("当前农场【"+farm.getFarmName()+"】的【" + plot.getPlotName()+"】"+ AlarmThresholdTypes.valueOf(entry.getKey()).getVal()+"为"+
                                                                value+AlarmThresholdTypes.valueOf(entry.getKey()).getUnit()+"高于阈值");
                                                    }
                                                    alarmRecordMapperr.insertTbAlarmRecord(tbAlarmRecord); //插入这条报警记录到数据库

                                                    messageRecordMapper.insertTbMessageRecord(messageRecord); //插入报警消息到消息表

                                                    deviceIsAlarming = true; //d.setStatus(DevStatusEnum.ALARMING.getCode());
                                                } //else { d.setStatus(DevStatusEnum.RUNING.getCode());}
                                            } else { //如果是黑夜，就用黑夜的阈值取判断做相应的操作吧
                                                if (value < alarmThreshold.getNightMinValue() || value > alarmThreshold.getNightMaxValue()) {
                                                    if (value < alarmThreshold.getNightMinValue()) {
                                                        tbAlarmRecord.setLessOrMore(0); //小于阈值则设为0

                                                        messageRecord.setMessageSubject(AlarmThresholdTypes.valueOf(entry.getKey()).getVal() + "过低报警");//主题
                                                        messageRecord.setMessageContent("当前农场【"+farm.getFarmName()+"】的【" + plot.getPlotName()+"】"+ AlarmThresholdTypes.valueOf(entry.getKey()).getVal()+"为"+
                                                                value+AlarmThresholdTypes.valueOf(entry.getKey()).getUnit()+"低于阈值");
                                                    } else {
                                                        tbAlarmRecord.setLessOrMore(1); //大于阈值则设为1

                                                        messageRecord.setMessageSubject(AlarmThresholdTypes.valueOf(entry.getKey()).getVal() + "过高报警");//主题
                                                        messageRecord.setMessageContent("当前农场【"+farm.getFarmName()+"】的【" + plot.getPlotName()+"】"+ AlarmThresholdTypes.valueOf(entry.getKey()).getVal()+"为"+
                                                                value+AlarmThresholdTypes.valueOf(entry.getKey()).getUnit()+"高于阈值");
                                                    }
                                                    alarmRecordMapperr.insertTbAlarmRecord(tbAlarmRecord); //插入这条报警记录到数据库

                                                    messageRecordMapper.insertTbMessageRecord(messageRecord); //插入报警消息到消息表
                                                    deviceIsAlarming = true; //d.setStatus(DevStatusEnum.ALARMING.getCode());
                                                } else {
                                                    d.setStatus(DevStatusEnum.RUNING.getCode());
                                                }
                                            }
                                            /**更新设备状态【是否报警中】*/
                                            tbResDeviceService.updateTbResDevice(d);
                                        }
                                    }  else if (System.currentTimeMillis() - tsValue >= disconnectSeconds){
                                        d.setStatus(DevStatusEnum.DISCONNECT.getCode()); //设备状态设置为断网了
                                        tbResDeviceService.updateTbResDevice(d);
                                    }
                                    
                                    /**---------------------------------------智能计划模块---------------------------------------*/
                                    /**时间上做个拦截，只有最近5分钟（300000毫秒）的数据才去跟阈值做比较*/
                                    if (System.currentTimeMillis() - tsValue <= 300000) {
                                    	//循环判断 当前条件是否 符合智能计划
                                    	for(TbFarmingPlanImplementVo fp:fpList) {
                                    		if(entry.getKey() != null  && entry.getKey().equals(fp.getThingsboardKey()) && d.getDevId().equals(fp.getDevId())) {
                                    			// 条件 0 大于 1大于等于 2小于 3小于等于 4 等于
                                    			switch (fp.getConditionVar()) {
                                    			case 0://大于
                                    				if(value > Float.parseFloat(fp.getDataVar())) {
                                    					// 符合智能计划
                                    					tbFarmingPlanService.changePlain(fp.getId());
                                    				}
                                    				break;
                                    			case 1://大于等于
                                    				if(value >= Float.parseFloat(fp.getDataVar())) {
                                    					// 符合智能计划
                                    					tbFarmingPlanService.changePlain(fp.getId());
                                    				}
                                    				break;
                                    			case 2://小于
                                    				if(value < Float.parseFloat(fp.getDataVar())) {
                                    					// 符合智能计划
                                    					tbFarmingPlanService.changePlain(fp.getId());
                                    				}
                                    				break;
                                    			case 3://小于等于
                                    				if(value <= Float.parseFloat(fp.getDataVar())) {
                                    					// 符合智能计划
                                    					tbFarmingPlanService.changePlain(fp.getId());
                                    				}
                                    				break;
                                    			case 4://等于
                                    				if(value == Float.parseFloat(fp.getDataVar())) {
                                    					// 符合智能计划
                                    					tbFarmingPlanService.changePlain(fp.getId());
                                    				}
                                    				break;
                                    			default:
                                    				//不符合计划
                                    				break;
                                    			}
                                    		}
                                    	}
                                    }
                                }
                            } catch (Exception e) {
                               // e.printStackTrace();
                            }
                        }

                        if (d.getDevType() == DevTypeEnum.MONITOR_DEV.getCode()) { //只有监测设备做判断
                            if (deviceIsDisConnect) {
                                d.setStatus(DevStatusEnum.DISCONNECT.getCode());
                                tbResDeviceService.updateTbResDevice(d);
                            } else {
                                d.setStatus(DevStatusEnum.RUNING.getCode());
                                tbResDeviceService.updateTbResDevice(d);
                            }

                            if (deviceIsAlarming) {
                                d.setStatus(DevStatusEnum.ALARMING.getCode());
                                tbResDeviceService.updateTbResDevice(d);
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取报警阈值（数据库表里没有就取默认值）
     *
     * @param plotId     地块ID
     * @param typesIndex 报警类型Index（参照枚举类型AlarmThresholdTypes）
     */
    private TbAlarmThreshold getAlarmThresholdByPlot(String farmId, Long plotId, Integer typesIndex) {
        TbAlarmThreshold threshold = new TbAlarmThreshold();
        threshold.setFarmId(farmId); //设置农场ID
        threshold.setPlotId(plotId); //设置地块ID
        threshold.setTypesIndex(typesIndex); //设置Index
        List<TbAlarmThreshold> listAlarm = alarmThresholdMapper.selectTbAlarmThresholdList(threshold);
        /**如果tb_alarm_threshold表还没有相应记录，则根据typesIndex取报警阈值默认值*/
        if (listAlarm.size() <= 0) {
            threshold.setNightMaxValue(AlarmThresholdDefaultVlue.getDescByCode(typesIndex).getNightMaxValue()); //根据默认值设置夜晚阈值最大值
            threshold.setNightMinValue(AlarmThresholdDefaultVlue.getDescByCode(typesIndex).getNightMinValue()); //根据默认值设置夜晚阈值最小值
            threshold.setDayMaxValue(AlarmThresholdDefaultVlue.getDescByCode(typesIndex).getDayMaxValue()); //根据默认值设置白天阈值最大值
            threshold.setDayMinValue(AlarmThresholdDefaultVlue.getDescByCode(typesIndex).getDayMinValue()); //根据默认值设置白天阈值最小值
        } else {
            threshold = listAlarm.get(0);
        }

        return threshold;
    }

    /**
     * 根据农场和地块ID获取TbAlarmDayTime对象，如果没有则返回一个默认值
     *
     * @param farmId 农场ID
     * @param plotId 地块ID
     */
    private TbAlarmDayTime getAlarmDayTime(String farmId, Long plotId) {
        TbAlarmDayTime dayTime = new TbAlarmDayTime();
        dayTime.setFarmId(farmId);
        dayTime.setPlotId(plotId);

        List<TbAlarmDayTime> listAlarm = alarmDayTimeMapper.selectTbAlarmDayTimeList(dayTime);
        if (listAlarm.size() <= 0) { //如果tb_alarm_threshold表还没有响应记录，则根据typesIndex取报警阈值默认值
            Time endT = new Time(DayAlarmDefaultConstants.DAY_END_HOUR, DayAlarmDefaultConstants.DAY_END_MINUTE, DayAlarmDefaultConstants.DAY_END_SECOND); //默认白天结束时间
            Time startT = new Time(DayAlarmDefaultConstants.DAY_START_HOUR, DayAlarmDefaultConstants.DAY_START_MINUTE, DayAlarmDefaultConstants.DAY_START_SECOND); //默认白天起始时间
            dayTime.setDayStartTime(startT);
            dayTime.setDayEndTime(endT);
        } else {
            dayTime = listAlarm.get(0);
        }

        return dayTime;
    }

    /**
     * 根据传入的两个白天的起止时间对象（分别为早上、晚上）判断当前时间是否为白天
     *
     * @param startT 开始时间
     * @param endT   终止时间
     */
    private boolean isDay(Time startT, Time endT) {
        Date now = new Date();

        if (startT == null) {
            startT = new Time(DayAlarmDefaultConstants.DAY_START_HOUR, DayAlarmDefaultConstants.DAY_START_MINUTE, DayAlarmDefaultConstants.DAY_START_SECOND); //默认白天起始时间
        }
        if (endT == null) {
            endT = new Time(DayAlarmDefaultConstants.DAY_END_HOUR, DayAlarmDefaultConstants.DAY_END_MINUTE, DayAlarmDefaultConstants.DAY_END_SECOND); //默认白天结束时间
        }

        int nowHour = now.getHours();
        int nowMinutes = now.getMinutes();
        if (nowHour > startT.getHours() && nowHour < endT.getHours()) {
            return true;
        } else if (nowHour == startT.getHours()) {
            if (nowMinutes >= startT.getMinutes()) {
                return true;
            } else {
                return false;
            }
        } else if (nowHour == endT.getHours()) {
            if (nowMinutes <= endT.getMinutes()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
