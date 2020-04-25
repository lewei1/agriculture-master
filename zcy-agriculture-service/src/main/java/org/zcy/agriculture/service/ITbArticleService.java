package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbArticle;

import java.util.HashMap;
import java.util.List;

/**
 * 物品 服务层
 * 
 * @author linlq
 * @date 2019-06-21
 */
public interface ITbArticleService 
{
	/**
     * 查询物品信息
     * 
     * @return 物品信息
     */
	public TbArticle selectTbArticleById(TbArticle tbArticle);
	
	/**
     * 查询物品列表
     * 
     * @param tbArticle 物品信息
     * @return 物品集合
     */
	public List<TbArticle> selectTbArticleList(TbArticle tbArticle);
	
	/**
     * 新增物品
     * 
     * @param tbArticle 物品信息
     * @return 结果
     */
	public int insertTbArticle(TbArticle tbArticle);
	
	/**
     * 修改物品
     * 
     * @param tbArticle 物品信息
     * @return 结果
     */
	public int updateTbArticle(TbArticle tbArticle);
		
	/**
     * 删除物品对象 且 删除库存
     * 
     * @return 结果
     */
	public void deleteTbArticleById(TbArticle tbArticle) throws Exception;
	/**
	 * 根据创库ID查询物品
	 * @param warehouseId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByWarehouseId(Long warehouseId);
}
