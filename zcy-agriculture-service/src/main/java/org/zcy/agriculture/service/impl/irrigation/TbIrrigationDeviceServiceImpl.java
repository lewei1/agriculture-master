package org.zcy.agriculture.service.impl.irrigation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbIrrigationDevice;
import org.zcy.agriculture.mapper.irrigation.TbIrrigationDeviceMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.irrigation.ITbIrrigationDeviceService;

/**
 * 灌溉分组设备中间 服务层实现
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbIrrigationDeviceServiceImpl implements ITbIrrigationDeviceService
{
	@Autowired
	private TbIrrigationDeviceMapper tbIrrigationDeviceMapper;

	/**
     * 查询灌溉分组设备中间信息
     * 
     * @param deviceId 灌溉分组设备中间ID
     * @return 灌溉分组设备中间信息
     */
    @Override
	public TbIrrigationDevice selectTbIrrigationDeviceById(Long deviceId)
	{
	    return tbIrrigationDeviceMapper.selectTbIrrigationDeviceById(deviceId);
	}
	
	/**
     * 查询灌溉分组设备中间列表
     * 
     * @param tbIrrigationDevice 灌溉分组设备中间信息
     * @return 灌溉分组设备中间集合
     */
	@Override
	public List<TbIrrigationDevice> selectTbIrrigationDeviceList(TbIrrigationDevice tbIrrigationDevice)
	{
	    return tbIrrigationDeviceMapper.selectTbIrrigationDeviceList(tbIrrigationDevice);
	}
	
    /**
     * 新增灌溉分组设备中间
     * 
     * @param tbIrrigationDevice 灌溉分组设备中间信息
     * @return 结果
     */
	@Override
	public int insertTbIrrigationDevice(TbIrrigationDevice tbIrrigationDevice)
	{
	    return tbIrrigationDeviceMapper.insertTbIrrigationDevice(tbIrrigationDevice);
	}
	
	/**
     * 修改灌溉分组设备中间
     * 
     * @param tbIrrigationDevice 灌溉分组设备中间信息
     * @return 结果
     */
	@Override
	public int updateTbIrrigationDevice(TbIrrigationDevice tbIrrigationDevice)
	{
	    return tbIrrigationDeviceMapper.updateTbIrrigationDevice(tbIrrigationDevice);
	}

	/**
     * 删除灌溉分组设备中间对象
     * 
     * @param tbIrrigationDevice 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbIrrigationDevice(TbIrrigationDevice tbIrrigationDevice)
	{
		return tbIrrigationDeviceMapper.deleteTbIrrigationDevice(tbIrrigationDevice);
	}
	
}
