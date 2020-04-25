package org.zcy.agriculture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbPlanMaterial;
import org.zcy.agriculture.mapper.TbPlanMaterialMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbPlanMaterialService;

/**
 * 计划使用的物品 服务层实现
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbPlanMaterialServiceImpl implements ITbPlanMaterialService 
{
	@Autowired
	private TbPlanMaterialMapper tbPlanMaterialMapper;

	/**
     * 查询计划使用的物品信息
     * 
     * @param planMaterialId 计划使用的物品ID
     * @return 计划使用的物品信息
     */
    @Override
	public TbPlanMaterial selectTbPlanMaterialById(Long planMaterialId)
	{
	    return tbPlanMaterialMapper.selectTbPlanMaterialById(planMaterialId);
	}
	
	/**
     * 查询计划使用的物品列表
     * 
     * @param tbPlanMaterial 计划使用的物品信息
     * @return 计划使用的物品集合
     */
	@Override
	public List<TbPlanMaterial> selectTbPlanMaterialList(TbPlanMaterial tbPlanMaterial)
	{
	    return tbPlanMaterialMapper.selectTbPlanMaterialList(tbPlanMaterial);
	}
	
    /**
     * 新增计划使用的物品
     * 
     * @param tbPlanMaterial 计划使用的物品信息
     * @return 结果
     */
	@Override
	public int insertTbPlanMaterial(TbPlanMaterial tbPlanMaterial)
	{
	    return tbPlanMaterialMapper.insertTbPlanMaterial(tbPlanMaterial);
	}
	
	/**
     * 修改计划使用的物品
     * 
     * @param tbPlanMaterial 计划使用的物品信息
     * @return 结果
     */
	@Override
	public int updateTbPlanMaterial(TbPlanMaterial tbPlanMaterial)
	{
	    return tbPlanMaterialMapper.updateTbPlanMaterial(tbPlanMaterial);
	}

	/**
     * 删除计划使用的物品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbPlanMaterialByIds(String ids)
	{
		return tbPlanMaterialMapper.deleteTbPlanMaterialByIds(Convert.toStrArray(ids));
	}
	
}
