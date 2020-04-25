package org.zcy.agriculture.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.enums.AlarmThresholdTypes;
import org.zcy.agriculture.enums.ThingsboardDeviceEnum;
import org.zcy.agriculture.mapper.TbLiveStreamingMapper;
import org.zcy.agriculture.mapper.TbResDeviceAttributesMapper;
import org.zcy.agriculture.param.LiveStreamingParam;
import org.zcy.agriculture.service.ITbLiveStreamingService;
import org.zcy.agriculture.util.HttpCodeMsg;
import org.zcy.agriculture.util.HttpUtils;
import org.zcy.agriculture.vo.DeviceStatusStatisticsVo;
import org.zcy.agriculture.vo.DeviceTypeStatisticsVo;
import org.zcy.agriculture.vo.LiveStreamingDetailVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 视频直播 服务层实现
 *
 * @author lky
 * @date 2019-07-09
 */
@Service
public class TbLiveStreamingServiceImpl extends BaseServiceImpl implements ITbLiveStreamingService {
    @Autowired
    private TbLiveStreamingMapper tbLiveStreamingMapper;


    /**
     * 查询摄像头信息
     *
     * @param liveStreamingParam
     * @return 设备信息
     */
    @Override
    public LiveStreamingDetailVo selectCameraById(LiveStreamingParam liveStreamingParam) {
        return tbLiveStreamingMapper.selectCameraById(liveStreamingParam);
    }

    /**
     * 查询摄像头信息by序列号
     *
     * @param liveStreamingParam
     * @return 设备信息
     */
    @Override
    public LiveStreamingDetailVo selectCameraByDevNum(LiveStreamingParam liveStreamingParam) {
        return tbLiveStreamingMapper.selectCameraByDevNum(liveStreamingParam);
    }

    /**
     * 查询摄像头列表
     *
     * @param liveStreamingParam 设备信息
     * @return
     */
    @Override
    public List<LiveStreamingDetailVo> selectCameraList(LiveStreamingParam liveStreamingParam) {
        return tbLiveStreamingMapper.selectCameraList(liveStreamingParam);
    }


    /**
     * 新增摄像头
     *
     * @param liveStreamingParam 设备信息
     * @return 结果
     */
    @Override
    public int insertCameraDevice(LiveStreamingParam liveStreamingParam) {
        int insertResult = tbLiveStreamingMapper.insertCameraDevice(liveStreamingParam);
        return insertResult;
    }

    /**
     * 修改摄像头
     *
     * @param liveStreamingParam 设备信息
     * @return 结果
     */
    @Override
    public int updateCameraDevice(LiveStreamingParam liveStreamingParam) {
        return tbLiveStreamingMapper.updateCameraDevice(liveStreamingParam);
    }

    /**
     * 删除摄像头对象
     *
     * @return 结果
     */
    @Override
    public int deleteCameraDeviceById(LiveStreamingParam liveStreamingParam) {
        int result = tbLiveStreamingMapper.deleteCameraDeviceById(liveStreamingParam);
        return result;
    }


}
