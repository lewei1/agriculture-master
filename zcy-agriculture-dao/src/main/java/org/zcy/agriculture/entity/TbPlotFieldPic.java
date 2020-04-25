package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 地块对应实地图片表 tb_plot_field_pic
 * 
 * @author numberone
 * @date 2019-06-25
 */
public class TbPlotFieldPic extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long fieldPicId;
	/** 图片url */
	private String picUrl;
	/** 图片序列 */
	private Integer picSequence;
	/** 地块id */
	private Long plotId;

	public void setFieldPicId(Long fieldPicId) 
	{
		this.fieldPicId = fieldPicId;
	}

	public Long getFieldPicId() 
	{
		return fieldPicId;
	}
	public void setPicUrl(String picUrl) 
	{
		this.picUrl = picUrl;
	}

	public String getPicUrl() 
	{
		return picUrl;
	}
	public void setPicSequence(Integer picSequence) 
	{
		this.picSequence = picSequence;
	}

	public Integer getPicSequence() 
	{
		return picSequence;
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
            .append("fieldPicId", getFieldPicId())
            .append("picUrl", getPicUrl())
            .append("picSequence", getPicSequence())
            .append("plotId", getPlotId())
            .toString();
    }
}
