package org.zcy.agriculture.mapper;


import org.zcy.agriculture.entity.TbUseRecord;

import java.util.List;

/**
 * 农机使用记录 数据层
 * 
 * @author linlq
 * @date 2019-06-21
 */
public interface TbUseRecordMapper 
{
	/**
     * 查询农机使用记录信息
     * 
     * @param machineRecordId 农机使用记录ID
     * @return 农机使用记录信息
     */
	public TbUseRecord selectTbUseRecordById(Long machineRecordId);
	
	/**
     * 查询农机使用记录列表
     * 
     * @param tbUseRecord 农机使用记录信息
     * @return 农机使用记录集合
     */
	public List<TbUseRecord> selectTbUseRecordList(TbUseRecord tbUseRecord);
	
	/**
     * 新增农机使用记录
     * 
     * @param tbUseRecord 农机使用记录信息
     * @return 结果
     */
	public int insertTbUseRecord(TbUseRecord tbUseRecord);
	
	/**
     * 修改农机使用记录
     * 
     * @param tbUseRecord 农机使用记录信息
     * @return 结果
     */
	public int updateTbUseRecord(TbUseRecord tbUseRecord);
	
	/**
     * 删除农机使用记录
     * 
     * @param machineRecordId 农机使用记录ID
     * @return 结果
     */
	public int deleteTbUseRecordById(Long machineRecordId);
	
	/**
     * 批量删除农机使用记录
     * 
     * @param machineRecordIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbUseRecordByIds(String[] machineRecordIds);
	
}