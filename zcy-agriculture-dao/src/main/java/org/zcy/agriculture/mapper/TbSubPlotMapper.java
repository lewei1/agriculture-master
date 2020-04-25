package org.zcy.agriculture.mapper;


import org.zcy.agriculture.entity.TbSubPlot;

import java.util.List;
import java.util.Map;

/**
 * 子地块 数据层
 * 
 * @author numberone
 * @date 2019-06-25
 */
public interface TbSubPlotMapper 
{
	/**
     * 查询子地块信息
     * 
     * @param subPlotId 子地块ID
     * @return 子地块信息
     */
	public TbSubPlot selectTbSubPlotById(Long subPlotId);
	
	/**
     * 查询子地块列表
     * 
     * @param tbSubPlot 子地块信息
     * @return 子地块集合
     */
	public List<TbSubPlot> selectTbSubPlotList(TbSubPlot tbSubPlot);
	
	/**
     * 新增子地块
     * 
     * @param tbSubPlot 子地块信息
     * @return 结果
     */
	public int insertTbSubPlot(TbSubPlot tbSubPlot);
	
	/**
     * 修改子地块
     * 
     * @param tbSubPlot 子地块信息
     * @return 结果
     */
	public int updateTbSubPlot(TbSubPlot tbSubPlot);
	
	/**
     * 删除子地块
     * 
     * @param subPlotId 子地块ID
     * @return 结果
     */
	public int deleteTbSubPlotById(Long subPlotId);
	
	/**
     * 批量删除子地块
     * 
     * @param subPlotIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbSubPlotByIds(String[] subPlotIds);

	/**
	 * 统计每个地块下子地块数量
	 *
	 * @param plotId 地块ID
	 * @return 结果
	 */
	public Long getSubPlotNumByPlotId(Long plotId);

	/**
	 * 统计每个地块下管理人员数量
	 *
	 * @param plotId 地块ID
	 * @return 结果
	 */
	public Long getSubPlotPersonNumByPlotId(Long plotId);

	/**
	 * 根据地块ID统计每个地块下子地块数量
	 *
	 * @param plotId 地块ID
	 * @return 结果
	 */
	public Long getSubPlotNumberByPlotId(Long plotId);

	/**
	 * 根据农场ID 和子地块ID查询 是否存在
	 * 
	 * @param farmId
	 * @param subPlotId
	 * @return
	 */
	public Long selectByFarmIdAndSubPlotId(String farmId, Long subPlotId);

	/**
	 * 根据农场ID负责人code查询子地块
	 */
	List<TbSubPlot> selectByFarmIdAndUserCode(Map map);
}