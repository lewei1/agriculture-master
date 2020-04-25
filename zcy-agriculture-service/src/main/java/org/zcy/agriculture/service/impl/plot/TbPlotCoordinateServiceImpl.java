package org.zcy.agriculture.service.impl.plot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbPlotCoordinate;
import org.zcy.agriculture.mapper.plot.TbPlotCoordinateMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.plot.ITbPlotCoordinateService;

import java.util.List;

/**
 * 地块坐标 服务层实现
 * 
 * @author numberone
 * @date 2019-06-25
 */
@Service
public class TbPlotCoordinateServiceImpl implements ITbPlotCoordinateService
{
	@Autowired
	private TbPlotCoordinateMapper tbPlotCoordinateMapper;

	/**
     * 查询地块坐标信息
     * 
     * @param coordinateId 地块坐标ID
     * @return 地块坐标信息
     */
    @Override
	public TbPlotCoordinate selectTbPlotCoordinateById(Long coordinateId)
	{
	    return tbPlotCoordinateMapper.selectTbPlotCoordinateById(coordinateId);
	}
	
	/**
     * 查询地块坐标列表
     * 
     * @param tbPlotCoordinate 地块坐标信息
     * @return 地块坐标集合
     */
	@Override
	public List<TbPlotCoordinate> selectTbPlotCoordinateList(TbPlotCoordinate tbPlotCoordinate)
	{
	    return tbPlotCoordinateMapper.selectTbPlotCoordinateList(tbPlotCoordinate);
	}
	
    /**
     * 新增地块坐标
     * 
     * @param tbPlotCoordinate 地块坐标信息
     * @return 结果
     */
	@Override
	public int insertTbPlotCoordinate(TbPlotCoordinate tbPlotCoordinate)
	{
	    return tbPlotCoordinateMapper.insertTbPlotCoordinate(tbPlotCoordinate);
	}
	
	/**
     * 修改地块坐标
     * 
     * @param tbPlotCoordinate 地块坐标信息
     * @return 结果
     */
	@Override
	public int updateTbPlotCoordinate(TbPlotCoordinate tbPlotCoordinate)
	{
	    return tbPlotCoordinateMapper.updateTbPlotCoordinate(tbPlotCoordinate);
	}

	/**
     * 删除地块坐标对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbPlotCoordinateByIds(String ids)
	{
		return tbPlotCoordinateMapper.deleteTbPlotCoordinateByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据地块ID删除地块坐标信息
	 *
	 * @param plotId 需要删除的地块ID
	 * @return 结果
	 */
	@Override
	public int deleteTbPlotCoordinateByPlotId(Long plotId) {
		return tbPlotCoordinateMapper.deleteTbPlotCoordinateByPlotId(plotId);
	}

	@Override
	public int insertTbPlotCoordinateList(List<TbPlotCoordinate> tbPlotCoordinateList) {
		return tbPlotCoordinateMapper.insertTbPlotCoordinateList(tbPlotCoordinateList);
	}
}
