package org.zcy.agriculture.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 地块对应的水土质检报告表 tb_plot_report
 * 
 * @author numberone
 * @date 2019-06-25
 */
public class TbPlotReport extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long reportId;
	/** 报告名称 */
	private String reportName;
	/** 检测单位 */
	private String reportCompany;
	/** 检测时间 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date reportTime;
	/** 结论 */
	private String reportResult;
	/** 地块id */
	private Long plotId;

	public void setReportId(Long reportId) 
	{
		this.reportId = reportId;
	}

	public Long getReportId() 
	{
		return reportId;
	}
	public void setReportName(String reportName) 
	{
		this.reportName = reportName;
	}

	public String getReportName() 
	{
		return reportName;
	}
	public void setReportCompany(String reportCompany) 
	{
		this.reportCompany = reportCompany;
	}

	public String getReportCompany() 
	{
		return reportCompany;
	}
	public void setReportTime(Date reportTime) 
	{
		this.reportTime = reportTime;
	}

	public Date getReportTime() 
	{
		return reportTime;
	}
	public void setReportResult(String reportResult) 
	{
		this.reportResult = reportResult;
	}

	public String getReportResult() 
	{
		return reportResult;
	}
	public void setPlotId(Long plotId) 
	{
		this.plotId = plotId;
	}

	public Long getPlotId() 
	{
		return plotId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId", getReportId())
            .append("reportName", getReportName())
            .append("reportCompany", getReportCompany())
            .append("reportTime", getReportTime())
            .append("reportResult", getReportResult())
            .append("plotId", getPlotId())
            .toString();
    }
}
