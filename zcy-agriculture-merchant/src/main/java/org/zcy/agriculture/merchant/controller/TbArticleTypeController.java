package org.zcy.agriculture.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbArticleType;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbArticleTypeService;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 物品类型 信息操作处理
 *
 * @author linlq lky.
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/tbArticleType")
public class TbArticleTypeController extends BaseController {
    private String prefix = "system/tbArticleType";

    @Autowired
    private ITbArticleTypeService tbArticleTypeService;

    @GetMapping()
    public String tbArticleType() {
        return prefix + "/tbArticleType";
    }

    /**
     * 查询物品类型列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbArticleType tbArticleType) {
        //startPage();
        tbArticleType.setFarmId(getFarmUUID());
        List<TbArticleType> list = tbArticleTypeService.selectTbArticleTypeList(tbArticleType);
        return getDataTable(list);
    }


    /**
     * 导出物品类型列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbArticleType tbArticleType) {
        tbArticleType.setFarmId(getFarmUUID());
        List<TbArticleType> list = tbArticleTypeService.selectTbArticleTypeList(tbArticleType);
        ExcelUtil<TbArticleType> util = new ExcelUtil<TbArticleType>(TbArticleType.class);
        return util.exportExcel(list, "tbArticleType");
    }

    /**
     * 新增或编辑物品类型
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult add(@RequestBody TbArticleType tbArticleType) {
        //先判断参数是否异常
        if (ValidationUtil.isEmpty(tbArticleType.getTypeName())) {
            return error("请输入物品类型名称");
        }
        //设置农机所属的农场ID
        tbArticleType.setFarmId(getFarmUUID());
        //判断id是否为空,id为空时为新增
        if (ValidationUtil.isEmpty(tbArticleType.getMaterialTypeId())) {
            //新增操作
            int num = tbArticleTypeService.insertTbArticleType(tbArticleType);
            if (num < 1) {
                return error("新增物品类型失败");
            }
        } else {
            //修改操作
            tbArticleTypeService.updateTbArticleType(tbArticleType);
        }
        return success();
    }


    /**
     * 查询物品类型详情
     */
    @PostMapping("/detail/info")
    @ResponseBody
    public AjaxResult getDetailInfo(@RequestBody TbArticleType tbArticleType) {
        tbArticleType.setFarmId(getFarmUUID());
        TbArticleType detailVo = tbArticleTypeService.selectTbArticleTypeById(tbArticleType);
        if (detailVo != null) {
            return success(detailVo);
        } else {
            return error("查询失败");
        }
    }

    /**
     * 删除物品类型
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbArticleType tbArticleType) {
        tbArticleType.setFarmId(getFarmUUID());
        return toAjax(tbArticleTypeService.deleteTbArticleTypeById(tbArticleType));
    }

}
