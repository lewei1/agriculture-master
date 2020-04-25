package org.zcy.agriculture.entity;

import org.zcy.agriculture.util.StringUtils;

/**
 * 展示模板详情表 tb_sale_templet_info
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbSaleTempletInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 销售管理，展示模板ID */
	private Long saleTempletId;
	/** 名称 */
	private String name;
	/** 链接 */
	private String link;
	/** 图片地址 */
	private String imgUrl;
	private String [] imgUrls;
	/** 图片类型（1：农场图片栏，2报告栏，3证书栏，4产品栏） */
	private Integer type;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setSaleTempletId(Long saleTempletId) 
	{
		this.saleTempletId = saleTempletId;
	}

	public Long getSaleTempletId() 
	{
		return saleTempletId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setLink(String link) 
	{
		this.link = link;
	}

	public String getLink() 
	{
		return link;
	}
	public void setImgUrl(String imgUrl) 
	{
		this.imgUrl = imgUrl;
		if(StringUtils.isNotEmpty(imgUrl)) {
			imgUrls = imgUrl.split(",");
		}
	}

	public String getImgUrl() 
	{
		return imgUrl;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}

	public String[] getImgUrls() {
		return imgUrls;
	}
}
