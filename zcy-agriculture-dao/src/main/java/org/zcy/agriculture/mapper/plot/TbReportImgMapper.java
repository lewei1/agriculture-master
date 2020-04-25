package org.zcy.agriculture.mapper.plot;

import org.zcy.agriculture.entity.TbReportImg;

import java.util.List;

/**
 * 水质报告图片 数据层
 *
 * @author numberone
 * @date 2019-06-25
 */
public interface TbReportImgMapper {
    /**
     * 查询水质报告图片信息
     *
     * @param reportImgId 水质报告图片ID
     * @return 水质报告图片信息
     */
    TbReportImg selectTbReportImgById(Long reportImgId);

    /**
     * 查询水质报告图片列表
     *
     * @param tbReportImg 水质报告图片信息
     * @return 水质报告图片集合
     */
    List<TbReportImg> selectTbReportImgList(TbReportImg tbReportImg);

    /**
     * 新增水质报告图片
     *
     * @param tbReportImg 水质报告图片信息
     * @return 结果
     */
    int insertTbReportImg(TbReportImg tbReportImg);

    /**
     * 修改水质报告图片
     *
     * @param tbReportImg 水质报告图片信息
     * @return 结果
     */
    int updateTbReportImg(TbReportImg tbReportImg);

    /**
     * 删除水质报告图片
     *
     * @param reportImgId 水质报告图片ID
     * @return 结果
     */
    int deleteTbReportImgById(Long reportImgId);

    /**
     * 批量删除水质报告图片
     *
     * @param reportImgIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTbReportImgByIds(String[] reportImgIds);

    /**
     * 新增地块报告图片列表
     * @param reportImgList
     * @return
     */
    int insertTbReportImgList(List<TbReportImg> reportImgList);

    /**
     * 根据报告id，删除报告图片
     * @param reportId
     * @return
     */
    int deleteTbReportImgByReportId(Long reportId);

    /**
     * 根据报告id，查询报告列表
     * @param reportId
     * @return
     */
    String selectReportFirstImgById(Long reportId);
}