package org.zcy.agriculture.merchant.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbWarehouseRecordType;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbWarehouseRecordTypeService;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 出入库类型 信息操作处理
 *
 * @author linlq lky.
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/api/tbWarehouseRecordType")
public class TbWarehouseRecordTypeController extends BaseController {
    private String prefix = "system/tbWarehouseRecordType";

    @Autowired
    private ITbWarehouseRecordTypeService tbWarehouseRecordTypeService;

    @GetMapping()
    public String tbWarehouseRecordType() {
        return prefix + "/tbWarehouseRecordType";
    }

    /**
     * 查询出入库类型列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbWarehouseRecordType tbWarehouseRecordType) {
        //startPage();
        //tbWarehouseRecordType.setFarmId(getFarmUUID());
        List<TbWarehouseRecordType> list = tbWarehouseRecordTypeService.selectTbWarehouseRecordTypeList(tbWarehouseRecordType);
        return getDataTable(list);
    }


    /**
     * 导出出入库类型列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbWarehouseRecordType tbWarehouseRecordType) {
        tbWarehouseRecordType.setFarmId(getFarmUUID());
        List<TbWarehouseRecordType> list = tbWarehouseRecordTypeService.selectTbWarehouseRecordTypeList(tbWarehouseRecordType);
        ExcelUtil<TbWarehouseRecordType> util = new ExcelUtil<TbWarehouseRecordType>(TbWarehouseRecordType.class);
        return util.exportExcel(list, "tbWarehouseRecordType");
    }


    /**
     * 新增保存出入库类型
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult add(@RequestBody TbWarehouseRecordType tbWarehouseRecordType) {
        //先判断参数是否异常
        if (ValidationUtil.isEmpty(tbWarehouseRecordType.getName())) {
            return error("请输入出入库类型");
        }
        if (ValidationUtil.isEmpty(tbWarehouseRecordType.getInOutType())) {
            return error("请输入出入库操作类型");
        }
        //设置出入库类型所属的农场ID
        tbWarehouseRecordType.setFarmId(getFarmUUID());
        //判断id是否为空,id为空时为新增
        if (ValidationUtil.isEmpty(tbWarehouseRecordType.getInOutTypeId())) {
            //新增操作
            tbWarehouseRecordType.setCreateBy(getFarmUserCode());
            tbWarehouseRecordType.setCreateTime(new Date());
            int num = tbWarehouseRecordTypeService.insertTbWarehouseRecordType(tbWarehouseRecordType);
            if (num < 1) {
                return error("新增出入库类型失败");
            }
        } else {
            //修改操作
            tbWarehouseRecordType.setUpdateBy(getFarmUserCode());
            tbWarehouseRecordType.setUpdateTime(new Date());
            tbWarehouseRecordTypeService.updateTbWarehouseRecordType(tbWarehouseRecordType);
        }
        return success();
    }

    /**
     * 查询出入库类型详情
     */
    @PostMapping("/detail/info")
    @ResponseBody
    public AjaxResult getDetailInfo(@RequestBody TbWarehouseRecordType tbWarehouseRecordType) {
        tbWarehouseRecordType.setFarmId(getFarmUUID());
        TbWarehouseRecordType detailVo = tbWarehouseRecordTypeService.selectTbWarehouseRecordTypeById(tbWarehouseRecordType);
        if (detailVo != null) {
            return success(detailVo);
        } else {
            return error("查询失败");
        }
    }

    /**
     * 删除出入库类型
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbWarehouseRecordType tbWarehouseRecordType) {
        tbWarehouseRecordType.setFarmId(getFarmUUID());
        return toAjax(tbWarehouseRecordTypeService.deleteTbWarehouseRecordTypeById(tbWarehouseRecordType));
    }

}
