package org.zcy.agriculture.service;

import java.util.List;

import org.zcy.agriculture.entity.TbWarehouseDynamic;

/**
 * 出入库动态 服务层
 * 
 * @author numberone
 * @date 2019-07-11
 */
public interface ITbWarehouseDynamicService 
{
	/**
     * 查询出入库动态信息
     * 
     * @return 出入库动态信息
     */
	public TbWarehouseDynamic selectTbWarehouseDynamicById(TbWarehouseDynamic tbWarehouseDynamic);
	
	/**
     * 查询出入库动态列表
     * 
     * @param tbWarehouseDynamic 出入库动态信息
     * @return 出入库动态集合
     */
	public List<TbWarehouseDynamic> selectTbWarehouseDynamicList(TbWarehouseDynamic tbWarehouseDynamic);
	
	/**
     * 新增出入库动态
     * 
     * @param tbWarehouseDynamic 出入库动态信息
     * @return 结果
     */
	public int insertTbWarehouseDynamic(TbWarehouseDynamic tbWarehouseDynamic);
	
	/**
     * 修改出入库动态
     * 
     * @param tbWarehouseDynamic 出入库动态信息
     * @return 结果
     */
	public int updateTbWarehouseDynamic(TbWarehouseDynamic tbWarehouseDynamic);
		
	/**
     * 删除出入库动态信息
     * 
     * @return 结果
     */
	public int deleteTbWarehouseDynamicById(TbWarehouseDynamic tbWarehouseDynamic);
	/**
	 * 新增计划时 插入物品动态
	 * 
	 * @param farmId     农场ID
	 * @param materialId 物品ID
	 * @param info       描述
	 */
	public void save(String farmId, Long materialId, String info,Long userCode);
}
