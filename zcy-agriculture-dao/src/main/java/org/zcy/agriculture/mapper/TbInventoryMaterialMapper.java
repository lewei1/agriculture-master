package org.zcy.agriculture.mapper;


import org.zcy.agriculture.entity.TbInventoryMaterial;

import java.util.List;

/**
 * 仓库和物品关联(库存) 数据层
 *
 * @author linlq
 * @date 2019-07-02
 */
public interface TbInventoryMaterialMapper {
    /**
     * 查询仓库和物品关联(库存)信息
     *
     * @param warehouseMaterialId 仓库和物品关联(库存)ID
     * @return 仓库和物品关联(库存)信息
     */
    public TbInventoryMaterial selectTbInventoryMaterialById(Long warehouseMaterialId);

    /**
     * 查询仓库和物品关联(库存)列表
     *
     * @param tbInventoryMaterial 仓库和物品关联(库存)信息
     * @return 仓库和物品关联(库存)集合
     */
    public List<TbInventoryMaterial> selectTbInventoryMaterialList(TbInventoryMaterial tbInventoryMaterial);

    /**
     * 查询仓库和物品关联(库存)列表 用于报警监测
     *
     * @param tbInventoryMaterial 仓库和物品关联(库存)信息
     * @return 仓库和物品关联(库存)集合
     */
    public List<TbInventoryMaterial> selectTbInventoryMaterialListForQuartz(TbInventoryMaterial tbInventoryMaterial);


    /**
     * 新增仓库和物品关联(库存)
     *
     * @param tbInventoryMaterial 仓库和物品关联(库存)信息
     * @return 结果
     */
    public int insertTbInventoryMaterial(TbInventoryMaterial tbInventoryMaterial);

    /**
     * 修改仓库和物品关联(库存)
     *
     * @param tbInventoryMaterial 仓库和物品关联(库存)信息
     * @return 结果
     */
    public int updateTbInventoryMaterial(TbInventoryMaterial tbInventoryMaterial);

    /**
     * 删除仓库和物品关联(库存)
     *
     * @return 结果
     */
    public int deleteTbInventoryMaterialById(TbInventoryMaterial tbInventoryMaterial);


    /**
     * 删除库存 by物品id
     *
     * @return 结果
     */
    public int deleteTbInventoryMaterialByMaterialId(TbInventoryMaterial tbInventoryMaterial);

    /**
     * 删除库存 by仓库id
     *
     * @return 结果
     */
    public int deleteTbInventoryMaterialByWarehouseId(TbInventoryMaterial tbInventoryMaterial);

    /**
     * 批量删除仓库和物品关联(库存)
     *
     * @param warehouseMaterialIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbInventoryMaterialByIds(String[] warehouseMaterialIds);

}