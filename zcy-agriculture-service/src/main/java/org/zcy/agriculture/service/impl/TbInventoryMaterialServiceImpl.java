package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbInventoryMaterial;
import org.zcy.agriculture.mapper.TbInventoryMaterialMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbInventoryMaterialService;


/**
 * 仓库和物品关联(库存) 服务层实现
 * 
 * @author linlq
 * @date 2019-07-02
 */
@Service
public class TbInventoryMaterialServiceImpl implements ITbInventoryMaterialService
{
	@Autowired
	private TbInventoryMaterialMapper tbInventoryMaterialMapper;

	/**
     * 查询仓库和物品关联(库存)信息
     * 
     * @param warehouseMaterialId 仓库和物品关联(库存)ID
     * @return 仓库和物品关联(库存)信息
     */
    @Override
	public TbInventoryMaterial selectTbInventoryMaterialById(Long warehouseMaterialId)
	{
	    return tbInventoryMaterialMapper.selectTbInventoryMaterialById(warehouseMaterialId);
	}
	
	/**
     * 查询仓库和物品关联(库存)列表
     * 
     * @param tbInventoryMaterial 仓库和物品关联(库存)信息
     * @return 仓库和物品关联(库存)集合
     */
	@Override
	public List<TbInventoryMaterial> selectTbInventoryMaterialList(TbInventoryMaterial tbInventoryMaterial)
	{
	    return tbInventoryMaterialMapper.selectTbInventoryMaterialList(tbInventoryMaterial);
	}
	
    /**
     * 新增仓库和物品关联(库存)
     * 
     * @param tbInventoryMaterial 仓库和物品关联(库存)信息
     * @return 结果
     */
	@Override
	public int insertTbInventoryMaterial(TbInventoryMaterial tbInventoryMaterial)
	{
	    return tbInventoryMaterialMapper.insertTbInventoryMaterial(tbInventoryMaterial);
	}
	
	/**
     * 修改仓库和物品关联(库存)
     * 
     * @param tbInventoryMaterial 仓库和物品关联(库存)信息
     * @return 结果
     */
	@Override
	public int updateTbInventoryMaterial(TbInventoryMaterial tbInventoryMaterial)
	{
	    return tbInventoryMaterialMapper.updateTbInventoryMaterial(tbInventoryMaterial);
	}

	/**
     * 删除仓库和物品关联(库存)对象
     * 
     * @return 结果
     */
	@Override
	public int deleteTbInventoryMaterialById(TbInventoryMaterial tbInventoryMaterial)
	{
		return tbInventoryMaterialMapper.deleteTbInventoryMaterialById(tbInventoryMaterial);
	}
	
}
