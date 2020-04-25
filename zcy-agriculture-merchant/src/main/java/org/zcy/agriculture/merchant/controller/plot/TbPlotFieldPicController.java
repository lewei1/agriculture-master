package org.zcy.agriculture.merchant.controller.plot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.TbPlotFieldPic;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.param.plot.FieldPicParam;
import org.zcy.agriculture.service.plot.ITbPlotFieldPicService;
import org.zcy.agriculture.util.ValidationUtil;

import java.util.List;

/**
 * 地块对应实地图片 信息操作处理
 *
 * @author numberone
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/api/plot/field/")
public class TbPlotFieldPicController extends BaseController {

    @Autowired
    private ITbPlotFieldPicService tbPlotFieldPicService;

    /**
     * 查询地块对应实地图片列表
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(Long plotId) {
        if(ValidationUtil.isEmpty(plotId))
            return error("地块id"+ ValidationConstants.SUFFIX_NOT_EMPTY);

        TbPlotFieldPic plotFieldPic = new TbPlotFieldPic();
        plotFieldPic.setPlotId(plotId);
        List<TbPlotFieldPic> list = tbPlotFieldPicService.selectTbPlotFieldPicList(plotFieldPic);
        return success(list);
    }


    /**
     * 新增保存地块对应实地图片
     */
    @PostMapping("/addOrUpdateOrDelete")
    @ResponseBody
    public AjaxResult addOrUpdateOrDelete(@RequestBody FieldPicParam fieldPicParam) {

        AjaxResult validation = validation(fieldPicParam);
        if(AjaxResult.getResultStatus(validation) == RequestStatus.FAILED.getStatus())
            return validation;

        return toAjax(tbPlotFieldPicService.insertTbPlotFieldPicList(fieldPicParam));
    }


    /**
     * 删除地块对应实地图片
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tbPlotFieldPicService.deleteTbPlotFieldPicByIds(ids));
    }

    @Override
    protected AjaxResult validation(Object object) {

        if(object instanceof FieldPicParam) {
            FieldPicParam param = (FieldPicParam)object;
            if(ValidationUtil.isEmpty(param.getPlotId()))
                return error("地块ID"+ ValidationConstants.SUFFIX_NOT_EMPTY);
        }
        return super.validation(object);
    }
}
