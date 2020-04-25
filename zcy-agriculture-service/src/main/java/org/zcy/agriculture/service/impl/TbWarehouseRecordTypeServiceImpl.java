package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbWarehouseRecordType;
import org.zcy.agriculture.mapper.TbWarehouseRecordTypeMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbWarehouseRecordTypeService;


/**
 * 出入库类型 服务层实现
 * 
 * @author linlq
 * @date 2019-06-28
 */
@Service
public class TbWarehouseRecordTypeServiceImpl implements ITbWarehouseRecordTypeService
{
	@Autowired
	private TbWarehouseRecordTypeMapper tbWarehouseRecordTypeMapper;

	/**
     * 查询出入库类型信息
     * 
     * @return 出入库类型信息
     */
    @Override
	public TbWarehouseRecordType selectTbWarehouseRecordTypeById(TbWarehouseRecordType tbWarehouseRecordType)
	{
	    return tbWarehouseRecordTypeMapper.selectTbWarehouseRecordTypeById(tbWarehouseRecordType);
	}
	
	/**
     * 查询出入库类型列表
     * 
     * @param tbWarehouseRecordType 出入库类型信息
     * @return 出入库类型集合
     */
	@Override
	public List<TbWarehouseRecordType> selectTbWarehouseRecordTypeList(TbWarehouseRecordType tbWarehouseRecordType)
	{
	    return tbWarehouseRecordTypeMapper.selectTbWarehouseRecordTypeList(tbWarehouseRecordType);
	}
	
    /**
     * 新增出入库类型
     * 
     * @param tbWarehouseRecordType 出入库类型信息
     * @return 结果
     */
	@Override
	public int insertTbWarehouseRecordType(TbWarehouseRecordType tbWarehouseRecordType)
	{
	    return tbWarehouseRecordTypeMapper.insertTbWarehouseRecordType(tbWarehouseRecordType);
	}
	
	/**
     * 修改出入库类型
     * 
     * @param tbWarehouseRecordType 出入库类型信息
     * @return 结果
     */
	@Override
	public int updateTbWarehouseRecordType(TbWarehouseRecordType tbWarehouseRecordType)
	{
	    return tbWarehouseRecordTypeMapper.updateTbWarehouseRecordType(tbWarehouseRecordType);
	}

	/**
     * 删除出入库类型对象
     * 
     * @return 结果
     */
	@Override
	public int deleteTbWarehouseRecordTypeById(TbWarehouseRecordType tbWarehouseRecordType)
	{
		return tbWarehouseRecordTypeMapper.deleteTbWarehouseRecordTypeById(tbWarehouseRecordType);
	}
	
}
