package org.zcy.agriculture.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.SysDictData;
import org.zcy.agriculture.entity.TbHarvest;
import org.zcy.agriculture.entity.TbHarvestImg;
import org.zcy.agriculture.mapper.TbHarvestImgMapper;
import org.zcy.agriculture.mapper.TbHarvestMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbHarvestService;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.FileUploadAndDownloadUtils;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.vo.TbHarvestExportVo;
import org.zcy.agriculture.vo.TbHarvestVo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 作物采收 服务层实现
 * 
 * @author numberone
 * @date 2019-06-27
 */
@Service
public class TbHarvestServiceImpl extends BaseServiceImpl implements ITbHarvestService {
	@Autowired
	private TbHarvestMapper tbHarvestMapper;
	@Autowired
	private TbHarvestImgMapper tbHarvestImgMapper;

	/**
	 * 查询作物采收信息
	 * 
	 * @param harvestId 作物采收ID
	 * @return 作物采收信息
	 */
	@Override
	public TbHarvest selectTbHarvestById(Long harvestId) {
		return tbHarvestMapper.selectTbHarvestById(harvestId);
	}

	/**
	 * 查询作物采收列表
	 * 
	 * @param tbHarvest 作物采收信息
	 * @return 作物采收集合
	 */
	@Override
	public List<TbHarvest> selectTbHarvestList(TbHarvest tbHarvest) {
		return tbHarvestMapper.selectTbHarvestList(tbHarvest);
	}

	/**
	 * 新增作物采收
	 * 
	 * @param tbHarvest 作物采收信息
	 * @return 结果
	 */
	@Transactional
	public int insertTbHarvest(TbHarvestVo tbHarvest) {
		int res = tbHarvestMapper.insertTbHarvest(tbHarvest);
		String imgList[] = tbHarvest.getImgUrl().split(";");
		List<TbHarvestImg> t = new ArrayList<>();
		for (String i : imgList) {
			TbHarvestImg tbHarvestImg = new TbHarvestImg();
			tbHarvestImg.setImgUrl(i);
			tbHarvestImg.setHarvestId(tbHarvest.getHarvestId());
			t.add(tbHarvestImg);
		}
		tbHarvestImgMapper.insertHarvestImgBatch(t);
		return res;
	}

	/**
	 * 修改作物采收
	 * 
	 * @param tbHarvest 作物采收信息
	 * @return 结果
	 */
	@Transactional
	public int updateTbHarvest(TbHarvestVo tbHarvest, String imgPath) {
		TbHarvestImg hi = new TbHarvestImg();
		hi.setHarvestId(tbHarvest.getHarvestId());
		// 查询采收图片列表
		List<TbHarvestImg> hiList = tbHarvestImgMapper.selectTbHarvestImgList(hi);

		// 删除采收图片
		tbHarvestImgMapper.deleteTbHarvestImgByHarvestId(tbHarvest.getHarvestId());
		String imgList[] = tbHarvest.getImgUrl().split(";");
		List<TbHarvestImg> t = new ArrayList<>();
		for (String i : imgList) {
			TbHarvestImg tbHarvestImg = new TbHarvestImg();
			tbHarvestImg.setImgUrl(i);
			tbHarvestImg.setHarvestId(tbHarvest.getHarvestId());
			t.add(tbHarvestImg);
		}
		// 重新插入
		tbHarvestImgMapper.insertHarvestImgBatch(t);
		// 更新采收表
		int res = tbHarvestMapper.updateTbHarvest(tbHarvest);

		// 当数据处理成功之后，删除图片；
		for (TbHarvestImg ti : hiList) {
			if (StringUtils.isNotEmpty(ti.getImgUrl())) {
				String img[] = ti.getImgUrl().split(";");
				for (String i : img) {
					// 判断当前图片是否修改，如果已经修改则删除
					if (!tbHarvest.getImgUrl().contains(i)) {
						FileUploadAndDownloadUtils.deletedFile(imgPath.substring(0, imgPath.length() - 1) + i);
					}
				}
			}
		}
		return res;
	}

	/**
	 * 删除作物采收对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteTbHarvestByIds(String ids) {
		return tbHarvestMapper.deleteTbHarvestByIds(Convert.toStrArray(ids));
	}

	/**
	 * 汇总式采收列表
	 * 
	 * @param hVo
	 * @return
	 */
	public List<TbHarvestVo> selectByCshz(TbHarvestVo hVo) {
		return tbHarvestMapper.selectByCshz(hVo);
	}

	/**
	 * 汇总式采收列表查询总条数
	 * 
	 * @param hVo
	 * @return
	 */
	public int selectByCshzListCou(TbHarvestVo hVo) {
		return tbHarvestMapper.selectByCshzListCou(hVo);
	}

