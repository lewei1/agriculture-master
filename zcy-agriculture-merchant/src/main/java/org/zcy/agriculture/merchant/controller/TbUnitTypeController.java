package org.zcy.agriculture.merchant.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbUnitType;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbUnitTypeService;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 物品-单位 信息操作处理
 *
 * @author linlq lky.
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/tbUnitType")
public class TbUnitTypeController extends BaseController {
    private String prefix = "system/tbUnitType";

    @Autowired
    private ITbUnitTypeService tbUnitTypeService;

    @GetMapping()
    public String tbUnitType() {
        return prefix + "/tbUnitType";
    }

    /**
     * 查询物品-单位列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbUnitType tbUnitType) {
        tbUnitType.setFarmId(getFarmUUID());
        List<TbUnitType> list = null;
        //物品id为空查询所有单位
        if (ValidationUtil.isEmpty(tbUnitType.getMaterialId())) {
            list = tbUnitTypeService.selectTbUnitTypeList(tbUnitType);
        } else {
            //物品id不为空查询该物品的单位
            list = tbUnitTypeService.selectTbUnitTypeListByMaterialId(tbUnitType);
        }
        return getDataTable(list);
    }


    /**
     * 导出物品-单位列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbUnitType tbUnitType) {
        tbUnitType.setFarmId(getFarmUUID());
        List<TbUnitType> list = tbUnitTypeService.selectTbUnitTypeList(tbUnitType);
        ExcelUtil<TbUnitType> util = new ExcelUtil<TbUnitType>(TbUnitType.class);
        return util.exportExcel(list, "tbUnitType");
    }


    /**
     * 新增保存物品-单位
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult add(@RequestBody TbUnitType tbUnitType) {
        //先判断参数是否异常
        if (ValidationUtil.isEmpty(tbUnitType.getTypeName())) {
            return error("请输入物品单位名称");
        }
        //设置农机所属的农场ID
        tbUnitType.setFarmId(getFarmUUID());
        //判断id是否为空,id为空时为新增
        if (ValidationUtil.isEmpty(tbUnitType.getMaterialUnitId())) {
            //新增操作
            int num = tbUnitTypeService.insertTbUnitType(tbUnitType);
            if (num < 1) {
                return error("新增物品单位失败");
            }
        } else {
            //修改操作
            tbUnitTypeService.updateTbUnitType(tbUnitType);
        }
        return success();
    }

    /**
     * 查询物品单位详情
     */
    @PostMapping("/detail/info")
    @ResponseBody
    public AjaxResult getDetailInfo(@RequestBody TbUnitType tbUnitType) {
        tbUnitType.setFarmId(getFarmUUID());
        TbUnitType detailVo = tbUnitTypeService.selectTbUnitTypeById(tbUnitType);
        if (detailVo != null) {
            return success(detailVo);
        } else {
            return error("查询失败");
        }
    }

    /**
     * 删除物品-单位
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbUnitType tbUnitType) {
        tbUnitType.setFarmId(getFarmUUID());
        return toAjax(tbUnitTypeService.deleteTbUnitTypeById(tbUnitType));
    }

}
