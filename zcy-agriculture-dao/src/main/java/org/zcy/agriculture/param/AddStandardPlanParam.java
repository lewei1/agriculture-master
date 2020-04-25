package org.zcy.agriculture.param;

import java.io.Serializable;
import java.util.List;

public class AddStandardPlanParam implements Serializable {
	private static final long serialVersionUID = 7843409934821939846L;

	private List<Long> farmingIds;

	private List<AddPloatUserParam> ploats;

	/** 添加人 */
	private Long createBy;
	/** 农场Id */
	private String farmId;

	public List<Long> getFarmingIds() {
		return farmingIds;
	}

	public void setFarmingIds(List<Long> farmingIds) {
		this.farmingIds = farmingIds;
	}

	public List<AddPloatUserParam> getPloats() {
		return ploats;
	}

	public void setPloats(List<AddPloatUserParam> ploats) {
		this.ploats = ploats;
	}
	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

}
