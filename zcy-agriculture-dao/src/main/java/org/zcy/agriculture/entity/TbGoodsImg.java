package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品图片表 tb_goods_img
 * 
 * @author numberone
 * @date 2019-06-17
 */
public class TbGoodsImg extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long imgId;
	/** 图片url */
	private String imgUrl;
	/** 图片序列 */
	private Integer imgSequence;
	/** 状态，0-删除,1-正常  */
	private Integer imgStatus;
	/** 商品id */
	private Long goodsId;

	public void setImgId(Long imgId) 
	{
		this.imgId = imgId;
	}

	public Long getImgId() 
	{
		return imgId;
	}
	public void setImgUrl(String imgUrl) 
	{
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() 
	{
		return imgUrl;
	}
	public void setImgSequence(Integer imgSequence) 
	{
		this.imgSequence = imgSequence;
	}

	public Integer getImgSequence() 
	{
		return imgSequence;
	}
	public void setImgStatus(Integer imgStatus) 
	{
		this.imgStatus = imgStatus;
	}

	public Integer getImgStatus() 
	{
		return imgStatus;
	}
	public void setGoodsId(Long goodsId) 
	{
		this.goodsId = goodsId;
	}

	public Long getGoodsId() 
	{
		return goodsId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("imgId", getImgId())
            .append("imgUrl", getImgUrl())
            .append("imgSequence", getImgSequence())
            .append("imgStatus", getImgStatus())
            .append("goodsId", getGoodsId())
            .toString();
    }
}
