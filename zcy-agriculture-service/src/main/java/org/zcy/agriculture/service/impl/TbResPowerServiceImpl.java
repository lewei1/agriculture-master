package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbResPower;
import org.zcy.agriculture.mapper.TbResPowerMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbResPowerService;

/**
 * 权限 服务层实现
 * 
 * @author zh
 * @date 2019-06-26
 */
@Service
public class TbResPowerServiceImpl implements ITbResPowerService
{
	@Autowired
	private TbResPowerMapper tbResPowerMapper;

	/**
     * 查询权限信息
     * 
     * @param powerId 权限ID
     * @return 权限信息
     */
    @Override
	public TbResPower selectTbResPowerById(Long powerId)
	{
	    return tbResPowerMapper.selectTbResPowerById(powerId);
	}

	/**
     * 查询权限列表
     * 
     * @param tbResPower 权限信息
     * @return 权限集合
     */
	@Override
	public List<TbResPower> selectTbResPowerList(TbResPower tbResPower)
	{
	    return tbResPowerMapper.selectTbResPowerList(tbResPower);
	}
	
    /**
     * 新增权限
     * 
     * @param tbResPower 权限信息
     * @return 结果
     */
	@Override
	public int insertTbResPower(TbResPower tbResPower)
	{
	    return tbResPowerMapper.insertTbResPower(tbResPower);
	}
	
	/**
     * 修改权限
     * 
     * @param tbResPower 权限信息
     * @return 结果
     */
	@Override
	public int updateTbResPower(TbResPower tbResPower)
	{
	    return tbResPowerMapper.updateTbResPower(tbResPower);
	}

	/**
     * 删除权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbResPowerByIds(String ids)
	{
		return tbResPowerMapper.deleteTbResPowerByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据权限ID判断权限是否存在
	 *
	 * @param id 权限ID
	 * @return 结果
	 */
	public Boolean isExistWithPowerID(Long id) {
		Long num = tbResPowerMapper.getCountWithPowerID(id);
		if (num > 0){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据一个权限id,查找其所有子权限
	 * @param powerId 权限ID
	 * @return 权限的子权限集合
	 */
	@Override
	public List<TbResPower> getPowerChilds(Long powerId) {
        return tbResPowerMapper.getPowerChilds(powerId);
	}

    /**
     * 根据权限level查找权限
     * @param level 权限层级
     * @return 权限的子权限集合
     */
    @Override
    public List<TbResPower> getPowerChildsBylevel(Integer level) {
        return tbResPowerMapper.getPowerChildsBylevel(level);
    }

	/**
	 * 根据角色id查找权限
	 * @param roleId 权限层级
	 * @return 权限集合
	 */
	@Override
	public List<TbResPower> getPowerListByRoleId(Long roleId) {
		return tbResPowerMapper.getPowerListByRoleId(roleId);
	}

	/**
	 * 查找公共权限(在第一,二层级菜单下的公共接口)
	 * @return 权限集合
	 */
	@Override
	public List<String> getCommonPowerList() {
		return tbResPowerMapper.getCommonPowerList();
	}
}
