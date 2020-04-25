package org.zcy.agriculture.mapper.farm;

import org.zcy.agriculture.entity.TbFarm;
import org.zcy.agriculture.vo.FarmUserRelationVo;

import java.util.HashMap;
import java.util.List;


/**
 * 农场(基础) 数据层
 * 
 * @author numberone
 * @date 2019-06-25
 */
public interface TbFarmMapper 
{
	/**
     * 查询农场(基础)信息
     * 
     * @param farmId 农场(基础)ID
     * @return 农场(基础)信息
     */
	public TbFarm selectTbFarmById(String farmId);
	
	/**
     * 查询农场(基础)列表
     * 
     * @param tbFarm 农场(基础)信息
     * @return 农场(基础)集合
     */
	public List<TbFarm> selectTbFarmList(TbFarm tbFarm);
	
	/**
     * 新增农场(基础)
     * 
     * @param tbFarm 农场(基础)信息
     * @return 结果
     */
	public int insertTbFarm(TbFarm tbFarm);
	
	/**
     * 修改农场(基础)
     * 
     * @param tbFarm 农场(基础)信息
     * @return 结果
     */
	public int updateTbFarm(TbFarm tbFarm);
	
	/**
     * 删除农场(基础)
     * 
     * @param farmId 农场(基础)ID
     * @return 结果
     */
	public int deleteTbFarmById(String farmId);
	
	/**
     * 批量删除农场(基础)
     * 
     * @param farmIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbFarmByIds(String[] farmIds);



	/**
	 * 查询所有商户创建的农场
	 * @param phone
	 * @return
	 */
	List<FarmUserRelationVo> selectMerchantAllFarmList(String phone);

	/**
	 * 查询所有用户加入的农场
	 * @param phone
	 * @return
	 */
	List<FarmUserRelationVo> selectUserAllFarmList(String phone);
	/**
	 * 根据农场ID查询农场所在地
	 * @param farmId
	 * @return
	 */
	public HashMap<String,Object> selectTbFarmByCity(String farmId);
}