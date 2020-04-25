package org.zcy.agriculture.service.plot;

import org.zcy.agriculture.entity.TbReportImg;

import java.util.List;

/**
 * 水质报告图片 服务层
 * 
 * @author numberone
 * @date 2019-06-25
 */
public interface ITbReportImgService 
{
	/**
     * 查询水质报告图片信息
     * 
     * @param reportImgId 水质报告图片ID
     * @return 水质报告图片信息
     */
	public TbReportImg selectTbReportImgById(Long reportImgId);
	
	/**
     * 查询水质报告图片列表
     * 
     * @param tbReportImg 水质报告图片信息
     * @return 水质报告图片集合
     */
	public List<TbReportImg> selectTbReportImgList(TbReportImg tbReportImg);
	
	/**
     * 新增水质报告图片
     * 
     * @param tbReportImg 水质报告图片信息
     * @return 结果
     */
	public int insertTbReportImg(TbReportImg tbReportImg);
	
	/**
     * 修改水质报告图片
     * 
     * @param tbReportImg 水质报告图片信息
     * @return 结果
     */
	public int updateTbReportImg(TbReportImg tbReportImg);
		
	/**
     * 删除水质报告图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbReportImgByIds(String ids);
	
}
