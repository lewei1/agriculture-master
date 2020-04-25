package org.zcy.agriculture.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 消息记录表 tb_message_record
 * 
 * @author lky
 * @date 2019-07-16
 */
public class TbMessageRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long messageId;
	/** 消息类型 0是报警消息 1是任务消息 */
	private Integer messageType;
	/** 消息主题 */
	private String messageSubject;
	/** 消息内容 */
	private String messageContent;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/** 消息状态 （0未读  1已读） */
	private Integer messageStatus;
	/** 发送方用户id */
	private Long sendUserId;
	/** 接收方用户id */
	private Long receiveUserId;
	/** 农场ID */
	private String farmId;

	public void setMessageId(Long messageId) 
	{
		this.messageId = messageId;
	}

	public Long getMessageId() 
	{
		return messageId;
	}
	public void setMessageType(Integer messageType) 
	{
		this.messageType = messageType;
	}

	public Integer getMessageType() 
	{
		return messageType;
	}
	public void setMessageSubject(String messageSubject) 
	{
		this.messageSubject = messageSubject;
	}

	public String getMessageSubject() 
	{
		return messageSubject;
	}
	public void setMessageContent(String messageContent) 
	{
		this.messageContent = messageContent;
	}

	public String getMessageContent() 
	{
		return messageContent;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setMessageStatus(Integer messageStatus) 
	{
		this.messageStatus = messageStatus;
	}

	public Integer getMessageStatus() 
	{
		return messageStatus;
	}
	public void setSendUserId(Long sendUserId) 
	{
		this.sendUserId = sendUserId;
	}

	public Long getSendUserId() 
	{
		return sendUserId;
	}
	public void setReceiveUserId(Long receiveUserId) 
	{
		this.receiveUserId = receiveUserId;
	}

	public Long getReceiveUserId() 
	{
		return receiveUserId;
	}
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("messageType", getMessageType())
            .append("messageSubject", getMessageSubject())
            .append("messageContent", getMessageContent())
            .append("createTime", getCreateTime())
            .append("messageStatus", getMessageStatus())
            .append("sendUserId", getSendUserId())
            .append("receiveUserId", getReceiveUserId())
            .append("farmId", getFarmId())
            .toString();
    }
}
