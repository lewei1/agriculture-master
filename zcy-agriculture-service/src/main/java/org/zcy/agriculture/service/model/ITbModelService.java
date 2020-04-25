package org.zcy.agriculture.service.model;

import org.zcy.agriculture.entity.TbModel;
import org.zcy.agriculture.param.model.ModelCropCategoryParam;
import org.zcy.agriculture.param.model.ModelOverviewParam;
import org.zcy.agriculture.param.model.ModelDetailParam;
import org.zcy.agriculture.vo.model.*;

import java.util.HashMap;
import java.util.List;

/**
 * 作物模型 服务层
 *
 * @author numberone
 * @date 2019-06-26
 */
public interface ITbModelService {
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
     * 删除作物模型信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTbModelByIds(String ids);

    /**
     *
     * @param detailParam
     * @return
     * @throws Exception
     */
    int insertTbModelDetail(ModelDetailParam detailParam) throws Exception;

    /**
     *获取select下拉框列表集合
     * @return
     */
    ModelSelectDataVo selectAddPageList(String farmId);

    /**
     *查询模型概览
     * @param param
     * @return
     */
    List<ModelOverviewVo> selectModelOverviewList(ModelOverviewParam param);

    /**
     *查询模型统计数据
     * @param farmId
     * @return
     */
    ModelStatisticsVo selectModelStatistics(String farmId);

    /**
     *根据作物id，查询作物模型列表
     * @param param
     * @return
     */
    List<ModelCropCategoryVo> selectModelListByCropCategoryId(ModelCropCategoryParam param);

    /**
     *根据id，查询作物模型详情
     * @param modelId
     * @param farmId
     * @return
     */
    ModelDetailVo selectModelDetailById(Long modelId, String farmId);
    /**
     * 根据种植作物，作物标准， 作物环境查询模型
     * @param cmodelId
     * @param farmId
     * @return
     */
    public List<HashMap<String,Object>> selectByCropCategoryList(Long modelId,String farmId);
}
