package org.zcy.agriculture.mapper.model;

import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbModel;
import org.zcy.agriculture.param.model.ModelOverviewParam;
import org.zcy.agriculture.vo.model.ModelDetailVo;
import org.zcy.agriculture.vo.model.ModelOverviewVo;
import org.zcy.agriculture.vo.model.ModelStatisticsVo;

import java.util.List;

/**
 * 作物模型 数据层
 *
 * @author numberone
 * @date 2019-06-26
 */
public interface TbModelMapper {
    /**
     * 查询作物模型信息
     *
     * @param modelId 作物模型ID
     * @return 作物模型信息
     */
    TbModel selectTbModelById(Long modelId);

    /**
     * 查询作物模型列表
     *
     * @param tbModel 作物模型信息
     * @return 作物模型集合
     */
    List<TbModel> selectTbModelList(TbModel tbModel);

    /**
     * 新增作物模型
     *
     * @param tbModel 作物模型信息
     * @return 结果
     */
    int insertTbModel(TbModel tbModel);

    /**
     * 修改作物模型
     *
     * @param tbModel 作物模型信息
     * @return 结果
     */
    int updateTbModel(TbModel tbModel);

    /**
     * 删除作物模型
     *
     * @param modelId 作物模型ID
     * @return 结果
     */
    int deleteTbModelById(Long modelId);

    /**
     * 批量删除作物模型
     *
     * @param modelIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbModelByIds(String[] modelIds);

    /**
     * 查询模型概览列表
     * @param param
     * @return
     */
    List<ModelOverviewVo> selectModelOverviewList(ModelOverviewParam param);

    /**
     * 查询统计信息
     * @param farmId
     * @return
     */
    ModelStatisticsVo selectModelStatistics(String farmId);

    /**
     * 查询模型详情
     * @param modelId
     * @param farmId
     * @return
     */
    ModelDetailVo selectModelDetail(@Param("modelId") Long modelId, @Param("farmId") String farmId);

    /**
     * 添加标准计划后 模型使用次数+1
     * @param list
     * @return
     */
    public int updateTbModelByModelUseTime(List<Long> list);
}