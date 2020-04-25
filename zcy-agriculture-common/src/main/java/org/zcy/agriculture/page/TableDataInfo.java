package org.zcy.agriculture.page;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author numberone
 */
public class TableDataInfo implements Serializable {
	private static final long serialVersionUID = -5183329705361963576L;
	/** 总记录数 */
	private long total;
	/** 列表数据 */
	private Object rows;
	/** 消息状态码 */
	private int code;
	/** 返回描述 */
	private String msg;

	/**
	 * 表格数据对象
	 */
	public TableDataInfo() {
	}
	public TableDataInfo(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	/**
	 * 分页
	 * 
	 * @param list  列表数据
	 * @param total 总记录数
	 */
	public TableDataInfo(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
