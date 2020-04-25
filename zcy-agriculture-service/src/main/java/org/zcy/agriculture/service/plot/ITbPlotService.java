package org.zcy.agriculture.service.plot;

import org.zcy.agriculture.entity.SysDictData;
import org.zcy.agriculture.entity.TbPlot;
import org.zcy.agriculture.entity.TbPlotCoordinate;
import org.zcy.agriculture.param.plot.PlotDetailParam;
import org.zcy.agriculture.vo.MonitorCenterPlotVo;
import org.zcy.agriculture.vo.TbPlotVo;
import org.zcy.agriculture.vo.plot.PlotDetailVo;
import org.zcy.agriculture.vo.plot.PlotStaticsVo;
import org.zcy.agriculture.vo.plot.RegionPlotDetailVo;

import java.util.List;

/**
 * 地块 服务层
 *
 * @author numberone
 * @date 2019-06-25
 */
public interface ITbPlotService {
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
     * @return 地块集合
     */
    List<RegionPlotDetailVo> selectTbPlotList(String farmId);

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
     * 删除地块信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTbPlotByIds(String ids);


    int insertTbPlotDetail(PlotDetailParam plotDetailParam) throws Exception;


    PlotStaticsVo selectTbPlotStatics(String farmId);

    /**
     * 此方式要根据农场查询所有的地块，并且吧子地块也一并查询
     *
     * @param farmId
     * @return
     */
    List<TbPlotVo> selectTbPlotResultBySubPlot(String farmId, Integer plantingStatus);

    /**
     * 查询地块列表
     *
     * @param tbPlot
     * @return
     */
    List<TbPlot> selectTbPlotList(TbPlot tbPlot);

    /**
     * 监控中心地块信息获取
     *
     * @return 地块信息集合
     */
    List<MonitorCenterPlotVo> getMonitoringCenterPlotList(String farmId);

    /**
     * 查询字典地块类型列表
     *
     * @return
     */
    List<SysDictData> selectPlotTypeList();

    /**
     * 查询地块经纬度列表
     *
     * @param plotId
     * @return
     */
    List<TbPlotCoordinate> selectPlotCoordinateById(Long plotId);


    /**
     * 通过农场id，查询农场的所有地块和坐标组
     * @param farmId
     * @return
     */
    List<PlotDetailVo> selectPlotCoordinateByFarmId(String farmId);

    /**
     * 通过农场id，单纯的，查询农场下所有地块
     * @param farmId
     * @return
     */
    List<TbPlot> selectPurePlotList(String farmId);
}
