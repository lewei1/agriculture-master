package org.zcy.agriculture.mapper.irrigation;

import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbIrrigationTiming;

import java.util.List;

/**
 * 灌溉分组定时 数据层
 *
 * @author numberone
 * @date 2019-07-01
 */
public interface TbIrrigationTimingMapper {
    /**
     * 查询灌溉分组定时信息
     *
     * @param timingId 灌溉分组定时ID
     * @return 灌溉分组定时信息
     */
    TbIrrigationTiming selectTbIrrigationTimingById(Long timingId);

    /**
     * 查询灌溉分组定时列表
     *
     * @param tbIrrigationTiming 灌溉分组定时信息
     * @return 灌溉分组定时集合
     */
    List<TbIrrigationTiming> selectTbIrrigationTimingList(TbIrrigationTiming tbIrrigationTiming);

    /**
     * 新增灌溉分组定时
     *
     * @param tbIrrigationTiming 灌溉分组定时信息
     * @return 结果
     */
    int insertTbIrrigationTiming(TbIrrigationTiming tbIrrigationTiming);

    /**
     * 修改灌溉分组定时
     *
     * @param tbIrrigationTiming 灌溉分组定时信息
     * @return 结果
     */
    int updateTbIrrigationTiming(TbIrrigationTiming tbIrrigationTiming);

    /**
     * 删除灌溉分组定时
     *
     * @param timingId 灌溉分组定时ID
     * @return 结果
     */
    int deleteTbIrrigationTimingById(Long timingId);

    int deleteTbIrrigationTimingByGroupId(Long groupId);

    /**
     * 批量删除灌溉分组定时
     *
     * @param timingIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbIrrigationTimingByIds(String[] timingIds);

    /**
     * 新增灌溉分组定时列表
     * @param timingList
     * @return
     */
    int insertTbIrrigationTimingList(List<TbIrrigationTiming> timingList);

    /**
     * 批量更新分组定时列表状态
     * @param groupId
     * @param timingStatus
     * @return
     */
    int updateTbIrrigationTimingByGroupId(@Param("groupId") Long groupId, @Param("timingStatus") Integer timingStatus);
}