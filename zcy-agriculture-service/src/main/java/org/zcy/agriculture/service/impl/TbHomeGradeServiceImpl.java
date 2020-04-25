package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbHomeGrade;
import org.zcy.agriculture.mapper.TbHomeGradeMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbHomeGradeService;
import org.zcy.agriculture.vo.TbHomeGradeVo;

/**
 * 首页模块排列 服务层实现
 * 
 * @author numberone
 * @date 2019-07-11
 */
@Service
public class TbHomeGradeServiceImpl implements ITbHomeGradeService {
	@Autowired
	private TbHomeGradeMapper tbHomeGradeMapper;

	/**
	 * 查询首页模块排列信息
	 * 
	 * @param id 首页模块排列ID
	 * @return 首页模块排列信息
	 */
	@Override
	public TbHomeGrade selectTbHomeGradeById(Long id) {
		return tbHomeGradeMapper.selectTbHomeGradeById(id);
	}

	/**
	 * 查询首页模块排列列表
	 * 
	 * @param tbHomeGrade 首页模块排列信息
	 * @return 首页模块排列集合
	 */
	@Override
	public List<TbHomeGrade> selectTbHomeGradeList(TbHomeGrade tbHomeGrade) {
		return tbHomeGradeMapper.selectTbHomeGradeList(tbHomeGrade);
	}

	/**
	 * 新增首页模块排列
	 * 
	 * @param tbHomeGrade 首页模块排列信息
	 * @return 结果
	 */
	@Override
	public int insertTbHomeGrade(TbHomeGrade tbHomeGrade) {
		return tbHomeGradeMapper.insertTbHomeGrade(tbHomeGrade);
	}

	/**
	 * 修改首页模块排列
	 * 
	 * @param tbHomeGrade 首页模块排列信息
	 * @return 结果
	 */
	@Override
	public int updateTbHomeGrade(TbHomeGrade tbHomeGrade) {
		return tbHomeGradeMapper.updateTbHomeGrade(tbHomeGrade);
	}

	/**
	 * 删除首页模块排列对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteTbHomeGradeByIds(String ids) {
		return tbHomeGradeMapper.deleteTbHomeGradeByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据农场ID 获取首页模块集合
	 * 
	 * @param farmId
	 * @return
	 */
	public List<TbHomeGradeVo> selectByHomeIndexList(String farmId, Integer status) {
		return tbHomeGradeMapper.selectByHomeIndexList(farmId, status);
	}

	/**
	 * 保存 配置首页展示面板
	 * 
	 * @param homeList
	 */
	@Transactional
	public void saveHome(List<TbHomeGradeVo> homeList) {
		tbHomeGradeMapper.deleteTbHomeGradeByFarmId(homeList.get(0).getFarmId());
		tbHomeGradeMapper.insertByHomeBatch(homeList);
	}
}
