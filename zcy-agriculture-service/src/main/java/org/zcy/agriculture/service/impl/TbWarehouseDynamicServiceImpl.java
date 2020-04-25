package org.zcy.agriculture.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbWarehouseDynamic;
import org.zcy.agriculture.mapper.TbWarehouseDynamicMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbWarehouseDynamicService;

import com.google.common.collect.Maps;

/**
 * 出入库动态 服务层实现
 * 
 * @author linlq
 * @date 2019-07-11
 */
@Service
public class TbWarehouseDynamicServiceImpl implements ITbWarehouseDynamicService {
	@Autowired
	private TbWarehouseDynamicMapper tbWarehouseDynamicMapper;

	/**
	 * 查询出入库动态信息
	 * 
	 * @return 出入库动态信息
	 */
	@Override
	public TbWarehouseDynamic selectTbWarehouseDynamicById(TbWarehouseDynamic tbWarehouseDynamic) {
		return tbWarehouseDynamicMapper.selectTbWarehouseDynamicById(tbWarehouseDynamic);
	}

	/**
	 * 查询出入库动态列表
	 * 
	 * @param tbWarehouseDynamic 出入库动态信息
	 * @return 出入库动态集合
	 */
	@Override
	public List<TbWarehouseDynamic> selectTbWarehouseDynamicList(TbWarehouseDynamic tbWarehouseDynamic) {
		return tbWarehouseDynamicMapper.selectTbWarehouseDynamicList(tbWarehouseDynamic);
	}

	/**
	 * 新增出入库动态
	 * 
	 * @param tbWarehouseDynamic 出入库动态信息
	 * @return 结果
	 */
	@Override
	public int insertTbWarehouseDynamic(TbWarehouseDynamic tbWarehouseDynamic) {
		return tbWarehouseDynamicMapper.insertTbWarehouseDynamic(tbWarehouseDynamic);
	}

	/**
	 * 修改出入库动态
	 * 
	 * @param tbWarehouseDynamic 出入库动态信息
	 * @return 结果
	 */
	@Override
	public int updateTbWarehouseDynamic(TbWarehouseDynamic tbWarehouseDynamic) {
		return tbWarehouseDynamicMapper.updateTbWarehouseDynamic(tbWarehouseDynamic);
	}

	/**
	 * 删除出入库动态对象
	 * 
	 * @return 结果
	 */
	@Override
	public int deleteTbWarehouseDynamicById(TbWarehouseDynamic tbWarehouseDynamic) {
		return tbWarehouseDynamicMapper.deleteTbWarehouseDynamicById(tbWarehouseDynamic);
	}

	/**
	 * 新增计划时 插入物品动态
	 * 
	 * @param farmId     农场ID
	 * @param materialId 物品ID
	 * @param info       描述
	 */
	@Transactional
	public void save(String farmId, Long materialId, String info, Long userCode) {
		HashMap<String, Object> m = Maps.newHashMap();
		m.put("farmId", farmId);
		m.put("userCode", userCode);
		m.put("info", info);
		m.put("materialId", materialId);
		tbWarehouseDynamicMapper.insertByMap(m);
	}
}
