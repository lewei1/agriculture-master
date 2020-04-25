package org.zcy.agriculture.entity;

import java.util.Date;

/**
 * 日志记录表 tb_pay_log
 * 
 * @author numberone
 * @date 2019-06-29
 */
public class TbPayLog extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 日志ID */
	private Integer logId;
	/** 管理员ID */
	private String userId;
	/** 农场ID */
	private String farmId;
	/** 创建时间 */
	private Date createTime;
	/** 耗时 */
	private Integer spendTime;
	/** 请求类型 */
	private String method;
	/** 用户标识 */
	private String userAgent;
	/** 用户IP */
	private String userIp;
	/** 请求内容 */
	private String optContent;
	/** 请求路径 */
	private String url;
	/**  */
	private String optResult;

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	public String getFarmId() {
		return farmId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setSpendTime(Integer spendTime) {
		this.spendTime = spendTime;
	}

	public Integer getSpendTime() {
		return spendTime;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}

	public String getOptContent() {
		return optContent;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setOptResult(String optResult) {
		this.optResult = optResult;
	}

	public String getOptResult() {
		return optResult;
	}

}
