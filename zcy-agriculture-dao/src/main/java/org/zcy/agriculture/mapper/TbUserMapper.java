package org.zcy.agriculture.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.zcy.agriculture.entity.TbUser;
import org.zcy.agriculture.param.TbUserSearchParam;
import org.zcy.agriculture.param.registerlogin.PasswordRetrieveParam;
import org.zcy.agriculture.vo.TbUserRoleVo;
import org.zcy.agriculture.vo.TbUserVo;

import java.util.List;

/**
 * 用户（成员） 数据层
 * 
 * @author zh
 * @date 2019-06-25
 */
public interface TbUserMapper 
{
	/**
     * 查询用户（成员）信息
     * 
     * @param userId 用户（成员）ID
     * @return 用户（成员）信息
     */
	public TbUser selectTbUserById(Long userId);
	/**
	 * 根据userCode 查询用户信息 报过创建的管理员
	 * @param userCode
	 * @return
	 */
	public TbUser selectTbUserByUserCode(String userCode,String farmId);
	
	/**
     * 查询用户（成员）列表
     * 
     * @param tbUser 用户（成员）信息
     * @return 用户（成员）集合
     */
	public List<TbUser> selectTbUserList(TbUser tbUser);
	
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
     * 删除用户（成员）
     * 
     * @param userId 用户（成员）ID
     * @return 结果
     */
	public int deleteTbUserById(Long userId);
	
	/**
     * 批量删除用户（成员）
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbUserByIds(String[] userIds);

	public Long getUserCountWithPhone(String phone);

	public Long getUserCountWithEmail(String email);

	public Long getUserCountWithRoleId(Long roleId);

	/**
	 * 查询农场所有用户 包过管理员
	 * 
	 * @param tbUserVo
	 * @return
	 */
	public List<TbUserVo> selectTbUserOrAdminList(TbUserVo tbUserVo);

	public List<TbUser> searchTbUserList(TbUserSearchParam searchParam);

    TbUser selectUserByPhoneAndId(@Param("phone") String phone, @Param("farmId") String farmId);

    int updateUserPasswordByParam(PasswordRetrieveParam param);

	/**
	 * 根据用户编号和农场id，查找tb_merchant或者tb_user表信息
	 * @param code
	 * @return
	 */
	TbUser selectVbUserByCode(@Param("code") Long code, @Param("farmId") String farmId);

	/**
	 * 根据userId,farmId查找用户角色信息
	 */
	TbUserRoleVo getUserRoleByUserId(@Param("userId")Long userId,@Param("farmId")String farmId);

	TbUser getTbUserByPhone(String phone);
}