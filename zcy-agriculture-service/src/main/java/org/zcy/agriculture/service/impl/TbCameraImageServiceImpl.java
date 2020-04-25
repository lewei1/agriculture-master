package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbCameraImage;
import org.zcy.agriculture.mapper.TbCameraImageMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbCameraImageService;

/**
 * 摄像头拍照记录 服务层实现
 * 
 * @author zh
 * @date 2019-06-21
 */
@Service
public class TbCameraImageServiceImpl implements ITbCameraImageService
{
	@Autowired
	private TbCameraImageMapper tbCameraImageMapper;

	/**
     * 查询摄像头拍照记录信息
     * 
     * @param picId 摄像头拍照记录ID
     * @return 摄像头拍照记录信息
     */
    @Override
	public TbCameraImage selectTbCameraImageById(Long picId)
	{
	    return tbCameraImageMapper.selectTbCameraImageById(picId);
	}
	
	/**
     * 查询摄像头拍照记录列表
     * 
     * @param tbCameraImage 摄像头拍照记录信息
     * @return 摄像头拍照记录集合
     */
	@Override
	public List<TbCameraImage> selectTbCameraImageList(TbCameraImage tbCameraImage)
	{
	    return tbCameraImageMapper.selectTbCameraImageList(tbCameraImage);
	}
	
    /**
     * 新增摄像头拍照记录
     * 
     * @param tbCameraImage 摄像头拍照记录信息
     * @return 结果
     */
	@Override
	public int insertTbCameraImage(TbCameraImage tbCameraImage)
	{
	    return tbCameraImageMapper.insertTbCameraImage(tbCameraImage);
	}
	
	/**
     * 修改摄像头拍照记录
     * 
     * @param tbCameraImage 摄像头拍照记录信息
     * @return 结果
     */
	@Override
	public int updateTbCameraImage(TbCameraImage tbCameraImage)
	{
	    return tbCameraImageMapper.updateTbCameraImage(tbCameraImage);
	}

	/**
     * 删除摄像头拍照记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbCameraImageByIds(String ids)
	{
		return tbCameraImageMapper.deleteTbCameraImageByIds(Convert.toStrArray(ids));
	}
	/**
	 *  销售模板 H5页面 展示图片
	 * @param farmId
	 * @return
	 */
	public List<String> selectByH5Tp(String farmId){
		return tbCameraImageMapper.selectByH5Tp(farmId);
	}
	/**
	 * 种植管理查询 图像记录
	 * @param plotId
	 * @param createTimeStr
	 * @param createTimeEnd
	 * @return
	 */
	public List<TbCameraImage> selectByPlantingList(Long plotId,String createTimeStr,String createTimeEnd){
		return tbCameraImageMapper.selectByPlantingList(plotId, createTimeStr, createTimeEnd);
	}
}
