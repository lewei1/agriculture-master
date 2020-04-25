package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbFarmMerchant;

import java.util.List;

/**
 * 商户和农场关联 服务层
 * 
 * @author numberone
 * @date 2019-07-13
 */
public interface ITbFarmMerchantService 
{
	/**
     * 查询商户和农场关联信息
     * 
     * @param farmMerchantId 商户和农场关联ID
     * @return 商户和农场关联信息
     */
	public TbFarmMerchant selectTbFarmMerchantById(Long farmMerchantId);
	
	/**
     * 查询商户和农场关联列表
     * 
     * @param tbFarmMerchant 商户和农场关联信息
     * @return 商户和农场关联集合
     */
	public List<TbFarmMerchant> selectTbFarmMerchantList(TbFarmMerchant tbFarmMerchant);
	
	/**
     * 新增商户和农场关联
     * 
     * @param tbFarmMerchant 商户和农场关联信息
     * @return 结果
     */
	public int insertTbFarmMerchant(TbFarmMerchant tbFarmMerchant);
	
	/**
     * 修改商户和农场关联
     * 
     * @param tbFarmMerchant 商户和农场关联信息
     * @return 结果
     */
	public int updateTbFarmMerchant(TbFarmMerchant tbFarmMerchant);
		
	/**
     * 删除商户和农场关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbFarmMerchantByIds(String ids);
	
}
