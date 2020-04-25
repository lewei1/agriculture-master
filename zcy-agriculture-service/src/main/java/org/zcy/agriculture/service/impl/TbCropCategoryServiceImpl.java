package org.zcy.agriculture.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbCropCategory;
import org.zcy.agriculture.mapper.TbCropCategoryMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbCropCategoryService;

import java.util.List;

/**
 * 农作物种类 服务层实现
 * 
 * @author numberone
 * @date 2019-07-19
 */
@Service
public class TbCropCategoryServiceImpl implements ITbCropCategoryService {
    @Autowired
    private TbCropCategoryMapper tbCropCategoryMapper;

	/**
     * 查询农作物种类信息
     * 
     * @param cropCategoryId 农作物种类ID
     * @return 农作物种类信息
     */
    @Override
    public TbCropCategory selectTbCropCategoryById(Long cropCategoryId) {
        return tbCropCategoryMapper.selectTbCropCategoryById(cropCategoryId);
    }

    /**
     * 查询农作物种类列表
     * 
     * @param tbCropCategory 农作物种类信息
     * @return 农作物种类集合
     */
    @Override
    public List<TbCropCategory> selectTbCropCategoryList(TbCropCategory tbCropCategory) {
        return tbCropCategoryMapper.selectTbCropCategoryList(tbCropCategory);
    }

    /**
     * 新增农作物种类
     * 
     * @param tbCropCategory 农作物种类信息
     * @return 结果
     */
    @Override
    public int insertTbCropCategory(TbCropCategory tbCropCategory) {
        return tbCropCategoryMapper.insertTbCropCategory(tbCropCategory);
    }

    /**
     * 修改农作物种类
     *
     * @param tbCropCategory 农作物种类信息
     * @return 结果
     */
    @Override
    public int updateTbCropCategory(TbCropCategory tbCropCategory) {
        return tbCropCategoryMapper.updateTbCropCategory(tbCropCategory);
    }

	/**
     * 删除农作物种类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbCropCategoryByIds(String ids) {
        return tbCropCategoryMapper.deleteTbCropCategoryByIds(Convert.toStrArray(ids));
    }
    /**
	 * 根据农场判断是否重复
	 * 
	 * @param categoryName
	 * @param farmId
	 * @return
	 */
	public int selectByCategoryNameRepeat(String categoryName, String farmId,Long cropCategoryId) {
		return tbCropCategoryMapper.selectByCategoryNameRepeat(categoryName, farmId,cropCategoryId);
	}
}
