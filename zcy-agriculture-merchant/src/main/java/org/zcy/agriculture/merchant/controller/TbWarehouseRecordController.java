package org.zcy.agriculture.merchant.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbArticle;
import org.zcy.agriculture.entity.TbInventoryMaterial;
import org.zcy.agriculture.entity.TbWarehouseDynamic;
import org.zcy.agriculture.entity.TbWarehouseRecord;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbArticleService;
import org.zcy.agriculture.service.ITbInventoryMaterialService;
import org.zcy.agriculture.service.ITbWarehouseDynamicService;
import org.zcy.agriculture.service.ITbWarehouseRecordService;
import org.zcy.agriculture.util.GenerateNoUtils;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 出入库记录 信息操作处理
 *
 * @author linlq  lky.
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/api/tbWarehouseRecord")
public class TbWarehouseRecordController extends BaseController {
    private String prefix = "system/tbWarehouseRecord";

    @Autowired
    private ITbWarehouseRecordService tbWarehouseRecordService;

    @Autowired
    private ITbArticleService tbArticleService;

    @GetMapping()
    public String tbWarehouseRecord() {
        return prefix + "/tbWarehouseRecord";
    }

    /**
     * 查询出入库记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbWarehouseRecord tbWarehouseRecord) {

        if (ValidationUtil.isEmpty(tbWarehouseRecord.getWarehouseId())) {
            TableDataInfo tableDataInfo = new TableDataInfo();
            tableDataInfo.setMsg("仓库ID不能为空");
            return tableDataInfo;
        }
        startPage();
        tbWarehouseRecord.setFarmId(getFarmUUID());
        List<TbWarehouseRecord> list = tbWarehouseRecordService.selectTbWarehouseRecordList(tbWarehouseRecord);
        return getDataTable(list);
    }

    /**
     * 搜索出入库记录列表
     */
    @PostMapping("/search")
    @ResponseBody
    public TableDataInfo search(@RequestBody TbWarehouseRecord tbWarehouseRecord) {
        startPage();
        tbWarehouseRecord.setFarmId(getFarmUUID());
        List<TbWarehouseRecord> list = tbWarehouseRecordService.selectTbWarehouseRecordListByName(tbWarehouseRecord);
        return getDataTable(list);
    }


    /**
     * 导出出入库记录列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbWarehouseRecord tbWarehouseRecord) {
        tbWarehouseRecord.setFarmId(getFarmUUID());
        List<TbWarehouseRecord> list = tbWarehouseRecordService.selectTbWarehouseRecordList(tbWarehouseRecord);
        ExcelUtil<TbWarehouseRecord> util = new ExcelUtil<TbWarehouseRecord>(TbWarehouseRecord.class);
        return util.exportExcel(list, "tbWarehouseRecord");
    }


    /**
     * 新增保存出入库记录
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@RequestBody List<TbWarehouseRecord> tbWarehouseRecords) {


        for (TbWarehouseRecord tbWarehouseRecord : tbWarehouseRecords) {
            //先判断参数是否异常
            if (ValidationUtil.isEmpty(tbWarehouseRecord.getWarehouseId())) {
                return error("请选择仓库");
            }
            if (ValidationUtil.isEmpty(tbWarehouseRecord.getInOutType()) || !("I".equals(tbWarehouseRecord.getInOutType()) || "O".equals(tbWarehouseRecord.getInOutType()))) {
                return error("请选择出入库类型");
            }
            if (ValidationUtil.isEmpty(tbWarehouseRecord.getInOutTypeId())) {
                return error("请输入出入库类型id");
            }
            if (ValidationUtil.isEmpty(tbWarehouseRecord.getOperatorId()) || ValidationUtil.isEmpty(tbWarehouseRecord.getOperatorName())) {
                return error("请选择经办人");
            }
            if (ValidationUtil.isEmpty(tbWarehouseRecord.getArticle())) {
                return error("请选择物品名称");
            }
            if (ValidationUtil.isEmpty(tbWarehouseRecord.getUnit())) {
                return error("请选择单位");
            }
            if (ValidationUtil.isEmpty(tbWarehouseRecord.getQuantity()) || tbWarehouseRecord.getQuantity() <= 0) {
                return error("请输入正确的数量");
            }

            //校验物品单位
            TbArticle tbArticle = new TbArticle();
            tbArticle.setFarmId(getFarmUUID());
            tbArticle.setMaterialId(tbWarehouseRecord.getMaterialId());
            tbArticle = tbArticleService.selectTbArticleById(tbArticle);
            //启用多单位，0为不启动，1为启动
            if (tbArticle.getMultipleUnit() == 1) {
                if (!tbWarehouseRecord.getUnit().equals(tbArticle.getMeterUnitId()) && !tbWarehouseRecord.getUnit().equals(tbArticle.getUnitId1()) && !tbWarehouseRecord.getUnit().equals(tbArticle.getUnitId2())) {
                    return error("所选单位不是该物品的单位");
                }
            } else {
                if (!tbWarehouseRecord.getUnit().equals(tbArticle.getMeterUnitId())) {
                    return error("所选单位与计价单位不一致");
                }
            }
            //设置入库所属的农场ID
            tbWarehouseRecord.setFarmId(getFarmUUID());
            tbWarehouseRecord.setCreateBy(getFarmUserCode());
            tbWarehouseRecord.setCreateTime(new Date());
            //生成单号
            if ("I".equals(tbWarehouseRecord.getInOutType())) {
                tbWarehouseRecord.setNumber(GenerateNoUtils.gens("IN-", "-" + String.valueOf(new Random().nextLong()).substring(3, 7)));
            } else {
                tbWarehouseRecord.setNumber(GenerateNoUtils.gens("OUT-", "-" + String.valueOf(new Random().nextLong()).substring(3, 7)));
            }
        }

        try {
            //如果失败 事务回滚
            tbWarehouseRecordService.batchAddWarehouseRecord(tbWarehouseRecords, getFarmUUID());
        } catch (Exception e) {
            e.printStackTrace();
            return error("新增入库记录失败");
        }
        return success();
    }


    /**
     * 查询出入库记录详情
     */
    @PostMapping("/detail/info")
    @ResponseBody
    public AjaxResult getDetailInfo(@RequestBody TbWarehouseRecord tbWarehouseRecord) {
        tbWarehouseRecord.setFarmId(getFarmUUID());
        TbWarehouseRecord detailVo = tbWarehouseRecordService.selectTbWarehouseRecordById(tbWarehouseRecord);
        if (detailVo != null) {
            return success(detailVo);
        } else {
            return error("查询失败");
        }
    }

    /**
     * 修改保存出入库记录
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody TbWarehouseRecord tbWarehouseRecord) {
        tbWarehouseRecord.setFarmId(getFarmUUID());
        tbWarehouseRecord.setUpdateBy(getFarmUserCode());
        tbWarehouseRecord.setUpdateTime(new Date());
        return toAjax(tbWarehouseRecordService.updateTbWarehouseRecord(tbWarehouseRecord));
    }

    /**
     * 删除出入库记录
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbWarehouseRecord tbWarehouseRecord) {
        tbWarehouseRecord.setFarmId(getFarmUUID());
        return toAjax(tbWarehouseRecordService.deleteTbWarehouseRecordById(tbWarehouseRecord));
    }

}
