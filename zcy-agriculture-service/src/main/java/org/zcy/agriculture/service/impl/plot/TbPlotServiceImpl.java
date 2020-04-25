package org.zcy.agriculture.service.impl.plot;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.PlotConstants;
import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.mapper.*;
import org.zcy.agriculture.mapper.plot.TbPlotCoordinateMapper;
import org.zcy.agriculture.mapper.plot.TbPlotMapper;
import org.zcy.agriculture.mapper.plot.TbPlotRegionMapper;
import org.zcy.agriculture.mapper.system.SysDictDataMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.plot.PlotDetailParam;
import org.zcy.agriculture.service.impl.BaseServiceImpl;
import org.zcy.agriculture.service.plot.ITbPlotService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.*;
import org.zcy.agriculture.vo.plot.PlotDetailVo;
import org.zcy.agriculture.vo.plot.PlotStaticsVo;
import org.zcy.agriculture.vo.plot.RegionPlotDetailVo;
import org.zcy.agriculture.vo.plot.RegionSidebarVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 地块 服务层实现
 *
 * @author numberone
 * @date 2019-06-25
 */
@Service
public class TbPlotServiceImpl extends BaseServiceImpl implements ITbPlotService {
    @Autowired
    private TbPlotMapper tbPlotMapper;

    @Autowired
    private TbPlotCoordinateMapper tbPlotCoordinateMapper;

    @Autowired
    private TbPlotRegionMapper tbPlotRegionMapper;

    @Autowired
    private TbPlantingMapper tbPlantingMapper;

    @Autowired
    private TbSubPlotMapper tbSubPlotMapper;

    @Autowired
    private TbResDeviceMapper tbResDeviceMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询地块信息
     *
     * @param plotId 地块ID
     * @return 地块信息
     */
    @Override
    public TbPlot selectTbPlotById(Long plotId) {
        return tbPlotMapper.selectTbPlotById(plotId);
    }



    /**
     * 查询地块列表
     *
     * @return 地块集合
     */
    @Override
    public List<RegionPlotDetailVo> selectTbPlotList(String farmId) {
        //目标list
        List<RegionPlotDetailVo> detailList = Lists.newArrayList();
        //地块区域
        List<RegionSidebarVo> list = tbPlotRegionMapper.selectSidebarRegionList(farmId);
        if(!ValidationUtil.isEmpty(list)) {
            for(RegionSidebarVo vo : list) {

                RegionPlotDetailVo detailVo = new RegionPlotDetailVo();
                detailVo.setRegionName(vo.getRegionName());

                TbPlot tbPlot = new TbPlot();
                tbPlot.setRegionId(vo.getRegionId());
                tbPlot.setPlotStatus(NormalOrDeleteEnum.NORMAL.getCode());
                tbPlot.setFarmId(farmId);
                List<TbPlot> plotList = tbPlotMapper.selectTbPlotList(tbPlot);

                List<TbPlotInfo> plotInfoList = Lists.newArrayList();
                if(!ValidationUtil.isEmpty(plotList)) {
                    for(TbPlot plot : plotList) {

                        TbPlotInfo plotInfo = new TbPlotInfo();
                        BeanUtils.copyBeanProp(plotInfo, plot);
                        try {
                            if (plot.getPlotType()!=null){
                                long type = Long.parseLong(plot.getPlotType());
                                plotInfo.setPlotTypeName(sysDictDataMapper.selectDictDataById(type).getDictLabel());
                            }
                        } catch (Exception e){}

                        List<TbPlantingVo> plantingVoList = tbPlantingMapper.selectByCategoryNameList(plot.getPlotId());//根据地块ID查询农作物和负责人
                        plotInfo.setPlantingVoList(plantingVoList);

                        /**获取地块下坐标信息*/
                        TbPlotCoordinate coordinate = new TbPlotCoordinate();
                        coordinate.setPlotId(plot.getPlotId());
                        List<TbPlotCoordinate> coordinateList = tbPlotCoordinateMapper.selectTbPlotCoordinateList(coordinate);
                        plotInfo.setCoordinateList(coordinateList);

                        plotInfoList.add(plotInfo);
                    }
                }
                detailVo.setPlotList(plotInfoList);
                detailList.add(detailVo);
            }
        }
        return detailList;
    }


    /**
     * 监控中心地块信息获取
     *
     * @return 地块信息集合
     */
    @Override
    public List<MonitorCenterPlotVo> getMonitoringCenterPlotList(String farmId) {
        //地块区域
        List<MonitorCenterPlotVo> list = tbPlotMapper.selectMonitoringCenterPlotList(farmId);
        if(!ValidationUtil.isEmpty(list)) {
            for(MonitorCenterPlotVo vo : list) {
                TbPlotCoordinate coordinate = new TbPlotCoordinate();
                coordinate.setPlotId(vo.getPlotId());
                coordinate.setCoordinateStatus(0);
                List<TbPlotCoordinate> coordinateList = tbPlotCoordinateMapper.selectTbPlotCoordinateList(coordinate);

                /**获取坐标信息*/
                List<CoordinateVo> coordinateVoList = new ArrayList<>();
                for(TbPlotCoordinate c : coordinateList) {
                    CoordinateVo cTemp = new CoordinateVo();
                    cTemp.setLatitude(c.getLatitude());
                    cTemp.setLongitude(c.getLongitude());
                    coordinateVoList.add(cTemp);
                }

                vo.setList(coordinateVoList);

                /**获取子地块数量*/
                vo.setSubPlotNum(tbSubPlotMapper.getSubPlotNumberByPlotId(vo.getPlotId()));

                /**获取设备数量*/
                TbResDevice device = new TbResDevice();
                device.setPlotId(vo.getPlotId());
                vo.setDevNum(tbResDeviceMapper.getCountFromTbResDevice(device));
            }
        }
        return list;
    }

