package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbAgriculturalMachine;
import org.zcy.agriculture.entity.TbAmType;

import java.util.List;

/**
 * 农机类型 服务层
 * 
 * @author linlq
 * @date 2019-06-21
 */
public interface ITbAmTypeService 
{
	/**
     * 查询农机类型信息
     * 
     * @param machineTypeId 农机类型ID
     * @return 农机类型信息
     */
	public TbAmType selectTbAmTypeById(Long machineTypeId);

	/**
	 * 查询农机类型信息
	 *
	 * @return 农机类型信息
	 */
	public TbAmType selectTbAmTypeByClass(TbAmType tbAmType);


	/**
     * 查询农机类型列表
     * 
     * @param tbAmType 农机类型信息
     * @return 农机类型集合
     */
	public List<TbAmType> selectTbAmTypeList(TbAmType tbAmType);
	
	/**
     * 新增农机类型
     * 
     * @param tbAmType 农机类型信息
     * @return 结果
     */
	public int insertTbAmType(TbAmType tbAmType);
	
	/**
     * 修改农机类型
     * 
     * @param tbAmType 农机类型信息
     * @return 结果
     */
	public int updateTbAmType(TbAmType tbAmType);
		
	/**
     * 删除农机类型信息
     * 
     * @return 结果
     */
	public int deleteTbAmTypeById(TbAmType tbAmType);


}
