package org.zcy.agriculture.param;

import java.io.Serializable;

public class TbUserParam implements Serializable {

    /** ID */
    private Long userId;
    /** 昵称 */
    private String nickName;
    /** 手机号 */
    private String phone;
    /** 用户邮箱 */
    private String email;
    /** 角色id */
    private String roleId;
    /** 农场id */
    private String farmId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    @Override
    public String toString() {
        return "TbUserParam{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", roleId='" + roleId + '\'' +
                ", farmId='" + farmId + '\'' +
                '}';
    }
}
