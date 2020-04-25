package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbDicDeviceGroup;

import java.util.List;

/**
 * 设备与分组关联 数据层
 * 
 * @author zh
 * @date 2019-06-21
 */
public interface TbDicDeviceGroupMapper 
{
	/**
     * 查询设备与分组关联信息
     * 
     * @param devGroupId 设备与分组关联ID
     * @return 设备与分组关联信息
     */
	public TbDicDeviceGroup selectTbDicDeviceGroupById(Long devGroupId);
	
	/**
     * 查询设备与分组关联列表
     * 
     * @param tbDicDeviceGroup 设备与分组关联信息
     * @return 设备与分组关联集合
     */
	public List<TbDicDeviceGroup> selectTbDicDeviceGroupList(TbDicDeviceGroup tbDicDeviceGroup);
	
	/**
     * 新增设备与分组关联
     * 
     * @param tbDicDeviceGroup 设备与分组关联信息
     * @return 结果
     */
	public int insertTbDicDeviceGroup(TbDicDeviceGroup tbDicDeviceGroup);
	
	/**
     * 修改设备与分组关联
     * 
     * @param tbDicDeviceGroup 设备与分组关联信息
     * @return 结果
     */
	public int updateTbDicDeviceGroup(TbDicDeviceGroup tbDicDeviceGroup);
	
	/**
     * 删除设备与分组关联
     * 
     * @param devGroupId 设备与分组关联ID
     * @return 结果
     */
	public int deleteTbDicDeviceGroupById(Long devGroupId);
	
	/**
     * 批量删除设备与分组关联
     * 
     * @param devGroupIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbDicDeviceGroupByIds(String[] devGroupIds);
	
}