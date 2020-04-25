package org.zcy.agriculture.entity;

import java.util.List;

public class TbModelPeriodAndFarmings extends TbModelPeriod {


    private List<TbModelPeriodFarming> farmingList;

    public List<TbModelPeriodFarming> getFarmingList() {
        return farmingList;
    }

    public void setFarmingList(List<TbModelPeriodFarming> farmingList) {
        this.farmingList = farmingList;
    }

}
