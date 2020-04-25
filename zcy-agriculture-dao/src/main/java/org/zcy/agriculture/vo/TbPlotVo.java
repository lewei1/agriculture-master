package org.zcy.agriculture.vo;

import java.util.List;

import org.zcy.agriculture.entity.TbPlot;

/**
 * 地块表 tb_plot
 * 
 * @author numberone
 * @date 2019-06-25
 */
public class TbPlotVo extends TbPlot {
	private static final long serialVersionUID = -3290708655837016768L;
	private List<TbSubPlotVo> subPlot;// 子地块列表

	public List<TbSubPlotVo> getSubPlot() {
		return subPlot;
	}

	public void setSubPlot(List<TbSubPlotVo> subPlot) {
		this.subPlot = subPlot;
	}

}