    @Override
    public List<SysDictData> selectPlotTypeList() {
        return getDictList("地块类型");
    }

    @Override
    public List<TbPlotCoordinate> selectPlotCoordinateById(Long plotId) {
        List<TbPlotCoordinate> coordinateList = Lists.newArrayList();

        TbPlot plot = tbPlotMapper.selectTbPlotById(plotId);
        if(!ValidationUtil.isEmpty(plot) ) {
            TbPlotCoordinate coordinate = new TbPlotCoordinate();
            coordinate.setPlotId(plotId);
            coordinate.setCoordinateStatus(NormalOrDeleteEnum.NORMAL.getCode());
            coordinateList = tbPlotCoordinateMapper.selectTbPlotCoordinateList(coordinate);
            if(!ValidationUtil.isEmpty(coordinateList))
                return coordinateList;
        }
        return coordinateList;
    }

    @Override
    public List<PlotDetailVo> selectPlotCoordinateByFarmId(String farmId) {
        List<PlotDetailVo> plotDetailVoList = Lists.newArrayList();

        TbPlot plot = new TbPlot();
        plot.setFarmId(farmId);
        plot.setPlotStatus(NormalOrDeleteEnum.NORMAL.getCode());
        List<TbPlot> plotList = tbPlotMapper.selectTbPlotList(plot);
        if(!ValidationUtil.isEmpty(plotList)) {
            for(TbPlot tbPlot : plotList) {
                PlotDetailVo vo = new PlotDetailVo();
                BeanUtils.copyBeanProp(vo, tbPlot);

                TbPlotCoordinate coordinate = new TbPlotCoordinate();
                coordinate.setCoordinateStatus(NormalOrDeleteEnum.NORMAL.getCode());
                coordinate.setPlotId(tbPlot.getPlotId());
                List<TbPlotCoordinate> coordinateList = tbPlotCoordinateMapper.selectTbPlotCoordinateList(coordinate);
                if(ValidationUtil.isEmpty(coordinateList))
                    coordinateList = Lists.newArrayList();
                vo.setCoordinateList(coordinateList);

                plotDetailVoList.add(vo);
            }
        }

        return plotDetailVoList;
    }

    @Override
    public List<TbPlot> selectPurePlotList(String farmId) {
        TbPlot plot = new TbPlot();
        plot.setFarmId(farmId);
        plot.setPlotStatus(NormalOrDeleteEnum.NORMAL.getCode());
        return tbPlotMapper.selectTbPlotList(plot);
    }

    /**
     * 新增地块
     *
     * @param tbPlot 地块信息
     * @return 结果
     */
    @Override
    public int insertTbPlot(TbPlot tbPlot) {
        return tbPlotMapper.insertTbPlot(tbPlot);
    }

    /**
     * 修改地块
     *
     * @param tbPlot 地块信息
     * @return 结果
     */
    @Override
    public int updateTbPlot(TbPlot tbPlot) {
        return tbPlotMapper.updateTbPlot(tbPlot);
    }

    /**
     * 删除地块对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbPlotByIds(String ids) {
        return tbPlotMapper.deleteTbPlotByIds(Convert.toStrArray(ids));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertTbPlotDetail(PlotDetailParam plotDetailParam) throws Exception {
        TbPlot plot = new TbPlot();
        int result = 0;
        try {
            BeanUtils.copyBeanProp(plot, plotDetailParam);
            result = tbPlotMapper.insertTbPlot(plotDetailParam);

            List<TbPlotCoordinate> coordinateList = plotDetailParam.getCoordinateList();
            if (!ValidationUtil.isEmpty(coordinateList)) {
                for (TbPlotCoordinate coordinate : coordinateList) {
                    coordinate.setPlotId(plotDetailParam.getPlotId());
                }
                result = tbPlotCoordinateMapper.insertTbPlotCoordinateList(coordinateList);
                if(result < 0)
                    return result;
            }

            //默认添加 主地块 到子地块表
            TbSubPlot tbSubPlot = new TbSubPlot();
            tbSubPlot.setPlotId(plotDetailParam.getPlotId());
            tbSubPlot.setSubPlotName(PlotConstants.DEFAULT_MAIN_PLOT);
            tbSubPlot.setSubPlotPerson(plotDetailParam.getCreateBy());
            tbSubPlot.setSubPlotAcreage(plotDetailParam.getPlotAcreage());
            tbSubPlot.setCreateBy(plotDetailParam.getCreateBy());

            result = tbSubPlotMapper.insertTbSubPlot(tbSubPlot);
        } catch (Exception e) {
            throw new Exception();
        }
        return result;
    }

    @Override
    public PlotStaticsVo selectTbPlotStatics(String farmId) {
        return tbPlotMapper.selectTbPlotStatics(farmId);
    }
    /**
     * 此方式要根据农场查询所有的地块，并且吧子地块也一并查询 
     * @param farmId
     * @return
     */
    public List<TbPlotVo> selectTbPlotResultBySubPlot(String farmId,Integer plantingStatus){
    	return tbPlotMapper.selectTbPlotResultBySubPlot(farmId,plantingStatus);
    }

    @Override
    public List<TbPlot> selectTbPlotList(TbPlot tbPlot) {
        return tbPlotMapper.selectTbPlotList(tbPlot);
    }

}
