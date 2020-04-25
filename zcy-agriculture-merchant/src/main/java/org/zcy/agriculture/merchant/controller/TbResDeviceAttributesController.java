package org.zcy.agriculture.merchant.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbResDeviceAttributesService;
import org.zcy.agriculture.vo.TbResDeviceAttributesVo;

/**
 * 设备属性（每个设备包含的属性记录） 信息操作处理
 * 
 * @author zh
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/deviceAttributes")
public class TbResDeviceAttributesController extends BaseController
{
    private String prefix = "/api/deviceAttributes";
	
	@Autowired
	private ITbResDeviceAttributesService tbResDeviceAttributesService;

	@GetMapping()
	public String tbResDeviceAttributes()
	{
	    return prefix + "/deviceAttributes";
	}
	
	/**
	 * 查询设备属性（每个设备包含的属性记录）列表
	 */
	@GetMapping("/list")
	@ResponseBody
	public TableDataInfo list(TbResDeviceAttributesVo tbResDeviceAttributes)
	{
		startPage();
        List<TbResDeviceAttributes> list = tbResDeviceAttributesService.selectTbResDeviceAttributesList(tbResDeviceAttributes);
		return getDataTable(list);
	}
	
	
//	/**
//	 * 导出设备属性（每个设备包含的属性记录）列表
//	 */
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(TbResDeviceAttributes tbResDeviceAttributes)
//    {
//    	List<TbResDeviceAttributes> list = tbResDeviceAttributesService.selectTbResDeviceAttributesList(tbResDeviceAttributes);
//        ExcelUtil<TbResDeviceAttributes> util = new ExcelUtil<TbResDeviceAttributes>(TbResDeviceAttributes.class);
//        return util.exportExcel(list, "tbResDeviceAttributes");
//    }
	
	/**
	 * 新增设备属性（每个设备包含的属性记录）
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备属性（每个设备包含的属性记录）
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TbResDeviceAttributes tbResDeviceAttributes)
	{		
		return toAjax(tbResDeviceAttributesService.insertTbResDeviceAttributes(tbResDeviceAttributes));
	}

	/**
	 * 修改设备属性（每个设备包含的属性记录）
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TbResDeviceAttributes tbResDeviceAttributes = tbResDeviceAttributesService.selectTbResDeviceAttributesById(id);
		mmap.put("tbResDeviceAttributes", tbResDeviceAttributes);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备属性（每个设备包含的属性记录）
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TbResDeviceAttributes tbResDeviceAttributes)
	{		
		return toAjax(tbResDeviceAttributesService.updateTbResDeviceAttributes(tbResDeviceAttributes));
	}
	
	/**
	 * 删除设备属性（每个设备包含的属性记录）
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tbResDeviceAttributesService.deleteTbResDeviceAttributesByIds(ids));
	}

	/**
	 * 根据地块查询设备和传感器
	 */
	@GetMapping("/getDevNumAndAttributes")
	@ResponseBody
	public AjaxResult getDevNumAndAttributes(Long plotId) {
		if (plotId == null) {
			return error(RequestStatus.PARAM_REQUIRED.getMessage());
		}
		return success(tbResDeviceAttributesService.selectByDevNumAndAttributes(plotId));
	}
	
}
