package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.vo.DeviceStatusStatisticsVo;
import org.zcy.agriculture.vo.DeviceTypeStatisticsVo;

import java.util.HashMap;
import java.util.List;

/**
 * 设备 服务层
 * 
 * @author zh
 * @date 2019-06-21
 */
public interface ITbResDeviceService 
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

	/**判断设备在物联网端是否存在*/
	public int isDeviceExistinDevice (String devNum);

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
     * 删除设备信息
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbResDeviceById(Long id);

	/**
	 * 统计设备状态信息
	 *
	 * @return 统计结果
	 */
	public DeviceStatusStatisticsVo deviceStatusStatistics(String farmID);

	/**
	 * 统计设备类型信息
	 *
	 * @return 统计结果
	 */
	public DeviceTypeStatisticsVo deviceTypeStatistics(String farmID);

	/**
	 * 根据传入的TbResDevice类型对象来获取对应的数据
	 *
	 * @return 统计结果
	 */
	public Long getCountFromTbResDevice(TbResDevice tbResDevice);

	public Long getCountFromTbResDeviceByDevNum(String devNum);

	/**
	 * 根据传入设备属性查询设备当前值并返回消息
	 *
	 * @param devNum 设备序列号
	 * @param attributeStr 设备传感器列表
	 * @return 字典数据集合信息
	 */
	public String getDeviceAttributeNowDataFromThingsboard(String devNum,String attributeStr);

	public String deviceAttributeWithRecent24Hours(String devNum,String attribut);

	/**
	 * 首页设备统计
	 * @param farmId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByDeviceType(String farmId);

	public String getIOTToken();

	/**
	 * 更新灌溉设备状态
	 * @param resDevice
	 * @return
	 */
    int updateIrrigationDevice(TbResDevice resDevice) throws Exception;


	public int getTbResDeviceAttributeFromThingsboard(TbResDevice tbResDevice);
	/**
	 * 根据地块 和类型查询 设备 
	 * @param map
	 * @return
	 */
	public List<TbResDevice>selectByDeviceList(HashMap<String,Object> map);
}
