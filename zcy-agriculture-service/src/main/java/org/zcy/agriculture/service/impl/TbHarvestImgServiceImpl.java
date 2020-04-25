package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbHarvestImg;
import org.zcy.agriculture.mapper.TbHarvestImgMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbHarvestImgService;

/**
 * 采收对应图片 服务层实现
 * 
 * @author numberone
 * @date 2019-06-27
 */
@Service
public class TbHarvestImgServiceImpl implements ITbHarvestImgService 
{
	@Autowired
	private TbHarvestImgMapper tbHarvestImgMapper;

	/**
     * 查询采收对应图片信息
     * 
     * @param harvestImgId 采收对应图片ID
     * @return 采收对应图片信息
     */
    @Override
	public TbHarvestImg selectTbHarvestImgById(Long harvestImgId)
	{
	    return tbHarvestImgMapper.selectTbHarvestImgById(harvestImgId);
	}
	
	/**
     * 查询采收对应图片列表
     * 
     * @param tbHarvestImg 采收对应图片信息
     * @return 采收对应图片集合
     */
	@Override
	public List<TbHarvestImg> selectTbHarvestImgList(TbHarvestImg tbHarvestImg)
	{
	    return tbHarvestImgMapper.selectTbHarvestImgList(tbHarvestImg);
	}
	
    /**
     * 新增采收对应图片
     * 
     * @param tbHarvestImg 采收对应图片信息
     * @return 结果
     */
	@Override
	public int insertTbHarvestImg(TbHarvestImg tbHarvestImg)
	{
	    return tbHarvestImgMapper.insertTbHarvestImg(tbHarvestImg);
	}
	
	/**
     * 修改采收对应图片
     * 
     * @param tbHarvestImg 采收对应图片信息
     * @return 结果
     */
	@Override
	public int updateTbHarvestImg(TbHarvestImg tbHarvestImg)
	{
	    return tbHarvestImgMapper.updateTbHarvestImg(tbHarvestImg);
	}

	/**
     * 删除采收对应图片对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbHarvestImgByIds(String ids)
	{
		return tbHarvestImgMapper.deleteTbHarvestImgByIds(Convert.toStrArray(ids));
	}
	
}
