package org.zcy.agriculture.service;

import java.util.HashMap;
import java.util.List;

import org.zcy.agriculture.entity.SysDictData;
import org.zcy.agriculture.entity.TbHarvest;
import org.zcy.agriculture.vo.TbHarvestExportVo;
import org.zcy.agriculture.vo.TbHarvestVo;

/**
 * 作物采收 服务层
 * 
 * @author numberone
 * @date 2019-06-27
 */
public interface ITbHarvestService {
	/**
	 * 查询作物采收信息
	 * 
	 * @param harvestId 作物采收ID
	 * @return 作物采收信息
	 */
	public TbHarvest selectTbHarvestById(Long harvestId);

	/**
	 * 查询作物采收列表
	 * 
	 * @param tbHarvest 作物采收信息
	 * @return 作物采收集合
	 */
	public List<TbHarvest> selectTbHarvestList(TbHarvest tbHarvest);

	/**
	 * 新增作物采收
	 * 
	 * @param tbHarvest 作物采收信息
	 * @return 结果
	 */
	public int insertTbHarvest(TbHarvestVo tbHarvest);

	/**
	 * 修改作物采收
	 * 
	 * @param tbHarvest 作物采收信息
	 * @return 结果
	 */
	public int updateTbHarvest(TbHarvestVo tbHarvest,String imgPath);

	/**
	 * 删除作物采收信息
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbHarvestByIds(String ids);

	/**
	 * 汇总式采收列表
	 * 
	 * @param hVo
	 * @return
	 */
	public List<TbHarvestVo> selectByCshz(TbHarvestVo hVo);

	/**
	 * 汇总式采收列表查询总条数
	 * 
	 * @param hVo
	 * @return
	 */
	public int selectByCshzListCou(TbHarvestVo hVo);

	/**
	 * 采收记录
	 * 
	 * @param hVo
	 * @return
	 */
	public List<TbHarvestVo> selectByCsjlList(TbHarvestVo hVo);

	/**
	 * 批量删除作物采收记录
	 * 
	 * @param harvestList
	 * @return
	 */
	public int updateTbHarvestBatch(List<TbHarvestVo> harvestList);

	/**
	 * 采收统计列表
	 * 
	 * @param hVo
	 * @return
	 */
	public List<TbHarvestExportVo> selectByStatistics(TbHarvestVo hVo);

	/**
	 * 采收统计列表总条数
	 * 
	 * @param hVo
	 * @return
	 */
	public int selectByStatisticsCou(TbHarvestVo hVo);

	List<SysDictData> getLevelList(String dictName);
	/**
	 * 今日采收 汇总
	 *
	 * @param plotId
	 * @return
	 */
	public HashMap<String, Object> selectByStatisticsToDay(Long plotId);
}
