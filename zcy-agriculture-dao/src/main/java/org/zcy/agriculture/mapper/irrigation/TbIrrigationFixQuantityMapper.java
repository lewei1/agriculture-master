package org.zcy.agriculture.mapper.irrigation;


import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbIrrigationFixQuantity;

import java.util.List;

/**
 * 灌溉分组定量 数据层
 *
 * @author numberone
 * @date 2019-07-01
 */
public interface TbIrrigationFixQuantityMapper {
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
     * 删除灌溉分组定量
     *
     * @param fixQuantityId 灌溉分组定量ID
     * @return 结果
     */
    int deleteTbIrrigationFixQuantityById(Long fixQuantityId);

    /**
     * 批量删除灌溉分组定量
     *
     * @param fixQuantityIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbIrrigationFixQuantityByIds(String[] fixQuantityIds);

    /**
     * 新增定量列表信息
     * @param fixQuantityList
     * @return
     */
    int insertIrrigationFixQuantityList(List<TbIrrigationFixQuantity> fixQuantityList);

    /**
     * 根据分组id，删除定量分组信息
     * @param groupId
     * @return
     */
    int deleteIrrigationFixQuantityByGroupId(Long groupId);

    /**
     * 批量更新定量分组设备状态
     * @param groupId
     * @param fixQuantityStatus
     * @return
     */
    int updateIrrigationFixQuantityByGroupId(@Param("groupId") Long groupId, @Param("fixQuantityStatus") Integer fixQuantityStatus);
}