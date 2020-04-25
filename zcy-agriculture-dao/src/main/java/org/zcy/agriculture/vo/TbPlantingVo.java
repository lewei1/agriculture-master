package org.zcy.agriculture.vo;

import org.zcy.agriculture.entity.TbPlanting;

/**
 * 种植表 tb_planting
 * 
 * @author numberone
 * @date 2019-06-25
 */
public class TbPlantingVo extends TbPlanting {
	private static final long serialVersionUID = -1255851461748874790L;
	private String plotName;// 地块名称
	private String subPlotName;// 子地块名称
	private String samplePicture;// 农作物样本图片地址
	private String categoryName;// 作物名称
	private String nickName;// 子地块负责人
	private String farmName;// 农场名称
	private Integer plantingStatus;// 查询地块状态 1未种植地块
	private Long subPlotUserId;//子地块负责人ID
	private Double subPlotAcreage;//子地块面积

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

	public String getSubPlotName() {
		return subPlotName;
	}

	public void setSubPlotName(String subPlotName) {
		this.subPlotName = subPlotName;
	}

	public String getSamplePicture() {
		return samplePicture;
	}

	public void setSamplePicture(String samplePicture) {
		this.samplePicture = samplePicture;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public Integer getPlantingStatus() {
		return plantingStatus;
	}

	public void setPlantingStatus(Integer plantingStatus) {
		this.plantingStatus = plantingStatus;
	}

	public Long getSubPlotUserId() {
		return subPlotUserId;
	}

	public void setSubPlotUserId(Long subPlotUserId) {
		this.subPlotUserId = subPlotUserId;
	}

	public Double getSubPlotAcreage() {
		return subPlotAcreage;
	}

	public void setSubPlotAcreage(Double subPlotAcreage) {
		this.subPlotAcreage = subPlotAcreage;
	}


	
}
