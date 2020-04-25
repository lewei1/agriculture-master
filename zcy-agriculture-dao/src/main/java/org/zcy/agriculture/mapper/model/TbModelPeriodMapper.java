package org.zcy.agriculture.mapper.model;

import org.zcy.agriculture.entity.TbModelPeriod;

import java.util.HashMap;
import java.util.List;

/**
 * 作物模型-阶段 数据层
 *
 * @author numberone
 * @date 2019-06-26
 */
public interface TbModelPeriodMapper {
    /**
     * 查询作物模型-阶段信息
     *
     * @param periodId 作物模型-阶段ID
     * @return 作物模型-阶段信息
     */
    TbModelPeriod selectTbModelPeriodById(Long periodId);

    /**
     * 查询作物模型-阶段列表
     *
     * @param tbModelPeriod 作物模型-阶段信息
     * @return 作物模型-阶段集合
     */
    List<TbModelPeriod> selectTbModelPeriodList(TbModelPeriod tbModelPeriod);

    /**
     * 新增作物模型-阶段
     *
     * @param tbModelPeriod 作物模型-阶段信息
     * @return 结果
     */
    int insertTbModelPeriod(TbModelPeriod tbModelPeriod);

    /**
     * 修改作物模型-阶段
     *
     * @param tbModelPeriod 作物模型-阶段信息
     * @return 结果
     */
    int updateTbModelPeriod(TbModelPeriod tbModelPeriod);

    /**
     * 删除作物模型-阶段
     *
     * @param periodId 作物模型-阶段ID
     * @return 结果
     */
    int deleteTbModelPeriodById(Long periodId);

    /**
     * 批量删除作物模型-阶段
     *
     * @param periodIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbModelPeriodByIds(String[] periodIds);

    /**
     * 根据种植作物，作物标准， 作物环境查询模型
     * @param modelId
     * @param farmId
     * @return
     */
    public List<HashMap<String,Object>> selectByCropCategoryList(Long modelId,String farmId);
}