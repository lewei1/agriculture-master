package org.zcy.agriculture.service.irrigation;

import org.zcy.agriculture.entity.TbIrrigationFixQuantity;
import org.zcy.agriculture.param.irrigation.IrrigationFixQuantityDetailParam;

import java.util.List;

/**
 * 灌溉分组定量 服务层
 *
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbIrrigationFixQuantityService {
    /**
     * 查询灌溉分组定量信息
     *
     * @param fixQuantityId 灌溉分组定量ID
     * @return 灌溉分组定量信息
     */
    TbIrrigationFixQuantity selectTbIrrigationFixQuantityById(Long fixQuantityId);

    /**
     * 查询灌溉分组定量列表
     *
     * @param tbIrrigationFixQuantity 灌溉分组定量信息
     * @return 灌溉分组定量集合
     */
    List<TbIrrigationFixQuantity> selectTbIrrigationFixQuantityList(TbIrrigationFixQuantity tbIrrigationFixQuantity);

    /**
     * 新增灌溉分组定量
     *
     * @param tbIrrigationFixQuantity 灌溉分组定量信息
     * @return 结果
     */
    int insertTbIrrigationFixQuantity(TbIrrigationFixQuantity tbIrrigationFixQuantity);

    /**
     * 修改灌溉分组定量
     *
     * @param tbIrrigationFixQuantity 灌溉分组定量信息
     * @return 结果
     */
    int updateTbIrrigationFixQuantity(TbIrrigationFixQuantity tbIrrigationFixQuantity);

    /**
     * 删除灌溉分组定量信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTbIrrigationFixQuantityByIds(String ids);

    /**
     * 新增定量详情
     * @param param
     * @return
     */
    int insertIrrigationFixQuantityDetail(IrrigationFixQuantityDetailParam param);

    /**
     * 更新定量详情
     * @param param
     * @return
     * @throws Exception
     */
    int updateIrrigationFixQuantityDetail(IrrigationFixQuantityDetailParam param) throws Exception;

    /**
     * 更新定量状态
     * @param groupId
     * @param fixQuantityStatus
     * @return
     */
    int updateIrrigationFixQuantityByGroupId(Long groupId, Integer fixQuantityStatus);
}
