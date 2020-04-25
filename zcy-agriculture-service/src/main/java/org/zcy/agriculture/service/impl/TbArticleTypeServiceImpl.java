package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbArticleType;
import org.zcy.agriculture.mapper.TbArticleTypeMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbArticleTypeService;

/**
 * 物品类型 服务层实现
 * 
 * @author linlq
 * @date 2019-06-21
 */
@Service
public class TbArticleTypeServiceImpl implements ITbArticleTypeService
{
	@Autowired
	private TbArticleTypeMapper tbArticleTypeMapper;

	/**
     * 查询物品类型信息
     *
     * @return 物品类型信息
     */
    @Override
	public TbArticleType selectTbArticleTypeById(TbArticleType tbArticleType)
	{
	    return tbArticleTypeMapper.selectTbArticleTypeById(tbArticleType);
	}
	
	/**
     * 查询物品类型列表
     * 
     * @param tbArticleType 物品类型信息
     * @return 物品类型集合
     */
	@Override
	public List<TbArticleType> selectTbArticleTypeList(TbArticleType tbArticleType)
	{
	    return tbArticleTypeMapper.selectTbArticleTypeList(tbArticleType);
	}
	
    /**
     * 新增物品类型
     * 
     * @param tbArticleType 物品类型信息
     * @return 结果
     */
	@Override
	public int insertTbArticleType(TbArticleType tbArticleType)
	{
	    return tbArticleTypeMapper.insertTbArticleType(tbArticleType);
	}
	
	/**
     * 修改物品类型
     * 
     * @param tbArticleType 物品类型信息
     * @return 结果
     */
	@Override
	public int updateTbArticleType(TbArticleType tbArticleType)
	{
	    return tbArticleTypeMapper.updateTbArticleType(tbArticleType);
	}

	/**
     * 删除物品类型对象
     * 
     * @return 结果
     */
	@Override
	public int deleteTbArticleTypeById(TbArticleType tbArticleType)
	{
		return tbArticleTypeMapper.deleteTbArticleTypeById(tbArticleType);
	}
	
}
