package org.zcy.agriculture.vo;

import org.zcy.agriculture.entity.TbResDeviceAttributes;

/**
 * 设备属性（每个设备包含的属性记录）表 tb_res_device_attributes
 * 
 * @author numberone
 * @date 2019-07-08
 */
public class TbResDeviceAttributesVo extends TbResDeviceAttributes {
	private static final long serialVersionUID = 7643986675857082836L;
	private Integer isTypesIndex;

	public Integer getIsTypesIndex() {
		return isTypesIndex;
	}

	public void setIsTypesIndex(Integer isTypesIndex) {
		this.isTypesIndex = isTypesIndex;
	}

}
