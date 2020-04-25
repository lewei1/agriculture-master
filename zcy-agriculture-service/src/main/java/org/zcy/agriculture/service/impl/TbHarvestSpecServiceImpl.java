package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbHarvestSpec;
import org.zcy.agriculture.mapper.TbHarvestSpecMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbHarvestSpecService;

/**
 * 采收规格 服务层实现
 * 
 * @author numberone
 * @date 2019-06-27
 */
@Service
public class TbHarvestSpecServiceImpl implements ITbHarvestSpecService 
{
	@Autowired
	private TbHarvestSpecMapper tbHarvestSpecMapper;

	/**
     * 查询采收规格信息
     * 
     * @param harvestSpecId 采收规格ID
     * @return 采收规格信息
     */
    @Override
	public TbHarvestSpec selectTbHarvestSpecById(Long harvestSpecId)
	{
	    return tbHarvestSpecMapper.selectTbHarvestSpecById(harvestSpecId);
	}
	
	/**
     * 查询采收规格列表
     * 
     * @param tbHarvestSpec 采收规格信息
     * @return 采收规格集合
     */
	@Override
	public List<TbHarvestSpec> selectTbHarvestSpecList(TbHarvestSpec tbHarvestSpec)
	{
	    return tbHarvestSpecMapper.selectTbHarvestSpecList(tbHarvestSpec);
	}
	
    /**
     * 新增采收规格
     * 
     * @param tbHarvestSpec 采收规格信息
     * @return 结果
     */
	@Override
	public int insertTbHarvestSpec(TbHarvestSpec tbHarvestSpec)
	{
	    return tbHarvestSpecMapper.insertTbHarvestSpec(tbHarvestSpec);
	}
	
	/**
     * 修改采收规格
     * 
     * @param tbHarvestSpec 采收规格信息
     * @return 结果
     */
	@Override
	public int updateTbHarvestSpec(TbHarvestSpec tbHarvestSpec)
	{
	    return tbHarvestSpecMapper.updateTbHarvestSpec(tbHarvestSpec);
	}

	/**
     * 删除采收规格对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbHarvestSpecByIds(String ids)
	{
		return tbHarvestSpecMapper.deleteTbHarvestSpecByIds(Convert.toStrArray(ids));
	}
	/**
	 * 根据农场判断名称是否重复
	 * @param farmId
	 * @param specName
	 * @param harvestSpecId
	 * @return
	 */
	public int selectBySpecNameRepeat(String farmId,String specName,Long harvestSpecId) {
		return tbHarvestSpecMapper.selectBySpecNameRepeat(farmId, specName, harvestSpecId);
	}
}
