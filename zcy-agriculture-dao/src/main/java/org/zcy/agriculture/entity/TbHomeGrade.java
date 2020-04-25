package org.zcy.agriculture.entity;


/**
 * 首页模块排列表 tb_home_grade
 * 
 * @author numberone
 * @date 2019-07-11
 */
public class TbHomeGrade extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 排列顺序 */
	private Integer sort;
	/** 字典值(对应 sys_dict_data 字段) */
	private String dictValue;
	/** 农场ID */
	private String farmId;
	private Integer status;//状态（0正常，1关闭）

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setSort(Integer sort) 
	{
		this.sort = sort;
	}

	public Integer getSort() 
	{
		return sort;
	}
	public void setDictValue(String dictValue) 
	{
		this.dictValue = dictValue;
	}

	public String getDictValue() 
	{
		return dictValue;
	}
	public void setFarmId(String farmId) 
	{
		this.farmId = farmId;
	}

	public String getFarmId() 
	{
		return farmId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
