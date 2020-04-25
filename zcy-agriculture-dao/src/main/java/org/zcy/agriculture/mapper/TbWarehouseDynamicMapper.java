package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbWarehouseDynamic;

import java.util.HashMap;
import java.util.List;

/**
 * 出入库动态 数据层
 *
 * @author numberone
 * @date 2019-07-11
 */
public interface TbWarehouseDynamicMapper {
    /**
     * 查询出入库动态信息
     *
     * @return 出入库动态信息
     */
    public TbWarehouseDynamic selectTbWarehouseDynamicById(TbWarehouseDynamic tbWarehouseDynamic);

    /**
     * 查询出入库动态列表
     *
     * @param tbWarehouseDynamic 出入库动态信息
     * @return 出入库动态集合
     */
    public List<TbWarehouseDynamic> selectTbWarehouseDynamicList(TbWarehouseDynamic tbWarehouseDynamic);

    /**
     * 新增出入库动态
     *
     * @param tbWarehouseDynamic 出入库动态信息
     * @return 结果
     */
    public int insertTbWarehouseDynamic(TbWarehouseDynamic tbWarehouseDynamic);

    /**
     * 修改出入库动态
     *
     * @param tbWarehouseDynamic 出入库动态信息
     * @return 结果
     */
    public int updateTbWarehouseDynamic(TbWarehouseDynamic tbWarehouseDynamic);

    /**
     * 删除出入库动态
     *
     * @return 结果
     */
    public int deleteTbWarehouseDynamicById(TbWarehouseDynamic tbWarehouseDynamic);

    /**
     * 批量删除出入库动态
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbWarehouseDynamicByIds(String[] ids);

    /**
     * 插入动态
     *
     * @param m
     * @return
     */
    public int insertByMap(HashMap<String, Object> m);
}