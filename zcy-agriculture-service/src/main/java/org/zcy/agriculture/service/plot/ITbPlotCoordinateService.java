package org.zcy.agriculture.service.plot;


import org.zcy.agriculture.entity.TbPlotCoordinate;

import java.util.List;

/**
 * 地块坐标 服务层
 * 
 * @author numberone
 * @date 2019-06-25
 */
public interface ITbPlotCoordinateService 
{
	/**
     * 查询地块坐标信息
     * 
     * @param coordinateId 地块坐标ID
     * @return 地块坐标信息
     */
	public TbPlotCoordinate selectTbPlotCoordinateById(Long coordinateId);
	
	/**
     * 查询地块坐标列表
     * 
     * @param tbPlotCoordinate 地块坐标信息
     * @return 地块坐标集合
     */
	public List<TbPlotCoordinate> selectTbPlotCoordinateList(TbPlotCoordinate tbPlotCoordinate);
	
	/**
     * 新增地块坐标
     * 
     * @param tbPlotCoordinate 地块坐标信息
     * @return 结果
     */
	public int insertTbPlotCoordinate(TbPlotCoordinate tbPlotCoordinate);
	
	/**
     * 修改地块坐标
     * 
     * @param tbPlotCoordinate 地块坐标信息
     * @return 结果
     */
	public int updateTbPlotCoordinate(TbPlotCoordinate tbPlotCoordinate);
		
	/**
     * 删除地块坐标信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlotCoordinateByIds(String ids);

	/**
	 * 根据地块ID删除地块坐标信息
	 *
	 * @param plotId 需要删除的地块ID
	 * @return 结果
	 */
	public int deleteTbPlotCoordinateByPlotId(Long plotId);


	public int insertTbPlotCoordinateList(List<TbPlotCoordinate> tbPlotCoordinateList);
}
