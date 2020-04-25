package org.zcy.agriculture.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbWarehouseDynamic;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbWarehouseDynamicService;

/**
 * 出入库动态 信息操作处理
 *
 * @author linlq lky.
 * @date 2019-07-11
 */
@Controller
@RequestMapping("/api/tbWarehouseDynamic")
public class TbWarehouseDynamicController extends BaseController {
    private String prefix = "system/tbWarehouseDynamic";

    @Autowired
    private ITbWarehouseDynamicService tbWarehouseDynamicService;

    @GetMapping()
    public String tbWarehouseDynamic() {
        return prefix + "/tbWarehouseDynamic";
    }

    /**
     * 查询出入库动态列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbWarehouseDynamic tbWarehouseDynamic) {
        startPage();
        tbWarehouseDynamic.setFarmId(getFarmUUID());
        List<TbWarehouseDynamic> list = tbWarehouseDynamicService.selectTbWarehouseDynamicList(tbWarehouseDynamic);
        return getDataTable(list);
    }


    /**
     * 导出出入库动态列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbWarehouseDynamic tbWarehouseDynamic) {
        tbWarehouseDynamic.setFarmId(getFarmUUID());
        List<TbWarehouseDynamic> list = tbWarehouseDynamicService.selectTbWarehouseDynamicList(tbWarehouseDynamic);
        ExcelUtil<TbWarehouseDynamic> util = new ExcelUtil<TbWarehouseDynamic>(TbWarehouseDynamic.class);
        return util.exportExcel(list, "tbWarehouseDynamic");
    }

    /**
     * 新增出入库动态
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存出入库动态
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody TbWarehouseDynamic tbWarehouseDynamic) {
        tbWarehouseDynamic.setFarmId(getFarmUUID());
        return toAjax(tbWarehouseDynamicService.insertTbWarehouseDynamic(tbWarehouseDynamic));
    }


    /**
     * 查询出入库动态详情
     */
    @PostMapping("/detail/info")
    @ResponseBody
    public AjaxResult getDetailInfo(@RequestBody TbWarehouseDynamic tbWarehouseDynamic) {
        tbWarehouseDynamic.setFarmId(getFarmUUID());
        TbWarehouseDynamic detailVo = tbWarehouseDynamicService.selectTbWarehouseDynamicById(tbWarehouseDynamic);
        if (detailVo != null) {
            return success(detailVo);
        } else {
            return error("查询失败");
        }
    }

    /**
     * 修改保存出入库动态
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody TbWarehouseDynamic tbWarehouseDynamic) {
        tbWarehouseDynamic.setFarmId(getFarmUUID());
        return toAjax(tbWarehouseDynamicService.updateTbWarehouseDynamic(tbWarehouseDynamic));
    }

    /**
     * 删除出入库动态
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbWarehouseDynamic tbWarehouseDynamic) {
        tbWarehouseDynamic.setFarmId(getFarmUUID());
        return toAjax(tbWarehouseDynamicService.deleteTbWarehouseDynamicById(tbWarehouseDynamic));
    }

}
