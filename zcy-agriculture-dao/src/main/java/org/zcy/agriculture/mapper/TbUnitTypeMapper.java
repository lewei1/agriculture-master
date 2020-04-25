package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbUnitType;

import java.util.List;

/**
 * 物品-单位 数据层
 *
 * @author numberone
 * @date 2019-06-21
 */
public interface TbUnitTypeMapper {
    /**
     * 查询物品-单位信息
     *
     * @return 物品-单位信息
     */
    public TbUnitType selectTbUnitTypeById(TbUnitType tbUnitType);

    /**
     * 查询物品-单位列表
     *
     * @param tbUnitType 物品-单位信息
     * @return 物品-单位集合
     */
    public List<TbUnitType> selectTbUnitTypeList(TbUnitType tbUnitType);

    /**
     * 查询物品-单位列表 by物品id
     *
     * @param tbUnitType 物品-单位信息
     * @return 物品-单位集合
     */
    public List<TbUnitType> selectTbUnitTypeListByMaterialId(TbUnitType tbUnitType);

    /**
     * 新增物品-单位
     *
     * @param tbUnitType 物品-单位信息
     * @return 结果
     */
    public int insertTbUnitType(TbUnitType tbUnitType);

    /**
     * 修改物品-单位
     *
     * @param tbUnitType 物品-单位信息
     * @return 结果
     */
    public int updateTbUnitType(TbUnitType tbUnitType);

    /**
     * 删除物品-单位
     *
     * @return 结果
     */
    public int deleteTbUnitTypeById(TbUnitType tbUnitType);

    /**
     * 批量删除物品-单位
     *
     * @param materialUnitIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbUnitTypeByIds(String[] materialUnitIds);

}