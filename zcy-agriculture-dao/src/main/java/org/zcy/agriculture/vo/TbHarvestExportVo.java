package org.zcy.agriculture.vo;

import org.zcy.agriculture.constants.Excel;

/**
 * 作物采收表统计导出 tb_harvest
 * 
 * @author numberone
 * @date 2019-06-27
 */
public class TbHarvestExportVo {
	@Excel(name = "地块名称")
	private String plotName;// 地块名称
	@Excel(name = "子地块名称")
	private String subPlotName;// 子地块名称
	@Excel(name = "农作物")
	private String categoryName;// 农作物名称
	@Excel(name = "规格")
	private String specName;// 采收规格名称
	@Excel(name = "作物等级")
	private String cropLevel;
	@Excel(name = "负责人")
	private String nickName;// 负责人名称
	@Excel(name = "采收重量（KG）")
	private Integer harvestAmount;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public String getCropLevel() {
		return cropLevel;
	}
	public void setCropLevel(String cropLevel) {
		this.cropLevel = cropLevel;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getHarvestAmount() {
		return harvestAmount;
	}
	public void setHarvestAmount(Integer harvestAmount) {
		this.harvestAmount = harvestAmount;
	}
	
}
