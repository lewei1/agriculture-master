package org.zcy.agriculture.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class FarmingPlanSpecialVo implements Serializable {

	private static final long serialVersionUID = -5726195347972962782L;
	private String farmId;//农场ID
	private String plotId;// 地块ID
	private String plotName;// 地块名称
	private Long subPlotId;// 子地块ID
	private String subPlotName;// 子地块名称
	private String today;// 当前日期
	private Integer completeCou;// 完成任务
	private Integer totalCou;// 总任务
	private List<TbFarmingPlanVo> fp;
	private Long currentUserCode;//当前登录的用户编码

	public String getPlotId() {
		return plotId;
	}

	public void setPlotId(String plotId) {
		this.plotId = plotId;
	}

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

	public Long getSubPlotId() {
		return subPlotId;
	}

	public void setSubPlotId(Long subPlotId) {
		this.subPlotId = subPlotId;
	}

	public String getSubPlotName() {
		return subPlotName;
	}

	public void setSubPlotName(String subPlotName) {
		this.subPlotName = subPlotName;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public List<TbFarmingPlanVo> getFp() {
		return fp;
	}

	public void setFp(List<TbFarmingPlanVo> fp) {
		this.fp = fp;
	}

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	public Integer getCompleteCou() {
		return completeCou;
	}

	public void setCompleteCou(Integer completeCou) {
		this.completeCou = completeCou;
	}

	public Integer getTotalCou() {
		return totalCou;
	}

	public void setTotalCou(Integer totalCou) {
		this.totalCou = totalCou;
	}

	public Long getCurrentUserCode() {
		return currentUserCode;
	}

	public void setCurrentUserCode(Long currentUserCode) {
		this.currentUserCode = currentUserCode;
	}

}
