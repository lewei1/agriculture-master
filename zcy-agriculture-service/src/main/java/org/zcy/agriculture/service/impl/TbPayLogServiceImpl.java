package org.zcy.agriculture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbPayLog;
import org.zcy.agriculture.mapper.TbPayLogMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.TbPayLogService;

/**
 * 日志记录 服务层实现
 * 
 * @author numberone
 * @date 2019-06-29
 */
@Service
public class TbPayLogServiceImpl implements TbPayLogService {
	@Autowired
	private TbPayLogMapper tbPayLogMapper;

	/**
	 * 查询日志记录信息
	 * 
	 * @param logId 日志记录ID
	 * @return 日志记录信息
	 */
	@Override
	public TbPayLog selectTbPayLogById(Integer logId) {
		return tbPayLogMapper.selectTbPayLogById(logId);
	}

	/**
	 * 查询日志记录列表
	 * 
	 * @param tbPayLog 日志记录信息
	 * @return 日志记录集合
	 */
	@Override
	public List<TbPayLog> selectTbPayLogList(TbPayLog tbPayLog) {
		return tbPayLogMapper.selectTbPayLogList(tbPayLog);
	}

	/**
	 * 新增日志记录
	 * 
	 * @param tbPayLog 日志记录信息
	 * @return 结果
	 */
	@Override
	public int insertTbPayLog(TbPayLog tbPayLog) {
		return tbPayLogMapper.insertTbPayLog(tbPayLog);
	}

	/**
	 * 修改日志记录
	 * 
	 * @param tbPayLog 日志记录信息
	 * @return 结果
	 */
	@Override
	public int updateTbPayLog(TbPayLog tbPayLog) {
		return tbPayLogMapper.updateTbPayLog(tbPayLog);
	}

	/**
	 * 删除日志记录对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteTbPayLogByIds(String ids) {
		return tbPayLogMapper.deleteTbPayLogByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除30天以前的数据
	 * 
	 * @param today
	 * @return
	 */
	public int deleteByDate(String today) {
		return tbPayLogMapper.deleteByDate(today);
	}
}
