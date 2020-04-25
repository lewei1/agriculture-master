package org.zcy.agriculture.mapper.plot;


import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbPlot;
import org.zcy.agriculture.vo.MonitorCenterPlotVo;
import org.zcy.agriculture.vo.plot.PlotSidebarVo;
import org.zcy.agriculture.vo.plot.PlotStaticsVo;
import org.zcy.agriculture.vo.TbPlotVo;

import java.util.List;

/**
 * 地块 数据层
 *
 * @author numberone
 * @date 2019-06-25
 */
public interface TbPlotMapper {
    /**
     * 查询地块信息
     *
     * @param plotId 地块ID
     * @return 地块信息
     */
    TbPlot selectTbPlotById(Long plotId);

    /**
     * 查询地块列表
     *
     * @param tbPlot 地块信息
     * @return 地块集合
     */
    List<TbPlot> selectTbPlotList(TbPlot tbPlot);

    /**
     * 新增地块
     *
     * @param tbPlot 地块信息
     * @return 结果
     */
    int insertTbPlot(TbPlot tbPlot);

    /**
     * 修改地块
     *
     * @param tbPlot 地块信息
     * @return 结果
     */
    int updateTbPlot(TbPlot tbPlot);

    /**
     * 删除地块
     *
     * @param plotId 地块ID
     * @return 结果
     */
    int deleteTbPlotById(Long plotId);

    /**
     * 批量删除地块
     *
     * @param plotIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbPlotByIds(String[] plotIds);

    /**
     * 根据农场id查询地块统计数据
     * @param farmId
     * @return
     */
    PlotStaticsVo selectTbPlotStatics(String farmId);

    /**
     * 查询中间栏列表
     * @param regionId
     * @param farmId
     * @return
     */
    List<PlotSidebarVo> selectSidebarPlotList(@Param("regionId") Long regionId, @Param("farmId") String farmId);

    /**
     * 此方式要根据农场查询所有的地块，并且吧子地块也一并查询
     *
     * @param farmId
     * @return
     */
    List<TbPlotVo> selectTbPlotResultBySubPlot(@Param("farmId") String farmId, @Param("plantingStatus") Integer plantingStatus);

    /**
     * 监控中心查询地块列表接口
     *
     * @param farmId
     * @return
     */
    List<MonitorCenterPlotVo> selectMonitoringCenterPlotList(@Param("farmId") String farmId);

}