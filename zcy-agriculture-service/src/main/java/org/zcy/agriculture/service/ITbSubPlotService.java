package org.zcy.agriculture.service;


import org.zcy.agriculture.entity.TbSubPlot;

import java.util.List;
import java.util.Map;

/**
 * 子地块 服务层
 * 
 * @author numberone
 * @date 2019-06-25
 */
public interface ITbSubPlotService 
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
     * 删除子地块信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbSubPlotByIds(String ids);

	/**
	 * 删除子地块对象
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbSubPlotById(Long id);

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
	 * 导入子地块数据
	 *
	 * @param subPlotList 数据列表
	 * @param createUserId 创建者ID
	 * @return 结果
	 */
	public String importSubPlot(List<TbSubPlot> subPlotList,  Long createUserId);

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
