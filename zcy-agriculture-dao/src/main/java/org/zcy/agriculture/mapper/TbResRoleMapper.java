package org.zcy.agriculture.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zcy.agriculture.entity.TbResRole;

import java.util.List;

/**
 * 角色 数据层
 * 
 * @author zenghao
 * @date 2019-06-20
 */
@Mapper
@Repository
public interface TbResRoleMapper 
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
     * 删除角色
     * 
     * @param id 角色ID
     * @return 结果
     */
	public int deleteTbResRoleById(Long id);
	
	/**
     * 批量删除角色
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbResRoleByIds(String[] ids);

	/**
	 * 获取角色表里包含角色名的数据条数（status=1除外）
	 *
	 * @param name 角色名
	 * @return 结果
	 */
	public Long getRoleCountWithName(@Param(value = "name") String name, @Param(value = "farmId")String farmId);

	/**
	 * 获取角色表里包含角色名的数据条数（status=1除外）
	 *
	 * @param roleId 角色ID
	 * @return 结果
	 */
	public Long getRoleCountWithID(Long roleId);

	/**
	 * 删除角色
	 *
	 * @param roleId 用户（成员）ID
	 * @return 结果
	 */
	public int deleteTbRoleById(Long roleId);
}