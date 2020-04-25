package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbSaleTempletInfo;
import org.zcy.agriculture.mapper.TbSaleTempletInfoMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbSaleTempletInfoService;

/**
 * 展示模板详情 服务层实现
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbSaleTempletInfoServiceImpl implements ITbSaleTempletInfoService 
{
	@Autowired
	private TbSaleTempletInfoMapper tbSaleTempletInfoMapper;

	/**
     * 查询展示模板详情信息
     * 
     * @param id 展示模板详情ID
     * @return 展示模板详情信息
     */
    @Override
	public TbSaleTempletInfo selectTbSaleTempletInfoById(Long id)
	{
	    return tbSaleTempletInfoMapper.selectTbSaleTempletInfoById(id);
	}
	
	/**
     * 查询展示模板详情列表
     * 
     * @param tbSaleTempletInfo 展示模板详情信息
     * @return 展示模板详情集合
     */
	@Override
	public List<TbSaleTempletInfo> selectTbSaleTempletInfoList(TbSaleTempletInfo tbSaleTempletInfo)
	{
	    return tbSaleTempletInfoMapper.selectTbSaleTempletInfoList(tbSaleTempletInfo);
	}
	
    /**
     * 新增展示模板详情
     * 
     * @param tbSaleTempletInfo 展示模板详情信息
     * @return 结果
     */
	@Override
	public int insertTbSaleTempletInfo(TbSaleTempletInfo tbSaleTempletInfo)
	{
	    return tbSaleTempletInfoMapper.insertTbSaleTempletInfo(tbSaleTempletInfo);
	}
	
	/**
     * 修改展示模板详情
     * 
     * @param tbSaleTempletInfo 展示模板详情信息
     * @return 结果
     */
	@Override
	public int updateTbSaleTempletInfo(TbSaleTempletInfo tbSaleTempletInfo)
	{
	    return tbSaleTempletInfoMapper.updateTbSaleTempletInfo(tbSaleTempletInfo);
	}

	/**
     * 删除展示模板详情对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbSaleTempletInfoByIds(String ids)
	{
		return tbSaleTempletInfoMapper.deleteTbSaleTempletInfoByIds(Convert.toStrArray(ids));
	}
	
}
