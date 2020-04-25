package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbDicDeviceGroup;
import org.zcy.agriculture.mapper.TbDicDeviceGroupMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbDicDeviceGroupService;

/**
 * 设备与分组关联 服务层实现
 * 
 * @author zh
 * @date 2019-06-21
 */
@Service
public class TbDicDeviceGroupServiceImpl implements ITbDicDeviceGroupService
{
	@Autowired
	private TbDicDeviceGroupMapper tbDicDeviceGroupMapper;

	/**
     * 查询设备与分组关联信息
     * 
     * @param devGroupId 设备与分组关联ID
     * @return 设备与分组关联信息
     */
    @Override
	public TbDicDeviceGroup selectTbDicDeviceGroupById(Long devGroupId)
	{
	    return tbDicDeviceGroupMapper.selectTbDicDeviceGroupById(devGroupId);
	}
	
	/**
     * 查询设备与分组关联列表
     * 
     * @param tbDicDeviceGroup 设备与分组关联信息
     * @return 设备与分组关联集合
     */
	@Override
	public List<TbDicDeviceGroup> selectTbDicDeviceGroupList(TbDicDeviceGroup tbDicDeviceGroup)
	{
	    return tbDicDeviceGroupMapper.selectTbDicDeviceGroupList(tbDicDeviceGroup);
	}
	
    /**
     * 新增设备与分组关联
     * 
     * @param tbDicDeviceGroup 设备与分组关联信息
     * @return 结果
     */
	@Override
	public int insertTbDicDeviceGroup(TbDicDeviceGroup tbDicDeviceGroup)
	{
	    return tbDicDeviceGroupMapper.insertTbDicDeviceGroup(tbDicDeviceGroup);
	}
	
	/**
     * 修改设备与分组关联
     * 
     * @param tbDicDeviceGroup 设备与分组关联信息
     * @return 结果
     */
	@Override
	public int updateTbDicDeviceGroup(TbDicDeviceGroup tbDicDeviceGroup)
	{
	    return tbDicDeviceGroupMapper.updateTbDicDeviceGroup(tbDicDeviceGroup);
	}

	/**
     * 删除设备与分组关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbDicDeviceGroupByIds(String ids)
	{
		return tbDicDeviceGroupMapper.deleteTbDicDeviceGroupByIds(Convert.toStrArray(ids));
	}
	
}
