package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbCropType;
import org.zcy.agriculture.mapper.TbCropTypeMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbCropTypeService;

/**
 * 农作物品种 服务层实现
 * 
 * @author numberone
 * @date 2019-06-26
 */
@Service
public class TbCropTypeServiceImpl implements ITbCropTypeService 
{
	@Autowired
	private TbCropTypeMapper tbCropTypeMapper;

	/**
     * 查询农作物品种信息
     * 
     * @param cropTypeId 农作物品种ID
     * @return 农作物品种信息
     */
    @Override
	public TbCropType selectTbCropTypeById(Long cropTypeId)
	{
	    return tbCropTypeMapper.selectTbCropTypeById(cropTypeId);
	}
	
	/**
     * 查询农作物品种列表
     * 
     * @param tbCropType 农作物品种信息
     * @return 农作物品种集合
     */
	@Override
	public List<TbCropType> selectTbCropTypeList(TbCropType tbCropType)
	{
	    return tbCropTypeMapper.selectTbCropTypeList(tbCropType);
	}
	
    /**
     * 新增农作物品种
     * 
     * @param tbCropType 农作物品种信息
     * @return 结果
     */
	@Override
	public int insertTbCropType(TbCropType tbCropType)
	{
	    return tbCropTypeMapper.insertTbCropType(tbCropType);
	}
	
	/**
     * 修改农作物品种
     * 
     * @param tbCropType 农作物品种信息
     * @return 结果
     */
	@Override
	public int updateTbCropType(TbCropType tbCropType)
	{
	    return tbCropTypeMapper.updateTbCropType(tbCropType);
	}

	/**
     * 删除农作物品种对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbCropTypeByIds(String ids)
	{
		return tbCropTypeMapper.deleteTbCropTypeByIds(Convert.toStrArray(ids));
	}
	/**
	 * 根据农场判断名称是否重复
	 * 
	 * @param farmId
	 * @param cropName
	 * @param cropCategoryId
	 * @param cropTypeId
	 * @return
	 */
	public int selectByCropNameRepeat(String farmId, String cropName, Long cropCategoryId, Long cropTypeId) {
		return tbCropTypeMapper.selectByCropNameRepeat(farmId, cropName, cropCategoryId, cropTypeId);
	}
}
