package org.zcy.agriculture.mapper.plot;


import org.springframework.web.bind.annotation.RequestParam;
import org.zcy.agriculture.entity.TbDicRolePower;
import org.zcy.agriculture.entity.TbPlotCoordinate;

import java.util.List;

/**
 * 地块坐标 数据层
 * 
 * @author numberone
 * @date 2019-06-25
 */
public interface TbPlotCoordinateMapper {
	/**
	 * 查询地块坐标信息
	 *
	 * @param coordinateId 地块坐标ID
	 * @return 地块坐标信息
	 */
	TbPlotCoordinate selectTbPlotCoordinateById(Long coordinateId);

	/**
	 * 查询地块坐标列表
	 *
	 * @param tbPlotCoordinate 地块坐标信息
	 * @return 地块坐标集合
	 */
	List<TbPlotCoordinate> selectTbPlotCoordinateList(TbPlotCoordinate tbPlotCoordinate);

	/**
	 * 新增地块坐标
	 *
	 * @param tbPlotCoordinate 地块坐标信息
	 * @return 结果
	 */
	int insertTbPlotCoordinate(TbPlotCoordinate tbPlotCoordinate);


	int insertTbPlotCoordinateList(List<TbPlotCoordinate> tbPlotCoordinateList);

	/**
	 * 修改地块坐标
	 *
	 * @param tbPlotCoordinate 地块坐标信息
	 * @return 结果
	 */
	int updateTbPlotCoordinate(TbPlotCoordinate tbPlotCoordinate);

	/**
	 * 删除地块坐标
	 *
	 * @param coordinateId 地块坐标ID
	 * @return 结果
	 */
	int deleteTbPlotCoordinateById(Long coordinateId);

	/**
	 * 批量删除地块坐标
	 *
	 * @param coordinateIds 需要删除的数据ID
	 * @return 结果
	 */
	int deleteTbPlotCoordinateByIds(String[] coordinateIds);

	/**
	 * 根据地块ID删除地块坐标信息
	 *
	 * @param plotId 需要删除的地块ID
	 * @return 结果
	 */
	int deleteTbPlotCoordinateByPlotId(@RequestParam Long plotId);

}
