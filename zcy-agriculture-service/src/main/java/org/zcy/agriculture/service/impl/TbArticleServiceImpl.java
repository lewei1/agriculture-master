package org.zcy.agriculture.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbArticle;
import org.zcy.agriculture.entity.TbInventoryMaterial;
import org.zcy.agriculture.mapper.TbArticleMapper;
import org.zcy.agriculture.mapper.TbInventoryMaterialMapper;
import org.zcy.agriculture.service.ITbArticleService;

/**
 * 物品 服务层实现
 *
 * @author linlq
 * @date 2019-06-21
 */
@Service
public class TbArticleServiceImpl implements ITbArticleService {
    @Autowired
    private TbArticleMapper tbArticleMapper;
    @Autowired
    private TbInventoryMaterialMapper tbInventoryMaterialMapper;

    /**
     * 查询物品信息
     *
     * @return 物品信息
     */
    @Override
    public TbArticle selectTbArticleById(TbArticle tbArticle) {
        return tbArticleMapper.selectTbArticleById(tbArticle);
    }

    /**
     * 查询物品列表
     *
     * @param tbArticle 物品信息
     * @return 物品集合
     */
    @Override
    public List<TbArticle> selectTbArticleList(TbArticle tbArticle) {
        return tbArticleMapper.selectTbArticleList(tbArticle);
    }

    /**
     * 新增物品
     *
     * @param tbArticle 物品信息
     * @return 结果
     */
    @Override
    public int insertTbArticle(TbArticle tbArticle) {
        return tbArticleMapper.insertTbArticle(tbArticle);
    }

    /**
     * 修改物品
     *
     * @param tbArticle 物品信息
     * @return 结果
     */
    @Override
    public int updateTbArticle(TbArticle tbArticle) {
        return tbArticleMapper.updateTbArticle(tbArticle);
    }

    /**
     * 删除物品对象 且 删除库存
     *
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTbArticleById(TbArticle tbArticle) throws Exception {

        try {
            tbArticleMapper.deleteTbArticleById(tbArticle);
            TbInventoryMaterial tbInventoryMaterial = new TbInventoryMaterial();
            tbInventoryMaterial.setFarmId(tbArticle.getFarmId());
            tbInventoryMaterial.setMaterialId(tbArticle.getMaterialId());
            tbInventoryMaterialMapper.deleteTbInventoryMaterialByMaterialId(tbInventoryMaterial);
        } catch (Exception e) {
            throw new Exception();
        }

    }

    /**
     * 根据创库ID查询物品
     *
     * @param warehouseId
     * @return
     */
    public List<HashMap<String, Object>> selectByWarehouseId(Long warehouseId) {
        return tbArticleMapper.selectByWarehouseId(warehouseId);
    }
}
