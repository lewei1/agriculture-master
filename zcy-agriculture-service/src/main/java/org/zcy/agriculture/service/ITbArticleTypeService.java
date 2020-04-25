package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbArticleType;

import java.util.List;

/**
 * 物品类型 服务层
 * 
 * @author linlq
 * @date 2019-06-21
 */
public interface ITbArticleTypeService 
{
	/**
     * 查询物品类型信息
     * 
     * @return 物品类型信息
     */
	public TbArticleType selectTbArticleTypeById(TbArticleType tbArticleType);
	
	/**
     * 查询物品类型列表
     * 
     * @param tbArticleType 物品类型信息
     * @return 物品类型集合
     */
	public List<TbArticleType> selectTbArticleTypeList(TbArticleType tbArticleType);
	
	/**
     * 新增物品类型
     * 
     * @param tbArticleType 物品类型信息
     * @return 结果
     */
	public int insertTbArticleType(TbArticleType tbArticleType);
	
	/**
     * 修改物品类型
     * 
     * @param tbArticleType 物品类型信息
     * @return 结果
     */
	public int updateTbArticleType(TbArticleType tbArticleType);
		
	/**
     * 删除物品类型信息
     * 
     * @return 结果
     */
	public int deleteTbArticleTypeById(TbArticleType tbArticleType);
	
}
