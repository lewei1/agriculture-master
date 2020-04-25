package org.zcy.agriculture.entity;


import java.util.Date;

/**
 * 商户（超级管理员，注册用户）表 tb_merchant
 * 
 * @author numberone
 * @date 2019-07-13
 */
public class TbMerchant extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long merchantId;
	/** 商户编码 */
	private Long merchantCode;
	/** 昵称 */
	private String nickName;
	/** 头像 */
	private String headUrl;
	/** 账号 */
	private String account;
	/** 手机号 */
	private String phone;
	/** 用户邮箱 */
	private String email;
	/** 公司名称 */
	private String companyName;
	/** 商户状态(0-未开通，1-已开通，2-删除) */
	private Integer status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long createBy;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private Long updateBy;
	/** 角色ID */
	private Long roleId;
	/** 密码 */
	private String password;

	public Long getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(Long merchantCode) {
		this.merchantCode = merchantCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMerchantId(Long merchantId)
	{
		this.merchantId = merchantId;
	}

	public Long getMerchantId() 
	{
		return merchantId;
	}
	public void setNickName(String nickName) 
	{
		this.nickName = nickName;
	}

	public String getNickName() 
	{
		return nickName;
	}
	public void setHeadUrl(String headUrl) 
	{
		this.headUrl = headUrl;
	}

	public String getHeadUrl() 
	{
		return headUrl;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getAccount() 
	{
		return account;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName() 
	{
		return companyName;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setCreateBy(Long createBy) 
	{
		this.createBy = createBy;
	}

	public Long getCreateBy() 
	{
		return createBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setUpdateBy(Long updateBy) 
	{
		this.updateBy = updateBy;
	}

	public Long getUpdateBy() 
	{
		return updateBy;
	}
	public void setRoleId(Long roleId) 
	{
		this.roleId = roleId;
	}

	public Long getRoleId() 
	{
		return roleId;
	}

}
