package org.zcy.agriculture.merchant.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.zcy.agriculture.entity.TbDicDeviceGroup;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbDicDeviceGroupService;

/**
 * 设备与分组关联 信息操作处理
 * 
 * @author numberone
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/system/tbDicDeviceGroup")
public class TbDicDeviceGroupController extends BaseController
{
    private String prefix = "system/tbDicDeviceGroup";
	
	@Autowired
	private ITbDicDeviceGroupService tbDicDeviceGroupService;

	@GetMapping()
	public String tbDicDeviceGroup()
	{
	    return prefix + "/tbDicDeviceGroup";
	}
	
	/**
	 * 查询设备与分组关联列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TbDicDeviceGroup tbDicDeviceGroup)
	{
		startPage();
        List<TbDicDeviceGroup> list = tbDicDeviceGroupService.selectTbDicDeviceGroupList(tbDicDeviceGroup);
		return getDataTable(list);
	}
	
	
//	/**
//	 * 导出设备与分组关联列表
//	 */
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(TbDicDeviceGroup tbDicDeviceGroup)
//    {
//    	List<TbDicDeviceGroup> list = tbDicDeviceGroupService.selectTbDicDeviceGroupList(tbDicDeviceGroup);
//        ExcelUtil<TbDicDeviceGroup> util = new ExcelUtil<TbDicDeviceGroup>(TbDicDeviceGroup.class);
//        return util.exportExcel(list, "tbDicDeviceGroup");
//    }
	
	/**
	 * 新增设备与分组关联
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备与分组关联
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TbDicDeviceGroup tbDicDeviceGroup)
	{		
		return toAjax(tbDicDeviceGroupService.insertTbDicDeviceGroup(tbDicDeviceGroup));
	}

	/**
	 * 修改设备与分组关联
	 */
	@GetMapping("/edit/{devGroupId}")
	public String edit(@PathVariable("devGroupId") Long devGroupId, ModelMap mmap)
	{
		TbDicDeviceGroup tbDicDeviceGroup = tbDicDeviceGroupService.selectTbDicDeviceGroupById(devGroupId);
		mmap.put("tbDicDeviceGroup", tbDicDeviceGroup);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备与分组关联
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TbDicDeviceGroup tbDicDeviceGroup)
	{		
		return toAjax(tbDicDeviceGroupService.updateTbDicDeviceGroup(tbDicDeviceGroup));
	}
	
	/**
	 * 删除设备与分组关联
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tbDicDeviceGroupService.deleteTbDicDeviceGroupByIds(ids));
	}
	
}
