package org.zcy.agriculture.mapper;


import org.zcy.agriculture.entity.TbWarehouse;
import java.util.List;

/**
 * 仓库 数据层
 * 
 * @author linlq
 * @date 2019-06-28
 */
public interface TbWarehouseMapper 
{
	/**
     * 查询仓库信息
     * 
     * @return 仓库信息
     */
	public TbWarehouse selectTbWarehouseById(TbWarehouse tbWarehouse);
	
	/**
     * 查询仓库列表
     * 
     * @param tbWarehouse 仓库信息
     * @return 仓库集合
     */
	public List<TbWarehouse> selectTbWarehouseList(TbWarehouse tbWarehouse);
	
	/**
     * 新增仓库
     * 
     * @param tbWarehouse 仓库信息
     * @return 结果
     */
	public int insertTbWarehouse(TbWarehouse tbWarehouse);
	
	/**
     * 修改仓库
     * 
     * @param tbWarehouse 仓库信息
     * @return 结果
     */
	public int updateTbWarehouse(TbWarehouse tbWarehouse);
	
	/**
     * 删除仓库
     * 
     * @return 结果
     */
	public int deleteTbWarehouseById(TbWarehouse tbWarehouse);
	
	/**
     * 批量删除仓库
     * 
     * @param warehouseIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbWarehouseByIds(String[] warehouseIds);
	
}