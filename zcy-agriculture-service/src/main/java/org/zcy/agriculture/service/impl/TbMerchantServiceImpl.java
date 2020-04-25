package org.zcy.agriculture.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbMerchant;
import org.zcy.agriculture.mapper.TbMerchantMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.registerlogin.PasswordRetrieveParam;
import org.zcy.agriculture.service.ITbMerchantService;
import org.zcy.agriculture.util.UUIDUtils;

import java.util.List;

/**
 * 商户（超级管理员，注册用户） 服务层实现
 *
 * @author numberone
 * @date 2019-07-13
 */
@Service
public class TbMerchantServiceImpl implements ITbMerchantService {
    @Autowired
    private TbMerchantMapper tbMerchantMapper;

    /**
     * 查询商户（超级管理员，注册用户）信息
     *
     * @param merchantId 商户（超级管理员，注册用户）ID
     * @return 商户（超级管理员，注册用户）信息
     */
    @Override
    public TbMerchant selectTbMerchantById(Long merchantId) {
        return tbMerchantMapper.selectTbMerchantById(merchantId);
    }

    /**
     * 查询商户（超级管理员，注册用户）列表
     *
     * @param tbMerchant 商户（超级管理员，注册用户）信息
     * @return 商户（超级管理员，注册用户）集合
     */
    @Override
    public List<TbMerchant> selectTbMerchantList(TbMerchant tbMerchant) {
        return tbMerchantMapper.selectTbMerchantList(tbMerchant);
    }

    /**
     * 新增商户（超级管理员，注册用户）
     *
     * @param tbMerchant 商户（超级管理员，注册用户）信息
     * @return 结果
     */
    @Override
    public int insertTbMerchant(TbMerchant tbMerchant) {
    	tbMerchant.setMerchantCode(Long.parseLong(UUIDUtils.getId(6)));
        return tbMerchantMapper.insertTbMerchant(tbMerchant);
    }

    /**
     * 修改商户（超级管理员，注册用户）
     *
     * @param tbMerchant 商户（超级管理员，注册用户）信息
     * @return 结果
     */
    @Override
    public int updateTbMerchant(TbMerchant tbMerchant) {
        return tbMerchantMapper.updateTbMerchant(tbMerchant);
    }

    /**
     * 删除商户（超级管理员，注册用户）对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbMerchantByIds(String ids) {
        return tbMerchantMapper.deleteTbMerchantByIds(Convert.toStrArray(ids));
    }

    @Override
    public int updateMerchantPasswordByParam(PasswordRetrieveParam param) {
        return tbMerchantMapper.updateMerchantPasswordByParam(param);
    }

}
