package org.zcy.agriculture.mapper;

import java.util.List;

import org.zcy.agriculture.entity.TbHarvestSpec;	

/**
 * 采收规格 数据层
 * 
 * @author numberone
 * @date 2019-06-27
 */
public interface TbHarvestSpecMapper 
{
	/**
     * 查询采收规格信息
     * 
     * @param harvestSpecId 采收规格ID
     * @return 采收规格信息
     */
	public TbHarvestSpec selectTbHarvestSpecById(Long harvestSpecId);
	
	/**
     * 查询采收规格列表
     * 
     * @param tbHarvestSpec 采收规格信息
     * @return 采收规格集合
     */
	public List<TbHarvestSpec> selectTbHarvestSpecList(TbHarvestSpec tbHarvestSpec);
	
	/**
     * 新增采收规格
     * 
     * @param tbHarvestSpec 采收规格信息
     * @return 结果
     */
	public int insertTbHarvestSpec(TbHarvestSpec tbHarvestSpec);
	
	/**
     * 修改采收规格
     * 
     * @param tbHarvestSpec 采收规格信息
     * @return 结果
     */
	public int updateTbHarvestSpec(TbHarvestSpec tbHarvestSpec);
	
	/**
     * 删除采收规格
     * 
     * @param harvestSpecId 采收规格ID
     * @return 结果
     */
	public int deleteTbHarvestSpecById(Long harvestSpecId);
	
	/**
     * 批量删除采收规格
     * 
     * @param harvestSpecIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbHarvestSpecByIds(String[] harvestSpecIds);
	/**
	 * 根据农场判断名称是否重复
	 * @param farmId
	 * @param specName
	 * @param harvestSpecId
	 * @return
	 */
	public int selectBySpecNameRepeat(String farmId,String specName,Long harvestSpecId);
}