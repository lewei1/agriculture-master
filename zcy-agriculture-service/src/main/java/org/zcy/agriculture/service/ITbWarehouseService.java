package org.zcy.agriculture.service;


import org.zcy.agriculture.entity.TbWarehouse;
import java.util.List;

/**
 * 仓库 服务层
 * 
 * @author linlq
 * @date 2019-06-28
 */
public interface ITbWarehouseService 
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
     * 删除仓库信息 且 删除库存
     * 
     * @return 结果
     */
	public void deleteTbWarehouseById(TbWarehouse tbWarehouse) throws Exception;
	
}
