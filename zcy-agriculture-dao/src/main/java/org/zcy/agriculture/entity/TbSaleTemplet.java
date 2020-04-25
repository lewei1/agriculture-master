package org.zcy.agriculture.entity;

import java.util.Date;

/**
 * 销售管理-展示模板表 tb_sale_templet
 * 
 * @author numberone
 * @date 2019-07-01
 */
public class TbSaleTemplet extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long saleTempletId;
	/** 状态（0未发布，1已发布） */
	private Integer status;
	/** 农场名称 */
	private String farmName;
	/** 农场介绍 */
	private String farmRemark;
	/** 商城链接 */
	private String mallLink;
	/** 联系方式 */
	private String contactWay;
	/** 农场地址 */
	private String farmAddress;
	/** 是否选中地图（0未选中，1已选中） */
	private Integer isMap;
	/** 是否选中气候条件（0未选中，1已选中） */
	private Integer isClimate;
	/** 是否选中实时数据（0未选中，1已选中） */
	private Integer isRealData;
	/** 是否选中实时图像（0未选中，1已选中） */
	private Integer isRealPicture;
	/** 是否选中农业活动（0未选中，1已选中） */
	private Integer isActivity;
	/** 创建时间 */
	private Date createDate;
	/** 修改时间 */
	private Date updateDate;
	/** 创建人 */
	private Long createBy;
	/** 更新人 */
	private Long updateBy;
	/** 农场id,UUID */
	private String farmId;
	/** 二维码路径 */
	private String qrCodeUrl;
	private String corporateName;//企业名称
	private String corporateAddress;//企业地址
	public void setSaleTempletId(Long saleTempletId) 
	{
		this.saleTempletId = saleTempletId;
	}

	public Long getSaleTempletId() 
	{
		return saleTempletId;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setFarmName(String farmName) 
	{
		this.farmName = farmName;
	}

	public String getFarmName() 
	{
		return farmName;
	}
	public void setFarmRemark(String farmRemark) 
	{
		this.farmRemark = farmRemark;
	}

	public String getFarmRemark() 
	{
		return farmRemark;
	}
	public void setMallLink(String mallLink) 
	{
		this.mallLink = mallLink;
	}

	public String getMallLink() 
	{
		return mallLink;
	}
	public void setContactWay(String contactWay) 
	{
		this.contactWay = contactWay;
	}

	public String getContactWay() 
	{
		return contactWay;
	}
	public void setFarmAddress(String farmAddress) 
	{
		this.farmAddress = farmAddress;
	}

	public String getFarmAddress() 
	{
		return farmAddress;
	}
	public void setIsMap(Integer isMap) 
	{
		this.isMap = isMap;
	}

	public Integer getIsMap() 
	{
		return isMap;
	}
	public void setIsClimate(Integer isClimate) 
	{
		this.isClimate = isClimate;
	}

	public Integer getIsClimate() 
	{
		return isClimate;
	}
	public void setIsRealData(Integer isRealData) 
	{
		this.isRealData = isRealData;
	}

	public Integer getIsRealData() 
	{
		return isRealData;
	}
	public void setIsRealPicture(Integer isRealPicture) 
	{
		this.isRealPicture = isRealPicture;
	}

	public Integer getIsRealPicture() 
	{
		return isRealPicture;
	}
	public void setIsActivity(Integer isActivity) 
	{
		this.isActivity = isActivity;
	}

	public Integer getIsActivity() 
	{
		return isActivity;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setUpdateDate(Date updateDate) 
	{
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() 
	{
		return updateDate;
	}
	public void setCreateBy(Long createBy) 
	{
		this.createBy = createBy;
	}

	public Long getCreateBy() 
	{
		return createBy;
	}
	public void setUpdateBy(Long updateBy) 
	{
		this.updateBy = updateBy;
	}

	public Long getUpdateBy() 
	{
		return updateBy;
	}
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}
	public void setQrCodeUrl(String qrCodeUrl) 
	{
		this.qrCodeUrl = qrCodeUrl;
	}

	public String getQrCodeUrl() 
	{
		return qrCodeUrl;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getCorporateAddress() {
		return corporateAddress;
	}

	public void setCorporateAddress(String corporateAddress) {
		this.corporateAddress = corporateAddress;
	}

}
