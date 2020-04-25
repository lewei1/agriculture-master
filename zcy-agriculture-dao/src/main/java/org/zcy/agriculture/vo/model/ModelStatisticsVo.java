package org.zcy.agriculture.vo.model;

import org.zcy.agriculture.vo.BaseVo;

/**
 * 作物模型-概览-上边栏模型统计
 */
public class ModelStatisticsVo extends BaseVo {

    private Integer totalModelNumber;

    private Integer farmModelNumber;

    public Integer getTotalModelNumber() {
        return totalModelNumber;
    }

    public void setTotalModelNumber(Integer totalModelNumber) {
        this.totalModelNumber = totalModelNumber;
    }

    public Integer getFarmModelNumber() {
        return farmModelNumber;
    }

    public void setFarmModelNumber(Integer farmModelNumber) {
        this.farmModelNumber = farmModelNumber;
    }
}
