package org.zcy.agriculture.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbSubPlot;
import org.zcy.agriculture.exception.BusinessException;
import org.zcy.agriculture.mapper.TbSubPlotMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbSubPlotService;
import org.zcy.agriculture.util.StringUtils;

/**
 * 子地块 服务层实现
 * 
 * @author numberone
 * @date 2019-06-25
 */
@Service
public class TbSubPlotServiceImpl implements ITbSubPlotService
{
	@Autowired
	private TbSubPlotMapper tbSubPlotMapper;

	/**
     * 查询子地块信息
     * 
     * @param subPlotId 子地块ID
     * @return 子地块信息
     */
    @Override
	public TbSubPlot selectTbSubPlotById(Long subPlotId)
	{
	    return tbSubPlotMapper.selectTbSubPlotById(subPlotId);
	}
	
	/**
     * 查询子地块列表
     * 
     * @param tbSubPlot 子地块信息
     * @return 子地块集合
     */
	@Override
	public List<TbSubPlot> selectTbSubPlotList(TbSubPlot tbSubPlot)
	{
	    return tbSubPlotMapper.selectTbSubPlotList(tbSubPlot);
	}
	
    /**
     * 新增子地块
     * 
     * @param tbSubPlot 子地块信息
     * @return 结果
     */
	@Override
	public int insertTbSubPlot(TbSubPlot tbSubPlot)
	{
	    return tbSubPlotMapper.insertTbSubPlot(tbSubPlot);
	}
	
	/**
     * 修改子地块
     * 
     * @param tbSubPlot 子地块信息
     * @return 结果
     */
	@Override
	public int updateTbSubPlot(TbSubPlot tbSubPlot)
	{
	    return tbSubPlotMapper.updateTbSubPlot(tbSubPlot);
	}

	/**
     * 删除子地块对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbSubPlotByIds(String ids)
	{
		return tbSubPlotMapper.deleteTbSubPlotByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除子地块对象
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteTbSubPlotById(Long id)
	{
		return tbSubPlotMapper.deleteTbSubPlotById(id);
	}

	/**
	 * 统计每个地块下子地块数量
	 *
	 * @param plotId 地块ID
	 * @return 结果
	 */
	public Long getSubPlotNumByPlotId(Long plotId) { return tbSubPlotMapper.getSubPlotNumByPlotId(plotId); }

	/**
	 * 统计每个地块下管理人员数量
	 *
	 * @param plotId 地块ID
	 * @return 结果
	 */
	public Long getSubPlotPersonNumByPlotId(Long plotId) { return tbSubPlotMapper.getSubPlotPersonNumByPlotId(plotId); }

	/**
	 * 导入子地块数据
	 *
	 * @param subPlotList 数据列表
	 * @param createUserId 创建者ID
	 * @return 结果
	 */
	public String importSubPlot(List<TbSubPlot> subPlotList, Long createUserId){
		if (StringUtils.isNull(subPlotList) || subPlotList.size() == 0)
		{
			throw new BusinessException("导入用户数据不能为空！");
		}

		int successNum = 0;
		int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		for (TbSubPlot subPlot : subPlotList) {
			try
			{
				TbSubPlot tbSubPlotTemp = new TbSubPlot();
				tbSubPlotTemp.setSubPlotName(subPlot.getSubPlotName());
				tbSubPlotTemp.setPlotId(subPlot.getPlotId());
				List<TbSubPlot> list = tbSubPlotMapper.selectTbSubPlotList(tbSubPlotTemp);
				if (list.size() > 0) { //该名已存在
					failureNum++;
					failureMsg.append("<br/>" + failureNum + "、子地块名 " + subPlot.getSubPlotName() + " 已存在");
				} else { //该名如果不存在
					subPlot.setCreateBy(createUserId);
					tbSubPlotMapper.insertTbSubPlot(subPlot);
					successNum++;
					successMsg.append("<br/>" + successNum + "、子地块名 " + subPlot.getSubPlotName() + " 导入成功");
				}
			} catch (Exception e)
			{
				failureNum++;
				String msg = "<br/>" + failureNum + "、子地块名" + subPlot.getSubPlotName() + " 导入失败：";
				failureMsg.append(msg + e.getMessage());
			}
		}
		if (failureNum > 0)
		{
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new BusinessException(failureMsg.toString());
		}
		else
		{
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}

		return successMsg.toString();
	}
	/**
	 * 根据农场ID 和子地块ID查询 是否存在
	 * 
	 * @param farmId
	 * @param subPlotId
	 * @return
	 */
	public Long selectByFarmIdAndSubPlotId(String farmId, Long subPlotId) {
		return tbSubPlotMapper.selectByFarmIdAndSubPlotId(farmId, subPlotId);
	}

	/**
	 * 根据农场ID负责人code查询子地块
	 */
	@Override
	public List<TbSubPlot> selectByFarmIdAndUserCode(Map map) {

		return tbSubPlotMapper.selectByFarmIdAndUserCode(map);
	}
}
