package org.zcy.agriculture.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbAgriculturalMachine;
import org.zcy.agriculture.entity.TbAmType;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbAmTypeService;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 农机类型 信息操作处理
 *
 * @author linlq lky.
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/tbAmType")
public class TbAmTypeController extends BaseController {
    private String prefix = "system/tbAmType";

    @Autowired
    private ITbAmTypeService tbAmTypeService;

    @GetMapping()
    public String tbAmType() {
        return prefix + "/tbAmType";
    }

    /**
     * 查询农机类型列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbAmType tbAmType) {
        //startPage();
        tbAmType.setFarmId(getFarmUUID());
        List<TbAmType> list = tbAmTypeService.selectTbAmTypeList(tbAmType);
        return getDataTable(list);
    }


    /**
     * 导出农机类型列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbAmType tbAmType) {
        tbAmType.setFarmId(getFarmUUID());
        List<TbAmType> list = tbAmTypeService.selectTbAmTypeList(tbAmType);
        ExcelUtil<TbAmType> util = new ExcelUtil<TbAmType>(TbAmType.class);
        return util.exportExcel(list, "tbAmType");
    }

    /**
     * 新增农机类型
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody TbAmType info) {
        //先判断参数是否异常
        if (ValidationUtil.isEmpty(info.getTypeName())) {
            return error("请填写农机类型名称");
        }
        //设置农机类型所属的农场ID
        info.setFarmId(getFarmUUID());
        //判断id是否为空,id为空时为新增
        if (ValidationUtil.isEmpty(info.getMachineTypeId())) {
            //新增操作
            int num = tbAmTypeService.insertTbAmType(info);
            if (num < 1) {
                return error("新增农机类型失败");
            }
        } else {
            //修改操作
            tbAmTypeService.updateTbAmType(info);
        }
        return success();
    }

    /**
     * 新增保存农机类型
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody TbAmType tbAmType) {
        tbAmType.setFarmId(getFarmUUID());
        return toAjax(tbAmTypeService.insertTbAmType(tbAmType));
    }

    /**
     * 修改农机类型
     */
    @GetMapping("/edit/{machineTypeId}")
    public String edit(@PathVariable("machineTypeId") Long machineTypeId, ModelMap mmap) {
        TbAmType tbAmType = tbAmTypeService.selectTbAmTypeById(machineTypeId);
        mmap.put("tbAmType", tbAmType);
        return prefix + "/edit";
    }

    /**
     * 查询农机类型详情
     */
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult detail(@RequestBody TbAmType tbAmType) {
        tbAmType.setFarmId(getFarmUUID());
        TbAmType tbAM = tbAmTypeService.selectTbAmTypeByClass(tbAmType);
        return success(tbAM);
    }

    /**
     * 修改保存农机类型
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody TbAmType tbAmType) {
        tbAmType.setFarmId(getFarmUUID());
        return toAjax(tbAmTypeService.updateTbAmType(tbAmType));
    }


    /**
     * 删除农机类型
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbAmType tbAmType) {
        tbAmType.setFarmId(getFarmUUID());
        return toAjax(tbAmTypeService.deleteTbAmTypeById(tbAmType));
    }

}
