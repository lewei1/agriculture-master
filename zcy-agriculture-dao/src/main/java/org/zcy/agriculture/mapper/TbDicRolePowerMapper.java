package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbDicRolePower;

import java.util.List;

/**
 * 角色权限关联 数据层
 * 
 * @author zh
 * @date 2019-07-02
 */
public interface TbDicRolePowerMapper 
{
	/**
     * 查询角色权限关联信息
     * 
     * @param roleId 角色权限关联ID
     * @return 角色权限关联信息
     */
	public TbDicRolePower selectTbDicRolePowerById(Long roleId);
	
	/**
     * 查询角色权限关联列表
     * 
     * @param tbDicRolePower 角色权限关联信息
     * @return 角色权限关联集合
     */
	public List<TbDicRolePower> selectTbDicRolePowerList(TbDicRolePower tbDicRolePower);
	
	/**
     * 新增角色权限关联
     * 
     * @param tbDicRolePower 角色权限关联信息
     * @return 结果
     */
	public int insertTbDicRolePower(TbDicRolePower tbDicRolePower);
	/**
     * 新增角色权限关联集合
     *
     * @param tbDicRolePowerList 角色权限关联信息
     * @return 结果
     */
	public int insertTbDicRolePowerList(List<TbDicRolePower> tbDicRolePowerList);

	/**
     * 修改角色权限关联
     * 
     * @param tbDicRolePower 角色权限关联信息
     * @return 结果
     */
	public int updateTbDicRolePower(TbDicRolePower tbDicRolePower);
	
	/**
     * 删除角色权限关联
     * 
     * @param roleId 角色权限关联ID
     * @return 结果
     */
	public int deleteTbDicRolePowerById(Long roleId);
	
	/**
     * 批量删除角色权限关联
     * 
     * @param roleIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbDicRolePowerByIds(String[] roleIds);
	
}