package org.zcy.agriculture.mapper;


import org.zcy.agriculture.entity.TbArticle;

import java.util.HashMap;
import java.util.List;

/**
 * 物品 数据层
 * 
 * @author linlq
 * @date 2019-06-21
 */
public interface TbArticleMapper 
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
     * 删除物品
     * 
     * @return 结果
     */
	public int deleteTbArticleById(TbArticle tbArticle);
	
	/**
     * 批量删除物品
     * 
     * @param materialIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbArticleByIds(String[] materialIds);
	
	/**
	 * 根据创库ID查询物品
	 * @param warehouseId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByWarehouseId(Long warehouseId);
}