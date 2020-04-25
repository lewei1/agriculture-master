package org.zcy.agriculture.mapper.irrigation;

import org.zcy.agriculture.entity.TbIrrigationDeviceImg;

import java.util.List;

/**
 * 灌溉设备图片 数据层
 *
 * @author numberone
 * @date 2019-07-22
 */
public interface TbIrrigationDeviceImgMapper {
    /**
     * 查询灌溉设备图片信息
     *
     * @param imgId 灌溉设备图片ID
     * @return 灌溉设备图片信息
     */
    TbIrrigationDeviceImg selectTbIrrigationDeviceImgById(Long imgId);

    /**
     * 查询灌溉设备图片列表
     *
     * @param tbIrrigationDeviceImg 灌溉设备图片信息
     * @return 灌溉设备图片集合
     */
    List<TbIrrigationDeviceImg> selectTbIrrigationDeviceImgList(TbIrrigationDeviceImg tbIrrigationDeviceImg);

    /**
     * 新增灌溉设备图片
     *
     * @param tbIrrigationDeviceImg 灌溉设备图片信息
     * @return 结果
     */
    int insertTbIrrigationDeviceImg(TbIrrigationDeviceImg tbIrrigationDeviceImg);

    /**
     * 修改灌溉设备图片
     *
     * @param tbIrrigationDeviceImg 灌溉设备图片信息
     * @return 结果
     */
    int updateTbIrrigationDeviceImg(TbIrrigationDeviceImg tbIrrigationDeviceImg);

    /**
     * 删除灌溉设备图片
     *
     * @param imgId 灌溉设备图片ID
     * @return 结果
     */
    int deleteTbIrrigationDeviceImgById(Long imgId);

    /**
     * 批量删除灌溉设备图片
     *
     * @param imgIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbIrrigationDeviceImgByIds(String[] imgIds);

    /**
     *通过设备id删除记录
     * @param devId
     * @return
     */
    int deleteIrrigationDeviceImgByDevId(Long devId);

    /**
     * 查询默认图片列表
     * @return
     */
    List<TbIrrigationDeviceImg> selectDefaultDeviceImgList();

    /**
     * 通过设备id查询设备图片信息
     * @param devId
     * @return
     */
    TbIrrigationDeviceImg selectIrrigationDeviceImgByDevId(Long devId);

}