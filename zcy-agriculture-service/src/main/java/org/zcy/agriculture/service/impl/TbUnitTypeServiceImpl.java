package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbUnitType;
import org.zcy.agriculture.mapper.TbUnitTypeMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbUnitTypeService;

/**
 * 物品-单位 服务层实现
 * 
 * @author numberone
 * @date 2019-06-21
 */
@Service
public class TbUnitTypeServiceImpl implements ITbUnitTypeService
{
	@Autowired
	private TbUnitTypeMapper tbUnitTypeMapper;

	/**
     * 查询物品-单位信息
     * 
     * @return 物品-单位信息
     */
    @Override
	public TbUnitType selectTbUnitTypeById(TbUnitType tbUnitType)
	{
	    return tbUnitTypeMapper.selectTbUnitTypeById(tbUnitType);
	}
	
	/**
     * 查询物品-单位列表
     * 
     * @param tbUnitType 物品-单位信息
     * @return 物品-单位集合
     */
	@Override
	public List<TbUnitType> selectTbUnitTypeList(TbUnitType tbUnitType)
	{
	    return tbUnitTypeMapper.selectTbUnitTypeList(tbUnitType);
	}

	/**
	 * 查询物品-单位列表 by物品id
	 *
	 * @param tbUnitType 物品-单位信息
	 * @return 物品-单位集合
	 */
	@Override
	public List<TbUnitType> selectTbUnitTypeListByMaterialId(TbUnitType tbUnitType)
	{
		return tbUnitTypeMapper.selectTbUnitTypeListByMaterialId(tbUnitType);
	}
	
    /**
     * 新增物品-单位
     * 
     * @param tbUnitType 物品-单位信息
     * @return 结果
     */
	@Override
	public int insertTbUnitType(TbUnitType tbUnitType)
	{
	    return tbUnitTypeMapper.insertTbUnitType(tbUnitType);
	}
	
	/**
     * 修改物品-单位
     * 
     * @param tbUnitType 物品-单位信息
     * @return 结果
     */
	@Override
	public int updateTbUnitType(TbUnitType tbUnitType)
	{
	    return tbUnitTypeMapper.updateTbUnitType(tbUnitType);
	}

	/**
     * 删除物品-单位对象
     * 
     * @return 结果
     */
	@Override
	public int deleteTbUnitTypeById(TbUnitType tbUnitType)
	{
		return tbUnitTypeMapper.deleteTbUnitTypeById(tbUnitType);
	}
	
}
