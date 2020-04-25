package org.zcy.agriculture.service.impl.plot;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbPlotRegion;
import org.zcy.agriculture.mapper.plot.TbPlotMapper;
import org.zcy.agriculture.mapper.plot.TbPlotRegionMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.plot.ITbPlotRegionService;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.plot.PlotSidebarVo;
import org.zcy.agriculture.vo.plot.RegionPlotSidebarVo;
import org.zcy.agriculture.vo.plot.RegionSidebarVo;

import java.util.List;

/**
 * 地块区域 服务层实现
 *
 * @author numberone
 * @date 2019-06-25
 */
@Service
public class TbPlotRegionServiceImpl implements ITbPlotRegionService {
    @Autowired
    private TbPlotRegionMapper tbPlotRegionMapper;

    @Autowired
    private TbPlotMapper tbPlotMapper;

    /**
     * 查询地块区域信息
     *
     * @param regionId 地块区域ID
     * @return 地块区域信息
     */
    @Override
    public TbPlotRegion selectTbPlotRegionById(Long regionId) {
        return tbPlotRegionMapper.selectTbPlotRegionById(regionId);
    }

    /**
     * 查询地块区域列表
     *
     * @param tbPlotRegion 地块区域信息
     * @return 地块区域集合
     */
    @Override
    public List<TbPlotRegion> selectTbPlotRegionList(TbPlotRegion tbPlotRegion) {
        return tbPlotRegionMapper.selectTbPlotRegionList(tbPlotRegion);
    }

    /**
     * 新增地块区域
     *
     * @param tbPlotRegion 地块区域信息
     * @return 结果
     */
    @Override
    public int insertTbPlotRegion(TbPlotRegion tbPlotRegion) {
        return tbPlotRegionMapper.insertTbPlotRegion(tbPlotRegion);
    }

    /**
     * 修改地块区域
     *
     * @param tbPlotRegion 地块区域信息
     * @return 结果
     */
    @Override
    public int updateTbPlotRegion(TbPlotRegion tbPlotRegion) {
        return tbPlotRegionMapper.updateTbPlotRegion(tbPlotRegion);
    }

    /**
     * 删除地块区域对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbPlotRegionByIds(String ids) {
        return tbPlotRegionMapper.deleteTbPlotRegionByIds(Convert.toStrArray(ids));
    }

    @Override
    public List<RegionPlotSidebarVo> selectRegionAndPlotSidebarList(String farmId) {
        List<RegionPlotSidebarVo> regionPlotSidebarVoList = Lists.newArrayList();
        //地块区域
        List<RegionSidebarVo> list = tbPlotRegionMapper.selectSidebarRegionList(farmId);

        //遍历生成vo
        for (RegionSidebarVo region : list) {
            RegionPlotSidebarVo vo = new RegionPlotSidebarVo();

            vo.setRegionName(region.getRegionName());

            List<PlotSidebarVo> plotList = tbPlotMapper.selectSidebarPlotList(region.getRegionId(), farmId);
            if(ValidationUtil.isEmpty(plotList))
                plotList = Lists.newArrayList();

            vo.setPlotList(plotList);
            regionPlotSidebarVoList.add(vo);
        }

        return regionPlotSidebarVoList;
    }

}
