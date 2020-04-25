package org.zcy.agriculture.vo;

import org.zcy.agriculture.constants.Excel;
import org.zcy.agriculture.entity.TbHarvest;

/**
 * 作物采收表 tb_harvest
 * 
 * @author numberone
 * @date 2019-06-27
 */
public class TbHarvestVo extends TbHarvest {
	private static final long serialVersionUID = -1393471117687473326L;
	private String imgUrl;// 采集图片地址，多个以逗号隔开
	private String [] imgUrls;// 采集图片地址，多个以逗号隔开
	@Excel(name = "地块名称")
	private String plotName;// 地块名称
	@Excel(name = "子地块名称")
	private String subPlotName;// 子地块名称
	private Integer cou;// 采收次数
	@Excel(name = "规格")
	private String specName;// 采收规格名称
	private String categoryName;// 农作物名称
	@Excel(name = "采收批次")
	private Integer rankNo;// 采收批次
	private String harvestStr;// 采收开始时间
	private String harvestEnd;// 采收结束时间
	private String nickName;// 负责人名称
	private Integer pageNo;// 数据库查询开始条数（分组的时候不建议用自带的分页）
	private Integer pageSize;// 数据库查询条数
	private String toHr;//当前小时

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

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

	public Integer getCou() {
		return cou;
	}

	public void setCou(Integer cou) {
		this.cou = cou;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getRankNo() {
		return rankNo;
	}

	public void setRankNo(Integer rankNo) {
		this.rankNo = rankNo;
	}

	public String getHarvestStr() {
		return harvestStr;
	}

	public void setHarvestStr(String harvestStr) {
		this.harvestStr = harvestStr;
	}

	public String getHarvestEnd() {
		return harvestEnd;
	}

	public void setHarvestEnd(String harvestEnd) {
		this.harvestEnd = harvestEnd;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String[] getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String[] imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getToHr() {
		return toHr;
	}

	public void setToHr(String toHr) {
		this.toHr = toHr;
	}

}
