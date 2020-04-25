package org.zcy.agriculture.mapper;

import java.util.List;

import org.zcy.agriculture.entity.TbHarvestImg;	

/**
 * 采收对应图片 数据层
 * 
 * @author numberone
 * @date 2019-06-27
 */
public interface TbHarvestImgMapper 
{
	/**
     * 查询采收对应图片信息
     * 
     * @param harvestImgId 采收对应图片ID
     * @return 采收对应图片信息
     */
	public TbHarvestImg selectTbHarvestImgById(Long harvestImgId);
	
	/**
     * 查询采收对应图片列表
     * 
     * @param tbHarvestImg 采收对应图片信息
     * @return 采收对应图片集合
     */
	public List<TbHarvestImg> selectTbHarvestImgList(TbHarvestImg tbHarvestImg);
	
	/**
     * 新增采收对应图片
     * 
     * @param tbHarvestImg 采收对应图片信息
     * @return 结果
     */
	public int insertTbHarvestImg(TbHarvestImg tbHarvestImg);
	
	/**
     * 修改采收对应图片
     * 
     * @param tbHarvestImg 采收对应图片信息
     * @return 结果
     */
	public int updateTbHarvestImg(TbHarvestImg tbHarvestImg);
	
	/**
     * 删除采收对应图片
     * 
     * @param harvestImgId 采收对应图片ID
     * @return 结果
     */
	public int deleteTbHarvestImgById(Long harvestImgId);

	
	/**
     * 批量删除采收对应图片
     * 
     * @param harvestImgIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbHarvestImgByIds(String[] harvestImgIds);


	/**
	 * 删除作物图片 - 根据采收ID
	 *
	 * @param harvestId 作物采收ID
	 * @return 结果
	 */
	public int deleteTbHarvestImgByHarvestId(Long harvestId);
	
	/**
	 * 批量插入采收图片
	 * @param list
	 * @return
	 */
	public int insertHarvestImgBatch(List<TbHarvestImg> list);
}