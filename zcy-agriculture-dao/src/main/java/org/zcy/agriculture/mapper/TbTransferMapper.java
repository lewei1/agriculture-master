package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbTransfer;

import java.util.List;

/**
 * 调拨记录 数据层
 * 
 * @author linlq
 * @date 2019-07-02
 */
public interface TbTransferMapper 
{
	/**
     * 查询调拨记录信息
     * 
     * @return 调拨记录信息
     */
	public TbTransfer selectTbTransferById(TbTransfer tbTransfer);
	
	/**
     * 查询调拨记录列表
     * 
     * @param tbTransfer 调拨记录信息
     * @return 调拨记录集合
     */
	public List<TbTransfer> selectTbTransferList(TbTransfer tbTransfer);


	/**
	 * 搜索调拨记录列表
	 *
	 * @param tbTransfer 调拨记录信息
	 * @return 调拨记录集合
	 */
	public List<TbTransfer> selectTbTransferListByName(TbTransfer tbTransfer);

	/**
     * 新增调拨记录
     * 
     * @param tbTransfer 调拨记录信息
     * @return 结果
     */
	public int insertTbTransfer(TbTransfer tbTransfer);
	
	/**
     * 修改调拨记录
     * 
     * @param tbTransfer 调拨记录信息
     * @return 结果
     */
	public int updateTbTransfer(TbTransfer tbTransfer);
	
	/**
     * 删除调拨记录
     * 
     * @return 结果
     */
	public int deleteTbTransferById(TbTransfer tbTransfer);
	
	/**
     * 批量删除调拨记录
     * 
     * @param transferIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbTransferByIds(String[] transferIds);
	
}