package org.zcy.agriculture.service.irrigation;

import org.zcy.agriculture.entity.TbIrrigationDevice;
import org.zcy.agriculture.entity.TbIrrigationDeviceImg;
import org.zcy.agriculture.entity.TbIrrigationGroup;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.param.irrigation.IrrigationDetailParam;
import org.zcy.agriculture.param.irrigation.IrrigationDeviceDetailParam;
import org.zcy.agriculture.vo.irrigation.IrrigationDeviceDetailVo;
import org.zcy.agriculture.vo.irrigation.IrrigationGroupDetailVo;

import java.util.List;

/**
 * 灌溉中心分组 服务层
 *
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbIrrigationGroupService {
    /**
     * 查询灌溉中心分组信息
     *
     * @param groupId 灌溉中心分组ID
     * @return 灌溉中心分组信息
     */
    TbIrrigationGroup selectTbIrrigationGroupById(Long groupId);

    /**
     * 查询灌溉中心分组列表
     *
     * @param tbIrrigationGroup 灌溉中心分组信息
     * @return 灌溉中心分组集合
     */
    List<TbIrrigationGroup> selectTbIrrigationGroupList(TbIrrigationGroup tbIrrigationGroup);

    /**
     * 新增灌溉中心分组
     *
     * @param tbIrrigationGroup 灌溉中心分组信息
     * @return 结果
     */
    int insertTbIrrigationGroup(TbIrrigationGroup tbIrrigationGroup);

    /**
     * 修改灌溉中心分组
     *
     * @param tbIrrigationGroup 灌溉中心分组信息
     * @return 结果
     */
    int updateTbIrrigationGroup(IrrigationDetailParam tbIrrigationGroup);

    /**
     * 删除灌溉中心分组信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTbIrrigationGroupByIds(String ids);

    /**
     * 新增灌溉分组主表和关联表详情
     * @param irrigationDetailParam
     * @return
     * @throws Exception
     */
    int insertTbIrrigationGroupDetail(IrrigationDetailParam irrigationDetailParam) throws Exception;

    /**
     * 根据灌溉信息，删除分组设备
     * @param irrigationDevice
     * @return
     */
    int deleteTbIrrigationDevice(TbIrrigationDevice irrigationDevice);

    /**
     * 根据农场id查询灌溉设备详情
     * @param farmId
     * @return
     */
    List<IrrigationGroupDetailVo> selectIrrigationGroupDetailList(String farmId);

    /**
     *更新灌溉分组设备状态
     * @param group
     * @return
     */
    int updateIrrigationGroupStatus(TbIrrigationGroup group);

    /**
     * 新增灌溉设备
     * @param param
     * @return
     */
    int insertIrrigationDevice(IrrigationDeviceDetailParam param) throws Exception;

    /**
     * 更新灌溉设备
     * @param param
     * @return
     */
    int updateIrrigationDevice(IrrigationDeviceDetailParam param) throws Exception;


    /**
     * 查询默认图片列表
     * @return
     */
    List<TbIrrigationDeviceImg> selectDefaultDeviceImgList();

    /**
     * 查看灌溉设备列表详情
     * @param resDevice
     * @return
     */
    List<IrrigationDeviceDetailVo> selectDeviceDetailList(TbResDevice resDevice);
}
