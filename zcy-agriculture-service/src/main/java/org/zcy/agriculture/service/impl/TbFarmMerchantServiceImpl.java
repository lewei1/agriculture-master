package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbFarmMerchant;
import org.zcy.agriculture.mapper.TbFarmMerchantMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbFarmMerchantService;

/**
 * 商户和农场关联 服务层实现
 * 
 * @author numberone
 * @date 2019-07-13
 */
@Service
public class TbFarmMerchantServiceImpl implements ITbFarmMerchantService
{
	@Autowired
	private TbFarmMerchantMapper tbFarmMerchantMapper;

	/**
     * 查询商户和农场关联信息
     * 
     * @param farmMerchantId 商户和农场关联ID
     * @return 商户和农场关联信息
     */
    @Override
	public TbFarmMerchant selectTbFarmMerchantById(Long farmMerchantId)
	{
	    return tbFarmMerchantMapper.selectTbFarmMerchantById(farmMerchantId);
	}
	
	/**
     * 查询商户和农场关联列表
     * 
     * @param tbFarmMerchant 商户和农场关联信息
     * @return 商户和农场关联集合
     */
	@Override
	public List<TbFarmMerchant> selectTbFarmMerchantList(TbFarmMerchant tbFarmMerchant)
	{
	    return tbFarmMerchantMapper.selectTbFarmMerchantList(tbFarmMerchant);
	}
	
    /**
     * 新增商户和农场关联
     * 
     * @param tbFarmMerchant 商户和农场关联信息
     * @return 结果
     */
	@Override
	public int insertTbFarmMerchant(TbFarmMerchant tbFarmMerchant)
	{
	    return tbFarmMerchantMapper.insertTbFarmMerchant(tbFarmMerchant);
	}
	
	/**
     * 修改商户和农场关联
     * 
     * @param tbFarmMerchant 商户和农场关联信息
     * @return 结果
     */
	@Override
	public int updateTbFarmMerchant(TbFarmMerchant tbFarmMerchant)
	{
	    return tbFarmMerchantMapper.updateTbFarmMerchant(tbFarmMerchant);
	}

	/**
     * 删除商户和农场关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbFarmMerchantByIds(String ids)
	{
		return tbFarmMerchantMapper.deleteTbFarmMerchantByIds(Convert.toStrArray(ids));
	}
	
}
