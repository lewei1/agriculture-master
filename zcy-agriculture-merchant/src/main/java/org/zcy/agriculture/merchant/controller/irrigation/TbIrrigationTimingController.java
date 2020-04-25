package org.zcy.agriculture.merchant.controller.irrigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.TbIrrigationTiming;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.param.irrigation.IrrigationTimingDetailParam;
import org.zcy.agriculture.service.irrigation.ITbIrrigationTimingService;
import org.zcy.agriculture.util.ValidationUtil;

import java.util.List;

/**
 * 灌溉分组定时 信息操作处理
 *
 * @author numberone
 * @date 2019-07-02
 */
@Controller
@RequestMapping("/api/irrigation/timing")
public class TbIrrigationTimingController extends BaseController {

    @Autowired
    private ITbIrrigationTimingService tbIrrigationTimingService;

    /**
     * 查询灌溉分组定时列表
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(Long groupId) {
        if(ValidationUtil.isEmpty(groupId))
            return error("分组id"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        TbIrrigationTiming irrigationTiming = new TbIrrigationTiming();
        irrigationTiming.setGroupId(groupId);
        List<TbIrrigationTiming> list = tbIrrigationTimingService.selectTbIrrigationTimingList(irrigationTiming);
        return success(list);
    }


    /**
     * 新增或更新-灌溉分组定时
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody IrrigationTimingDetailParam irrigationTimingDetailParam) {

        Long groupId = irrigationTimingDetailParam.getGroupId();
        if(ValidationUtil.isEmpty(groupId))
            return error("分组id"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        int result = -1;

        try {
            TbIrrigationTiming irrigationTiming = new TbIrrigationTiming();
            irrigationTiming.setGroupId(groupId);
            List<TbIrrigationTiming> irrigationTimingList = tbIrrigationTimingService.selectTbIrrigationTimingList(irrigationTiming);

            if(ValidationUtil.isEmpty(irrigationTimingList)) {
                //插入
                result = tbIrrigationTimingService.insertTbIrrigationTimingDetail(irrigationTimingDetailParam);
            }else {
                //更新
                result = tbIrrigationTimingService.updateTbIrrigationTimingDetail(irrigationTimingDetailParam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toAjax(result);
    }


    /**
     * 更新定时状态
     */
    @GetMapping("/updateStatus")
    @ResponseBody
    public AjaxResult updateStatus(Long groupId, Integer timingStatus) {
        if(ValidationUtil.isEmpty(groupId))
            return error("分组id"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        if(ValidationUtil.isEmpty(timingStatus))
            return error("定时状态"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        else if(timingStatus > 1 || timingStatus < 0){
            return error("定时状态"+ ValidationConstants.SUFFIX_ILLEGAL_PARAMS);
        }

        return toAjax(tbIrrigationTimingService.updateTbIrrigationTimingByGroupId(groupId, timingStatus));
    }

}
