package org.zcy.agriculture.mapper.irrigation;

import org.zcy.agriculture.entity.TbIrrigationGroup;
import org.zcy.agriculture.vo.irrigation.IrrigationGroupDetailVo;

import java.util.List;

/**
 * 灌溉中心分组 数据层
 *
 * @author numberone
 * @date 2019-07-01
 */
public interface TbIrrigationGroupMapper {
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
    int updateTbIrrigationGroup(TbIrrigationGroup tbIrrigationGroup);

    /**
     * 删除灌溉中心分组
     *
     * @param groupId 灌溉中心分组ID
     * @return 结果
     */
    int deleteTbIrrigationGroupById(Long groupId);

    /**
     * 批量删除灌溉中心分组
     *
     * @param groupIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbIrrigationGroupByIds(String[] groupIds);

    /**
     * 根据农场id，查询灌溉分组详情
     *
     * @param farmId
     * @return
     */
    List<IrrigationGroupDetailVo> selectIrrigationGroupDetailList(String farmId);
}