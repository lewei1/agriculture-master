package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 水质报告图片表 tb_report_img
 * 
 * @author numberone
 * @date 2019-06-25
 */
public class TbReportImg extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long reportImgId;
	/** 主键id */
	private Long reportId;
	/** 图片url */
	private String imgUrl;
	/** 图片序列 */
	private Integer reportSequence;

	public void setReportImgId(Long reportImgId) 
	{
		this.reportImgId = reportImgId;
	}

	public Long getReportImgId() 
	{
		return reportImgId;
	}
	public void setReportId(Long reportId) 
	{
		this.reportId = reportId;
	}

	public Long getReportId() 
	{
		return reportId;
	}
	public void setImgUrl(String imgUrl) 
	{
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() 
	{
		return imgUrl;
	}
	public void setReportSequence(Integer reportSequence) 
	{
		this.reportSequence = reportSequence;
	}

	public Integer getReportSequence() 
	{
		return reportSequence;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("reportImgId", getReportImgId())
            .append("reportId", getReportId())
            .append("imgUrl", getImgUrl())
            .append("reportSequence", getReportSequence())
            .toString();
    }
}
