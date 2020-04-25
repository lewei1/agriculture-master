package org.zcy.agriculture.mapper;

import org.springframework.web.bind.annotation.RequestParam;
import org.zcy.agriculture.entity.TbResDevice;

import java.util.HashMap;
import java.util.List;

/**
 * 设备 数据层
 * 
 * @author zh
 * @date 2019-06-24
 */
public interface TbResDeviceMapper 
{
	/**
     * 查询设备信息
     * 
     * @param devId 设备ID
     * @return 设备信息
     */
	public TbResDevice selectTbResDeviceById(Long devId);
	
	/**
     * 查询设备列表
     * 
     * @param tbResDevice 设备信息
     * @return 设备集合
     */
	public List<TbResDevice> selectTbResDeviceList(TbResDevice tbResDevice);
	
	/**
     * 新增设备
     * 
     * @param tbResDevice 设备信息
     * @return 结果
     */
	public int insertTbResDevice(TbResDevice tbResDevice);
	
	/**
     * 修改设备
     * 
     * @param tbResDevice 设备信息
     * @return 结果
     */
	public int updateTbResDevice(TbResDevice tbResDevice);
	
	/**
     * 删除设备
     * 
     * @param devId 设备ID
     * @return 结果
     */
	public int deleteTbResDeviceById(Long devId);

	/**
	 * 根据传入的TbResDevice对象返回对应的数量
	 * @return 数量
	 */
	public Long getCountFromTbResDevice(TbResDevice tbResDevice);

	public Long getCountFromTbResDeviceByDevNum(@RequestParam String devNum);
	/**
	 * 首页设备统计
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByDeviceType(String farmId);

	/**
	 * 根据设备编号，更新设备状态
	 * @param resDeviceList
	 * @return
	 */
	int updateResDeviceListStatus(List<TbResDevice> resDeviceList);
	
	/**
	 * 根据地块 和类型查询 设备 
	 * @param map
	 * @return
	 */
	public List<TbResDevice>selectByDeviceList(HashMap<String,Object> map);
}