package org.zcy.agriculture.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.mapper.TbTransferMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.*;
import org.zcy.agriculture.util.GenerateNoUtils;


/**
 * 调拨记录 服务层实现
 *
 * @author linlq lky.
 * @date 2019-07-02
 */
@Service
public class TbTransferServiceImpl implements ITbTransferService {

    @Autowired
    private TbTransferMapper tbTransferMapper;

    @Autowired
    private ITbWarehouseRecordService tbWarehouseRecordService;

    @Autowired
    private ITbWarehouseDynamicService tbWarehouseDynamicService;

    @Autowired
    private ITbInventoryMaterialService tbInventoryMaterialService;

    @Autowired
    private ITbArticleService tbArticleService;

    @Autowired
    private ITbUnitTypeService tbUnitTypeService;

    /**
     * 查询调拨记录信息
     *
     * @return 调拨记录信息
     */
    @Override
    public TbTransfer selectTbTransferById(TbTransfer tbTransfer) {
        return tbTransferMapper.selectTbTransferById(tbTransfer);
    }

    /**
     * 查询调拨记录列表
     *
     * @param tbTransfer 调拨记录信息
     * @return 调拨记录集合
     */
    @Override
    public List<TbTransfer> selectTbTransferList(TbTransfer tbTransfer) {
        return tbTransferMapper.selectTbTransferList(tbTransfer);
    }

    @Override
    public List<TbTransfer> selectTbTransferListByName(TbTransfer tbTransfer) {
        return tbTransferMapper.selectTbTransferListByName(tbTransfer);
    }

    /**
     * 新增调拨记录
     *
     * @param tbTransfer 调拨记录信息
     * @return 结果
     */
    @Override
    public int insertTbTransfer(TbTransfer tbTransfer) {
        return tbTransferMapper.insertTbTransfer(tbTransfer);
    }

    /**
     * 修改调拨记录
     *
     * @param tbTransfer 调拨记录信息
     * @return 结果
     */
    @Override
    public int updateTbTransfer(TbTransfer tbTransfer) {
        return tbTransferMapper.updateTbTransfer(tbTransfer);
    }

    /**
     * 获取某个单位id 与计价单位id 之间的换算比率
     *
     * @return 结果
     */
    @Override
    public int deleteTbTransferById(TbTransfer tbTransfer) {
        return tbTransferMapper.deleteTbTransferById(tbTransfer);
    }


