package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.vo.TbResDeviceAttributesVo;

import java.util.HashMap;
import java.util.List;

/**
 * 设备属性（每个设备包含的属性记录） 数据层
 * 
 * @author zh
 * @date 2019-07-08
 */
public interface TbResDeviceAttributesMapper 
{
	/**
     * 查询设备属性（每个设备包含的属性记录）信息
     * 
     * @param id 设备属性（每个设备包含的属性记录）ID
     * @return 设备属性（每个设备包含的属性记录）信息
     */
	public TbResDeviceAttributes selectTbResDeviceAttributesById(Long id);
	
	/**
     * 查询设备属性（每个设备包含的属性记录）列表
     * 
     * @param tbResDeviceAttributes 设备属性（每个设备包含的属性记录）信息
     * @return 设备属性（每个设备包含的属性记录）集合
     */
	public List<TbResDeviceAttributes> selectTbResDeviceAttributesList(TbResDeviceAttributesVo tbResDeviceAttributes);
	
	/**
     * 新增设备属性（每个设备包含的属性记录）
     * 
     * @param tbResDeviceAttributes 设备属性（每个设备包含的属性记录）信息
     * @return 结果
     */
	public int insertTbResDeviceAttributes(TbResDeviceAttributes tbResDeviceAttributes);
	
	/**
     * 修改设备属性（每个设备包含的属性记录）
     * 
     * @param tbResDeviceAttributes 设备属性（每个设备包含的属性记录）信息
     * @return 结果
     */
	public int updateTbResDeviceAttributes(TbResDeviceAttributes tbResDeviceAttributes);
	
	/**
     * 删除设备属性（每个设备包含的属性记录）
     * 
     * @param id 设备属性（每个设备包含的属性记录）ID
     * @return 结果
     */
	public int deleteTbResDeviceAttributesById(Long id);
	
	/**
     * 批量删除设备属性（每个设备包含的属性记录）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbResDeviceAttributesByIds(String[] ids);

	/**
	 * 删除设备属性（每个设备包含的属性记录）
	 *
	 * @param devId 设备ID
	 * @return 结果
	 */
	public int deleteTbResDeviceAttributesByDevId(Long devId);
	/**
	 * 根据地块查询设备和传感器
	 * @param plotId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByDevNumAndAttributes(Long plotId);
}