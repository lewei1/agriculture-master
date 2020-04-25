package org.zcy.agriculture.vo.registerlogin;

import org.zcy.agriculture.vo.BaseVo;

import java.util.Date;

public class LoginUserVo extends BaseVo {

    /** ID 可以是用户id或者商户id，登录的时候不将userId保存到redis中，
     * 进入农场了才能确定id是用户表还是注册表的id*/
    private Long id;
    /** 编码*/
    private Long code;
    /** 昵称 */
    private String nickName;
    /** 账号，要么是手机号，要么是邮箱地址 */
    private String account;
    /** 手机号 */
    private String phone;
    /** 用户邮箱 */
    private String email;
    /** 0正常 1停用 */
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
    /** 头像 */
    private String headUrl;
    /** 密码 */
    private String password;
    /** 公司名称 */
    private String companyName;

    public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
