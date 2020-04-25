package org.zcy.agriculture.service.plot;

import org.zcy.agriculture.entity.TbPlotReport;
import org.zcy.agriculture.param.plot.PlotReportDetailParam;
import org.zcy.agriculture.vo.plot.PlotReportDetailVo;

import java.util.List;

/**
 * 地块对应的水土质检报告 服务层
 * 
 * @author numberone
 * @date 2019-06-25
 */
public interface ITbPlotReportService 
{
	/**
     * 查询地块对应的水土质检报告信息
     * 
     * @param reportId 地块对应的水土质检报告ID
     * @return 地块对应的水土质检报告信息
     */
	public TbPlotReport selectTbPlotReportById(Long reportId);
	
	/**
     * 查询地块对应的水土质检报告列表
     * 
     * @param tbPlotReport 地块对应的水土质检报告信息
     * @return 地块对应的水土质检报告集合
     */
	public List<TbPlotReport> selectTbPlotReportList(TbPlotReport tbPlotReport);
	
	/**
     * 新增地块对应的水土质检报告
     * 
     * @param tbPlotReport 地块对应的水土质检报告信息
     * @return 结果
     */
	public int insertTbPlotReport(TbPlotReport tbPlotReport);
	
	/**
     * 修改地块对应的水土质检报告
     * 
     * @param tbPlotReport 地块对应的水土质检报告信息
     * @return 结果
     */
	public int updateTbPlotReport(TbPlotReport tbPlotReport);
		
	/**
     * 删除地块对应的水土质检报告信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlotReportByIds(String ids);

    int deleteTbPlotReportById(Long reportId, Long plotId);

    int insertTbPlotReportDetail(PlotReportDetailParam reportDetailParam) throws Exception;

	int updateTbPlotReportDetail(PlotReportDetailParam reportDetailParam) throws Exception;

	List<PlotReportDetailVo> selectPlotReportDetailList(TbPlotReport tbPlotReport);

	/**
	 * 查询测试报告
	 * @param reportId
	 * @return
	 */
	PlotReportDetailVo selectPlotDetailReportById(Long reportId);
}
