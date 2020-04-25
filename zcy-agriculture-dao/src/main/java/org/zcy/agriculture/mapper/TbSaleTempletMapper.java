package org.zcy.agriculture.mapper;

import java.util.List;

import org.zcy.agriculture.entity.TbSaleTemplet;
import org.zcy.agriculture.vo.TbSaleTempletVo;

/**
 * 销售管理-展示模板 数据层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface TbSaleTempletMapper {
	/**
	 * 查询销售管理-展示模板信息
	 * 
	 * @param saleTempletId 销售管理-展示模板ID
	 * @return 销售管理-展示模板信息
	 */
	public TbSaleTemplet selectTbSaleTempletById(Long saleTempletId);

	/**
	 * 查询销售管理-展示模板列表
	 * 
	 * @param tbSaleTemplet 销售管理-展示模板信息
	 * @return 销售管理-展示模板集合
	 */
	public List<TbSaleTemplet> selectTbSaleTempletList(TbSaleTemplet tbSaleTemplet);

	/**
	 * 新增销售管理-展示模板
	 * 
	 * @param tbSaleTemplet 销售管理-展示模板信息
	 * @return 结果
	 */
	public int insertTbSaleTemplet(TbSaleTemplet tbSaleTemplet);

	/**
	 * 修改销售管理-展示模板
	 * 
	 * @param tbSaleTemplet 销售管理-展示模板信息
	 * @return 结果
	 */
	public int updateTbSaleTemplet(TbSaleTemplet tbSaleTemplet);

	/**
	 * 删除销售管理-展示模板
	 * 
	 * @param saleTempletId 销售管理-展示模板ID
	 * @return 结果
	 */
	public int deleteTbSaleTempletById(Long saleTempletId);

	/**
	 * 批量删除销售管理-展示模板
	 * 
	 * @param saleTempletIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbSaleTempletByIds(String[] saleTempletIds);

	/**
	 * 销售管理-展示模板列表
	 * 
	 * @param tbSaleTempletVo
	 * @return
	 */
	public List<TbSaleTempletVo> selectTbMbList(TbSaleTempletVo tbSaleTempletVo);

	/**
	 * 批量删除 逻辑删除
	 * 
	 * @param list
	 * @return
	 */
	public int updateTbSaleTempletBatch(List<Long> list);

	/**
	 * 根据农场和名称判断是否重复
	 * 
	 * @param farmId
	 * @param saleTempletId
	 * @param farmName
	 * @return
	 */
	public int selectByFarmNameRepeat(String farmId, Long saleTempletId, String farmName);
}