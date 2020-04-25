package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbFarmUser;
import org.zcy.agriculture.entity.TbUser;
import org.zcy.agriculture.param.TbUserSearchParam;
import org.zcy.agriculture.param.registerlogin.PasswordRetrieveParam;
import org.zcy.agriculture.vo.TbUserRoleVo;
import org.zcy.agriculture.vo.TbUserVo;

import java.util.List;

/**
 * 用户（成员） 服务层
 * 
 * @author zh
 * @date 2019-06-25
 */
public interface ITbUserService 
{
	/**
     * 查询用户（成员）信息
     * 
     * @param userId 用户（成员）ID
     * @return 用户（成员）信息
     */
	public TbUser selectTbUserById(Long userId);
	
	/**
     * 查询用户（成员）列表
     * 
     * @param tbUser 用户（成员）信息
     * @return 用户（成员）集合
     */
	public List<TbUser> selectTbUserList(TbUser tbUser);

	public List<TbUser> searchTbUserList(TbUserSearchParam searchParam);

	/**
     * 新增用户（成员）
     * 
     * @param tbUser 用户（成员）信息
     * @return 结果
     */
	public int insertTbUser(TbUser tbUser);
	
	/**
     * 修改用户（成员）
     * 
     * @param tbUser 用户（成员）信息
     * @return 结果
     */
	public int updateTbUser(TbUser tbUser);
		
	/**
     * 删除用户（成员）信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbUserByIds(String ids);

	/**
	 * 删除用户（成员）信息
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbUserById(Long id);

	public Boolean isExistWithPhone(String phone);

	public Boolean isExistWithEmail(String email);

	/**
	 * 根据角色ID返回用户数量
	 *
	 * @param roleId 角色ID
	 * @return 结果
	 */
	public Long getUserCountWithRoleId(Long roleId);
	
	/**
	 * 查询农场所有用户 包过管理员
	 * 
	 * @param tbUserVo
	 * @return
	 */
	public List<TbUserVo> selectTbUserOrAdminList(TbUserVo tbUserVo);

	/**
	 * 插入tb_farm_user, tb_user
	 * @param tbUser
	 * @return
	 */
    int insertUserRelationship(TbUser tbUser, String farmId,Long roleId) throws Exception;

	/**
	 * 根据手机号和农行id查询用户信息
	 * @param phone
	 * @param farmId
	 * @return
	 */
    TbUser selectUserByPhoneAndId(String phone, String farmId);

	/**
	 * 重置密码
	 * @param param
	 * @return
	 */
	int updateUserPasswordByParam(PasswordRetrieveParam param);
	
	/**
	 * 根据userCode 查询用户信息 报过创建的管理员
	 * @param userCode
	 * @return
	 */
	public TbUser selectTbUserByUserCode(String userCode,String farmId);

	/**
	 * 根据用户编号和农场id，查找tb_merchant或者tb_user视图信息
	 * @param code
	 * @return
	 */
	TbUser selectVbUserByCode(Long code, String farmId);

	/**
	 * 根据userId,farmId查找用户角色信息
	 */
	TbUserRoleVo getUserRoleByUserId(Long userId,String farmId);

	/**
	 * 根据userId和农场id查找该用户是否在当前农场下
	 */
	public List<TbFarmUser> selectTbFarmUserList(TbFarmUser tbFarmUser);

	/**
	 * 插入新增成员到当前农场下
	 */
	public int insertTbFarmUser(TbFarmUser farmUser);


	/**
	 * 根据电话号码查找用户信息
	 */
	TbUser getTbUserByPhone(String phone);
}
