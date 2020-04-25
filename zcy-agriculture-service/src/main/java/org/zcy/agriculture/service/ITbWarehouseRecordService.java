package org.zcy.agriculture.service;

import java.util.Date;
import java.util.List;

import org.zcy.agriculture.entity.TbInventoryMaterial;
import org.zcy.agriculture.entity.TbWarehouseDynamic;
import org.zcy.agriculture.entity.TbWarehouseRecord;
import org.zcy.agriculture.vo.TbWarehouseRecordVo;

/**
 * 出入库记录 服务层
 *
 * @author numberone lky.
 * @date 2019-06-28
 */
public interface ITbWarehouseRecordService {
    /**
     * 查询出入库记录信息
     *
     * @return 出入库记录信息
     */
    public TbWarehouseRecord selectTbWarehouseRecordById(TbWarehouseRecord tbWarehouseRecord);

    /**
     * 查询出入库记录列表
     *
     * @param tbWarehouseRecord 出入库记录信息
     * @return 出入库记录集合
     */
    public List<TbWarehouseRecord> selectTbWarehouseRecordList(TbWarehouseRecord tbWarehouseRecord);

    /**
     * 搜索出入库记录列表
     *
     * @param tbWarehouseRecord 出入库记录信息
     * @return 出入库记录集合
     */
    public List<TbWarehouseRecord> selectTbWarehouseRecordListByName(TbWarehouseRecord tbWarehouseRecord);


    /**
     * 新增出入库记录
     *
     * @param tbWarehouseRecord 出入库记录信息
     * @return 结果
     */
    public int insertTbWarehouseRecord(TbWarehouseRecord tbWarehouseRecord);

    /**
     * 修改出入库记录
     *
     * @param tbWarehouseRecord 出入库记录信息
     * @return 结果
     */
    public int updateTbWarehouseRecord(TbWarehouseRecord tbWarehouseRecord);

    /**
     * 删除出入库记录信息
     *
     * @return 结果
     */
    public int deleteTbWarehouseRecordById(TbWarehouseRecord tbWarehouseRecord);

    /**
     * 根据当前农村查询 出库和 入库数量
     *
     * @param farmId
     * @param today
     * @return
     */
    public List<TbWarehouseRecordVo> selectByTodayStatistics(String farmId, String today);


    /**
     * 批量添加出入库记录
     *
     * @param tbWarehouseRecords
     * @param farmUUID
     * @return
     */
    public void batchAddWarehouseRecord(List<TbWarehouseRecord> tbWarehouseRecords, String farmUUID) throws Exception;
}
