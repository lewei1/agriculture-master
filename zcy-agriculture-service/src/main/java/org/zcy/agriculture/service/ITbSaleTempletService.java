package org.zcy.agriculture.service;

import java.util.List;

import org.zcy.agriculture.entity.TbSaleTemplet;
import org.zcy.agriculture.vo.TbSaleTempletVo;

/**
 * 销售管理-展示模板 服务层
 * 
 * @author numberone
 * @date 2019-07-01
 */
public interface ITbSaleTempletService 
{
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
	public int insertTbSaleTemplet(TbSaleTempletVo tbSaleTempletVo);
	
	/**
     * 修改销售管理-展示模板
     * 
     * @param tbSaleTemplet 销售管理-展示模板信息
     * @return 结果
     */
	public int updateTbSaleTemplet(TbSaleTempletVo tbSaleTempletVo,String imgPath);
		
	/**
     * 删除销售管理-展示模板信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbSaleTempletByIds(String ids);
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
