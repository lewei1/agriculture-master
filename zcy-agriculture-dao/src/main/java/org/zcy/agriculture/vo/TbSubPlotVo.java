package org.zcy.agriculture.vo;

import java.util.Date;

import org.zcy.agriculture.entity.TbSubPlot;
import org.zcy.agriculture.util.DateTimeJsonSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 子地块表 tb_sub_plot
 * 
 * @author numberone
 * @date 2019-06-25
 */
public class TbSubPlotVo extends TbSubPlot {
	private static final long serialVersionUID = 4558896615238088461L;
	private String userName;// 子地块负责人姓名
	private String headUrl;//用户头像
	private String categoryName;
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date plantTime;
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date endTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getPlantTime() {
		return plantTime;
	}

	public void setPlantTime(Date plantTime) {
		this.plantTime = plantTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
