package org.zcy.agriculture.merchant.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbUseRecord;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbUseRecordService;

/**
 * 农机使用记录 信息操作处理
 *
 * @author linlq lky.
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/tbUseRecord")
public class TbUseRecordController extends BaseController {
    private String prefix = "system/tbUseRecord";

    @Autowired
    private ITbUseRecordService tbUseRecordService;

    @GetMapping()
    public String tbUseRecord() {
        return prefix + "/tbUseRecord";
    }

    /**
     * 查询农机使用记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbUseRecord tbUseRecord) {
        startPage();
        List<TbUseRecord> list = tbUseRecordService.selectTbUseRecordList(tbUseRecord);
        return getDataTable(list);
    }


    /**
     * 导出农机使用记录列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbUseRecord tbUseRecord) {
        List<TbUseRecord> list = tbUseRecordService.selectTbUseRecordList(tbUseRecord);
        ExcelUtil<TbUseRecord> util = new ExcelUtil<TbUseRecord>(TbUseRecord.class);
        return util.exportExcel(list, "tbUseRecord");
    }

    /**
     * 新增农机使用记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存农机使用记录
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody TbUseRecord tbUseRecord) {
        return toAjax(tbUseRecordService.insertTbUseRecord(tbUseRecord));
    }

    /**
     * 修改农机使用记录
     */
    @GetMapping("/edit/{machineRecordId}")
    public String edit(@PathVariable("machineRecordId") Long machineRecordId, ModelMap mmap) {
        TbUseRecord tbUseRecord = tbUseRecordService.selectTbUseRecordById(machineRecordId);
        mmap.put("tbUseRecord", tbUseRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存农机使用记录
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody TbUseRecord tbUseRecord) {
        return toAjax(tbUseRecordService.updateTbUseRecord(tbUseRecord));
    }

    /**
     * 删除农机使用记录
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tbUseRecordService.deleteTbUseRecordByIds(ids));
    }

}
