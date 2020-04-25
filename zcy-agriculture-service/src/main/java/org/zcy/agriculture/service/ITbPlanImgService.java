package org.zcy.agriculture.service;

import java.util.List;

import org.zcy.agriculture.entity.TbPlanImg;

/**
 * 计划对应图片 服务层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbPlanImgService 
{
	/**
     * 查询计划对应图片信息
     * 
     * @param planImgId 计划对应图片ID
     * @return 计划对应图片信息
     */
	public TbPlanImg selectTbPlanImgById(Long planImgId);
	
	/**
     * 查询计划对应图片列表
     * 
     * @param tbPlanImg 计划对应图片信息
     * @return 计划对应图片集合
     */
	public List<TbPlanImg> selectTbPlanImgList(TbPlanImg tbPlanImg);
	
	/**
     * 新增计划对应图片
     * 
     * @param tbPlanImg 计划对应图片信息
     * @return 结果
     */
	public int insertTbPlanImg(TbPlanImg tbPlanImg);
	
	/**
     * 修改计划对应图片
     * 
     * @param tbPlanImg 计划对应图片信息
     * @return 结果
     */
	public int updateTbPlanImg(TbPlanImg tbPlanImg);
		
	/**
     * 删除计划对应图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlanImgByIds(String ids);
	
}
