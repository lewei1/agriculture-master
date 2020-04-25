package org.zcy.agriculture.entity;

import java.util.Date;

import org.zcy.agriculture.util.DateTimeJsonSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 农事记录消息表 tb_plan_message
 * 
 * @author numberone
 * @date 2019-07-05
 */
public class TbPlanMessage extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 主键id */
	private Long messageId;
	/** 消息内容 */
	private String messageContent;
	/** 创建时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date createTime;
	/** 创建人(添加记录的人) */
	private Long createBy;
	/** 农事计划id */
	private Long planId;
	/** 图片地址，多个已分号隔开（;） */
	private String imgUrl;
	private Integer status;

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getPlanId() {
		return planId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
