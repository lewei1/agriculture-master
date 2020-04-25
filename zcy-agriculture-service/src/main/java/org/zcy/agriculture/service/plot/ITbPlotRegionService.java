package org.zcy.agriculture.service.plot;

import org.zcy.agriculture.entity.TbPlotRegion;
import org.zcy.agriculture.vo.plot.RegionPlotSidebarVo;

import java.util.List;


/**
 * 地块区域 服务层
 * 
 * @author numberone
 * @date 2019-06-25
 */
public interface ITbPlotRegionService 
{
	/**
     * 查询地块区域信息
     * 
     * @param regionId 地块区域ID
     * @return 地块区域信息
     */
	public TbPlotRegion selectTbPlotRegionById(Long regionId);
	
	/**
     * 查询地块区域列表
     * 
     * @param tbPlotRegion 地块区域信息
     * @return 地块区域集合
     */
	public List<TbPlotRegion> selectTbPlotRegionList(TbPlotRegion tbPlotRegion);
	
	/**
     * 新增地块区域
     * 
     * @param tbPlotRegion 地块区域信息
     * @return 结果
     */
	public int insertTbPlotRegion(TbPlotRegion tbPlotRegion);
	
	/**
     * 修改地块区域
     * 
     * @param tbPlotRegion 地块区域信息
     * @return 结果
     */
	public int updateTbPlotRegion(TbPlotRegion tbPlotRegion);
		
	/**
     * 删除地块区域信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlotRegionByIds(String ids);

    List<RegionPlotSidebarVo> selectRegionAndPlotSidebarList(String farmId);
}
