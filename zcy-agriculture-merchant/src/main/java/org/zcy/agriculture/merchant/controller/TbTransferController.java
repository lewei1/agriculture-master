package org.zcy.agriculture.merchant.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.service.*;
import org.zcy.agriculture.util.GenerateNoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 调拨记录 信息操作处理
 *
 * @author linlq lky.
 * @date 2019-07-02
 */
@Controller
@RequestMapping("/api/tbTransfer")
public class TbTransferController extends BaseController {
    private String prefix = "system/tbTransfer";

    @Autowired
    private ITbTransferService tbTransferService;

    @Autowired
    private ITbArticleService tbArticleService;

    @GetMapping()
    public String tbTransfer() {
        return prefix + "/tbTransfer";
    }

    /**
     * 查询调拨记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbTransfer tbTransfer) {

        // 这里的InWarehouseId为要查询的仓库id , 等同于WarehouseId，不是入库仓库的意思
        if (ValidationUtil.isEmpty(tbTransfer.getInWarehouseId())) {
            TableDataInfo tableDataInfo = new TableDataInfo();
            tableDataInfo.setMsg("仓库ID不能为空");
            return tableDataInfo;
        }
        startPage();
        tbTransfer.setFarmId(getFarmUUID());
        List<TbTransfer> list = tbTransferService.selectTbTransferList(tbTransfer);
        //同一条调拨记录的状态因为出入库的仓库的不同而不同
        for (TbTransfer tbTransfer1 : list) {
            tbTransfer1.setOperate(1);
            if (tbTransfer1.getInWarehouseId().equals(tbTransfer.getInWarehouseId())) {
                tbTransfer1.setMethod("调入");
                if (tbTransfer1.getStatus() != null && tbTransfer1.getStatus() == 0) {
                    //0为前端显示入库按钮，1不显示
                    tbTransfer1.setOperate(0);
                }
            } else {
                tbTransfer1.setMethod("调出");
            }
        }
        return getDataTable(list);
    }


    /**
     * 导出调拨记录列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbTransfer tbTransfer) {
        tbTransfer.setFarmId(getFarmUUID());
        List<TbTransfer> list = tbTransferService.selectTbTransferList(tbTransfer);
        ExcelUtil<TbTransfer> util = new ExcelUtil<TbTransfer>(TbTransfer.class);
        return util.exportExcel(list, "tbTransfer");
    }

    /**
     * 新增保存调拨记录
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@RequestBody List<TbTransfer> tbTransfers) {

        for (TbTransfer tbTransfer : tbTransfers) {
            //先判断参数是否异常
            if (ValidationUtil.isEmpty(tbTransfer.getOutWarehouseId())) {
                return error("请选择调出仓库");
            }
            if (ValidationUtil.isEmpty(tbTransfer.getInWarehouseId())) {
                return error("请选择调入仓库");
            }
            if (tbTransfer.getOutWarehouseId().equals(tbTransfer.getInWarehouseId())) {
                return error("调出仓库和调入仓库不能相同");
            }
            if (ValidationUtil.isEmpty(tbTransfer.getOperatorId()) || ValidationUtil.isEmpty(tbTransfer.getOperatorName())) {
                return error("请选择经办人");
            }
            if (ValidationUtil.isEmpty(tbTransfer.getArticle())) {
                return error("请选择物品名称");
            }
            if (ValidationUtil.isEmpty(tbTransfer.getUnit())) {
                return error("请选择单位");
            }
            if (ValidationUtil.isEmpty(tbTransfer.getQuantity())) {
                return error("请输入数量");
            }

            //校验物品单位
            TbArticle tbArticle = new TbArticle();
            tbArticle.setFarmId(getFarmUUID());
            tbArticle.setMaterialId(tbTransfer.getMaterialId());
            tbArticle = tbArticleService.selectTbArticleById(tbArticle);
            //启用多单位，0为不启动，1为启动
            if (tbArticle.getMultipleUnit() == 1) {
                if (!tbTransfer.getUnit().equals(tbArticle.getMeterUnitId()) && !tbTransfer.getUnit().equals(tbArticle.getUnitId1()) && !tbTransfer.getUnit().equals(tbArticle.getUnitId2())) {
                    return error("所选单位不是该物品的单位");
                }
            } else {
                if (!tbTransfer.getUnit().equals(tbArticle.getMeterUnitId())) {
                    return error("所选单位与计价单位不一致");
                }
            }
            //生成单号
            tbTransfer.setNumber(GenerateNoUtils.gens("DB-", "-" + String.valueOf(new Random().nextLong()).substring(3, 7)));
            tbTransfer.setFarmId(getFarmUUID());
            tbTransfer.setCreateBy(getFarmUserCode());
            tbTransfer.setCreateTime(new Date());
            tbTransfer.setStatus(0);
        }
        try {
            //如果失败 事务回滚
            tbTransferService.batchInsertTbTransfer(tbTransfers);
        } catch (Exception e) {
            e.printStackTrace();
            return error("新增调拨失败");
        }
        return success();
    }

    /**
     * 新增调拨记录--入库
     */
    @PostMapping("/storage")
    @ResponseBody
    public AjaxResult storage(@RequestBody TbTransfer tbTransfer) {
        //先判断参数是否异常
        if (ValidationUtil.isEmpty(tbTransfer.getTransferId())) {
            return error("请输入调拨记录ID");
        }
        tbTransfer.setFarmId(getFarmUUID());
        TbTransfer tbTf = tbTransferService.selectTbTransferById(tbTransfer);
        if (1 == tbTf.getStatus()) {
            return error("已入库，无法重复操作");
        }

        tbTf.setStatus(1);
        try {
            //如果失败 事务回滚
            tbTransferService.addStorage(tbTf, getFarmUUID(), getFarmUserCode());
        } catch (Exception e) {
            e.printStackTrace();
            return error("调拨入库操作失败");
        }

        return success();
    }


    /**
     * 查询调拨记录详情
     */
    @PostMapping("/detail/info")
    @ResponseBody
    public AjaxResult getDetailInfo(@RequestBody TbTransfer tbTransfer) {
        tbTransfer.setFarmId(getFarmUUID());
        TbTransfer detailVo = tbTransferService.selectTbTransferById(tbTransfer);
        if (detailVo != null) {
            return success(detailVo);
        } else {
            return error("查询失败");
        }
    }

    /**
     * 修改保存调拨记录
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody TbTransfer tbTransfer) {
        tbTransfer.setFarmId(getFarmUUID());
        return toAjax(tbTransferService.updateTbTransfer(tbTransfer));
    }

    /**
     * 删除调拨记录
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbTransfer tbTransfer) {
        tbTransfer.setFarmId(getFarmUUID());
        return toAjax(tbTransferService.deleteTbTransferById(tbTransfer));
    }

}
