package org.zcy.agriculture.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.TbPlot;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.entity.TbSubPlot;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.plot.ITbPlotService;
import org.zcy.agriculture.service.ITbResDeviceService;
import org.zcy.agriculture.service.ITbSubPlotService;
import org.zcy.agriculture.service.ITbUserService;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.SubPlotDataInfoVo;
import java.util.Date;
import java.util.List;

/**
 * 子地块 信息操作处理
 * 
 * @author numberone
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/api/subPlot")
public class TbSubPlotController extends BaseController
{
    private String prefix = "api/subPlot";

	@Autowired
	private ITbResDeviceService tbResDeviceService;

	@Autowired
	private ITbSubPlotService tbSubPlotService;

	@Autowired
	private ITbPlotService tbPlotService;

	@Autowired
	private ITbUserService tbUserService;

	@GetMapping()
	public String tbSubPlot()
	{
	    return prefix + "/tbSubPlot";
	}
	
	/**
	 * 查询子地块列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody TbSubPlot subPlot)
	{
		/**传入地块ID不能为空*/
		if (ValidationUtil.isEmpty(subPlot.getPlotId())) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}

		startPage();
        List<TbSubPlot> list = tbSubPlotService.selectTbSubPlotList(subPlot);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出子地块列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbSubPlot tbSubPlot)
    {
    	List<TbSubPlot> list = tbSubPlotService.selectTbSubPlotList(tbSubPlot);
        ExcelUtil<TbSubPlot> util = new ExcelUtil<TbSubPlot>(TbSubPlot.class);
        return util.exportExcel(list, "tbSubPlot");
    }
	
	/**
	 * 新增子地块
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存子地块
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody TbSubPlot subPlot)
	{
		if (ValidationUtil.isEmpty(subPlot.getPlotId())) {
			return error("地块ID"+ ValidationConstants.SUFFIX_NOT_EMPTY);
		}
		/**下面这段代码是检测传入的地块ID在数据库表tb_plot中是否存在，如果不存在则返回错误*/
		TbPlot tbPlot = new TbPlot();
		tbPlot.setPlotId(subPlot.getPlotId());
		List<TbPlot> list = tbPlotService.selectTbPlotList(tbPlot);
		if (list.size() <= 0) {
			return error("地块ID"+  ValidationConstants.SUFFIX_NOT_EXIST_PARAMS);
		}

		/**以下部门是判断传入的子地块名是否合法*/
		if (ValidationUtil.isEmpty(subPlot.getSubPlotName())) {
			return error("子地块名"+ ValidationConstants.SUFFIX_NOT_EMPTY);
		}
		{
			TbSubPlot subTemp = new TbSubPlot();
			subTemp.setSubPlotName(subPlot.getSubPlotName());
			subTemp.setPlotId(subPlot.getPlotId());
			List<TbSubPlot> listTemp = tbSubPlotService.selectTbSubPlotList(subTemp);
			if (listTemp != null && listTemp.size() >0) {
				return error("已存在同名的子地块，请换一个名字吧！");
			}
		}

		if (ValidationUtil.isEmpty(subPlot.getSubPlotPerson())) {
			return error("子地块负责人"+ ValidationConstants.SUFFIX_NOT_EMPTY);
		}
		if (tbUserService.selectVbUserByCode(subPlot.getSubPlotPerson(),getFarmUUID()) == null) {
			return error("子地块负责人"+ ValidationConstants.SUFFIX_NOT_EXIST_PARAMS);
		}

		Double subPlotAcreage = subPlot.getSubPlotAcreage();
		if (ValidationUtil.isEmpty(subPlotAcreage)) { //子地块面积
			return error("子地块面积"+ ValidationConstants.SUFFIX_NOT_EMPTY);
		} else if (subPlotAcreage <=0) {
			return error("子地块面积"+ ValidationConstants.SUFFIX_ILLEGAL_PARAMS);
		}

		subPlot.setCreateBy(getFarmUserCode()); //设置好子地块创建者
		subPlot.setCreateTime(new Date());    //设置好子地块创建时间
		return toAjax(tbSubPlotService.insertTbSubPlot(subPlot));
	}

	/**
	 * 修改子地块
	 */
	@GetMapping("/edit/{subPlotId}")
	public String edit(@PathVariable("subPlotId") Long subPlotId, ModelMap mmap)
	{
		TbSubPlot tbSubPlot = tbSubPlotService.selectTbSubPlotById(subPlotId);
		mmap.put("tbSubPlot", tbSubPlot);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存子地块
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(@RequestBody TbSubPlot subPlot)
	{
		if (ValidationUtil.isEmpty(subPlot.getSubPlotId())) {
			return error("子地块ID"+ ValidationConstants.SUFFIX_NOT_EMPTY);
		}
		TbSubPlot subTemp = tbSubPlotService.selectTbSubPlotById(subPlot.getSubPlotId());
		if (subTemp == null){
			return error("子地块ID"+ ValidationConstants.SUFFIX_NOT_EXIST_PARAMS);
		}

		/**当传入的子地块名不为空时候，判断传入的子地块名是否合法*/
		if (!ValidationUtil.isEmpty(subPlot.getSubPlotName())) {
			TbSubPlot subTemp2 = new TbSubPlot();
			subTemp2.setPlotId(subTemp.getPlotId());
			subTemp2.setSubPlotName(subPlot.getSubPlotName());
			List<TbSubPlot> listTemp = tbSubPlotService.selectTbSubPlotList(subTemp2);
			if (listTemp != null && listTemp.size() >0 &&listTemp.get(0).getSubPlotId().longValue() != subPlot.getSubPlotId().longValue()) {
				return error("已存在同名的子地块，请换一个名字吧！");
			}
		}

		if (!ValidationUtil.isEmpty(subPlot.getSubPlotPerson()) && tbUserService.selectVbUserByCode(subPlot.getSubPlotPerson(),getFarmUUID()) == null) {
			return error("子地块负责人"+ ValidationConstants.SUFFIX_NOT_EXIST_PARAMS);
		}

		/**子地块面积如果有传入就不能小于等于0*/
		Double subPlotAcreage = subPlot.getSubPlotAcreage();
		if (!ValidationUtil.isEmpty(subPlotAcreage) && subPlotAcreage <= 0 ) { //子地块面积
			return error("子地块面积"+ ValidationConstants.SUFFIX_ILLEGAL_PARAMS);
		}

		return toAjax(tbSubPlotService.updateTbSubPlot(subPlot));
	}
	
//	/**
//	 * 删除子地块
//	 */
//	@PostMapping( "/remove")
//	@ResponseBody
//	public AjaxResult remove(@RequestBody String ids)
//	{
//		return toAjax(tbSubPlotService.deleteTbSubPlotByIds(ids));
//	}

	/**
	 * 删除子地块
	 */
	@GetMapping("/remove")
	@ResponseBody
	public AjaxResult remove(Long id)
	{
		return toAjax(tbSubPlotService.deleteTbSubPlotById(id));
	}

	/**
	 * 地块信息返回
	 */
	@PostMapping("/data/info")
	@ResponseBody
	public AjaxResult getSubPlotData(@RequestBody TbSubPlot subPlot)
	{
		/**传入地块ID不能为空*/
		if (ValidationUtil.isEmpty(subPlot.getPlotId())) {
			return error("查询的地块ID不能为空");
		}

		SubPlotDataInfoVo vo = new SubPlotDataInfoVo();
		/**子地块数量*/
		vo.setSubPlotNum(tbSubPlotService.getSubPlotNumByPlotId(subPlot.getPlotId()));
		/**管理人员数量*/
		vo.setManagersNum(tbSubPlotService.getSubPlotPersonNumByPlotId(subPlot.getPlotId()));

		/**获取地块下监测设备数*/
		TbResDevice device = new TbResDevice();
		device.setPlotId(subPlot.getPlotId()); //地块ID
		device.setFarmId(getFarmUUID()); //农场ID
		device.setDevType(0); //设备类型设置为监测设备类型
		vo.setMonNum(tbResDeviceService.getCountFromTbResDevice(device));
		return success(vo);
	}

	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception
	{
		ExcelUtil<TbSubPlot> util = new ExcelUtil<TbSubPlot>(TbSubPlot.class);
		List<TbSubPlot> userList = util.importExcel(file.getInputStream());
		Long createUserId = getFarmUserCode(); //设置好子地块创建者ID
		String message = tbSubPlotService.importSubPlot(userList, createUserId);
		return AjaxResult.success(message);
	}
}
