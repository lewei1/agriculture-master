package org.zcy.agriculture.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbPlanting;
import org.zcy.agriculture.vo.TbPlantingVo;

/**
 * 种植 数据层
 * 
 * @author numberone
 * @date 2019-06-25
 */
public interface TbPlantingMapper {
	/**
	 * 查询种植信息
	 * 
	 * @param plantingId 种植ID
	 * @return 种植信息
	 */
	public TbPlanting selectTbPlantingById(Long plantingId);

	/**
	 * 查询种植列表
	 * 
	 * @param tbPlanting 种植信息
	 * @return 种植集合
	 */
	public List<TbPlanting> selectTbPlantingList(TbPlanting tbPlanting);

	/**
	 * 新增种植
	 * 
	 * @param tbPlanting 种植信息
	 * @return 结果
	 */
	public int insertTbPlanting(TbPlanting tbPlanting);

	/**
	 * 修改种植
	 * 
	 * @param tbPlanting 种植信息
	 * @return 结果
	 */
	public int updateTbPlanting(TbPlanting tbPlanting);

	/**
	 * 删除种植
	 * 
	 * @param plantingId 种植ID
	 * @return 结果
	 */
	public int deleteTbPlantingById(Long plantingId);

	/**
	 * 批量删除种植
	 * 
	 * @param plantingIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbPlantingByIds(String[] plantingIds);

	/**
	 * 种植管理 概览
	 * 
	 * @param tbPlantingVo
	 * @return
	 */
	public List<TbPlantingVo> selectByNncultivatedList(TbPlantingVo tbPlantingVo);

	/**
	 * 种植管理 子地块种植列表
	 * 
	 * @param tbPlantingVo
	 * @return
	 */
	public List<TbPlantingVo> selectByPlantingAllList(TbPlantingVo tbPlantingVo);

	/**
	 * 批量插入前，判断子地块是否在种植中
	 * 
	 * @param plantingList
	 * @return
	 */
	public int selectByPlantingStatus(@Param("plantingList") List<TbPlanting> plantingList);

	/**
	 * 批量种植
	 * 
	 * @param plantingList
	 * @return
	 */
	public int insertByPlantingBatch(@Param("plantingList") List<TbPlanting> plantingList);

	/**
	 * 批量结束种植
	 * 
	 * @param plantingList
	 * @return
	 */
	public int updateTbPlantingBatch(@Param("plantingList") List<TbPlanting> plantingList);

	/**
	 * 根据地块ID查询农作物和负责人
	 * 
	 * @param plotId
	 * @return
	 */
	public List<TbPlantingVo> selectByCategoryNameList(@Param("plotId") Long plotId);
	
	/**
	 * 根据子地块ID 获取正在种植的数据
	 * @param subPlotIds
	 * @return
	 */
	public List<TbPlantingVo> selectListBySubPlotIds(@Param("list") Set<Long> list,@Param("farmId") String farmId);
}