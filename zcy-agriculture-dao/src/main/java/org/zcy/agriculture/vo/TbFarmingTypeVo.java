package org.zcy.agriculture.vo;

import java.util.List;

import org.zcy.agriculture.entity.TbFarmingType;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 农事类型表 tb_farming_type
 * 
 * @author numberone
 * @date 2019-06-26
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TbFarmingTypeVo extends TbFarmingType {

	private static final long serialVersionUID = 2172582843571450472L;
	private List<TbFarmingPlanVo> fp;// 计划信息
	private Integer completeCou;// 完成任务
	private Integer totalCou;// 总任务

	public List<TbFarmingPlanVo> getFp() {
		return fp;
	}

	public void setFp(List<TbFarmingPlanVo> fp) {
		this.fp = fp;
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

}
