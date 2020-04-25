package org.zcy.agriculture.vo;

import org.zcy.agriculture.entity.TbHomeGrade;

/**
 * 首页模块排列表 tb_home_grade
 * 
 * @author numberone
 * @date 2019-07-11
 */
public class TbHomeGradeVo extends TbHomeGrade {
	private static final long serialVersionUID = -1824503829968933592L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
