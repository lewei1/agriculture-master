package org.zcy.agriculture.service.impl.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbModelPeriodFarming;
import org.zcy.agriculture.mapper.model.TbModelPeriodFarmingMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.model.ITbModelPeriodFarmingService;

/**
 * 作物模型-阶段农事 服务层实现
 * 
 * @author numberone
 * @date 2019-06-26
 */
@Service
public class TbModelPeriodFarmingServiceImpl implements ITbModelPeriodFarmingService
{
	@Autowired
	private TbModelPeriodFarmingMapper tbModelPeriodFarmingMapper;

	/**
     * 查询作物模型-阶段农事信息
     * 
     * @param farmingId 作物模型-阶段农事ID
     * @return 作物模型-阶段农事信息
     */
    @Override
	public TbModelPeriodFarming selectTbModelPeriodFarmingById(Long farmingId)
	{
	    return tbModelPeriodFarmingMapper.selectTbModelPeriodFarmingById(farmingId);
	}
	
	/**
     * 查询作物模型-阶段农事列表
     * 
     * @param tbModelPeriodFarming 作物模型-阶段农事信息
     * @return 作物模型-阶段农事集合
     */
	@Override
	public List<TbModelPeriodFarming> selectTbModelPeriodFarmingList(TbModelPeriodFarming tbModelPeriodFarming)
	{
	    return tbModelPeriodFarmingMapper.selectTbModelPeriodFarmingList(tbModelPeriodFarming);
	}
	
    /**
     * 新增作物模型-阶段农事
     * 
     * @param tbModelPeriodFarming 作物模型-阶段农事信息
     * @return 结果
     */
	@Override
	public int insertTbModelPeriodFarming(TbModelPeriodFarming tbModelPeriodFarming)
	{
	    return tbModelPeriodFarmingMapper.insertTbModelPeriodFarming(tbModelPeriodFarming);
	}
	
	/**
     * 修改作物模型-阶段农事
     * 
     * @param tbModelPeriodFarming 作物模型-阶段农事信息
     * @return 结果
     */
	@Override
	public int updateTbModelPeriodFarming(TbModelPeriodFarming tbModelPeriodFarming)
	{
	    return tbModelPeriodFarmingMapper.updateTbModelPeriodFarming(tbModelPeriodFarming);
	}

	/**
     * 删除作物模型-阶段农事对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbModelPeriodFarmingByIds(String ids)
	{
		return tbModelPeriodFarmingMapper.deleteTbModelPeriodFarmingByIds(Convert.toStrArray(ids));
	}
	
}
