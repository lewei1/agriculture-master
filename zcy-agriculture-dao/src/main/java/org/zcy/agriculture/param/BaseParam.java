package org.zcy.agriculture.param;

import java.io.Serializable;

public class BaseParam implements Serializable {

    private String farmId;

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }
}
