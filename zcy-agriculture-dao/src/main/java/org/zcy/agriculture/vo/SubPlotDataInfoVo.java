package org.zcy.agriculture.vo;

/**
 * 子地块数据
 *
 * @author zh
 * @date 2019-06-27
 */
public class SubPlotDataInfoVo extends BaseVo {
    /**子地块数量*/
    private Long subPlotNum;
    /**管理人员数量*/
    private Long managersNum;
    /**检测设备数量*/
    private Long monNum;

    public Long getSubPlotNum() {
        return subPlotNum;
    }

    public void setSubPlotNum(Long subPlotNum) {
        this.subPlotNum = subPlotNum;
    }

    public Long getManagersNum() {
        return managersNum;
    }

    public void setManagersNum(Long managersNum) {
        this.managersNum = managersNum;
    }

    public Long getMonNum() {
        return monNum;
    }

    public void setMonNum(Long monNum) {
        this.monNum = monNum;
    }
}
