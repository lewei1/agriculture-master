package org.zcy.agriculture.service;

import java.util.List;

import org.zcy.agriculture.entity.TbPayLog;

/**
 * 日志记录 服务层
 * 
 * @author numberone
 * @date 2019-06-29
 */
public interface TbPayLogService {
	/**
	 * 查询日志记录信息
	 * 
	 * @param logId 日志记录ID
	 * @return 日志记录信息
	 */
	public TbPayLog selectTbPayLogById(Integer logId);

	/**
	 * 查询日志记录列表
	 * 
	 * @param tbPayLog 日志记录信息
	 * @return 日志记录集合
	 */
	public List<TbPayLog> selectTbPayLogList(TbPayLog tbPayLog);

	/**
	 * 新增日志记录
	 * 
	 * @param tbPayLog 日志记录信息
	 * @return 结果
	 */
	public int insertTbPayLog(TbPayLog tbPayLog);

	/**
	 * 修改日志记录
	 * 
	 * @param tbPayLog 日志记录信息
	 * @return 结果
	 */
	public int updateTbPayLog(TbPayLog tbPayLog);

	/**
	 * 删除日志记录信息
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbPayLogByIds(String ids);
	/**
	 * 删除30天以前的数据
	 * @param today
	 * @return
	 */
	public int deleteByDate(String today);
}