    /**
     * 调拨记录入库操作
     *
     * @param tbTf
     * @param farmUUID
     * @param farmUserCode
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addStorage(TbTransfer tbTf, String farmUUID, Long farmUserCode) throws Exception {

        try {
            //①修改调拨表tb_transfer 的记录状态
            this.updateTbTransfer(tbTf);

            //②往出入库记录表tb_warehouse_record 写记录  调入仓库和调出仓库各一条记录
            //调出记录
            TbWarehouseRecord tbWarehouseRecordOut = new TbWarehouseRecord();
            tbWarehouseRecordOut.setFarmId(farmUUID);
            tbWarehouseRecordOut.setCreateBy(farmUserCode);
            tbWarehouseRecordOut.setCreateTime(new Date());
            tbWarehouseRecordOut.setInOutType("O");
            //tbWarehouseRecordOut.setInOutTypeId(1L);// 暂时不设置出入库类型id
            tbWarehouseRecordOut.setMethod("调出");
            tbWarehouseRecordOut.setWarehouseId(tbTf.getOutWarehouseId());
            tbWarehouseRecordOut.setOperatorId(tbTf.getOperatorId());
            tbWarehouseRecordOut.setArticle(tbTf.getArticle());
            tbWarehouseRecordOut.setMaterialTypeId(tbTf.getMaterialTypeId());
            tbWarehouseRecordOut.setMaterialTypeName(tbTf.getMaterialTypeName());
            tbWarehouseRecordOut.setUnit(tbTf.getUnit());
            tbWarehouseRecordOut.setQuantity(tbTf.getQuantity());
            tbWarehouseRecordOut.setRemarks(tbTf.getRemarks());
            tbWarehouseRecordOut.setMaterialId(tbTf.getMaterialId());
            tbWarehouseRecordOut.setNumber(GenerateNoUtils.gens("OUT-", "-" + String.valueOf(new Random().nextLong()).substring(3, 7)));
            tbWarehouseRecordService.insertTbWarehouseRecord(tbWarehouseRecordOut);

            //调入记录
            TbWarehouseRecord tbWarehouseRecordIn = new TbWarehouseRecord();
            tbWarehouseRecordIn.setFarmId(farmUUID);
            tbWarehouseRecordIn.setCreateBy(farmUserCode);
            tbWarehouseRecordIn.setCreateTime(new Date());
            tbWarehouseRecordIn.setInOutType("I");
            //tbWarehouseRecordIn.setInOutTypeId(2L);// 暂时不设置出入库类型id
            tbWarehouseRecordIn.setMethod("调入");
            tbWarehouseRecordIn.setWarehouseId(tbTf.getInWarehouseId());
            tbWarehouseRecordIn.setOperatorId(tbTf.getOperatorId());
            tbWarehouseRecordIn.setArticle(tbTf.getArticle());
            tbWarehouseRecordIn.setMaterialTypeId(tbTf.getMaterialTypeId());
            tbWarehouseRecordIn.setMaterialTypeName(tbTf.getMaterialTypeName());
            tbWarehouseRecordIn.setUnit(tbTf.getUnit());
            tbWarehouseRecordIn.setQuantity(tbTf.getQuantity());
            tbWarehouseRecordIn.setRemarks(tbTf.getRemarks());
            tbWarehouseRecordIn.setMaterialId(tbTf.getMaterialId());
            tbWarehouseRecordIn.setNumber(GenerateNoUtils.gens("IN-", "-" + String.valueOf(new Random().nextLong()).substring(3, 7)));
            tbWarehouseRecordService.insertTbWarehouseRecord(tbWarehouseRecordIn);

            //③调拨成功向出入库动态表插入消息

            //根据单位id  查询 单位的名称
            TbUnitType tbUnitType = new TbUnitType();
            tbUnitType.setFarmId(farmUUID);
            tbUnitType.setMaterialUnitId(tbTf.getUnit());
            tbUnitType = tbUnitTypeService.selectTbUnitTypeById(tbUnitType);

            //出库
            TbWarehouseDynamic tbWarehouseDynamicOut = new TbWarehouseDynamic();
            tbWarehouseDynamicOut.setFarmId(farmUUID);
            tbWarehouseDynamicOut.setCreateTime(new Date());
            tbWarehouseDynamicOut.setInfo(tbTf.getOperatorName() + "出库" + doubleToString(tbWarehouseRecordOut.getQuantity()) + tbUnitType.getTypeName() + tbWarehouseRecordOut.getArticle());
            tbWarehouseDynamicOut.setInOutType("O");
            tbWarehouseDynamicService.insertTbWarehouseDynamic(tbWarehouseDynamicOut);

            //入库
            TbWarehouseDynamic tbWarehouseDynamicIn = new TbWarehouseDynamic();
            tbWarehouseDynamicIn.setFarmId(farmUUID);
            tbWarehouseDynamicIn.setCreateTime(new Date());
            tbWarehouseDynamicIn.setInfo(tbTf.getOperatorName() + "入库" + doubleToString(tbWarehouseRecordOut.getQuantity()) + tbUnitType.getTypeName() + tbWarehouseRecordOut.getArticle());
            tbWarehouseDynamicIn.setInOutType("I");
            tbWarehouseDynamicService.insertTbWarehouseDynamic(tbWarehouseDynamicIn);


            //④更新库存
            //数量的单位换算
            TbArticle tbArticle = new TbArticle();
            tbArticle.setFarmId(farmUUID);
            tbArticle.setMaterialId(tbTf.getMaterialId());
            tbArticle = tbArticleService.selectTbArticleById(tbArticle);
            //启用多单位，0为不启动，1为启动
            if (tbArticle.getMultipleUnit() == 1) {
                if (!tbTf.getUnit().equals(tbArticle.getMeterUnitId()) && !tbTf.getUnit().equals(tbArticle.getUnitId1()) && !tbTf.getUnit().equals(tbArticle.getUnitId2())) {
                    throw new Exception("传入单位不是该物品的单位");
                }
                if (tbTf.getUnit().equals(tbArticle.getUnitId1())) {
                    tbTf.setQuantity(tbTf.getQuantity() * tbArticle.getConversionRatio1());
                }
                if (tbTf.getUnit().equals(tbArticle.getUnitId2())) {
                    tbTf.setQuantity(tbTf.getQuantity() * tbArticle.getConversionRatio2());
                }
            } else {
                if (!tbTf.getUnit().equals(tbArticle.getMeterUnitId())) {
                    throw new Exception("单位id与计价单位id不一致");
                }
            }

            // 更新 入库仓库的库存
            TbInventoryMaterial tbInventoryMaterialIn = new TbInventoryMaterial();
            tbInventoryMaterialIn.setFarmId(farmUUID);
            tbInventoryMaterialIn.setWarehouseId(tbTf.getInWarehouseId());
            tbInventoryMaterialIn.setMaterialId(tbTf.getMaterialId());
            //查询库存表中该物品是否存在
            List<TbInventoryMaterial> list = tbInventoryMaterialService.selectTbInventoryMaterialList(tbInventoryMaterialIn);
            //有则更新，无则插入
            if (list.size() > 0) {
                tbInventoryMaterialIn.setQuantity(list.get(0).getQuantity() + tbTf.getQuantity());
                tbInventoryMaterialIn.setWarehouseMaterialId(list.get(0).getWarehouseMaterialId());
                tbInventoryMaterialService.updateTbInventoryMaterial(tbInventoryMaterialIn);
            } else {
                tbInventoryMaterialIn.setQuantity(tbTf.getQuantity());
                tbInventoryMaterialService.insertTbInventoryMaterial(tbInventoryMaterialIn);
            }

            //更新 出库仓库的库存
            TbInventoryMaterial tbInventoryMaterialOut = new TbInventoryMaterial();
            tbInventoryMaterialOut.setFarmId(farmUUID);
            tbInventoryMaterialOut.setWarehouseId(tbTf.getOutWarehouseId());
            tbInventoryMaterialOut.setMaterialId(tbTf.getMaterialId());
            //查询物品是否存在
            List<TbInventoryMaterial> listout = tbInventoryMaterialService.selectTbInventoryMaterialList(tbInventoryMaterialOut);
            //有则更新，无则插入
            if (listout.size() > 0) {
                tbInventoryMaterialOut.setQuantity(listout.get(0).getQuantity() - tbTf.getQuantity());
                tbInventoryMaterialOut.setWarehouseMaterialId(listout.get(0).getWarehouseMaterialId());
                tbInventoryMaterialService.updateTbInventoryMaterial(tbInventoryMaterialOut);
            } else {
                tbInventoryMaterialOut.setQuantity(0 - tbTf.getQuantity());
                tbInventoryMaterialService.insertTbInventoryMaterial(tbInventoryMaterialOut);
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public String doubleToString(double d) {
        String i = DecimalFormat.getInstance().format(d);
        return i.replaceAll(",", "");
    }

    /**
     * 批量 新增调拨记录
     *
     * @param tbTransfers 调拨记录信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertTbTransfer(List<TbTransfer> tbTransfers) throws Exception {
        try {
            for (TbTransfer tbTransfer : tbTransfers) {
                this.insertTbTransfer(tbTransfer);
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
