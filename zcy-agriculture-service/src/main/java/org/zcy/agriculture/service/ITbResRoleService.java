package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbResRole;

import java.util.List;

/**
 * 角色 服务层
 * 
 * @author zenghao
 * @date 2019-06-20
 */
public interface ITbResRoleService 
{
	/**
     * 查询角色信息
     * 
     * @param id 角色ID
     * @return 角色信息
     */
	public TbResRole selectTbResRoleById(Long id);
	
	/**
     * 查询角色列表
     * 
     * @param tbResRole 角色信息
     * @return 角色集合
     */
	public List<TbResRole> selectTbResRoleList(TbResRole tbResRole);
	
	/**
     * 新增角色
     * 
     * @param tbResRole 角色信息
     * @return 结果
     */
	public int insertTbResRole(TbResRole tbResRole);
	
	/**
     * 修改角色
     * 
     * @param tbResRole 角色信息
     * @return 结果
     */
	public int updateTbResRole(TbResRole tbResRole);
		
	/**
     * 删除角色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbResRoleByIds(String ids);

	/**
	 * 角色是否已存在
	 *
	 * @param name 角色名
	 * @return 结果
	 */
	public Boolean isExistWithRoleName(String name,String farmId);

	/**
	 * 角色是否已存在
	 *
	 * @param id 角ID
	 * @return 结果
	 */
	public Boolean isExistWithRoleID(Long id);

	/**
	 * 根据角色ID删除角色
	 *
	 * @param roleId 需要删除的角色ID
	 * @return 结果
	 */
	public int deleteRoleById(Long roleId);

}
