package org.zcy.agriculture.merchant.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbWarehouse;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbWarehouseService;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 仓库 信息操作处理
 *
 * @author numberone  lky.
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/api/tbWarehouse")
public class TbWarehouseController extends BaseController {
    private String prefix = "system/tbWarehouse";

    @Autowired
    private ITbWarehouseService tbWarehouseService;

    @GetMapping()
    public String tbWarehouse() {
        return prefix + "/tbWarehouse";
    }

    /**
     * 查询仓库列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbWarehouse tbWarehouse) {
        //startPage();
        tbWarehouse.setFarmId(getFarmUUID());
        List<TbWarehouse> list = tbWarehouseService.selectTbWarehouseList(tbWarehouse);
        return getDataTable(list);
    }


    /**
     * 导出仓库列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbWarehouse tbWarehouse) {
        tbWarehouse.setFarmId(getFarmUUID());
        List<TbWarehouse> list = tbWarehouseService.selectTbWarehouseList(tbWarehouse);
        ExcelUtil<TbWarehouse> util = new ExcelUtil<TbWarehouse>(TbWarehouse.class);
        return util.exportExcel(list, "tbWarehouse");
    }

    /**
     * 新增保存仓库
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult add(@RequestBody TbWarehouse tbWarehouse) {
        //先判断参数是否异常
        if (ValidationUtil.isEmpty(tbWarehouse.getName())) {
            return error("请输入仓库名称");
        }
        //设置仓库所属的农场ID
        tbWarehouse.setFarmId(getFarmUUID());
        //判断id是否为空,id为空时为新增
        if (ValidationUtil.isEmpty(tbWarehouse.getWarehouseId())) {
            //新增操作
            int num = tbWarehouseService.insertTbWarehouse(tbWarehouse);
            if (num < 1) {
                return error("新增仓库失败");
            }
        } else {
            //修改操作
            tbWarehouseService.updateTbWarehouse(tbWarehouse);
        }
        return success();
    }

    /**
     * 查询仓库详情
     */
    @PostMapping("/detail/info")
    @ResponseBody
    public AjaxResult getDetailInfo(@RequestBody TbWarehouse tbWarehouse) {
        tbWarehouse.setFarmId(getFarmUUID());
        TbWarehouse detailVo = tbWarehouseService.selectTbWarehouseById(tbWarehouse);
        if (detailVo != null) {
            return success(detailVo);
        } else {
            return error("查询失败");
        }
    }

    /**
     * 删除仓库 且 删除库存
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbWarehouse tbWarehouse) {
        tbWarehouse.setFarmId(getFarmUUID());
        try {
            tbWarehouseService.deleteTbWarehouseById(tbWarehouse);
        } catch (Exception e) {
            return toAjax(0);
        }
        return toAjax(1);
    }

}
