package org.zcy.agriculture.merchant.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbArticle;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbArticleService;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 物品 信息操作处理
 *
 * @author linlq lky.
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/tbArticle")
public class TbArticleController extends BaseController {
    private String prefix = "system/tbArticle";

    @Autowired
    private ITbArticleService tbArticleService;

    @GetMapping()
    public String tbArticle() {
        return prefix + "/tbArticle";
    }

    /**
     * 查询物品列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbArticle tbArticle) {
        startPage();
        tbArticle.setFarmId(getFarmUUID());
        List<TbArticle> list = tbArticleService.selectTbArticleList(tbArticle);
        return getDataTable(list);
    }

    /**
     * 根据仓库ID查询物品
     */
    @GetMapping("/warehouseIdList")
    @ResponseBody
    public TableDataInfo warehouseIdList(Long warehouseId) {
        return getDataTable(tbArticleService.selectByWarehouseId(warehouseId));
    }


    /**
     * 导出物品列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbArticle tbArticle) {
        List<TbArticle> list = tbArticleService.selectTbArticleList(tbArticle);
        ExcelUtil<TbArticle> util = new ExcelUtil<TbArticle>(TbArticle.class);
        return util.exportExcel(list, "tbArticle");
    }

    /**
     * 新增或修改物品
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult add(@RequestBody TbArticle tbArticle) {

        //启用多单位，0为不启动，1为启动
        if (tbArticle.getMultipleUnit() == 0) {
            //先判断参数是否异常
            if (ValidationUtil.isEmpty(tbArticle.getMaterialName())) {
                return error("请输入物品名称");
            }
            if (ValidationUtil.isEmpty(tbArticle.getMaterialTypeId())) {
                return error("请选择类型");
            }
            if (ValidationUtil.isEmpty(tbArticle.getPrice())) {
                return error("请输入物品单价");
            }
            if (ValidationUtil.isEmpty(tbArticle.getMeterUnitId())) {
                return error("请选择物品单位");
            }

            //不启用多单位的时候 设置 单价的单位 为空，计算的时候使用 计量单位
            tbArticle.setPriceUnitId(null);

        } else {
            //先判断参数是否异常
            if (ValidationUtil.isEmpty(tbArticle.getMaterialName())) {
                return error("请输入物品名称");
            }
            if (ValidationUtil.isEmpty(tbArticle.getMaterialTypeId())) {
                return error("请选择类型");
            }
            if (ValidationUtil.isEmpty(tbArticle.getMeterUnitId())) {
                return error("请选择最小单位");
            }
            if (ValidationUtil.isEmpty(tbArticle.getUnitId1())) {
                return error("请选择副单位1");
            }
            if (ValidationUtil.isEmpty(tbArticle.getConversionRatio1())) {
                return error("请输入换算比例1");
            }
            if (!ValidationUtil.isEmpty(tbArticle.getUnitId2()) && ValidationUtil.isEmpty(tbArticle.getConversionRatio2())) {
                return error("请输入换算比例2");
            }
            if (ValidationUtil.isEmpty(tbArticle.getPrice())) {
                return error("请输入物品单价");
            }
            if (ValidationUtil.isEmpty(tbArticle.getPriceUnitId())) {
                return error("请输入物品单价的单位");
            }

            // 三个单位之间 两两不相等
            if (tbArticle.getMeterUnitId().equals(tbArticle.getUnitId1()) || tbArticle.getUnitId1().equals(tbArticle.getUnitId2()) || tbArticle.getMeterUnitId().equals(tbArticle.getUnitId2())) {
                return error("不能选择相同单位");
            }
            //单价的单位必须是三个单位中的一个
            if (!tbArticle.getPriceUnitId().equals(tbArticle.getMeterUnitId()) && !tbArticle.getPriceUnitId().equals(tbArticle.getUnitId1()) && !tbArticle.getPriceUnitId().equals(tbArticle.getUnitId2())) {
                return error("单价的单位不正确");
            }
        }

        //设置物品所属的农场ID
        tbArticle.setFarmId(getFarmUUID());
        //判断id是否为空,id为空时为新增
        if (ValidationUtil.isEmpty(tbArticle.getMaterialId())) {
            //新增操作
            tbArticle.setCreateBy(getFarmUserCode());
            tbArticle.setCreateTime(new Date());
            int num = tbArticleService.insertTbArticle(tbArticle);
            if (num < 1) {
                return error("新增物品失败");
            }
        } else {
            //修改操作
            tbArticle.setUpdateBy(getFarmUserCode());
            tbArticle.setUpdateTime(new Date());
            tbArticleService.updateTbArticle(tbArticle);
        }
        return success();
    }


    /**
     * 查询物品详情
     */
    @PostMapping("/detail/info")
    @ResponseBody
    public AjaxResult getDetailInfo(@RequestBody TbArticle tbArticle) {
        tbArticle.setFarmId(getFarmUUID());
        TbArticle detailVo = tbArticleService.selectTbArticleById(tbArticle);
        if (detailVo != null) {
            return success(detailVo);
        } else {
            return error("查询失败");
        }
    }

    /**
     * 删除物品 且删除库存
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbArticle tbArticle) {
        tbArticle.setFarmId(getFarmUUID());
        try {
            tbArticleService.deleteTbArticleById(tbArticle);
        } catch (Exception e) {
            return toAjax(0);
        }
        return toAjax(1);
    }

}
