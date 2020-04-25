package org.zcy.agriculture.vo;

import org.zcy.agriculture.entity.TbUser;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 用户（成员）表 tb_user
 * 
 * @author zh
 * @date 2019-06-25
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TbUserVo extends TbUser {
	private static final long serialVersionUID = 3334147925787746477L;
	private String farmId;

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

}
