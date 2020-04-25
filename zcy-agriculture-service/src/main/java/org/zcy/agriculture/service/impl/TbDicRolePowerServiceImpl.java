package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbDicRolePower;
import org.zcy.agriculture.mapper.TbDicRolePowerMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbDicRolePowerService;

/**
 * 角色权限关联 服务层实现
 * 
 * @author zh
 * @date 2019-07-02
 */
@Service
public class TbDicRolePowerServiceImpl implements ITbDicRolePowerService
{
	@Autowired
	private TbDicRolePowerMapper tbDicRolePowerMapper;

	/**
     * 查询角色权限关联信息
     * 
     * @param roleId 角色权限关联ID
     * @return 角色权限关联信息
     */
    @Override
	public TbDicRolePower selectTbDicRolePowerById(Long roleId)
	{
	    return tbDicRolePowerMapper.selectTbDicRolePowerById(roleId);
	}
	
	/**
     * 查询角色权限关联列表
     * 
     * @param tbDicRolePower 角色权限关联信息
     * @return 角色权限关联集合
     */
	@Override
	public List<TbDicRolePower> selectTbDicRolePowerList(TbDicRolePower tbDicRolePower)
	{
	    return tbDicRolePowerMapper.selectTbDicRolePowerList(tbDicRolePower);
	}
	
    /**
     * 新增角色权限关联
     * 
     * @param tbDicRolePower 角色权限关联信息
     * @return 结果
     */
	@Override
	public int insertTbDicRolePower(TbDicRolePower tbDicRolePower)
	{
	    return tbDicRolePowerMapper.insertTbDicRolePower(tbDicRolePower);
	}

	/**
	 * 新增角色权限关联集合
	 *
	 * @param tbDicRolePowerList 角色权限关联信息
	 * @return 结果
	 */
	@Override
	public int insertTbDicRolePowerList(List<TbDicRolePower> tbDicRolePowerList) {
		return tbDicRolePowerMapper.insertTbDicRolePowerList(tbDicRolePowerList);
	}

	/**
     * 修改角色权限关联
     * 
     * @param tbDicRolePower 角色权限关联信息
     * @return 结果
     */
	@Override
	public int updateTbDicRolePower(TbDicRolePower tbDicRolePower)
	{
	    return tbDicRolePowerMapper.updateTbDicRolePower(tbDicRolePower);
	}

	/**
     * 删除角色权限关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbDicRolePowerByIds(String ids)
	{
		return tbDicRolePowerMapper.deleteTbDicRolePowerByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除角色权限关联
	 *
	 * @param roleId 根据角色ID产出关联权限
	 * @return 结果
	 */
	public int deleteTbDicRolePowerById(Long roleId)
	{
		return tbDicRolePowerMapper.deleteTbDicRolePowerById(roleId);
	}
}
