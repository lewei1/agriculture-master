package org.zcy.agriculture.service;

import java.util.List;

import org.zcy.agriculture.entity.TbHarvestSpec;

/**
 * 采收规格 服务层
 * 
 * @author numberone
 * @date 2019-06-27
 */
public interface ITbHarvestSpecService 
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
     * 删除采收规格信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbHarvestSpecByIds(String ids);
	/**
	 * 根据农场判断名称是否重复
	 * @param farmId
	 * @param specName
	 * @param harvestSpecId
	 * @return
	 */
	public int selectBySpecNameRepeat(String farmId,String specName,Long harvestSpecId);
}
