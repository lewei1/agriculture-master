package org.zcy.agriculture.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 出入库动态表 tb_warehouse_dynamic
 *
 * @author linlq
 * @date 2019-07-11
 */
public class TbWarehouseDynamic extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 出入库动态ID
     */
    private Long id;
    /**
     * 出入库操作类型,(O - 出库，I - 入库)
     */
    private String inOutType;
    /**
     * 动态信息
     */
    private String info;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 农场ID
     */
    private String farmId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }

    public String getInOutType() {
        return inOutType;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmId() {
        return farmId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("inOutType", getInOutType())
                .append("info", getInfo())
                .append("createTime", getCreateTime())
                .append("farmId", getFarmId())
                .toString();
    }
}
