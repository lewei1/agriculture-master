package org.zcy.agriculture.mapper.irrigation;

import org.zcy.agriculture.entity.TbIrrigationDevice;

import java.util.List;

/**
 * 灌溉分组设备中间 数据层
 *
 * @author numberone
 * @date 2019-07-01
 */
public interface TbIrrigationDeviceMapper {
    /**
     * 查询灌溉分组设备中间信息
     *
     * @param deviceId 灌溉分组设备中间ID
     * @return 灌溉分组设备中间信息
     */
    TbIrrigationDevice selectTbIrrigationDeviceById(Long deviceId);

    /**
     * 查询灌溉分组设备中间列表
     *
     * @param tbIrrigationDevice 灌溉分组设备中间信息
     * @return 灌溉分组设备中间集合
     */
    List<TbIrrigationDevice> selectTbIrrigationDeviceList(TbIrrigationDevice tbIrrigationDevice);

    /**
     * 新增灌溉分组设备中间
     *
     * @param tbIrrigationDevice 灌溉分组设备中间信息
     * @return 结果
     */
    int insertTbIrrigationDevice(TbIrrigationDevice tbIrrigationDevice);

    /**
     * 修改灌溉分组设备中间
     *
     * @param tbIrrigationDevice 灌溉分组设备中间信息
     * @return 结果
     */
    int updateTbIrrigationDevice(TbIrrigationDevice tbIrrigationDevice);

    /**
     * 新增灌溉关联表列表信息
     *
     * @param deviceList
     * @return
     * @throws Exception
     */
    int insertTbIrrigationDeviceList(List<TbIrrigationDevice> deviceList);

    /**
     * 根据灌溉信息，删除灌溉中心分组设备
     *
     * @param tbIrrigationDevice
     * @return
     */
    int deleteTbIrrigationDevice(TbIrrigationDevice tbIrrigationDevice);

}