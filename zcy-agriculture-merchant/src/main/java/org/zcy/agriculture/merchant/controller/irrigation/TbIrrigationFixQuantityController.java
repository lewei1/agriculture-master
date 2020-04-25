package org.zcy.agriculture.merchant.controller.irrigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.TbIrrigationFixQuantity;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.param.irrigation.IrrigationFixQuantityDetailParam;
import org.zcy.agriculture.service.irrigation.ITbIrrigationFixQuantityService;
import org.zcy.agriculture.util.ValidationUtil;

import java.util.List;

/**
 * 灌溉分组定量 信息操作处理
 *
 * @author numberone
 * @date 2019-07-02
 */
@Controller
@RequestMapping("/api/irrigation/fix/quantity")
public class TbIrrigationFixQuantityController extends BaseController {

    @Autowired
    private ITbIrrigationFixQuantityService tbIrrigationFixQuantityService;


    /**
     * 查询灌溉分组定量列表
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(Long groupId) {
        if(ValidationUtil.isEmpty(groupId))
            return error("分组id"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        TbIrrigationFixQuantity fixQuantity = new TbIrrigationFixQuantity();
        fixQuantity.setGroupId(groupId);
        List<TbIrrigationFixQuantity> list = tbIrrigationFixQuantityService.selectTbIrrigationFixQuantityList(fixQuantity);
        return success(list);
    }



    /**
         * 新增或更新-保存灌溉分组定量
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody IrrigationFixQuantityDetailParam param) {
        Long groupId = param.getGroupId();
        if(ValidationUtil.isEmpty(groupId))
            return error("分组id"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        int result = -1;
        try {
            TbIrrigationFixQuantity fixQuantity = new TbIrrigationFixQuantity();
            fixQuantity.setGroupId(groupId);
            List<TbIrrigationFixQuantity> fixQuantityList = tbIrrigationFixQuantityService.selectTbIrrigationFixQuantityList(fixQuantity);

            if(ValidationUtil.isEmpty(fixQuantityList)) {
                //插入
                result = tbIrrigationFixQuantityService.insertIrrigationFixQuantityDetail(param);
            }else {
                //更新
                result = tbIrrigationFixQuantityService.updateIrrigationFixQuantityDetail(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toAjax(result);
    }


    /**
     * 更新定量状态
     */
    @GetMapping("/updateStatus")
    @ResponseBody
    public AjaxResult updateStatus(Long groupId, Integer fixQuantityStatus) {
        if(ValidationUtil.isEmpty(groupId))
            return error("分组id"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        if(ValidationUtil.isEmpty(fixQuantityStatus))
            return error("定量状态"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        else if(fixQuantityStatus > 1 || fixQuantityStatus < 0){
            return error("定量状态"+ ValidationConstants.SUFFIX_ILLEGAL_PARAMS);
        }

        return toAjax(tbIrrigationFixQuantityService.updateIrrigationFixQuantityByGroupId(groupId, fixQuantityStatus));
    }


}
