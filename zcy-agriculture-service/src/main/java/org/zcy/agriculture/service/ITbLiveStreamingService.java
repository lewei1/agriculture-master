package org.zcy.agriculture.service;

import org.zcy.agriculture.param.LiveStreamingParam;
import org.zcy.agriculture.vo.LiveStreamingDetailVo;

import java.util.List;

/**
 * 视频直播 服务层
 *
 * @author lky
 * @date 2019-07-09
 */
public interface ITbLiveStreamingService {
    /**
     * 查询摄像头信息
     *
     * @param liveStreamingParam 设备信息
     * @return 设备信息
     */
    public LiveStreamingDetailVo selectCameraById(LiveStreamingParam liveStreamingParam);

    /**
     * 查询摄像头信息by序列号
     *
     * @param liveStreamingParam 设备信息
     * @return 设备信息
     */
    public LiveStreamingDetailVo selectCameraByDevNum(LiveStreamingParam liveStreamingParam);


    /**
     * 查询摄像头列表
     *
     * @param liveStreamingParam 设备信息
     * @return 设备集合
     */
    public List<LiveStreamingDetailVo> selectCameraList(LiveStreamingParam liveStreamingParam);


    /**
     * 新增摄像头
     *
     * @param liveStreamingParam 设备信息
     * @return 结果
     */
    public int insertCameraDevice(LiveStreamingParam liveStreamingParam);

    /**
     * 修改摄像头
     *
     * @param liveStreamingParam 设备信息
     * @return 结果
     */
    public int updateCameraDevice(LiveStreamingParam liveStreamingParam);

    /**
     * 删除摄像头信息
     *
     * @return 结果
     */
    public int deleteCameraDeviceById(LiveStreamingParam liveStreamingParam);


}
