package org.zcy.agriculture.service.impl.irrigation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbIrrigationLog;
import org.zcy.agriculture.mapper.irrigation.TbIrrigationLogMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.irrigation.IrrigationLogParam;
import org.zcy.agriculture.service.irrigation.ITbIrrigationLogService;
import org.zcy.agriculture.util.DateUtils;

/**
 * 灌溉操作日志 服务层实现
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbIrrigationLogServiceImpl implements ITbIrrigationLogService
{
	@Autowired
	private TbIrrigationLogMapper tbIrrigationLogMapper;

	/**
     * 查询灌溉操作日志信息
     * 
     * @param actionLogId 灌溉操作日志ID
     * @return 灌溉操作日志信息
     */
    @Override
	public TbIrrigationLog selectTbIrrigationLogById(Long actionLogId)
	{
	    return tbIrrigationLogMapper.selectTbIrrigationLogById(actionLogId);
	}
	
	/**
     * 查询灌溉操作日志列表
     * 
     * @param tbIrrigationLog 灌溉操作日志信息
     * @return 灌溉操作日志集合
     */
	@Override
	public List<TbIrrigationLog> selectTbIrrigationLogList(TbIrrigationLog tbIrrigationLog)
	{
	    return tbIrrigationLogMapper.selectTbIrrigationLogList(tbIrrigationLog);
	}


	/**
	 * 查询灌溉操作日志列表
	 *
	 * @param param 灌溉操作日志信息
	 * @return 灌溉操作日志集合
	 */
	@Override
	public List<TbIrrigationLog> selectTbIrrigationLogList(IrrigationLogParam param)
	{
		return tbIrrigationLogMapper.selectTbIrrigationLogList2(param);
	}

    /**
     * 新增灌溉操作日志
     * 
     * @param tbIrrigationLog 灌溉操作日志信息
     * @return 结果
     */
	@Override
	public int insertTbIrrigationLog(TbIrrigationLog tbIrrigationLog)
	{
	    return tbIrrigationLogMapper.insertTbIrrigationLog(tbIrrigationLog);
	}
	
	/**
     * 修改灌溉操作日志
     * 
     * @param tbIrrigationLog 灌溉操作日志信息
     * @return 结果
     */
	@Override
	public int updateTbIrrigationLog(TbIrrigationLog tbIrrigationLog)
	{
	    return tbIrrigationLogMapper.updateTbIrrigationLog(tbIrrigationLog);
	}

	/**
     * 删除灌溉操作日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbIrrigationLogByIds(String ids)
	{
		return tbIrrigationLogMapper.deleteTbIrrigationLogByIds(Convert.toStrArray(ids));
	}
	/**
	 * 根据农场ID查询 设备操作记录
	 * 
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String, Object>> selectByFarmIdList(String farmId){
		return tbIrrigationLogMapper.selectByFarmIdList(farmId);
	}
	/**
	 * 根据农场ID查询  统计操作次数
	 * @param farmId
	 * @return
	 */
	public Integer selectByFarmIdCou(String farmId,String createTimeStr){
		return tbIrrigationLogMapper.selectByFarmIdCou(farmId,createTimeStr);
	}
}
