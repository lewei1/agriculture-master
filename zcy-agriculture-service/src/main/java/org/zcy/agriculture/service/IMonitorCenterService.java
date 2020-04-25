package org.zcy.agriculture.service;

import org.zcy.agriculture.param.MonitorDeviceInfoParam;
import org.zcy.agriculture.vo.monitoringcenter.PlotAndDeviceDetailsVo;

/**
 * 农机类型 服务层
 * 
 * @author zh
 * @date 2019-06-21
 */
public interface IMonitorCenterService
{

	public PlotAndDeviceDetailsVo getPlotAndDeviceDetailsInfo(String farmId, Long plotId);

	/**
	 * 根据传入条件查询历史最大值/最小值
	 *
	 * @param param 包含开始日期/截止日期等字符串
	 * @param flag 历史值（0最小值，1最大值，2平均值）
	 * @return 字典数据集合信息
	 */
	public String deviceNowOrHistoryMaxMinAttribute(MonitorDeviceInfoParam param, int flag);

	/**
	 * 获取设备历史数据
	 *
	 * @param param （包含开始日期，结束日期，以及统计的时间间隔）
	 * @return 物联网端查询结果
	 */
	public String deviceAttributeHistoryInfo(MonitorDeviceInfoParam param);

	public String deviceAttributeHistoryInfoWithPage(MonitorDeviceInfoParam param,int pageNum,int pageSize);

}
