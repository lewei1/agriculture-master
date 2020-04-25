package org.zcy.agriculture.entity;


/**
 * 农场和用户中间表 tb_farm_user
 *
 * @author numberone
 * @date 2019-07-11
 */
public class TbFarmUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long roleId;
    /**
     * 农场id
     */
    private String farmId;
    /**
     * 用户id
     */
    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
