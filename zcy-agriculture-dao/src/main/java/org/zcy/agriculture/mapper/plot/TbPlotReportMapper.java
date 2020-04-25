package org.zcy.agriculture.mapper.plot;

import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbPlotReport;

import java.util.List;

/**
 * 地块对应的水土质检报告 数据层
 *
 * @author numberone
 * @date 2019-06-25
 */
public interface TbPlotReportMapper {
    /**
     * 查询地块对应的水土质检报告信息
     *
     * @param reportId 地块对应的水土质检报告ID
     * @return 地块对应的水土质检报告信息
     */
    TbPlotReport selectTbPlotReportById(Long reportId);

    /**
     * 查询地块对应的水土质检报告列表
     *
     * @param tbPlotReport 地块对应的水土质检报告信息
     * @return 地块对应的水土质检报告集合
     */
    List<TbPlotReport> selectTbPlotReportList(TbPlotReport tbPlotReport);

    /**
     * 新增地块对应的水土质检报告
     *
     * @param tbPlotReport 地块对应的水土质检报告信息
     * @return 结果
     */
    int insertTbPlotReport(TbPlotReport tbPlotReport);

    /**
     * 修改地块对应的水土质检报告
     *
     * @param tbPlotReport 地块对应的水土质检报告信息
     * @return 结果
     */
    int updateTbPlotReport(TbPlotReport tbPlotReport);

    /**
     * 删除地块对应的水土质检报告
     *
     * @param reportId 地块对应的水土质检报告ID
     * @return 结果
     */
    int deleteTbPlotReportById(@Param("reportId") Long reportId, @Param("plotId") Long plotId);

    /**
     * 批量删除地块对应的水土质检报告
     *
     * @param reportIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbPlotReportByIds(String[] reportIds);

}