package org.zcy.agriculture.service;

import java.util.List;

import org.zcy.agriculture.entity.TbHomeGrade;
import org.zcy.agriculture.vo.TbHomeGradeVo;

/**
 * 首页模块排列 服务层
 * 
 * @author numberone
 * @date 2019-07-11
 */
public interface ITbHomeGradeService {
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
	 * 删除首页模块排列信息
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbHomeGradeByIds(String ids);

	/**
	 * 根据农场ID 获取首页模块集合
	 * 
	 * @param farmId
	 * @return
	 */
	public List<TbHomeGradeVo> selectByHomeIndexList(String farmId,Integer status);
	/**
	 * 保存 配置首页展示面板
	 * 
	 * @param homeList
	 */
	public void saveHome(List<TbHomeGradeVo> homeList);
}
