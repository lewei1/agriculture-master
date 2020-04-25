package org.zcy.agriculture.merchant.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.entity.TbSaleTemplet;
import org.zcy.agriculture.entity.TbSaleTempletInfo;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbCameraImageService;
import org.zcy.agriculture.service.ITbFarmingPlanService;
import org.zcy.agriculture.service.ITbResDeviceService;
import org.zcy.agriculture.service.ITbSaleTempletInfoService;
import org.zcy.agriculture.service.ITbSaleTempletService;
import org.zcy.agriculture.service.plot.ITbPlotService;
import org.zcy.agriculture.util.DateUtils;
import org.zcy.agriculture.util.FileUploadAndDownloadUtils;
import org.zcy.agriculture.util.QrCodeCreateUtil;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.util.UUIDUtils;
import org.zcy.agriculture.vo.TbSaleTempletVo;

import com.google.common.collect.Maps;

/**
 * 销售管理-展示模板 信息操作处理
 * 
 * @author numberone
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/api/system/tbSaleTemplet")
public class TbSaleTempletController extends BaseController {
	@Value("${file.img}")
	private String imgPath;
	@Value("${file.qrCode-path}")
	private String qrCodePath;// 二维码存放路径
	@Value("${h5path}")
	private String h5path;// H5分享地址

	@Autowired
	private ITbSaleTempletService tbSaleTempletService;
	@Autowired
	private ITbSaleTempletInfoService tbSaleTempletInfoService;
	@Autowired
	private ITbResDeviceService tbResDeviceService;
	@Autowired
	private ITbFarmingPlanService tbFarmingPlanService;
	@Autowired
	private ITbPlotService tbPlotService;
	@Autowired
	private ITbCameraImageService tbCameraImageService;

	/**
	 * 查询销售管理-展示模板列表
	 */
	@GetMapping("/list")
	@ResponseBody
	public TableDataInfo list(TbSaleTempletVo tbSaleTempletVo, HttpServletRequest req) {
		tbSaleTempletVo.setFarmId(getFarmUUID());
		startPage();
		List<TbSaleTempletVo> list = tbSaleTempletService.selectTbMbList(tbSaleTempletVo);
		list.forEach(st -> {
			if (StringUtils.isNotEmpty(st.getImgUrl())) {
				st.setImgUrl(st.getImgUrl().split(",")[0]);
				st.setAccessAddress(h5path + st.getSaleTempletId());
			}
		});
		return getDataTable(list);
	}

	/**
	 * 新增保存销售管理-展示模板
	 */
	@PostMapping("/saveSaleTemplet")
	@ResponseBody
	public AjaxResult saveSaleTemplet(@RequestBody TbSaleTempletVo tbSaleTempletVo, HttpServletRequest req) {
		// 验证参数
		if (tbSaleTempletVo == null || tbSaleTempletVo.getTi() == null || tbSaleTempletVo.getTi().size() == 0) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		tbSaleTempletVo.setFarmId(getFarmUUID());
		tbSaleTempletVo.setUpdateBy(getFarmUserCode());
		tbSaleTempletVo.setUpdateDate(new Date());
		tbSaleTempletVo.setCreateBy(getFarmUserCode());

		try {
			if (tbSaleTempletVo.getSaleTempletId() == null) {
				if (StringUtils.isEmpty(tbSaleTempletVo.getFarmName())) {
					return error("农场名称不能为空！");
				} else if (StringUtils.isEmpty(tbSaleTempletVo.getCorporateName())) {
					return error("企业名称不能为空！");
				} else if (StringUtils.isEmpty(tbSaleTempletVo.getCorporateAddress())) {
					return error("企业地址不能为空！");
				} else if (tbSaleTempletService.selectByFarmNameRepeat(getFarmUUID(), tbSaleTempletVo.getSaleTempletId(), tbSaleTempletVo.getFarmName()) > 0) {
					return error("农场名称不能重复！");
				}
				// 创建二维码路径
				tbSaleTempletVo.setQrCodeUrl(DateUtils.dateTimeNow("yyyyMM") + "/" + UUIDUtils.getId(5) + ".jpg");
				// 插入展示模板
				tbSaleTempletService.insertTbSaleTemplet(tbSaleTempletVo);
				// 当插入展示模板成功之后 创建二维码
				QrCodeCreateUtil.createQrCode(qrCodePath + tbSaleTempletVo.getQrCodeUrl(), h5path + tbSaleTempletVo.getSaleTempletId());
				return success((Object) tbSaleTempletVo.getQrCodeUrl());
			} else {
				TbSaleTemplet st = tbSaleTempletService.selectTbSaleTempletById(tbSaleTempletVo.getSaleTempletId());
				if (st == null || !getFarmUUID().equals(st.getFarmId())) {
					return error("模板saleTempletId有误，不存在或者不属于当前农场！");
				}
				if (tbSaleTempletService.selectByFarmNameRepeat(getFarmUUID(), tbSaleTempletVo.getSaleTempletId(), tbSaleTempletVo.getFarmName()) > 0) {
					return error("农场名称不能重复！");
				}
				tbSaleTempletService.updateTbSaleTemplet(tbSaleTempletVo, imgPath);
				return success((Object) st.getQrCodeUrl());
			}
		} catch (Exception e) {
			logger.error("新增保存销售管理-展示模板异常！", e);
			return error();
		}
	}

	/**
	 * 查询模板详情
	 */
	@GetMapping("/getInfo")
	@ResponseBody
	public AjaxResult getInfo(Long saleTempletId) {
		// 验证参数
		if (saleTempletId == null) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		HashMap<String, Object> m = Maps.newHashMap();
		HashMap<String, List<TbSaleTempletInfo>> l = Maps.newHashMap();
		try {
			TbSaleTemplet st = tbSaleTempletService.selectTbSaleTempletById(saleTempletId);
			TbSaleTempletInfo tbSaleTempletInfo = new TbSaleTempletInfo();
			tbSaleTempletInfo.setSaleTempletId(saleTempletId);
			List<TbSaleTempletInfo> stiList = tbSaleTempletInfoService.selectTbSaleTempletInfoList(tbSaleTempletInfo);
			if (stiList != null && stiList.size() > 0) {
				int k = -1;
				List<TbSaleTempletInfo> list = null;
				for (TbSaleTempletInfo sti : stiList) {
					if (sti.getType() != k) {
						list = Lists.newArrayList();
						k = sti.getType();
						l.put(sti.getType() + "", list);
					}
					list.add(sti);
				}
			}

			m.put("saleTemplet", st);
			m.put("saleTempletInfo", l);
			return success(m);
		} catch (Exception e) {
			logger.error("查询模板详情异常！", e);
			return error();
		}
	}

	/**
	 * 删除 销售管理-展示模板
	 */
	@PostMapping("/deleteSaleTemplet")
	@ResponseBody
	public AjaxResult deleteSaleTemplet(@RequestBody List<Long> list) {
		// 验证参数
		if (list == null || list.size() == 0) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		try {
			tbSaleTempletService.updateTbSaleTempletBatch(list);
			return success();
		} catch (Exception e) {
			logger.error("新增保存销售管理-展示模板异常！", e);
			return error();
		}
	}

	/**
	 * H5展示页
	 */
	@GetMapping("/getTempletInfo")
	@ResponseBody
	public AjaxResult getTempletInfo(Long saleTempletId) {
		// 验证参数
		if (saleTempletId == null) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		HashMap<String, Object> m = Maps.newHashMap();
		HashMap<String, List<TbSaleTempletInfo>> l = Maps.newHashMap();

		try {
			TbSaleTemplet st = tbSaleTempletService.selectTbSaleTempletById(saleTempletId);
			if (st == null || st.getStatus()==NormalOrDeleteEnum.CLOSE.getCode()) {
				return error("信息不存在");
			}
			TbSaleTempletInfo tbSaleTempletInfo = new TbSaleTempletInfo();
			tbSaleTempletInfo.setSaleTempletId(saleTempletId);
			List<TbSaleTempletInfo> stiList = tbSaleTempletInfoService.selectTbSaleTempletInfoList(tbSaleTempletInfo);
			if (stiList != null && stiList.size() > 0) {
				int k = -1;
				List<TbSaleTempletInfo> list = null;
				for (TbSaleTempletInfo sti : stiList) {
					if (sti.getType() != k) {
						k = sti.getType();
						list = Lists.newArrayList();
						l.put(sti.getType() + "", list);
					}
					list.add(sti);
				}
			}
			// 是否选中实时数据（0未选中，1已选中）
			if (st.getIsRealData() == 1) {
				TbResDevice r = new TbResDevice();
				r.setFarmId(st.getFarmId());
				r.setDevType(0);// 监测设备
				List<TbResDevice> rList = tbResDeviceService.selectTbResDeviceList(r);
				m.put("detectionDeviceList", rList);// 监控 实时数据
			}
			// 是否选中气候条件（0未选中，1已选中）
//			if (st.getIsClimate() == 1) {
//				List<HashMap<String, Object>> s1 = Lists.newArrayList();
//				List<HashMap<String, Object>> s2 = Lists.newArrayList();
//				HashMap<String, Object> climate = Maps.newHashMap();
//				for (int i = 1; i <= 12; i++) {
//					HashMap<String, Object> mm = Maps.newHashMap();
//					mm.put("month", i);
//					mm.put("value", 0);
//					s1.add(mm);
//					s2.add(mm);
//				}
//				climate.put("rainfallList", s1);
//				climate.put("sunshineList", s2);
//				climate.put("rainfallAvg", 0);
//				climate.put("sunshineAvg", 0);
//				m.put("climate", climate);// 气候条件
//			}
			// 是否选中农业活动（0未选中，1已选中）
			if (st.getIsActivity() == 1) {
				m.put("messageList", tbFarmingPlanService.selectByH5Task(st.getFarmId()));// 农事记录 信息
			}

			// 是否选中实时图像（0未选中，1已选中）
			if (st.getIsRealPicture() == 1) {
				m.put("realTimeImage", tbCameraImageService.selectByH5Tp(st.getFarmId()));// 实时图像
			}
			// 是否选中地图（0未选中，1已选中）
			if (st.getIsMap() == 1) {
				m.put("mapList", tbPlotService.selectPlotCoordinateByFarmId(st.getFarmId()));// 实时地图
			}

			m.put("saleTemplet", st);// 模板信息
			m.put("saleTempletInfo", l);// 模板详情

			return success(m);
		} catch (Exception e) {
			logger.error("H5展示页异常！", e);
			return error();
		}
	}

	/**
	 * 下载二维码
	 */
	@GetMapping("/downloadQrCode")
	@ResponseBody
	public void downloadQrCode(String qrCodeUrl, Long saleTempletId, HttpServletRequest req, HttpServletResponse response) {
		// 验证参数
		if (StringUtils.isNotEmpty(qrCodeUrl) && saleTempletId != null) {
			try {
				int res = FileUploadAndDownloadUtils.downloadFile(req, response, qrCodePath + qrCodeUrl);
				if (res == -1) {
					File file = new File(qrCodePath + qrCodeUrl);
					FileInputStream fi = null;
					if (!file.exists()) {
						// 下载失败后重新 创建二维码
						QrCodeCreateUtil.createQrCode(qrCodePath + qrCodeUrl, h5path + saleTempletId);
					} else {
						fi = new FileInputStream(file);
						BufferedImage sourceImg = ImageIO.read(fi);
						// 判断图片是否损坏
						if (sourceImg == null) {
							// 下载失败后重新 创建二维码
							QrCodeCreateUtil.createQrCode(qrCodePath + qrCodeUrl, h5path + saleTempletId);
						}
					}
				}
			} catch (Exception e) {
				logger.error("下载二维码常！", e);
			}
		}
	}

}
