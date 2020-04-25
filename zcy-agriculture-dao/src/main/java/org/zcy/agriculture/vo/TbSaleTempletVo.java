package org.zcy.agriculture.vo;

import java.util.List;

import org.zcy.agriculture.entity.TbSaleTemplet;
import org.zcy.agriculture.entity.TbSaleTempletInfo;

/**
 * 销售管理-展示模板表 tb_sale_templet
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbSaleTempletVo extends TbSaleTemplet {

	private static final long serialVersionUID = -9012959184132679100L;

	private List<TbSaleTempletInfo> ti;

	private String imgUrl;// 模板第一张图片
	private String accessAddress;

	public List<TbSaleTempletInfo> getTi() {
		return ti;
	}

	public void setTi(List<TbSaleTempletInfo> ti) {
		this.ti = ti;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getAccessAddress() {
		return accessAddress;
	}

	public void setAccessAddress(String accessAddress) {
		this.accessAddress = accessAddress;
	}

}