	/**
	 * 采收记录
	 * 
	 * @param hVo
	 * @return
	 */
	public List<TbHarvestVo> selectByCsjlList(TbHarvestVo hVo) {
		return tbHarvestMapper.selectByCsjlList(hVo);
	}

	/**
	 * 批量删除作物采收记录
	 * 
	 * @param harvestList
	 * @return
	 */
	public int updateTbHarvestBatch(List<TbHarvestVo> harvestList) {
		return tbHarvestMapper.updateTbHarvestBatch(harvestList);
	}

	/**
	 * 采收统计列表
	 * 
	 * @param hVo
	 * @return
	 */
	public List<TbHarvestExportVo> selectByStatistics(TbHarvestVo hVo) {
		List<TbHarvestExportVo> voList = tbHarvestMapper.selectByStatistics(hVo);
		if (voList != null && voList.size() > 0) {
			for (TbHarvestExportVo vo : voList) {
				if (vo.getSubPlotName() != null && vo.getSubPlotName().indexOf(",") >= 0) {
					int r = vo.getSubPlotName().split(",").length;
					if(r >1) {
						vo.setNickName(vo.getSubPlotName().split(",")[1]);
					}
					if(r>0) {
						vo.setSubPlotName(vo.getSubPlotName().split(",")[0]);
					}
				}
			}
		}
		return voList;
	}

	/**
	 * 采收统计列表总条数
	 * 
	 * @param hVo
	 * @return
	 */
	public int selectByStatisticsCou(TbHarvestVo hVo) {
		return tbHarvestMapper.selectByStatisticsCou(hVo);
	}

	public List<SysDictData> getLevelList(String dictName) {
		return getDictList(dictName);
	}

	/**
	 * 今日采收 汇总
	 * 
	 * @param toDay
	 * @param plotId
	 * @return
	 */
	public HashMap<String, Object> selectByStatisticsToDay(Long plotId) {
		List<HashMap<String, Object>> list = Lists.newArrayList();
		HashMap<String, Object> m = Maps.newHashMap();
		HashMap<String, Object> m1 = null;
		int date = 30;
		// 获取当前日期
		Calendar ca = Calendar.getInstance();
		TbHarvestVo tbHarvestVo = new TbHarvestVo();
		tbHarvestVo.setPlotId(plotId);
		tbHarvestVo.setHarvestEnd(DateUtils.dateTime(ca.getTime()));
		ca.add(Calendar.DATE, -date + 1);
		tbHarvestVo.setHarvestStr(DateUtils.dateTime(ca.getTime()));
		// 查询今日汇总
		List<TbHarvestVo> hvList = tbHarvestMapper.selectByStatisticsToDay(tbHarvestVo);

		if (hvList != null && hvList.size() > 0) {
			for (int i = 0; i < date; i++) {
				m1 = Maps.newHashMap();
				String mm = DateUtils.parseDateToStr("MM-dd", ca.getTime());
				m1.put("dateStr", mm);
				m1.put("harvestAmount", 0);
				for (TbHarvestVo hv : hvList) {
					if (mm.equals(hv.getToHr())) {
						m1.put("harvestAmount", hv.getHarvestAmount());
					}
				}
				list.add(m1);
				ca.add(Calendar.DATE, 1);
			}
		}
		m.put("list", list);

		ca.add(Calendar.DATE, -1);
		tbHarvestVo.setHarvestStr(DateUtils.dateTime(ca.getTime()));
		tbHarvestVo.setHarvestEnd(tbHarvestVo.getHarvestStr());
		// 查询昨日汇总
		TbHarvestVo tv1 = tbHarvestMapper.selectByStatisticsYesterday(tbHarvestVo);
		Double total = tv1 == null || tv1.getHarvestAmount() == null || tv1.getHarvestAmount() == 0 ? 0 : tv1.getHarvestAmount();
		m.put("todayTotal", String.format("%.2f", total));
		// 查询昨日汇总
		ca.add(Calendar.DATE, -1);
		tbHarvestVo.setHarvestStr(DateUtils.dateTime(ca.getTime()));
		tbHarvestVo.setHarvestEnd(tbHarvestVo.getHarvestStr());
		TbHarvestVo tv2 = tbHarvestMapper.selectByStatisticsYesterday(tbHarvestVo);
		Double zuo = tv2 == null || tv2.getHarvestAmount() == null || tv2.getHarvestAmount() == 0 ? 0 : tv2.getHarvestAmount();
		m.put("proportion", (total > 0 && zuo == 0 ? "100" : (total == 0 && zuo == 0 ? "0" : String.format("%.2f", (total - zuo) * 100 / zuo))) + "%");
		return m;
	}

}
