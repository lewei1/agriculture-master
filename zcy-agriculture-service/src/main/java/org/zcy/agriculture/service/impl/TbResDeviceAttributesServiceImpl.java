package org.zcy.agriculture.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.mapper.TbResDeviceAttributesMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbResDeviceAttributesService;
import org.zcy.agriculture.vo.TbResDeviceAttributesVo;

/**
 * 设备属性（每个设备包含的属性记录） 服务层实现
 * 
 * @author numberone
 * @date 2019-06-21
 */
@Service
public class TbResDeviceAttributesServiceImpl implements ITbResDeviceAttributesService 
{
	@Autowired
	private TbResDeviceAttributesMapper tbResDeviceAttributesMapper;

	/**
     * 查询设备属性（每个设备包含的属性记录）信息
     * 
     * @param id 设备属性（每个设备包含的属性记录）ID
     * @return 设备属性（每个设备包含的属性记录）信息
     */
    @Override
	public TbResDeviceAttributes selectTbResDeviceAttributesById(Long id)
	{
	    return tbResDeviceAttributesMapper.selectTbResDeviceAttributesById(id);
	}
	
	/**
     * 查询设备属性（每个设备包含的属性记录）列表
     * 
     * @param tbResDeviceAttributes 设备属性（每个设备包含的属性记录）信息
     * @return 设备属性（每个设备包含的属性记录）集合
     */
	@Override
	public List<TbResDeviceAttributes> selectTbResDeviceAttributesList(TbResDeviceAttributesVo tbResDeviceAttributes)
	{
	    return tbResDeviceAttributesMapper.selectTbResDeviceAttributesList(tbResDeviceAttributes);
	}
	
    /**
     * 新增设备属性（每个设备包含的属性记录）
     * 
     * @param tbResDeviceAttributes 设备属性（每个设备包含的属性记录）信息
     * @return 结果
     */
	@Override
	public int insertTbResDeviceAttributes(TbResDeviceAttributes tbResDeviceAttributes)
	{
	    return tbResDeviceAttributesMapper.insertTbResDeviceAttributes(tbResDeviceAttributes);
	}
	
	/**
     * 修改设备属性（每个设备包含的属性记录）
     * 
     * @param tbResDeviceAttributes 设备属性（每个设备包含的属性记录）信息
     * @return 结果
     */
	@Override
	public int updateTbResDeviceAttributes(TbResDeviceAttributes tbResDeviceAttributes)
	{
	    return tbResDeviceAttributesMapper.updateTbResDeviceAttributes(tbResDeviceAttributes);
	}

	/**
     * 删除设备属性（每个设备包含的属性记录）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbResDeviceAttributesByIds(String ids)
	{
		return tbResDeviceAttributesMapper.deleteTbResDeviceAttributesByIds(Convert.toStrArray(ids));
	}
	/**
	 * 根据地块查询设备和传感器
	 * @param plotId
	 * @return
	 */
	public List<HashMap<String,Object>> selectByDevNumAndAttributes(Long plotId){
		return tbResDeviceAttributesMapper.selectByDevNumAndAttributes(plotId);
	}
}
