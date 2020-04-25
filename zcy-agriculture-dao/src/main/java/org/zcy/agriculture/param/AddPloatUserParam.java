package org.zcy.agriculture.param;

import java.io.Serializable;

public class AddPloatUserParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6903809391206717701L;
	/** 地块id */
	private Long plotId;
	/** 子地块id */
	private Long subPlotId;
	/** 责任人 */
	private Long responserId;
	/** 参与人id */
	private String participantIds;

	public Long getPlotId() {
		return plotId;
	}

	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}

	public Long getSubPlotId() {
		return subPlotId;
	}

	public void setSubPlotId(Long subPlotId) {
		this.subPlotId = subPlotId;
	}

	public Long getResponserId() {
		return responserId;
	}

	public void setResponserId(Long responserId) {
		this.responserId = responserId;
	}

	public String getParticipantIds() {
		return participantIds;
	}

	public void setParticipantIds(String participantIds) {
		this.participantIds = participantIds;
	}

}
