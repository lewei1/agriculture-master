package org.zcy.agriculture.merchant.quartz;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.entity.TbCameraImage;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.enums.DevTypeEnum;
import org.zcy.agriculture.param.CameraIOTParam;
import org.zcy.agriculture.service.ITbCameraImageService;
import org.zcy.agriculture.service.ITbResDeviceService;
import org.zcy.agriculture.util.HttpUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.device.CameraPhotoIOTReturnVo;
import org.zcy.agriculture.vo.device.DevicePhotoVo;
import org.zcy.agriculture.vo.irrigation.IrrigationReturnIOTVo;

import java.util.Date;
import java.util.List;

public class CameraPhotoJob implements Job {
    public static Logger logger = LogManager.getLogger(CameraPhotoJob.class);

    @Autowired
    private ITbResDeviceService deviceService;

    @Autowired
    private ITbCameraImageService cameraImageService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date now = new Date();
        System.out.println("拍照定时器@@@" + now);

        /**查询出所有摄像头*/
        TbResDevice tbResDevice = new TbResDevice();
        tbResDevice.setDevType(DevTypeEnum.CAMERA.getCode());
        List<TbResDevice> list = deviceService.selectTbResDeviceList(tbResDevice);
        for (TbResDevice d:list) {
//            Date lastPhotoTime = d.getLastPhotoTime();
//            Float shootingInterval = d.getShootingInterval();
//            if (lastPhotoTime != null && shootingInterval!= null) {
//                Date nextPhotoTime = DateUtils.addDateMinute(lastPhotoTime,shootingInterval);
//                if (DateUtils.compareDate(nextPhotoTime,now)){
//                    System.out.println("该执行了@@@");
//                }
//            }

            /**每个设备分别向物联网端发送拍照请求*/
            if (!ValidationUtil.isEmpty(d) && !ValidationUtil.isEmpty(d.getDevNum())) { //只有设备序列号非空的才做处理
                TbResDevice device = new TbResDevice();
                device.setDevNum(d.getDevNum());
                List<TbResDevice> deviceList = Lists.newArrayList();
                deviceList.add(device);

                /**构造请求的参数*/
                CameraIOTParam param = new  CameraIOTParam();
                param.setMethod(ThingsboardUrlConstants.DEVICE_CAMERA_METHOD);
                param.setDeviceList(deviceList);

                //发送拍照请求
                String result = HttpUtils.doPostWithToken(ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_CONTROL_URL , JSONObject.toJSONString(param), deviceService.getIOTToken());

                /**处理返回的结果*/
                if (result!= null && result!= "") {
                    CameraPhotoIOTReturnVo returnIOTVo = JSONObject.parseObject(result, CameraPhotoIOTReturnVo.class);
                    List<DevicePhotoVo> listDevicePhotoVo =  returnIOTVo.getDeviceList();
                    if (listDevicePhotoVo != null && listDevicePhotoVo.size() > 0) {
                        for (DevicePhotoVo vo : listDevicePhotoVo) { //遍历返回结构里的“deviceList”字段

                            TbResDevice deviceTemp = new TbResDevice();
                            deviceTemp.setDevNum(vo.getDevNum());
                            List<TbResDevice> list2= deviceService.selectTbResDeviceList(deviceTemp);
                            /**将物联网端获取的图片信息存入拍照记录表*/
                            if (list2.size()>0 && vo.getPicUrl()!= "") {
                                TbResDevice dev = list2.get(0);
                                TbCameraImage tbCameraImage = new TbCameraImage();
                                tbCameraImage.setDevId(dev.getDevId());   //设置好摄像头ID
                                tbCameraImage.setLoc(vo.getPicUrl());     //设置好图片位置
                                tbCameraImage.setCreateTime(new Date());  //设置好创建时间
                                tbCameraImage.setFarmId(dev.getFarmId()); //设置好农场ID
                                cameraImageService.insertTbCameraImage(tbCameraImage);
                            }
                        }
                    }
                }
            }
        }
    }

}
