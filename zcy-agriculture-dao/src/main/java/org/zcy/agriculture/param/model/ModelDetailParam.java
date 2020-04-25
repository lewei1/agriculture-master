package org.zcy.agriculture.param.model;

import org.zcy.agriculture.entity.TbModel;
import org.zcy.agriculture.entity.TbModelPeriodAndFarmings;

import java.util.List;

/**
 * 作物模型-新建或修改详情参数
 */
public class ModelDetailParam extends TbModel {

    private List<TbModelPeriodAndFarmings> periodAndFarmingsList;

    public List<TbModelPeriodAndFarmings> getPeriodAndFarmingsList() {
        return periodAndFarmingsList;
    }

    public void setPeriodAndFarmingsList(List<TbModelPeriodAndFarmings> periodAndFarmingsList) {
        this.periodAndFarmingsList = periodAndFarmingsList;
    }

}
