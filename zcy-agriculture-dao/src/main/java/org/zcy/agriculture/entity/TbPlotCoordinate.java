package org.zcy.agriculture.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 地块坐标表 tb_plot_coordinate
 *
 * @author numberone
 * @date 2019-06-25
 */
public class TbPlotCoordinate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long coordinateId;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 状态
     */
    private Integer coordinateStatus;
    /**
     *
     */
    private Long plotId;

    public void setCoordinateId(Long coordinateId) {
        this.coordinateId = coordinateId;
    }

    public Long getCoordinateId() {
        return coordinateId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setCoordinateStatus(Integer coordinateStatus) {
        this.coordinateStatus = coordinateStatus;
    }

    public Integer getCoordinateStatus() {
        return coordinateStatus;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public Long getPlotId() {
        return plotId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("coordinateId", getCoordinateId())
                .append("latitude", getLatitude())
                .append("longitude", getLongitude())
                .append("coordinateStatus", getCoordinateStatus())
                .append("plotId", getPlotId())
                .toString();
    }
}
