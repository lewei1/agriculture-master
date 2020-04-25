package org.zcy.agriculture.service;


import org.zcy.agriculture.entity.TbInventoryMaterial;

import java.util.List;

/**
 * 仓库和物品关联(库存) 服务层
 * 
 * @author linlq
 * @date 2019-07-02
 */
public interface ITbInventoryMaterialService 
{
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
     * 删除仓库和物品关联(库存)信息
     * 
     * @return 结果
     */
	public int deleteTbInventoryMaterialById(TbInventoryMaterial tbInventoryMaterial);
	
}
