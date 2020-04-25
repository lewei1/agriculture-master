package org.zcy.agriculture.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbResPower;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbResPowerService;

import java.util.Date;
import java.util.List;

/**
 * 权限 信息操作处理
 * 
 * @author zh
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/power")
public class TbResPowerController extends BaseController
{
    private String prefix = "api/power";
	
	@Autowired
	private ITbResPowerService tbResPowerService;

	@GetMapping()
	public String tbResPower()
	{
	    return prefix + "/tbResPower";
	}
	
	/**
	 * 查询权限列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody TbResPower power)
	{
		//startPage();
		power.setStatus(0);
        List<TbResPower> list = tbResPowerService.selectTbResPowerList(power);
		return getDataTable(list);
	}
	
	
//	/**
//	 * 导出权限列表
//	 */
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(TbResPower tbResPower)
//    {
//    	List<TbResPower> list = tbResPowerService.selectTbResPowerList(tbResPower);
//        ExcelUtil<TbResPower> util = new ExcelUtil<TbResPower>(TbResPower.class);
//        return util.exportExcel(list, "tbResPower");
//    }
	
	/**
	 * 新增权限
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存权限
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody TbResPower power)
	{
		power.setStatus(0);
		return toAjax(tbResPowerService.insertTbResPower(power));
	}

	/**
	 * 修改权限
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TbResPower tbResPower = tbResPowerService.selectTbResPowerById(id);
		mmap.put("tbResPower", tbResPower);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存权限
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TbResPower tbResPower)
	{
		return toAjax(tbResPowerService.updateTbResPower(tbResPower));
	}
	
	/**
	 * 删除权限
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String id)
	{		
		return toAjax(tbResPowerService.deleteTbResPowerByIds(id));
	}
	
}
