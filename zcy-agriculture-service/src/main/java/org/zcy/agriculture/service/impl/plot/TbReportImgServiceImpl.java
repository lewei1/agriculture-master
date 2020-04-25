package org.zcy.agriculture.service.impl.plot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbReportImg;
import org.zcy.agriculture.mapper.plot.TbReportImgMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.plot.ITbReportImgService;

/**
 * 水质报告图片 服务层实现
 * 
 * @author numberone
 * @date 2019-06-25
 */
@Service
public class TbReportImgServiceImpl implements ITbReportImgService
{
	@Autowired
	private TbReportImgMapper tbReportImgMapper;

	/**
     * 查询水质报告图片信息
     * 
     * @param reportImgId 水质报告图片ID
     * @return 水质报告图片信息
     */
    @Override
	public TbReportImg selectTbReportImgById(Long reportImgId)
	{
	    return tbReportImgMapper.selectTbReportImgById(reportImgId);
	}
	
	/**
     * 查询水质报告图片列表
     * 
     * @param tbReportImg 水质报告图片信息
     * @return 水质报告图片集合
     */
	@Override
	public List<TbReportImg> selectTbReportImgList(TbReportImg tbReportImg)
	{
	    return tbReportImgMapper.selectTbReportImgList(tbReportImg);
	}
	
    /**
     * 新增水质报告图片
     * 
     * @param tbReportImg 水质报告图片信息
     * @return 结果
     */
	@Override
	public int insertTbReportImg(TbReportImg tbReportImg)
	{
	    return tbReportImgMapper.insertTbReportImg(tbReportImg);
	}
	
	/**
     * 修改水质报告图片
     * 
     * @param tbReportImg 水质报告图片信息
     * @return 结果
     */
	@Override
	public int updateTbReportImg(TbReportImg tbReportImg)
	{
	    return tbReportImgMapper.updateTbReportImg(tbReportImg);
	}

	/**
     * 删除水质报告图片对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbReportImgByIds(String ids)
	{
		return tbReportImgMapper.deleteTbReportImgByIds(Convert.toStrArray(ids));
	}
	
}
