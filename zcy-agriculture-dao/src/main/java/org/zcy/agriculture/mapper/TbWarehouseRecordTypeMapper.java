package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbWarehouseRecordType;

import java.util.List;

/**
 * 出入库类型 数据层
 * 
 * @author linlq
 * @date 2019-06-28
 */
public interface TbWarehouseRecordTypeMapper 
{
	/**
     * 查询出入库类型信息
     * 
     * @return 出入库类型信息
     */
	public TbWarehouseRecordType selectTbWarehouseRecordTypeById(TbWarehouseRecordType tbWarehouseRecordType);
	
	/**
     * 查询出入库类型列表
     * 
     * @param tbWarehouseRecordType 出入库类型信息
     * @return 出入库类型集合
     */
	public List<TbWarehouseRecordType> selectTbWarehouseRecordTypeList(TbWarehouseRecordType tbWarehouseRecordType);
	
	/**
     * 新增出入库类型
     * 
     * @param tbWarehouseRecordType 出入库类型信息
     * @return 结果
     */
	public int insertTbWarehouseRecordType(TbWarehouseRecordType tbWarehouseRecordType);
	
	/**
     * 修改出入库类型
     * 
     * @param tbWarehouseRecordType 出入库类型信息
     * @return 结果
     */
	public int updateTbWarehouseRecordType(TbWarehouseRecordType tbWarehouseRecordType);
	
	/**
     * 删除出入库类型
     * 
     * @return 结果
     */
	public int deleteTbWarehouseRecordTypeById(TbWarehouseRecordType tbWarehouseRecordType);
	
	/**
     * 批量删除出入库类型
     * 
     * @param inOutTypeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbWarehouseRecordTypeByIds(String[] inOutTypeIds);
	
}