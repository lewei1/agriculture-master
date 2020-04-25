package org.zcy.agriculture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbPlanImg;	

/**
 * 计划对应图片 数据层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface TbPlanImgMapper 
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
	 * 根据计划ID复制数据
	 * @param tbPlanPlot
	 * @return
	 */
	public int copyPlanImg(@Param("planId")Long planId
			,@Param("newPlanId") Long newPlanId);
	
	/**
	 * 批量添加数据
	 * @param list
	 * @return
	 */
	public int insertBatchTbPlanImg(List<TbPlanImg> list);
	
	/**
     * 修改计划对应图片
     * 
     * @param tbPlanImg 计划对应图片信息
     * @return 结果
     */
	public int updateTbPlanImg(TbPlanImg tbPlanImg);
	
	/**
     * 删除计划对应图片
     * 
     * @param planImgId 计划对应图片ID
     * @return 结果
     */
	public int deleteTbPlanImgById(Long planImgId);
	public int deleteTbPlanImgByPlanId(Long planId);
	
	/**
     * 批量删除计划对应图片
     * 
     * @param planImgIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlanImgByIds(String[] planImgIds);
	
}