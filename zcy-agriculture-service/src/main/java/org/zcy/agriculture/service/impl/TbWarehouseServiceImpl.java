package org.zcy.agriculture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbInventoryMaterial;
import org.zcy.agriculture.entity.TbWarehouse;
import org.zcy.agriculture.mapper.TbInventoryMaterialMapper;
import org.zcy.agriculture.mapper.TbWarehouseMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbWarehouseService;

/**
 * 仓库 服务层实现
 *
 * @author linlq
 * @date 2019-06-28
 */
@Service
public class TbWarehouseServiceImpl implements ITbWarehouseService {

    @Autowired
    private TbWarehouseMapper tbWarehouseMapper;

    @Autowired
    private TbInventoryMaterialMapper tbInventoryMaterialMapper;

    /**
     * 查询仓库信息
     *
     * @return 仓库信息
     */
    @Override
    public TbWarehouse selectTbWarehouseById(TbWarehouse tbWarehouse) {
        return tbWarehouseMapper.selectTbWarehouseById(tbWarehouse);
    }

    /**
     * 查询仓库列表
     *
     * @param tbWarehouse 仓库信息
     * @return 仓库集合
     */
    @Override
    public List<TbWarehouse> selectTbWarehouseList(TbWarehouse tbWarehouse) {
        return tbWarehouseMapper.selectTbWarehouseList(tbWarehouse);
    }

    /**
     * 新增仓库
     *
     * @param tbWarehouse 仓库信息
     * @return 结果
     */
    @Override
    public int insertTbWarehouse(TbWarehouse tbWarehouse) {
        return tbWarehouseMapper.insertTbWarehouse(tbWarehouse);
    }

    /**
     * 修改仓库
     *
     * @param tbWarehouse 仓库信息
     * @return 结果
     */
    @Override
    public int updateTbWarehouse(TbWarehouse tbWarehouse) {
        return tbWarehouseMapper.updateTbWarehouse(tbWarehouse);
    }

    /**
     * 删除仓库对象 且 删除库存
     *
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTbWarehouseById(TbWarehouse tbWarehouse) throws Exception {
        {
            try {
                tbWarehouseMapper.deleteTbWarehouseById(tbWarehouse);
                TbInventoryMaterial tbInventoryMaterial = new TbInventoryMaterial();
                tbInventoryMaterial.setFarmId(tbWarehouse.getFarmId());
                tbInventoryMaterial.setWarehouseId(tbWarehouse.getWarehouseId());
                tbInventoryMaterialMapper.deleteTbInventoryMaterialByWarehouseId(tbInventoryMaterial);
            } catch (Exception e) {
                throw new Exception();
            }
        }

    }
}