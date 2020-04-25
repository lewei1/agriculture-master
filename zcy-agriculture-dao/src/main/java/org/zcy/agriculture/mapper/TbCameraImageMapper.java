package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbCameraImage;
import java.util.List;

/**
 * 摄像头拍照记录 数据层
 * 
 * @author zh
 * @date 2019-06-21
 */
public interface TbCameraImageMapper 
{
	/**
     * 查询摄像头拍照记录信息
     * 
     * @param picId 摄像头拍照记录ID
     * @return 摄像头拍照记录信息
     */
	public TbCameraImage selectTbCameraImageById(Long picId);
	
	/**
     * 查询摄像头拍照记录列表
     * 
     * @param tbCameraImage 摄像头拍照记录信息
     * @return 摄像头拍照记录集合
     */
	public List<TbCameraImage> selectTbCameraImageList(TbCameraImage tbCameraImage);
	
	/**
     * 新增摄像头拍照记录
     * 
     * @param tbCameraImage 摄像头拍照记录信息
     * @return 结果
     */
	public int insertTbCameraImage(TbCameraImage tbCameraImage);
	
	/**
     * 修改摄像头拍照记录
     * 
     * @param tbCameraImage 摄像头拍照记录信息
     * @return 结果
     */
	public int updateTbCameraImage(TbCameraImage tbCameraImage);
	
	/**
     * 删除摄像头拍照记录
     * 
     * @param picId 摄像头拍照记录ID
     * @return 结果
     */
	public int deleteTbCameraImageById(Long picId);
	
	/**
     * 批量删除摄像头拍照记录
     * 
     * @param picIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbCameraImageByIds(String[] picIds);
	
	/**
	 *  销售模板 H5页面 展示图片
	 * @param farmId
	 * @return
	 */
	public List<String> selectByH5Tp(String farmId);
	/**
	 * 种植管理查询 图像记录
	 * @param plotId
	 * @param createTimeStr
	 * @param createTimeEnd
	 * @return
	 */
	public List<TbCameraImage> selectByPlantingList(Long plotId,String createTimeStr,String createTimeEnd);
}