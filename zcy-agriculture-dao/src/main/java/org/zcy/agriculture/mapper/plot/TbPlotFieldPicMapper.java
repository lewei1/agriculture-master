package org.zcy.agriculture.mapper.plot;

import org.zcy.agriculture.entity.TbPlotFieldPic;

import java.util.List;

/**
 * 地块对应实地图片 数据层
 *
 * @author numberone
 * @date 2019-06-25
 */
public interface TbPlotFieldPicMapper {
    /**
     * 查询地块对应实地图片信息
     *
     * @param fieldPicId 地块对应实地图片ID
     * @return 地块对应实地图片信息
     */
    TbPlotFieldPic selectTbPlotFieldPicById(Long fieldPicId);

    /**
     * 查询地块对应实地图片列表
     *
     * @param tbPlotFieldPic 地块对应实地图片信息
     * @return 地块对应实地图片集合
     */
    List<TbPlotFieldPic> selectTbPlotFieldPicList(TbPlotFieldPic tbPlotFieldPic);

    /**
     * 新增地块对应实地图片
     *
     * @param tbPlotFieldPic 地块对应实地图片信息
     * @return 结果
     */
    int insertTbPlotFieldPic(TbPlotFieldPic tbPlotFieldPic);

    /**
     * 修改地块对应实地图片
     *
     * @param tbPlotFieldPic 地块对应实地图片信息
     * @return 结果
     */
    int updateTbPlotFieldPic(TbPlotFieldPic tbPlotFieldPic);

    /**
     * 删除地块对应实地图片
     *
     * @param fieldPicId 地块对应实地图片ID
     * @return 结果
     */
    int deleteTbPlotFieldPicById(Long fieldPicId);

    /**
     * 批量删除地块对应实地图片
     *
     * @param fieldPicIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbPlotFieldPicByIds(String[] fieldPicIds);

    /**
     * 新增地块图片列表
     * @param fieldPicList
     * @return
     */
    int insertTbPlotFieldPicList(List<TbPlotFieldPic> fieldPicList);

    /**
     * 根据地块id，删除地块图片
     * @param plotId
     * @return
     */
    int deleteTbPlotFieldPicByPlotId(Long plotId);
}