package org.zcy.agriculture.mapper.model;

import org.zcy.agriculture.entity.TbModelPeriodFarming;

import java.util.List;

/**
 * 作物模型-阶段农事 数据层
 *
 * @author numberone
 * @date 2019-06-26
 */
public interface TbModelPeriodFarmingMapper {
    /**
     * 查询作物模型-阶段农事信息
     *
     * @param farmingId 作物模型-阶段农事ID
     * @return 作物模型-阶段农事信息
     */
    TbModelPeriodFarming selectTbModelPeriodFarmingById(Long farmingId);

    /**
     * 查询作物模型-阶段农事列表
     *
     * @param tbModelPeriodFarming 作物模型-阶段农事信息
     * @return 作物模型-阶段农事集合
     */
    List<TbModelPeriodFarming> selectTbModelPeriodFarmingList(TbModelPeriodFarming tbModelPeriodFarming);

    /**
     * 根据流程Id列表批量获取
     *
     * @param list
     * @return
     */
    List<TbModelPeriodFarming> selectTbModelPeriodFarmingListByIds(List<Long> list);

    /**
     * 新增作物模型-阶段农事
     *
     * @param tbModelPeriodFarming 作物模型-阶段农事信息
     * @return 结果
     */
    int insertTbModelPeriodFarming(TbModelPeriodFarming tbModelPeriodFarming);

    /**
     * 修改作物模型-阶段农事
     *
     * @param tbModelPeriodFarming 作物模型-阶段农事信息
     * @return 结果
     */
    int updateTbModelPeriodFarming(TbModelPeriodFarming tbModelPeriodFarming);

    /**
     * 删除作物模型-阶段农事
     *
     * @param farmingId 作物模型-阶段农事ID
     * @return 结果
     */
    int deleteTbModelPeriodFarmingById(Long farmingId);

    /**
     * 批量删除作物模型-阶段农事
     *
     * @param farmingIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbModelPeriodFarmingByIds(String[] farmingIds);

}