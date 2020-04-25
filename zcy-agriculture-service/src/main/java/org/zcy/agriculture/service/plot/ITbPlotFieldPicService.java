package org.zcy.agriculture.service.plot;

import org.zcy.agriculture.entity.TbPlotFieldPic;
import org.zcy.agriculture.param.plot.FieldPicParam;

import java.util.List;

/**
 * 地块对应实地图片 服务层
 * 
 * @author numberone
 * @date 2019-06-25
 */
public interface ITbPlotFieldPicService 
{
	/**
     * 查询地块对应实地图片信息
     * 
     * @param fieldPicId 地块对应实地图片ID
     * @return 地块对应实地图片信息
     */
	public TbPlotFieldPic selectTbPlotFieldPicById(Long fieldPicId);
	
	/**
     * 查询地块对应实地图片列表
     * 
     * @param tbPlotFieldPic 地块对应实地图片信息
     * @return 地块对应实地图片集合
     */
	public List<TbPlotFieldPic> selectTbPlotFieldPicList(TbPlotFieldPic tbPlotFieldPic);
	
	/**
     * 新增地块对应实地图片
     * 
     * @param tbPlotFieldPic 地块对应实地图片信息
     * @return 结果
     */
	public int insertTbPlotFieldPic(TbPlotFieldPic tbPlotFieldPic);
	
	/**
     * 修改地块对应实地图片
     * 
     * @param tbPlotFieldPic 地块对应实地图片信息
     * @return 结果
     */
	public int updateTbPlotFieldPic(TbPlotFieldPic tbPlotFieldPic);
		
	/**
     * 删除地块对应实地图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbPlotFieldPicByIds(String ids);

    int insertTbPlotFieldPicList(FieldPicParam fieldPicParam);

    int deleteTbPlotFieldPicByPlotId(Long plotId);
}
