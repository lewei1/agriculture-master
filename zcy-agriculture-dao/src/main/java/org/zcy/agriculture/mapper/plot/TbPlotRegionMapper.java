package org.zcy.agriculture.mapper.plot;

import org.zcy.agriculture.entity.TbPlotRegion;
import org.zcy.agriculture.vo.plot.RegionSidebarVo;

import java.util.List;


/**
 * 地块区域 数据层
 *
 * @author numberone
 * @date 2019-06-25
 */
public interface TbPlotRegionMapper {
    /**
     * 查询地块区域信息
     *
     * @param regionId 地块区域ID
     * @return 地块区域信息
     */
    TbPlotRegion selectTbPlotRegionById(Long regionId);

    /**
     * 查询地块区域列表
     *
     * @param tbPlotRegion 地块区域信息
     * @return 地块区域集合
     */
    List<TbPlotRegion> selectTbPlotRegionList(TbPlotRegion tbPlotRegion);

    /**
     * 新增地块区域
     *
     * @param tbPlotRegion 地块区域信息
     * @return 结果
     */
    int insertTbPlotRegion(TbPlotRegion tbPlotRegion);

    /**
     * 修改地块区域
     *
     * @param tbPlotRegion 地块区域信息
     * @return 结果
     */
    int updateTbPlotRegion(TbPlotRegion tbPlotRegion);

    /**
     * 删除地块区域
     *
     * @param regionId 地块区域ID
     * @return 结果
     */
    int deleteTbPlotRegionById(Long regionId);

    /**
     * 批量删除地块区域
     *
     * @param regionIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbPlotRegionByIds(String[] regionIds);

    /**
     * 查询中间栏列表
     * @param farmId
     * @return
     */
    List<RegionSidebarVo> selectSidebarRegionList(String farmId);

}