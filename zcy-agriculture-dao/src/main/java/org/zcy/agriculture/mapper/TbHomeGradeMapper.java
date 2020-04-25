package org.zcy.agriculture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbHomeGrade;
import org.zcy.agriculture.vo.TbHomeGradeVo;

/**
 * 首页模块排列 数据层
 * 
 * @author numberone
 * @date 2019-07-11
 */
public interface TbHomeGradeMapper {
	/**
	 * 查询首页模块排列信息
	 * 
	 * @param id 首页模块排列ID
	 * @return 首页模块排列信息
	 */
	public TbHomeGrade selectTbHomeGradeById(Long id);

	/**
	 * 查询首页模块排列列表
	 * 
	 * @param tbHomeGrade 首页模块排列信息
	 * @return 首页模块排列集合
	 */
	public List<TbHomeGrade> selectTbHomeGradeList(TbHomeGrade tbHomeGrade);

	/**
	 * 新增首页模块排列
	 * 
	 * @param tbHomeGrade 首页模块排列信息
	 * @return 结果
	 */
	public int insertTbHomeGrade(TbHomeGrade tbHomeGrade);

	/**
	 * 修改首页模块排列
	 * 
	 * @param tbHomeGrade 首页模块排列信息
	 * @return 结果
	 */
	public int updateTbHomeGrade(TbHomeGrade tbHomeGrade);

	/**
	 * 删除首页模块排列
	 * 
	 * @param id 首页模块排列ID
	 * @return 结果
	 */
	public int deleteTbHomeGradeById(Long id);

	/**
	 * 批量删除首页模块排列
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbHomeGradeByIds(String[] ids);

	/**
	 * 根据农场ID 获取首页模块集合
	 * 
	 * @param farmId
	 * @return
	 */
	public List<TbHomeGradeVo> selectByHomeIndexList(String farmId, Integer status);

	/**
	 * 批量插入数据
	 * 
	 * @param homeList
	 * @return
	 */
	public int insertByHomeBatch(@Param("homeList") List<TbHomeGradeVo> homeList);

	/**
	 * 根据 farmId 删除首页模块
	 * 
	 * @param farmId
	 * @return
	 */
	public int deleteTbHomeGradeByFarmId(String farmId);

}