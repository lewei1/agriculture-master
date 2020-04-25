package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbMerchant;
import org.zcy.agriculture.param.registerlogin.PasswordRetrieveParam;

import java.util.List;

/**
 * 商户（超级管理员，注册用户） 数据层
 *
 * @author numberone
 * @date 2019-07-13
 */
public interface TbMerchantMapper {
    /**
     * 查询商户（超级管理员，注册用户）信息
     *
     * @param merchantId 商户（超级管理员，注册用户）ID
     * @return 商户（超级管理员，注册用户）信息
     */
    TbMerchant selectTbMerchantById(Long merchantId);

    /**
     * 查询商户（超级管理员，注册用户）列表
     *
     * @param tbMerchant 商户（超级管理员，注册用户）信息
     * @return 商户（超级管理员，注册用户）集合
     */
    List<TbMerchant> selectTbMerchantList(TbMerchant tbMerchant);

    /**
     * 新增商户（超级管理员，注册用户）
     *
     * @param tbMerchant 商户（超级管理员，注册用户）信息
     * @return 结果
     */
    int insertTbMerchant(TbMerchant tbMerchant);

    /**
     * 修改商户（超级管理员，注册用户）
     *
     * @param tbMerchant 商户（超级管理员，注册用户）信息
     * @return 结果
     */
    int updateTbMerchant(TbMerchant tbMerchant);

    /**
     * 删除商户（超级管理员，注册用户）
     *
     * @param merchantId 商户（超级管理员，注册用户）ID
     * @return 结果
     */
    int deleteTbMerchantById(Long merchantId);

    /**
     * 批量删除商户（超级管理员，注册用户）
     *
     * @param merchantIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbMerchantByIds(String[] merchantIds);

    /**
     * 重置商户密码
     * @param param
     * @return
     */
    int updateMerchantPasswordByParam(PasswordRetrieveParam param);
}