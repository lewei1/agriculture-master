package org.zcy.agriculture.service.irrigation;

import org.zcy.agriculture.entity.TbIrrigationLog;
import org.zcy.agriculture.param.irrigation.IrrigationLogParam;

import java.util.HashMap;
import java.util.List;

/**
 * 灌溉操作日志 服务层
 *
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbIrrigationLogService {
    /**
     * 查询灌溉操作日志信息
     *
     * @param actionLogId 灌溉操作日志ID
     * @return 灌溉操作日志信息
     */
    TbIrrigationLog selectTbIrrigationLogById(Long actionLogId);

    /**
     * 查询灌溉操作日志列表
     *
     * @param tbIrrigationLog 灌溉操作日志信息
     * @return 灌溉操作日志集合
     */
    List<TbIrrigationLog> selectTbIrrigationLogList(TbIrrigationLog tbIrrigationLog);

	List<TbIrrigationLog> selectTbIrrigationLogList(IrrigationLogParam param);

    /**
     * 新增灌溉操作日志
     *
     * @param tbIrrigationLog 灌溉操作日志信息
     * @return 结果
     */
    int insertTbIrrigationLog(TbIrrigationLog tbIrrigationLog);

    /**
     * 修改灌溉操作日志
     *
     * @param tbIrrigationLog 灌溉操作日志信息
     * @return 结果
     */
    int updateTbIrrigationLog(TbIrrigationLog tbIrrigationLog);

    /**
     * 删除灌溉操作日志信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTbIrrigationLogByIds(String ids);

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
