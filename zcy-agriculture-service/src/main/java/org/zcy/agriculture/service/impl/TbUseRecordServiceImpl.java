package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbUseRecord;
import org.zcy.agriculture.mapper.TbUseRecordMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbUseRecordService;


/**
 * 农机使用记录 服务层实现
 * 
 * @author linlq
 * @date 2019-06-21
 */
@Service
public class TbUseRecordServiceImpl implements ITbUseRecordService
{
	@Autowired
	private TbUseRecordMapper tbUseRecordMapper;

	/**
     * 查询农机使用记录信息
     * 
     * @param machineRecordId 农机使用记录ID
     * @return 农机使用记录信息
     */
    @Override
	public TbUseRecord selectTbUseRecordById(Long machineRecordId)
	{
	    return tbUseRecordMapper.selectTbUseRecordById(machineRecordId);
	}
	
	/**
     * 查询农机使用记录列表
     * 
     * @param tbUseRecord 农机使用记录信息
     * @return 农机使用记录集合
     */
	@Override
	public List<TbUseRecord> selectTbUseRecordList(TbUseRecord tbUseRecord)
	{
	    return tbUseRecordMapper.selectTbUseRecordList(tbUseRecord);
	}
	
    /**
     * 新增农机使用记录
     * 
     * @param tbUseRecord 农机使用记录信息
     * @return 结果
     */
	@Override
	public int insertTbUseRecord(TbUseRecord tbUseRecord)
	{
	    return tbUseRecordMapper.insertTbUseRecord(tbUseRecord);
	}
	
	/**
     * 修改农机使用记录
     * 
     * @param tbUseRecord 农机使用记录信息
     * @return 结果
     */
	@Override
	public int updateTbUseRecord(TbUseRecord tbUseRecord)
	{
	    return tbUseRecordMapper.updateTbUseRecord(tbUseRecord);
	}

	/**
     * 删除农机使用记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbUseRecordByIds(String ids)
	{
		return tbUseRecordMapper.deleteTbUseRecordByIds(Convert.toStrArray(ids));
	}
	
}
