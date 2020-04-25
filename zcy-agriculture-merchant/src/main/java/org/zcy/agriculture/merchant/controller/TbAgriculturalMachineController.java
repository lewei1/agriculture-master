package org.zcy.agriculture.merchant.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbAgriculturalMachine;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.system.ISysDictDataService;
import org.zcy.agriculture.service.ITbAgriculturalMachineService;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 农机 信息操作处理
 *
 * @author linlq lky.
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/tbAgriculturalMachine")
public class TbAgriculturalMachineController extends BaseController {
    private String prefix = "system/tbAgriculturalMachine";

    @Autowired
    private ITbAgriculturalMachineService tbAgriculturalMachineService;

    @Autowired
    private ISysDictDataService sysDictDataService;

    @GetMapping()
    public String tbAgriculturalMachine() {
        return prefix + "/tbAgriculturalMachine";
    }

    /**
     * 查询农机列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbAgriculturalMachine tbAgriculturalMachine) {
        startPage();
        tbAgriculturalMachine.setFarmId(getFarmUUID());
        List<TbAgriculturalMachine> list = tbAgriculturalMachineService.selectTbAgriculturalMachineList(tbAgriculturalMachine);
        return getDataTable(list);
    }

    /**
     * 查询农机列表按照农机类型分类
     */
    @PostMapping("/listByType")
    @ResponseBody
    public AjaxResult listByType(@RequestBody TbAgriculturalMachine tbAgriculturalMachine) {
        //startPage();
        tbAgriculturalMachine.setFarmId(getFarmUUID());
        List<TbAgriculturalMachine> list = tbAgriculturalMachineService.selectTbAgriculturalMachineList(tbAgriculturalMachine);

        List<Object> alllist = new ArrayList<>();
        Map<String, List<TbAgriculturalMachine>> map = new HashMap<String, List<TbAgriculturalMachine>>();
        for (TbAgriculturalMachine agriculturalMachine : list) {
            if (!ValidationUtil.isEmpty(agriculturalMachine.getMachineTypeName())) {
                if (ValidationUtil.isEmpty(map.get(agriculturalMachine.getMachineTypeName()))) {
                    List<TbAgriculturalMachine> listAM = new ArrayList<TbAgriculturalMachine>();
                    listAM.add(agriculturalMachine);
                    map.put(agriculturalMachine.getMachineTypeName(), listAM);
                } else {
                    List<TbAgriculturalMachine> listAM = map.get(agriculturalMachine.getMachineTypeName());
                    listAM.add(agriculturalMachine);
                    map.put(agriculturalMachine.getMachineTypeName(), listAM);
                }
            }
        }
        Set<Map.Entry<String, List<TbAgriculturalMachine>>> entrySet = map.entrySet();
        for (Map.Entry<String, List<TbAgriculturalMachine>> entry : entrySet) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            tmpMap.put("name", entry.getKey());
            tmpMap.put("id", entry.getValue().get(0).getMachineTypeId());
            tmpMap.put("children", entry.getValue());
            alllist.add(tmpMap);
        }
        return success(alllist);
    }

    /**
     * 查询农机详情
     */
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult detail(@RequestBody TbAgriculturalMachine tbAgriculturalMachine) {
        tbAgriculturalMachine.setFarmId(getFarmUUID());
        TbAgriculturalMachine tbAM = tbAgriculturalMachineService.selectTbAgriculturalMachineByClass(tbAgriculturalMachine);
        return success(tbAM);
    }

    /**
     * 搜索
     */
    @PostMapping("/search")
    @ResponseBody
    public AjaxResult search(@RequestBody TbAgriculturalMachine tbAgriculturalMachine) {
        tbAgriculturalMachine.setFarmId(getFarmUUID());
        List<TbAgriculturalMachine> tbAM = tbAgriculturalMachineService.selectTbAgriculturalMachineByName(tbAgriculturalMachine);
        return success(tbAM);
    }

    /**
     * 查询图标
     */
    @PostMapping("/selectImg")
    @ResponseBody
    public TableDataInfo selectImg() {
        List<TbAgriculturalMachine> tbAMImg = tbAgriculturalMachineService.selectTbAgriculturalMachineImg();
        return getDataTable(tbAMImg);
    }

    /**
     * 更新图标
     */
    @PostMapping("/updateImg")
    @ResponseBody
    public AjaxResult updateImg(@RequestBody TbAgriculturalMachine tbAgriculturalMachine) {
        tbAgriculturalMachine.setFarmId(getFarmUUID());
        tbAgriculturalMachineService.updateTbAgriculturalMachine(tbAgriculturalMachine);
        return success();
    }


    /**
     * 导出农机列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbAgriculturalMachine tbAgriculturalMachine) {
        tbAgriculturalMachine.setFarmId(getFarmUUID());
        List<TbAgriculturalMachine> list = tbAgriculturalMachineService.selectTbAgriculturalMachineList(tbAgriculturalMachine);
        ExcelUtil<TbAgriculturalMachine> util = new ExcelUtil<TbAgriculturalMachine>(TbAgriculturalMachine.class);
        return util.exportExcel(list, "tbAgriculturalMachine");
    }

    /**
     * 新增农机
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody TbAgriculturalMachine info) {
        //先判断参数是否异常
        if (ValidationUtil.isEmpty(info.getMachineTypeId())) {
            return error("请选择农机类型");
        }
        if (ValidationUtil.isEmpty(info.getName())) {
            return error("请填写农机名称");
        }
        if (ValidationUtil.isEmpty(info.getModel())) {
            return error("请填写农机型号");
        }
        if (ValidationUtil.isEmpty(info.getPrice())) {
            return error("请填写农机使用费用");
        }
        if (ValidationUtil.isEmpty(info.getImage())) {
            return error("请选择农机图标");
        }
        //设置农机所属的农场ID
        info.setFarmId(getFarmUUID());
        //判断id是否为空,id为空时为新增
        if (ValidationUtil.isEmpty(info.getMachineId())) {
            //新增操作
            int num = tbAgriculturalMachineService.insertTbAgriculturalMachine(info);
            if (num < 1) {
                return error("新增农机失败");
            }
        } else {
            //修改操作
            tbAgriculturalMachineService.updateTbAgriculturalMachine(info);
        }
        return success();
    }


    /**
     * 新增保存农机
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody TbAgriculturalMachine tbAgriculturalMachine) {
        tbAgriculturalMachine.setFarmId(getFarmUUID());
        return toAjax(tbAgriculturalMachineService.insertTbAgriculturalMachine(tbAgriculturalMachine));
    }

    /**
     * 修改农机
     */
    @PostMapping("/edit/{machineId}")
    @ResponseBody
    public AjaxResult edit(@PathVariable("machineId") Long machineId) {
        TbAgriculturalMachine tbAgriculturalMachine = new TbAgriculturalMachine();
        tbAgriculturalMachine.setFarmId(getFarmUUID());
        tbAgriculturalMachine.setMachineId(machineId);
        TbAgriculturalMachine tbAM = tbAgriculturalMachineService.selectTbAgriculturalMachineByClass(tbAgriculturalMachine);
        return success(tbAM);
    }

    /**
     * 修改保存农机
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody TbAgriculturalMachine tbAgriculturalMachine) {
        tbAgriculturalMachine.setFarmId(getFarmUUID());
        tbAgriculturalMachine.setUpdateTime(new Date());
        return toAjax(tbAgriculturalMachineService.updateTbAgriculturalMachine(tbAgriculturalMachine));
    }

    /**
     * 删除农机
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbAgriculturalMachine tbAgriculturalMachine) {
        tbAgriculturalMachine.setFarmId(getFarmUUID());
        return toAjax(tbAgriculturalMachineService.deleteTbAgriculturalMachineById(tbAgriculturalMachine));
    }

}
