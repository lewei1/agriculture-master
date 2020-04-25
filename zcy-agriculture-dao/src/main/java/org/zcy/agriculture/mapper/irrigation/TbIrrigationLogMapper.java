package org.zcy.agriculture.mapper.irrigation;

import org.zcy.agriculture.entity.TbIrrigationLog;
import org.zcy.agriculture.param.irrigation.IrrigationLogParam;

import java.util.HashMap;
import java.util.List;

/**
 * 灌溉操作日志 数据层
 *
 * @author numberone
 * @date 2019-07-01
 */
public interface TbIrrigationLogMapper {
    /**
     * 查询灌溉操作日志信息
     *
     * @param actionLogId 灌溉操作日志ID
     * @return 灌溉操作日志信息
     */
    public TbIrrigationLog selectTbIrrigationLogById(Long actionLogId);

    /**
     * 查询灌溉操作日志列表
     *
     * @param tbIrrigationLog 灌溉操作日志信息
     * @return 灌溉操作日志集合
     */
    public List<TbIrrigationLog> selectTbIrrigationLogList(TbIrrigationLog tbIrrigationLog);

	public List<TbIrrigationLog> selectTbIrrigationLogList2(IrrigationLogParam param);
    /**
     * 新增灌溉操作日志
     *
     * @param tbIrrigationLog 灌溉操作日志信息
     * @return 结果
     */
    public int insertTbIrrigationLog(TbIrrigationLog tbIrrigationLog);

    /**
     * 修改灌溉操作日志
     *
     * @param tbIrrigationLog 灌溉操作日志信息
     * @return 结果
     */
    public int updateTbIrrigationLog(TbIrrigationLog tbIrrigationLog);

    /**
     * 删除灌溉操作日志
     *
     * @param actionLogId 灌溉操作日志ID
     * @return 结果
     */
    public int deleteTbIrrigationLogById(Long actionLogId);

    /**
     * 批量删除灌溉操作日志
     *
     * @param actionLogIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbIrrigationLogByIds(String[] actionLogIds);

	/**
	 * 根据农场ID查询 设备操作记录
	 * 
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String, Object>> selectByFarmIdList(String farmId);
	/**
	 * 根据农场ID查询  统计操作次数
	 * @param farmId
	 * @return
	 */
	public Integer selectByFarmIdCou(String farmId,String createTimeStr);
}