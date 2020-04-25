package org.zcy.agriculture.vo;

import org.zcy.agriculture.entity.TbWarehouseRecord;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 出入库记录表 tb_warehouse_record
 * 
 * @author linlq
 * @date 2019-06-28
 */
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TbWarehouseRecordVo extends TbWarehouseRecord {
	private static final long serialVersionUID = -3263765849121853548L;
	private Integer cou;// 统计出入口数量

	public Integer getCou() {
		return cou;
	}

	public void setCou(Integer cou) {
		this.cou = cou;
	}

}
