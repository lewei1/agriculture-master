package org.zcy.agriculture.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.mapper.TbWarehouseRecordMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.*;
import org.zcy.agriculture.vo.TbWarehouseRecordVo;

/**
 * 出入库记录 服务层实现
 *
 * @author linlq lky.
 * @date 2019-06-28
 */
@Service
public class TbWarehouseRecordServiceImpl implements ITbWarehouseRecordService {
    @Autowired
    private TbWarehouseRecordMapper tbWarehouseRecordMapper;

    @Autowired
    private ITbInventoryMaterialService tbInventoryMaterialService;

    @Autowired
    private ITbWarehouseDynamicService tbWarehouseDynamicService;

    @Autowired
    private ITbArticleService tbArticleService;

    @Autowired
    private ITbUnitTypeService tbUnitTypeService;

    /**
     * 查询出入库记录信息
     *
     * @return 出入库记录信息
     */
    @Override
    public TbWarehouseRecord selectTbWarehouseRecordById(TbWarehouseRecord tbWarehouseRecord) {
        return tbWarehouseRecordMapper.selectTbWarehouseRecordById(tbWarehouseRecord);
    }

    /**
     * 查询出入库记录列表
     *
     * @param tbWarehouseRecord 出入库记录信息
     * @return 出入库记录集合
     */
    @Override
    public List<TbWarehouseRecord> selectTbWarehouseRecordList(TbWarehouseRecord tbWarehouseRecord) {
        return tbWarehouseRecordMapper.selectTbWarehouseRecordList(tbWarehouseRecord);
    }

    /**
     * 搜索出入库记录列表
     *
     * @param tbWarehouseRecord 出入库记录信息
     * @return 出入库记录集合
     */
    @Override
    public List<TbWarehouseRecord> selectTbWarehouseRecordListByName(TbWarehouseRecord tbWarehouseRecord) {
        return tbWarehouseRecordMapper.selectTbWarehouseRecordListByName(tbWarehouseRecord);
    }

    /**
     * 新增出入库记录
     *
     * @param tbWarehouseRecord 出入库记录信息
     * @return 结果
     */
    @Override
    public int insertTbWarehouseRecord(TbWarehouseRecord tbWarehouseRecord) {
        return tbWarehouseRecordMapper.insertTbWarehouseRecord(tbWarehouseRecord);
    }

    /**
     * 修改出入库记录
     *
     * @param tbWarehouseRecord 出入库记录信息
     * @return 结果
     */
    @Override
    public int updateTbWarehouseRecord(TbWarehouseRecord tbWarehouseRecord) {
        return tbWarehouseRecordMapper.updateTbWarehouseRecord(tbWarehouseRecord);
    }

    /**
     * 删除出入库记录对象
     *
     * @return 结果
     */
    @Override
    public int deleteTbWarehouseRecordById(TbWarehouseRecord tbWarehouseRecord) {
        return tbWarehouseRecordMapper.deleteTbWarehouseRecordById(tbWarehouseRecord);
    }

    /**
     * 根据当前农村查询 出库和 入库数量
     *
     * @param farmId
     * @param today
     * @return
     */
    public List<TbWarehouseRecordVo> selectByTodayStatistics(String farmId, String today) {
        return tbWarehouseRecordMapper.selectByTodayStatistics(farmId, today);
    }

