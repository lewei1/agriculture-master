package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbTransfer;

import java.util.List;

/**
 * 调拨记录 服务层
 *
 * @author linlq lky.
 * @date 2019-07-02
 */
public interface ITbTransferService {
    /**
     * 查询调拨记录信息
     *
     * @return 调拨记录信息
     */
    public TbTransfer selectTbTransferById(TbTransfer tbTransfer);

    /**
     * 查询调拨记录列表
     *
     * @param tbTransfer 调拨记录信息
     * @return 调拨记录集合
     */
    public List<TbTransfer> selectTbTransferList(TbTransfer tbTransfer);


    /**
     * 搜索调拨记录列表
     *
     * @param tbTransfer 调拨记录信息
     * @return 调拨记录集合
     */
    public List<TbTransfer> selectTbTransferListByName(TbTransfer tbTransfer);

    /**
     * 新增调拨记录
     *
     * @param tbTransfer 调拨记录信息
     * @return 结果
     */
    public int insertTbTransfer(TbTransfer tbTransfer);

    /**
     * 修改调拨记录
     *
     * @param tbTransfer 调拨记录信息
     * @return 结果
     */
    public int updateTbTransfer(TbTransfer tbTransfer);

    /**
     * 删除调拨记录信息
     *
     * @return 结果
     */
    public int deleteTbTransferById(TbTransfer tbTransfer);


    /**
     * 调拨记录入库操作
     *
     * @param tbTransfer
     * @param farmUUID
     * @param farmUserCode
     * @throws Exception
     */
    public void addStorage(TbTransfer tbTransfer, String farmUUID, Long farmUserCode) throws Exception;


    /**
     * 批量 新增调拨记录
     *
     * @param tbTransfers 调拨记录信息
     * @return 结果
     */
    public void batchInsertTbTransfer(List<TbTransfer> tbTransfers) throws Exception;
}
