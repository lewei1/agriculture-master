package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbFarmingType;
import org.zcy.agriculture.mapper.TbFarmingTypeMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbFarmingTypeService;

/**
 * 农事类型 服务层实现
 * 
 * @author numberone
 * @date 2019-06-26
 */
@Service
public class TbFarmingTypeServiceImpl implements ITbFarmingTypeService
{
	@Autowired
	private TbFarmingTypeMapper tbFarmingTypeMapper;

	/**
     * 查询农事类型信息
     * 
     * @param farmingTypeId 农事类型ID
     * @return 农事类型信息
     */
    @Override
	public TbFarmingType selectTbFarmingTypeById(Long farmingTypeId)
	{
	    return tbFarmingTypeMapper.selectTbFarmingTypeById(farmingTypeId);
	}
	
	/**
     * 查询农事类型列表
     * 
     * @param tbFarmingType 农事类型信息
     * @return 农事类型集合
     */
	@Override
	public List<TbFarmingType> selectTbFarmingTypeList(TbFarmingType tbFarmingType)
	{
	    return tbFarmingTypeMapper.selectTbFarmingTypeList(tbFarmingType);
	}
	
    /**
     * 新增农事类型
     * 
     * @param tbFarmingType 农事类型信息
     * @return 结果
     */
	@Override
	public int insertTbFarmingType(TbFarmingType tbFarmingType)
	{
	    return tbFarmingTypeMapper.insertTbFarmingType(tbFarmingType);
	}
	
	/**
     * 修改农事类型
     * 
     * @param tbFarmingType 农事类型信息
     * @return 结果
     */
	@Override
	public int updateTbFarmingType(TbFarmingType tbFarmingType)
	{
	    return tbFarmingTypeMapper.updateTbFarmingType(tbFarmingType);
	}

	/**
     * 删除农事类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbFarmingTypeByIds(String ids)
	{
		return tbFarmingTypeMapper.deleteTbFarmingTypeByIds(Convert.toStrArray(ids));
	}
	
}
