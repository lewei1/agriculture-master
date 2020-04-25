package org.zcy.agriculture.service.impl.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbModelPeriod;
import org.zcy.agriculture.mapper.model.TbModelPeriodMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.model.ITbModelPeriodService;

/**
 * 作物模型-阶段 服务层实现
 * 
 * @author numberone
 * @date 2019-06-26
 */
@Service
public class TbModelPeriodServiceImpl implements ITbModelPeriodService
{
	@Autowired
	private TbModelPeriodMapper tbModelPeriodMapper;

	/**
     * 查询作物模型-阶段信息
     * 
     * @param periodId 作物模型-阶段ID
     * @return 作物模型-阶段信息
     */
    @Override
	public TbModelPeriod selectTbModelPeriodById(Long periodId)
	{
	    return tbModelPeriodMapper.selectTbModelPeriodById(periodId);
	}
	
	/**
     * 查询作物模型-阶段列表
     * 
     * @param tbModelPeriod 作物模型-阶段信息
     * @return 作物模型-阶段集合
     */
	@Override
	public List<TbModelPeriod> selectTbModelPeriodList(TbModelPeriod tbModelPeriod)
	{
	    return tbModelPeriodMapper.selectTbModelPeriodList(tbModelPeriod);
	}
	
    /**
     * 新增作物模型-阶段
     * 
     * @param tbModelPeriod 作物模型-阶段信息
     * @return 结果
     */
	@Override
	public int insertTbModelPeriod(TbModelPeriod tbModelPeriod)
	{
	    return tbModelPeriodMapper.insertTbModelPeriod(tbModelPeriod);
	}
	
	/**
     * 修改作物模型-阶段
     * 
     * @param tbModelPeriod 作物模型-阶段信息
     * @return 结果
     */
	@Override
	public int updateTbModelPeriod(TbModelPeriod tbModelPeriod)
	{
	    return tbModelPeriodMapper.updateTbModelPeriod(tbModelPeriod);
	}

	/**
     * 删除作物模型-阶段对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbModelPeriodByIds(String ids)
	{
		return tbModelPeriodMapper.deleteTbModelPeriodByIds(Convert.toStrArray(ids));
	}
	
}