    /**
     * 批量添加出入库记录
     *
     * @param tbWarehouseRecords
     * @param farmUUID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAddWarehouseRecord(List<TbWarehouseRecord> tbWarehouseRecords, String farmUUID) throws Exception {

        try {
            for (TbWarehouseRecord tbWarehouseRecord : tbWarehouseRecords) {
                //①新增出入库记录
                int num = insertTbWarehouseRecord(tbWarehouseRecord);

                //②入库成功向出入库动态表插入消息
                TbWarehouseDynamic tbWarehouseDynamic = new TbWarehouseDynamic();
                tbWarehouseDynamic.setFarmId(farmUUID);
                tbWarehouseDynamic.setCreateTime(new Date());

                //根据单位id  查询 单位的名称
                TbUnitType tbUnitType = new TbUnitType();
                tbUnitType.setFarmId(farmUUID);
                tbUnitType.setMaterialUnitId(tbWarehouseRecord.getUnit());
                tbUnitType = tbUnitTypeService.selectTbUnitTypeById(tbUnitType);

                if ("I".equals(tbWarehouseRecord.getInOutType())) {
                    //入库
                    tbWarehouseDynamic.setInfo(tbWarehouseRecord.getOperatorName() + "入库" + doubleToString(tbWarehouseRecord.getQuantity()) + tbUnitType.getTypeName() +
                            tbWarehouseRecord.getArticle());
                    tbWarehouseDynamic.setInOutType("I");
                } else {
                    //出库
                    tbWarehouseDynamic.setInfo(tbWarehouseRecord.getOperatorName() + "出库" + doubleToString(tbWarehouseRecord.getQuantity()) + tbUnitType.getTypeName() +
                            tbWarehouseRecord.getArticle());
                    tbWarehouseDynamic.setInOutType("O");
                }
                tbWarehouseDynamicService.insertTbWarehouseDynamic(tbWarehouseDynamic);

                TbInventoryMaterial tbInventoryMaterial = new TbInventoryMaterial();
                tbInventoryMaterial.setFarmId(farmUUID);
                tbInventoryMaterial.setWarehouseId(tbWarehouseRecord.getWarehouseId());
                tbInventoryMaterial.setMaterialId(tbWarehouseRecord.getMaterialId());

                //③更新库存
                //数量的单位换算
                TbArticle tbArticle = new TbArticle();
                tbArticle.setFarmId(farmUUID);
                tbArticle.setMaterialId(tbWarehouseRecord.getMaterialId());
                tbArticle = tbArticleService.selectTbArticleById(tbArticle);
                //启用多单位，0为不启动，1为启动
                if (tbArticle.getMultipleUnit() == 1) {
                    if (!tbWarehouseRecord.getUnit().equals(tbArticle.getMeterUnitId()) && !tbWarehouseRecord.getUnit().equals(tbArticle.getUnitId1()) && !tbWarehouseRecord.getUnit().equals(tbArticle.getUnitId2())) {
                        throw new Exception();
                    }
                    if (tbWarehouseRecord.getUnit().equals(tbArticle.getUnitId1())) {
                        tbWarehouseRecord.setQuantity(tbWarehouseRecord.getQuantity() * tbArticle.getConversionRatio1());
                    }
                    if (tbWarehouseRecord.getUnit().equals(tbArticle.getUnitId2())) {
                        tbWarehouseRecord.setQuantity(tbWarehouseRecord.getQuantity() * tbArticle.getConversionRatio2());
                    }
                } else {
                    if (!tbWarehouseRecord.getUnit().equals(tbArticle.getMeterUnitId())) {
                        throw new Exception();
                    }
                }

                //查询物品是否存在
                List<TbInventoryMaterial> list = tbInventoryMaterialService.selectTbInventoryMaterialList(tbInventoryMaterial);
                //有则更新，无则插入
                if (list.size() > 0) {
                    if ("I".equals(tbWarehouseRecord.getInOutType())) {
                        //入库
                        tbInventoryMaterial.setQuantity(list.get(0).getQuantity() + tbWarehouseRecord.getQuantity());
                    } else {
                        //出库
                        tbInventoryMaterial.setQuantity(list.get(0).getQuantity() - tbWarehouseRecord.getQuantity());
                    }
                    tbInventoryMaterial.setWarehouseMaterialId(list.get(0).getWarehouseMaterialId());
                    tbInventoryMaterialService.updateTbInventoryMaterial(tbInventoryMaterial);
                } else {
                    if ("I".equals(tbWarehouseRecord.getInOutType())) {
                        //入库
                        tbInventoryMaterial.setQuantity(tbWarehouseRecord.getQuantity());
                    } else {
                        //出库
                        tbInventoryMaterial.setQuantity(0 - tbWarehouseRecord.getQuantity());
                    }
                    tbInventoryMaterialService.insertTbInventoryMaterial(tbInventoryMaterial);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public String doubleToString(double d) {
        String i = DecimalFormat.getInstance().format(d);
        return i.replaceAll(",", "");
    }
}
