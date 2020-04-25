package org.zcy.agriculture.merchant.controller.plot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.TbPlotReport;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.param.plot.PlotReportDetailParam;
import org.zcy.agriculture.service.plot.ITbPlotReportService;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.plot.PlotReportDetailVo;

/**
 * 地块对应的水土质检报告 信息操作处理
 *
 * @author numberone
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/api/plot/report")
public class TbPlotReportController extends BaseController {

    @Autowired
    private ITbPlotReportService tbPlotReportService;

    /**
     * 查询地块对应的水土质检报告列表
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(Long plotId) {
        if(ValidationUtil.isEmpty(plotId))
            return error("地块id"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        TbPlotReport tbPlotReport = new TbPlotReport();
        tbPlotReport.setPlotId(plotId);
        List<PlotReportDetailVo> list = tbPlotReportService.selectPlotReportDetailList(tbPlotReport);
        return success(list);
    }

    /**
     * 报告详情
     * @param reportId
     * @return
     */
    @GetMapping("/detail")
    @ResponseBody
    public AjaxResult detail(Long reportId) {
        if(ValidationUtil.isEmpty(reportId))
            return error("报告id"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        PlotReportDetailVo detailVo = tbPlotReportService.selectPlotDetailReportById(reportId);
        return success(detailVo);
    }

    /**
     * 新增保存地块对应的水土质检报告
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody PlotReportDetailParam reportDetailParam) {

        if(!ValidationUtil.isEmpty(reportDetailParam.getReportResult())){
            System.out.println("长度:"+reportDetailParam.getReportResult().length());
            if (reportDetailParam.getReportResult().length() > 500)
                return error("报告不能超过500个字");
        }

        int result = -1;
        try {
            AjaxResult validation = validation(reportDetailParam);
            if(AjaxResult.getResultStatus(validation) == RequestStatus.FAILED.getStatus())
                return validation;

            if(ValidationUtil.isEmpty(reportDetailParam.getReportId())) {
                result = tbPlotReportService.insertTbPlotReportDetail(reportDetailParam);
            }else {
                result = tbPlotReportService.updateTbPlotReportDetail(reportDetailParam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toAjax(result);
    }



    /**
     * 删除地块对应的水土质检报告
     */
    @GetMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long reportId, Long plotId) {
        if(ValidationUtil.isEmpty(reportId))
            return error("报告id"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        if(ValidationUtil.isEmpty(plotId))
            return error("地块id"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        int result = tbPlotReportService.deleteTbPlotReportById(reportId, plotId);
        return toAjax(result);
    }

    @Override
    protected AjaxResult validation(Object object) {
        if(object instanceof PlotReportDetailParam) {
            PlotReportDetailParam detailParam = (PlotReportDetailParam)object;
            if(ValidationUtil.isEmpty(detailParam.getReportName()))
                return error("报告名称"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            if(ValidationUtil.isEmpty(detailParam.getReportCompany()))
                return error("检测单位"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            if(ValidationUtil.isEmpty(detailParam.getReportTime()))
                return error("检测时间"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            if(ValidationUtil.isEmpty(detailParam.getReportResult()))
                return error("报告结论"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        }
        return success();
    }
}
