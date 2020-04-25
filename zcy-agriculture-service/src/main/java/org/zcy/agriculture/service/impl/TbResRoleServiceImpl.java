package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbResRole;
import org.zcy.agriculture.mapper.TbResRoleMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbResRoleService;

/**
 * 角色 服务层实现
 * 
 * @author zenghao
 * @date 2019-06-20
 */
@Service
public class TbResRoleServiceImpl implements ITbResRoleService
{
	@Autowired
	private TbResRoleMapper tbResRoleMapper;

	/**
     * 查询角色信息
     * 
     * @param id 角色ID
     * @return 角色信息
     */
    @Override
	public TbResRole selectTbResRoleById(Long id)
	{
	    return tbResRoleMapper.selectTbResRoleById(id);
	}
	
	/**
     * 查询角色列表
     * 
     * @param tbResRole 角色信息
     * @return 角色集合
     */
	@Override
	public List<TbResRole> selectTbResRoleList(TbResRole tbResRole)
	{
	    return tbResRoleMapper.selectTbResRoleList(tbResRole);
	}
	
    /**
     * 新增角色
     * 
     * @param tbResRole 角色信息
     * @return 结果
     */
	@Override
	public int insertTbResRole(TbResRole tbResRole)
	{
	    return tbResRoleMapper.insertTbResRole(tbResRole);
	}
	
	/**
     * 修改角色
     * 
     * @param tbResRole 角色信息
     * @return 结果
     */
	@Override
	public int updateTbResRole(TbResRole tbResRole)
	{
	    return tbResRoleMapper.updateTbResRole(tbResRole);
	}

	/**
     * 删除角色对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbResRoleByIds(String ids)
	{
		return tbResRoleMapper.deleteTbResRoleByIds(Convert.toStrArray(ids));
	}

	/**
	 * 角色是否已存在
	 *
	 * @param name 角色名
	 * @return 结果
	 */
	public Boolean isExistWithRoleName(String name,String farmId)
	{
		Long num = tbResRoleMapper.getRoleCountWithName(name,farmId);
		if (num > 0){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据角色ID判断角色是否存在
	 *
	 * @param id 角色ID
	 * @return 结果
	 */
	public Boolean isExistWithRoleID(Long id) {
		Long num = tbResRoleMapper.getRoleCountWithID(id);
		if (num > 0){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据角色ID删除角色
	 *
	 * @param roleId 需要删除的角色ID
	 * @return 结果
	 */
	public int deleteRoleById(Long roleId) {return tbResRoleMapper.deleteTbRoleById(roleId);}
}
