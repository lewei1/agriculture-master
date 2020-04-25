package org.zcy.agriculture.service.impl.plot;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbPlotReport;
import org.zcy.agriculture.entity.TbReportImg;
import org.zcy.agriculture.mapper.plot.TbPlotReportMapper;
import org.zcy.agriculture.mapper.plot.TbReportImgMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.plot.PlotReportDetailParam;
import org.zcy.agriculture.service.plot.ITbPlotReportService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.plot.PlotReportDetailVo;

import java.util.List;

/**
 * 地块对应的水土质检报告 服务层实现
 *
 * @author numberone
 * @date 2019-06-25
 */
@Service
public class TbPlotReportServiceImpl implements ITbPlotReportService {
    @Autowired
    private TbPlotReportMapper tbPlotReportMapper;

    @Autowired
    private TbReportImgMapper tbReportImgMapper;

    /**
     * 查询地块对应的水土质检报告信息
     *
     * @param reportId 地块对应的水土质检报告ID
     * @return 地块对应的水土质检报告信息
     */
    @Override
    public TbPlotReport selectTbPlotReportById(Long reportId) {
        return tbPlotReportMapper.selectTbPlotReportById(reportId);
    }

    /**
     * 查询地块对应的水土质检报告列表
     *
     * @param tbPlotReport 地块对应的水土质检报告信息
     * @return 地块对应的水土质检报告集合
     */
    @Override
    public List<TbPlotReport> selectTbPlotReportList(TbPlotReport tbPlotReport) {
        return tbPlotReportMapper.selectTbPlotReportList(tbPlotReport);
    }

    /**
     * 新增地块对应的水土质检报告
     *
     * @param tbPlotReport 地块对应的水土质检报告信息
     * @return 结果
     */
    @Override
    public int insertTbPlotReport(TbPlotReport tbPlotReport) {
        return tbPlotReportMapper.insertTbPlotReport(tbPlotReport);
    }

    /**
     * 修改地块对应的水土质检报告
     *
     * @param tbPlotReport 地块对应的水土质检报告信息
     * @return 结果
     */
    @Override
    public int updateTbPlotReport(TbPlotReport tbPlotReport) {
        return tbPlotReportMapper.updateTbPlotReport(tbPlotReport);
    }

    /**
     * 删除地块对应的水土质检报告对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbPlotReportByIds(String ids) {
        return tbPlotReportMapper.deleteTbPlotReportByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deleteTbPlotReportById(Long reportId, Long plotId) {
        return tbPlotReportMapper.deleteTbPlotReportById(reportId, plotId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertTbPlotReportDetail(PlotReportDetailParam reportDetailParam) throws Exception {
        int result ;

        try {
            TbPlotReport report = new TbPlotReport();
            BeanUtils.copyBeanProp(report, reportDetailParam);
            result = tbPlotReportMapper.insertTbPlotReport(report);
            if(result < 0) {
                return result;
            }

            List<TbReportImg> reportImgList = reportDetailParam.getReportImgList();
            if(!ValidationUtil.isEmpty(reportImgList))
                result = insertImgs(reportImgList, report.getReportId());

        } catch (Exception e) {
            throw new Exception();
        }
        return result;
    }

    @Override
    public int updateTbPlotReportDetail(PlotReportDetailParam reportDetailParam) throws Exception {

        int result;
        try {
            TbPlotReport report = new TbPlotReport();
            BeanUtils.copyBeanProp(report, reportDetailParam);
            result = tbPlotReportMapper.updateTbPlotReport(report);
            if(result < 0) {
                return result;
            }
            //先删除对应图片
            tbReportImgMapper.deleteTbReportImgByReportId(reportDetailParam.getReportId());

            List<TbReportImg> reportImgList = reportDetailParam.getReportImgList();
            if(!ValidationUtil.isEmpty(reportImgList))
                result = insertImgs(reportImgList, report.getReportId());

        } catch (Exception e) {
            throw new Exception();
        }
        return result;
    }

    @Override
    public List<PlotReportDetailVo> selectPlotReportDetailList(TbPlotReport tbPlotReport) {
        List<PlotReportDetailVo> reportDetailVoList = Lists.newLinkedList();

        List<TbPlotReport> plotReportList = tbPlotReportMapper.selectTbPlotReportList(tbPlotReport);
        if(!ValidationUtil.isEmpty(plotReportList)) {
            for(TbPlotReport report : plotReportList) {
                PlotReportDetailVo detailVo = new PlotReportDetailVo();
                BeanUtils.copyBeanProp(detailVo, report);

                //查询报告的第一张图片
                String firstImgUrl = tbReportImgMapper.selectReportFirstImgById(report.getReportId());
                if(!ValidationUtil.isEmpty(firstImgUrl))
                    detailVo.setFirstImgUrl(firstImgUrl);

                reportDetailVoList.add(detailVo);
            }
        }

        return reportDetailVoList;
    }

    @Override
    public PlotReportDetailVo selectPlotDetailReportById(Long reportId) {
        PlotReportDetailVo detailVo = new PlotReportDetailVo();

        TbPlotReport report = tbPlotReportMapper.selectTbPlotReportById(reportId);
        if(!ValidationUtil.isEmpty(report)) {

            BeanUtils.copyBeanProp(detailVo, report);

            TbReportImg img = new TbReportImg();
            img.setReportId(reportId);
            List<TbReportImg> reportImgList = tbReportImgMapper.selectTbReportImgList(img);
            if(!ValidationUtil.isEmpty(reportImgList))
                detailVo.setImgList(reportImgList);
        }
        return detailVo;
    }


    private int insertImgs(List<TbReportImg> reportImgList, Long reportId) throws Exception {
        int result = -1;
        if(!ValidationUtil.isEmpty(reportImgList)) {
            for(TbReportImg img : reportImgList) {
                img.setReportId(reportId);
            }

            try {
                result = tbReportImgMapper.insertTbReportImgList(reportImgList);
                if(result < 0) {
                    return result;
                }
            } catch (Exception e) {
                throw new Exception();
            }
        }
        return result;
    }

}
