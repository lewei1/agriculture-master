package org.zcy.agriculture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbPlanting;
import org.zcy.agriculture.mapper.TbPlantingMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbPlantingService;
import org.zcy.agriculture.vo.TbPlantingVo;

/**
 * 种植 服务层实现
 * 
 * @author numberone
 * @date 2019-06-25
 */
@Service
public class TbPlantingServiceImpl implements ITbPlantingService {
	@Autowired
	private TbPlantingMapper tbPlantingMapper;

	/**
	 * 查询种植信息
	 * 
	 * @param plantingId 种植ID
	 * @return 种植信息
	 */
	@Override
	public TbPlanting selectTbPlantingById(Long plantingId) {
		return tbPlantingMapper.selectTbPlantingById(plantingId);
	}

	/**
	 * 查询种植列表
	 * 
	 * @param tbPlanting 种植信息
	 * @return 种植集合
	 */
	@Override
	public List<TbPlanting> selectTbPlantingList(TbPlanting tbPlanting) {
		return tbPlantingMapper.selectTbPlantingList(tbPlanting);
	}

	/**
	 * 新增种植
	 * 
	 * @param tbPlanting 种植信息
	 * @return 结果
	 */
	@Override
	public int insertTbPlanting(TbPlanting tbPlanting) {
		return tbPlantingMapper.insertTbPlanting(tbPlanting);
	}

	/**
	 * 修改种植
	 * 
	 * @param tbPlanting 种植信息
	 * @return 结果
	 */
	@Override
	public int updateTbPlanting(TbPlanting tbPlanting) {
		return tbPlantingMapper.updateTbPlanting(tbPlanting);
	}

	/**
	 * 删除种植对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteTbPlantingByIds(String ids) {
		return tbPlantingMapper.deleteTbPlantingByIds(Convert.toStrArray(ids));
	}

	/**
	 * 种植管理 概览
	 * 
	 * @param tbPlantingVo
	 * @return
	 */
	public List<TbPlantingVo> selectByNncultivatedList(TbPlantingVo tbPlantingVo) {
		return tbPlantingMapper.selectByNncultivatedList(tbPlantingVo);
	}

	/**
	 * 种植管理 子地块种植列表
	 * 
	 * @param tbPlantingVo
	 * @return
	 */
	public List<TbPlantingVo> selectByPlantingAllList(TbPlantingVo tbPlantingVo) {
		return tbPlantingMapper.selectByPlantingAllList(tbPlantingVo);
	}

	/**
	 * 批量插入前，判断子地块是否在种植中
	 * 
	 * @param plantingList
	 * @return
	 */
	public int selectByPlantingStatus(List<TbPlanting> plantingList) {
		return tbPlantingMapper.selectByPlantingStatus(plantingList);
	}

	/**
	 * 批量种植
	 * 
	 * @param plantingList
	 * @return
	 */
	public int insertByPlantingBatch(List<TbPlanting> plantingList) {
		return tbPlantingMapper.insertByPlantingBatch(plantingList);
	}

	/**
	 * 批量结束种植
	 * 
	 * @param plantingList
	 * @return
	 */
	public int updateTbPlantingBatch(List<TbPlanting> plantingList) {
		return tbPlantingMapper.updateTbPlantingBatch(plantingList);
	}

	/**
	 * 根据地块ID查询农作物和负责人
	 * 
	 * @param plotId
	 * @return
	 */
	public List<TbPlantingVo> selectByCategoryNameList(Long plotId) {
		return tbPlantingMapper.selectByCategoryNameList(plotId);
	}
}
