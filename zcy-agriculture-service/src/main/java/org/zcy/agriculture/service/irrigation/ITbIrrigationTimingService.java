package org.zcy.agriculture.service.irrigation;

import org.zcy.agriculture.entity.TbIrrigationTiming;
import org.zcy.agriculture.param.irrigation.IrrigationTimingDetailParam;

import java.util.List;

/**
 * 灌溉分组定时 服务层
 *
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbIrrigationTimingService {
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
     * 删除灌溉分组定时信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTbIrrigationTimingByIds(String ids);

    /**
     * 新增定时详情
     * @param irrigationTimingDetailParam
     * @return
     */
    int insertTbIrrigationTimingDetail(IrrigationTimingDetailParam irrigationTimingDetailParam);

    /**
     * 更新定时详情
     * @param irrigationTimingDetailParam
     * @return
     * @throws Exception
     */
    int updateTbIrrigationTimingDetail(IrrigationTimingDetailParam irrigationTimingDetailParam) throws Exception;

    /**
     * 更新定时状态
     * @param groupId
     * @param timingStatus
     * @return
     */
    int updateTbIrrigationTimingByGroupId(Long groupId, Integer timingStatus);
}
