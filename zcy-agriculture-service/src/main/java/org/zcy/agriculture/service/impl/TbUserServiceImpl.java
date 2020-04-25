package org.zcy.agriculture.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbFarmUser;
import org.zcy.agriculture.entity.TbUser;
import org.zcy.agriculture.mapper.TbFarmUserMapper;
import org.zcy.agriculture.mapper.TbUserMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.TbUserSearchParam;
import org.zcy.agriculture.param.registerlogin.PasswordRetrieveParam;
import org.zcy.agriculture.service.ITbUserService;
import org.zcy.agriculture.util.UUIDUtils;
import org.zcy.agriculture.vo.TbUserRoleVo;
import org.zcy.agriculture.vo.TbUserVo;

import java.util.List;

/**
 * 用户（成员） 服务层实现
 *
 * @author zh
 * @date 2019-06-25
 */
@Service
public class TbUserServiceImpl implements ITbUserService
{
	@Autowired
	private TbUserMapper tbUserMapper;

	@Autowired
	private TbFarmUserMapper tbFarmUserMapper;

	/**
     * 查询用户（成员）信息
     *
     * @param userId 用户（成员）ID
     * @return 用户（成员）信息
     */
    @Override
	public TbUser selectTbUserById(Long userId)
	{
	    return tbUserMapper.selectTbUserById(userId);
	}

	/**
     * 查询用户（成员）列表
     *
     * @param tbUser 用户（成员）信息
     * @return 用户（成员）集合
     */
	@Override
	public List<TbUser> selectTbUserList(TbUser tbUser)
	{
	    return tbUserMapper.selectTbUserList(tbUser);
	}

	@Override
	public List<TbUser> searchTbUserList(TbUserSearchParam searchParam)
	{
		return tbUserMapper.searchTbUserList(searchParam);
	}

    /**
     * 新增用户（成员）
     *
     * @param tbUser 用户（成员）信息
     * @return 结果
     */
	@Override
	public int insertTbUser(TbUser tbUser)
	{
		tbUser.setUserCode(UUIDUtils.getCode());
	    return tbUserMapper.insertTbUser(tbUser);
	}

	/**
     * 修改用户（成员）
     *
     * @param tbUser 用户（成员）信息
     * @return 结果
     */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateTbUser(TbUser tbUser)
	{
	    return tbUserMapper.updateTbUser(tbUser);
	}

	/**
     * 删除用户（成员）对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbUserByIds(String ids)
	{
		return tbUserMapper.deleteTbUserByIds(Convert.toStrArray(ids));
	}

	@Override
	public int deleteTbUserById(Long id)
	{
		return tbUserMapper.deleteTbUserById(id);
	}


	public Boolean isExistWithPhone(String phone){
		Long num = tbUserMapper.getUserCountWithPhone(phone);
		if (num > 0) {
			return true;
		}else {
			return false;
		}
	}

	public Boolean isExistWithEmail(String email){
		Long num = tbUserMapper.getUserCountWithEmail(email);
		if (num > 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 根据角色ID返回用户数量
	 *
	 * @param roleId 角色ID
	 * @return 结果
	 */
	public Long getUserCountWithRoleId(Long roleId) {
		return tbUserMapper.getUserCountWithRoleId(roleId);
	}
	/**
	 * 查询农场所有用户 包过管理员
	 *
	 * @param tbUserVo
	 * @return
	 */
	public List<TbUserVo> selectTbUserOrAdminList(TbUserVo tbUserVo){
		return tbUserMapper.selectTbUserOrAdminList(tbUserVo);
	}



	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertUserRelationship(TbUser tbUser, String farmId,Long roleId) throws Exception {
		int result = -1;

		try {
			tbUser.setUserCode(Long.parseLong(UUIDUtils.getId(6)));
			result = tbUserMapper.insertTbUser(tbUser);
			if(result < 0)
				return result;
			//插入tb_farm_user表
			TbFarmUser farmUser = new TbFarmUser();
			farmUser.setFarmId(farmId);
			farmUser.setUserId(tbUser.getUserId());
			farmUser.setRoleId(roleId);
			result = tbFarmUserMapper.insertTbFarmUser(farmUser);
		} catch (Exception e) {
			throw new Exception();
		}
		return result;
	}

	@Override
	public TbUser selectUserByPhoneAndId(String phone, String farmId) {
		return tbUserMapper.selectUserByPhoneAndId(phone, farmId);
	}

	@Override
	public int updateUserPasswordByParam(PasswordRetrieveParam param) {
		return tbUserMapper.updateUserPasswordByParam(param);
	}
	/**
	 * 根据userCode 查询用户信息 报过创建的管理员
	 * @param userCode
	 * @return
	 */
	public TbUser selectTbUserByUserCode(String userCode, String farmId) {
		return tbUserMapper.selectTbUserByUserCode(userCode,farmId);
	}

	@Override
	public TbUser selectVbUserByCode(Long code, String farmId) {
		return tbUserMapper.selectVbUserByCode(code, farmId);
	}

	/**
	 * 根据userId查找用户角色信息
	 */
	@Override
	public TbUserRoleVo getUserRoleByUserId(Long userId,String farmId) {

		return tbUserMapper.getUserRoleByUserId(userId,farmId);
	}

	/**
	 * 根据userId和农场id查找该用户是否在当前农场下
	 */
	@Override
	public List<TbFarmUser> selectTbFarmUserList(TbFarmUser tbFarmUser) {
		return tbFarmUserMapper.selectTbFarmUserList(tbFarmUser);
	}

	/**
	 * 插入新增成员到当前农场下
	 */
	@Override
	public int insertTbFarmUser(TbFarmUser farmUser) {
		return tbFarmUserMapper.insertTbFarmUser(farmUser);
	}

	/**
	 * 根据电话号码查找用户信息
	 */
	@Override
	public TbUser getTbUserByPhone(String phone) {

		return tbUserMapper.getTbUserByPhone(phone);
	}
}
