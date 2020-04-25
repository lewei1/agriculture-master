package org.zcy.agriculture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbSaleTemplet;
import org.zcy.agriculture.entity.TbSaleTempletInfo;
import org.zcy.agriculture.mapper.TbSaleTempletInfoMapper;
import org.zcy.agriculture.mapper.TbSaleTempletMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbSaleTempletService;
import org.zcy.agriculture.util.FileUploadAndDownloadUtils;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.vo.TbSaleTempletVo;

/**
 * 销售管理-展示模板 服务层实现
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbSaleTempletServiceImpl implements ITbSaleTempletService {
	@Autowired
	private TbSaleTempletMapper tbSaleTempletMapper;
	@Autowired
	private TbSaleTempletInfoMapper tbSaleTempletInfoMapper;

	/**
	 * 查询销售管理-展示模板信息
	 * 
	 * @param saleTempletId 销售管理-展示模板ID
	 * @return 销售管理-展示模板信息
	 */
	@Override
	public TbSaleTemplet selectTbSaleTempletById(Long saleTempletId) {
		return tbSaleTempletMapper.selectTbSaleTempletById(saleTempletId);
	}

	/**
	 * 查询销售管理-展示模板列表
	 * 
	 * @param tbSaleTemplet 销售管理-展示模板信息
	 * @return 销售管理-展示模板集合
	 */
	@Override
	public List<TbSaleTemplet> selectTbSaleTempletList(TbSaleTemplet tbSaleTemplet) {
		return tbSaleTempletMapper.selectTbSaleTempletList(tbSaleTemplet);
	}

	/**
	 * 新增销售管理-展示模板
	 * 
	 * @param tbSaleTemplet 销售管理-展示模板信息
	 * @return 结果
	 */
	@Transactional
	public int insertTbSaleTemplet(TbSaleTempletVo tbSaleTempletVo) {
		int res = tbSaleTempletMapper.insertTbSaleTemplet(tbSaleTempletVo);
		for (TbSaleTempletInfo ti : tbSaleTempletVo.getTi()) {
			ti.setSaleTempletId(tbSaleTempletVo.getSaleTempletId());
		}
		tbSaleTempletInfoMapper.insertSaleTempletInfoBatch(tbSaleTempletVo.getTi());
		return res;
	}

	/**
	 * 修改销售管理-展示模板
	 * 
	 * @param tbSaleTemplet 销售管理-展示模板信息
	 * @return 结果
	 */
	@Transactional
	public int updateTbSaleTemplet(TbSaleTempletVo tbSaleTempletVo,String imgPath) {
		TbSaleTempletInfo tbSaleTempletInfo = new TbSaleTempletInfo();
		tbSaleTempletInfo.setSaleTempletId(tbSaleTempletVo.getSaleTempletId());
		List<TbSaleTempletInfo> tiList = tbSaleTempletInfoMapper.selectTbSaleTempletInfoList(tbSaleTempletInfo);
		// 修改销售 展示模板
		int res = tbSaleTempletMapper.updateTbSaleTemplet(tbSaleTempletVo);
		// 删除销售 展示模板详情
		tbSaleTempletInfoMapper.deleteTbSaleTempletInfoBySaleTempletId(tbSaleTempletVo.getSaleTempletId());
		String imgStr = "";
		for (TbSaleTempletInfo ti : tbSaleTempletVo.getTi()) {
			imgStr += "," + ti.getImgUrl();
			ti.setSaleTempletId(tbSaleTempletVo.getSaleTempletId());
		}
		// 重新插入销售 展示模板详情
		tbSaleTempletInfoMapper.insertSaleTempletInfoBatch(tbSaleTempletVo.getTi());

		// 当数据处理成功之后，删除图片；
		for (TbSaleTempletInfo ti : tiList) {
			if (StringUtils.isNotEmpty(ti.getImgUrl())) {
				String img[] = ti.getImgUrl().split(";");
				for (String i : img) {
					// 判断当前图片是否修改，如果已经修改则删除
					if (!imgStr.contains(i)) {
						FileUploadAndDownloadUtils.deletedFile(imgPath.substring(0, imgPath.length() - 1) + i);
					}
				}
			}
		}
		return res;
	}

	/**
	 * 删除销售管理-展示模板对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteTbSaleTempletByIds(String ids) {
		return tbSaleTempletMapper.deleteTbSaleTempletByIds(Convert.toStrArray(ids));
	}

	/**
	 * 销售管理-展示模板列表
	 * 
	 * @param tbSaleTempletVo
	 * @return
	 */
	public List<TbSaleTempletVo> selectTbMbList(TbSaleTempletVo tbSaleTempletVo) {
		return tbSaleTempletMapper.selectTbMbList(tbSaleTempletVo);
	}
	/**
	 * 批量删除 逻辑删除
	 * 
	 * @param list
	 * @return
	 */
	public int updateTbSaleTempletBatch(List<Long> list) {
		return tbSaleTempletMapper.updateTbSaleTempletBatch(list);
	}
	/**
	 * 根据农场和名称判断是否重复
	 * 
	 * @param farmId
	 * @param saleTempletId
	 * @param farmName
	 * @return
	 */
	public int selectByFarmNameRepeat(String farmId, Long saleTempletId, String farmName) {
		return tbSaleTempletMapper.selectByFarmNameRepeat(farmId, saleTempletId, farmName);
	}
}
