package org.zcy.agriculture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbPlanImg;
import org.zcy.agriculture.mapper.TbPlanImgMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbPlanImgService;

/**
 * 计划对应图片 服务层实现
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbPlanImgServiceImpl implements ITbPlanImgService 
{
	@Autowired
	private TbPlanImgMapper tbPlanImgMapper;

	/**
     * 查询计划对应图片信息
     * 
     * @param planImgId 计划对应图片ID
     * @return 计划对应图片信息
     */
    @Override
	public TbPlanImg selectTbPlanImgById(Long planImgId)
	{
	    return tbPlanImgMapper.selectTbPlanImgById(planImgId);
	}
	
	/**
     * 查询计划对应图片列表
     * 
     * @param tbPlanImg 计划对应图片信息
     * @return 计划对应图片集合
     */
	@Override
	public List<TbPlanImg> selectTbPlanImgList(TbPlanImg tbPlanImg)
	{
	    return tbPlanImgMapper.selectTbPlanImgList(tbPlanImg);
	}
	
    /**
     * 新增计划对应图片
     * 
     * @param tbPlanImg 计划对应图片信息
     * @return 结果
     */
	@Override
	public int insertTbPlanImg(TbPlanImg tbPlanImg)
	{
	    return tbPlanImgMapper.insertTbPlanImg(tbPlanImg);
	}
	
	/**
     * 修改计划对应图片
     * 
     * @param tbPlanImg 计划对应图片信息
     * @return 结果
     */
	@Override
	public int updateTbPlanImg(TbPlanImg tbPlanImg)
	{
	    return tbPlanImgMapper.updateTbPlanImg(tbPlanImg);
	}

	/**
     * 删除计划对应图片对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbPlanImgByIds(String ids)
	{
		return tbPlanImgMapper.deleteTbPlanImgByIds(Convert.toStrArray(ids));
	}
	
}
