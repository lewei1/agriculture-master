package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbResPower;
import java.util.List;

/**
 * 权限 服务层
 * 
 * @author zh
 * @date 2019-06-26
 */
public interface ITbResPowerService 
{
	/**
     * 查询权限信息
     * 
     * @param powerId 权限ID
     * @return 权限信息
     */
	public TbResPower selectTbResPowerById(Long powerId);
	
	/**
     * 查询权限列表
     * 
     * @param tbResPower 权限信息
     * @return 权限集合
     */
	public List<TbResPower> selectTbResPowerList(TbResPower tbResPower);
	
	/**
     * 新增权限
     * 
     * @param tbResPower 权限信息
     * @return 结果
     */
	public int insertTbResPower(TbResPower tbResPower);
	
	/**
     * 修改权限
     * 
     * @param tbResPower 权限信息
     * @return 结果
     */
	public int updateTbResPower(TbResPower tbResPower);
		
	/**
     * 删除权限信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbResPowerByIds(String ids);

	/**
	 * 根据权限ID判断权限是否存在
	 *
	 * @param id 权限ID
	 * @return 结果
	 */
	public Boolean isExistWithPowerID(Long id);

	/**
	 * 根据一个权限id,查找其所有子权限
	 * @param powerId 权限ID
	 * @return 权限的子权限集合
	 */
	public List<TbResPower> getPowerChilds(Long powerId);

	/**
	 * 根据权限level查找权限
	 * @param level 权限层级
	 * @return 权限的子权限集合
	 */
	public List<TbResPower> getPowerChildsBylevel(Integer level);

	/**
	 * 根据角色id查找权限
	 * @param roleId 权限层级
	 * @return 权限集合
	 */
	public List<TbResPower> getPowerListByRoleId(Long roleId);

	/**
	 * 查找公共权限(在第一,二层级菜单下的公共接口)
	 * @return 权限集合
	 */
	public List<String> getCommonPowerList();
}
