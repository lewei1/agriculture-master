package org.zcy.agriculture.mapper;

import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbWarehouseRecord;
import org.zcy.agriculture.vo.TbWarehouseRecordVo;

import java.util.List;

/**
 * 出入库记录 数据层
 * 
 * @author linlq
 * @date 2019-06-28
 */
public interface TbWarehouseRecordMapper 
{
	/**
     * 查询出入库记录信息
     * 
     * @return 出入库记录信息
     */
	public TbWarehouseRecord selectTbWarehouseRecordById(TbWarehouseRecord tbWarehouseRecord);
	
	/**
     * 查询出入库记录列表
     * 
     * @param tbWarehouseRecord 出入库记录信息
     * @return 出入库记录集合
     */
	public List<TbWarehouseRecord> selectTbWarehouseRecordList(TbWarehouseRecord tbWarehouseRecord);

	/**
	 * 搜索出入库记录列表
	 *
	 * @param tbWarehouseRecord 出入库记录信息
	 * @return 出入库记录集合
	 */
	public List<TbWarehouseRecord> selectTbWarehouseRecordListByName(TbWarehouseRecord tbWarehouseRecord);
	
	/**
     * 新增出入库记录
     * 
     * @param tbWarehouseRecord 出入库记录信息
     * @return 结果
     */
	public int insertTbWarehouseRecord(TbWarehouseRecord tbWarehouseRecord);
	
	/**
     * 修改出入库记录
     * 
     * @param tbWarehouseRecord 出入库记录信息
     * @return 结果
     */
	public int updateTbWarehouseRecord(TbWarehouseRecord tbWarehouseRecord);
	
	/**
     * 删除出入库记录
     * 
     * @return 结果
     */
	public int deleteTbWarehouseRecordById(TbWarehouseRecord tbWarehouseRecord);
	
	/**
     * 批量删除出入库记录
     * 
     * @param inIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbWarehouseRecordByIds(String[] inIds);
	
	/**
	 * 根据当前农村查询 出库和 入库数量
	 * @param farmId
	 * @param today
	 * @return
	 */
	public List<TbWarehouseRecordVo> selectByTodayStatistics(@Param("farmId") String farmId ,@Param("today") String today);
}