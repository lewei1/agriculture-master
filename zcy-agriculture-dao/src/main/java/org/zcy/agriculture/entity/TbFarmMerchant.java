package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商户和农场关联表 tb_farm_merchant
 * 
 * @author numberone
 * @date 2019-07-13
 */
public class TbFarmMerchant extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long farmMerchantId;
	/** 农场id */
	private String farmId;
	/** 商户id */
	private Long merchantId;

	public void setFarmMerchantId(Long farmMerchantId) 
	{
		this.farmMerchantId = farmMerchantId;
	}

	public Long getFarmMerchantId() 
	{
		return farmMerchantId;
	}
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}
	public void setMerchantId(Long merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Long getMerchantId() 
	{
		return merchantId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("farmMerchantId", getFarmMerchantId())
            .append("farmId", getFarmId())
            .append("merchantId", getMerchantId())
            .toString();
    }
}
