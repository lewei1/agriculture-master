package org.zcy.agriculture.mapper;

import java.util.List;

import org.zcy.agriculture.entity.TbSaleTempletInfo;

/**
 * 展示模板详情 数据层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface TbSaleTempletInfoMapper {
	/**
	 * 查询展示模板详情信息
	 * 
	 * @param id 展示模板详情ID
	 * @return 展示模板详情信息
	 */
	public TbSaleTempletInfo selectTbSaleTempletInfoById(Long id);

	/**
	 * 查询展示模板详情列表
	 * 
	 * @param tbSaleTempletInfo 展示模板详情信息
	 * @return 展示模板详情集合
	 */
	public List<TbSaleTempletInfo> selectTbSaleTempletInfoList(TbSaleTempletInfo tbSaleTempletInfo);

	/**
	 * 新增展示模板详情
	 * 
	 * @param tbSaleTempletInfo 展示模板详情信息
	 * @return 结果
	 */
	public int insertTbSaleTempletInfo(TbSaleTempletInfo tbSaleTempletInfo);

	/**
	 * 修改展示模板详情
	 * 
	 * @param tbSaleTempletInfo 展示模板详情信息
	 * @return 结果
	 */
	public int updateTbSaleTempletInfo(TbSaleTempletInfo tbSaleTempletInfo);

	/**
	 * 删除展示模板详情
	 * 
	 * @param id 展示模板详情ID
	 * @return 结果
	 */
	public int deleteTbSaleTempletInfoById(Long id);
	/**
	 * 根据saleTempletId删除数据
	 * @param saleTempletId
	 * @return
	 */
	public int deleteTbSaleTempletInfoBySaleTempletId(Long saleTempletId);

	/**
	 * 批量删除展示模板详情
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbSaleTempletInfoByIds(String[] ids);

	/**
	 * 批量插入展示模板详情
	 * 
	 * @param list
	 * @return
	 */
	public int insertSaleTempletInfoBatch(List<TbSaleTempletInfo> list);

}