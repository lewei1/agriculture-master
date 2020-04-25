package org.zcy.agriculture.merchant.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbDicRolePower;
import org.zcy.agriculture.entity.TbResPower;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbDicRolePowerService;
import org.zcy.agriculture.service.ITbResPowerService;
import org.zcy.agriculture.service.ITbResRoleService;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 角色权限关联 信息操作处理
 * 
 * @author zh
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/role/power")
public class TbDicRolePowerController extends BaseController {
	private String prefix = "api/role/power";

	@Autowired
	private ITbDicRolePowerService tbDicRolePowerService;

	@Autowired
	private ITbResRoleService tbResRoleService;

	@Autowired
	private ITbResPowerService tbResPowerService;

	@GetMapping()
	public String tbDicRolePower() {
		return prefix + "/tbDicRolePower";
	}

	/**
	 * 查询角色权限关联列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody TbDicRolePower rowPower) {
		List<TbResPower> listPower =  new ArrayList<TbResPower>();

		TableDataInfo tb = new TableDataInfo();
		/**判断角色ID是否有传*/
		if (ValidationUtil.isEmpty(rowPower.getRoleId())) {
			tb.setMsg("请输入角色ID");
			tb.setCode(500);
			return tb;
		}

		/***根据传入的ID查询对应数据并返回*/
		List<TbDicRolePower> listRowPower = tbDicRolePowerService.selectTbDicRolePowerList(rowPower);
		for (int i = 0; i < listRowPower.size(); i++) {
			TbDicRolePower rp = listRowPower.get(i);
			TbResPower powerTemp = tbResPowerService.selectTbResPowerById(rp.getPowerId());
			listPower.add(powerTemp);
		}
		tb.setRows(listPower);
		tb.setTotal(listPower.size());
		tb.setCode(0);
		tb.setMsg("查询成功");
		return tb;
	}

	/**
	 * 角色权限回显接口
	 */
	@PostMapping("/reviewRole")
	@ResponseBody
	public AjaxResult reviewRole(@RequestBody Map<String,String> map){
		if(ValidationUtil.isEmpty(map.get("roleId"))){
			return AjaxResult.error("角色ID不能为空");
		}
		List<TbResPower> listPower =  new ArrayList<TbResPower>();
		TbDicRolePower rowPower = new TbDicRolePower();
		rowPower.setRoleId(Long.parseLong(map.get("roleId")));
		List<TbDicRolePower> listRowPower = tbDicRolePowerService.selectTbDicRolePowerList(rowPower);
		for (int i = 0; i < listRowPower.size(); i++) {
			TbDicRolePower rp = listRowPower.get(i);
			TbResPower powerTemp = tbResPowerService.selectTbResPowerById(rp.getPowerId());
			if(powerTemp.getLevel()!=4){
				listPower.add(powerTemp);
			}
		}
		return AjaxResult.success(listPower);
	}


	/**
	 * 导出角色权限关联列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(TbDicRolePower tbDicRolePower) {
		List<TbDicRolePower> list = tbDicRolePowerService.selectTbDicRolePowerList(tbDicRolePower);
		ExcelUtil<TbDicRolePower> util = new ExcelUtil<TbDicRolePower>(TbDicRolePower.class);
		return util.exportExcel(list, "tbDicRolePower");
	}

	/**
	 * 新增角色权限关联
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存角色权限关联
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody TbDicRolePower rolePower) {
		Boolean b = tbResRoleService.isExistWithRoleID(rolePower.getRoleId());

		/**判断角色ID是否有效存在*/
		if (ValidationUtil.isEmpty(rolePower.getRoleId())) {
			return error("请输入角色ID");
		} else if (!tbResRoleService.isExistWithRoleID(rolePower.getRoleId())) {
			return error("角色ID不存在");
		}

		/**判断权限ID是否有效存在*/
		if (ValidationUtil.isEmpty(rolePower.getPowerId())) {
			return error("请输入权限ID");
		} else if (!tbResPowerService.isExistWithPowerID(rolePower.getPowerId())) {
			return error("权限ID不存在");
		}

		/**获取记录list，如已有则设置为0*/
		List<TbDicRolePower> list = tbDicRolePowerService.selectTbDicRolePowerList(rolePower);
		if (list.size() > 0) {
			return error("已存在，权限不能重复添加");
		}

		return toAjax(tbDicRolePowerService.insertTbDicRolePower(rolePower));
	}

	/**
	 * 修改角色权限关联
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TbDicRolePower tbDicRolePower = tbDicRolePowerService.selectTbDicRolePowerById(id);
		mmap.put("tbDicRolePower", tbDicRolePower);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存角色权限关联
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TbDicRolePower rolePower)
	{
		return toAjax(tbDicRolePowerService.updateTbDicRolePower(rolePower));
	}
	
	/**
	 * 删除角色权限关联
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tbDicRolePowerService.deleteTbDicRolePowerByIds(ids));
	}
	
}
