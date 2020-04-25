package org.zcy.agriculture.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbInventoryMaterial;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbInventoryMaterialService;

/**
 * 仓库和物品关联(库存) 信息操作处理
 *
 * @author linlq lky.
 * @date 2019-07-02
 */
@Controller
@RequestMapping("/api/tbInventoryMaterial")
public class TbInventoryMaterialController extends BaseController {
    private String prefix = "system/tbInventoryMaterial";

    @Autowired
    private ITbInventoryMaterialService tbInventoryMaterialService;

    @GetMapping()
    public String tbInventoryMaterial() {
        return prefix + "/tbInventoryMaterial";
    }

    /**
     * 查询仓库和物品关联(库存)列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbInventoryMaterial tbInventoryMaterial) {
        startPage();
        tbInventoryMaterial.setFarmId(getFarmUUID());
        List<TbInventoryMaterial> list = tbInventoryMaterialService.selectTbInventoryMaterialList(tbInventoryMaterial);
        return getDataTable(list);
    }


    /**
     * 导出仓库和物品关联(库存)列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbInventoryMaterial tbInventoryMaterial) {
        tbInventoryMaterial.setFarmId(getFarmUUID());
        List<TbInventoryMaterial> list = tbInventoryMaterialService.selectTbInventoryMaterialList(tbInventoryMaterial);
        ExcelUtil<TbInventoryMaterial> util = new ExcelUtil<TbInventoryMaterial>(TbInventoryMaterial.class);
        return util.exportExcel(list, "tbInventoryMaterial");
    }

    /**
     * 新增仓库和物品关联(库存)
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存仓库和物品关联(库存)
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody TbInventoryMaterial tbInventoryMaterial) {
        tbInventoryMaterial.setFarmId(getFarmUUID());
        return toAjax(tbInventoryMaterialService.insertTbInventoryMaterial(tbInventoryMaterial));
    }

    /**
     * 修改仓库和物品关联(库存)
     */
    @GetMapping("/edit/{warehouseMaterialId}")
    public String edit(@PathVariable("warehouseMaterialId") Long warehouseMaterialId, ModelMap mmap) {
        TbInventoryMaterial tbInventoryMaterial = tbInventoryMaterialService.selectTbInventoryMaterialById(warehouseMaterialId);
        mmap.put("tbInventoryMaterial", tbInventoryMaterial);
        return prefix + "/edit";
    }

    /**
     * 修改保存仓库和物品关联(库存)
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody TbInventoryMaterial tbInventoryMaterial) {
        tbInventoryMaterial.setFarmId(getFarmUUID());
        return toAjax(tbInventoryMaterialService.updateTbInventoryMaterial(tbInventoryMaterial));
    }

    /**
     * 报警设置
     */
    @PostMapping("/alarmStock")
    @ResponseBody
    public AjaxResult alarmStock(@RequestBody TbInventoryMaterial tbInventoryMaterial) {
        tbInventoryMaterial.setFarmId(getFarmUUID());
        tbInventoryMaterialService.updateTbInventoryMaterial(tbInventoryMaterial);
        return success();
    }

    /**
     * 删除仓库和物品关联(库存)
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbInventoryMaterial tbInventoryMaterial) {
        tbInventoryMaterial.setFarmId(getFarmUUID());
        return toAjax(tbInventoryMaterialService.deleteTbInventoryMaterialById(tbInventoryMaterial));
    }

}
