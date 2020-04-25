package org.zcy.agriculture.merchant.controller.plot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.NormalOrDeleteEnum;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.TbPlotRegion;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.service.plot.ITbPlotRegionService;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.plot.RegionPlotSidebarVo;

import java.util.List;

/**
 * 地块区域 信息操作处理
 *
 * @author numberone
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/api/plot/region")
public class TbPlotRegionController extends BaseController {

    @Autowired
    private ITbPlotRegionService tbPlotRegionService;


    /**
     * 边栏列表
     */
    @GetMapping("/sidebar")
    @ResponseBody
    public AjaxResult list() {
        List<RegionPlotSidebarVo> list = tbPlotRegionService.selectRegionAndPlotSidebarList(getFarmUUID());
        return success(list);
    }

    /**
     * 地块区域列表
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult regionList() {

        TbPlotRegion region = new TbPlotRegion();
        region.setFarmId(getFarmUUID());
        region.setRegionStatus(NormalOrDeleteEnum.NORMAL.getCode());
        List<TbPlotRegion> plotRegionList = tbPlotRegionService.selectTbPlotRegionList(region);
        return success(plotRegionList);
    }

    /**
     * 新增保存地块区域
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody TbPlotRegion tbPlotRegion) {
        //校验
        AjaxResult validation = validation(tbPlotRegion);
        if(AjaxResult.getResultStatus(validation) == RequestStatus.FAILED.getStatus())
            return validation;

        tbPlotRegion.setFarmId(getFarmUUID());
        tbPlotRegion.setCreateBy(getFarmUserCode());
        int result = tbPlotRegionService.insertTbPlotRegion(tbPlotRegion);
        return toAjax(result);
    }

    /**
     * 删除地块区域
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tbPlotRegionService.deleteTbPlotRegionByIds(ids));
    }


    @Override
    protected AjaxResult validation(Object object) {
        if (object instanceof TbPlotRegion) {
            TbPlotRegion plotRegion = (TbPlotRegion) object;
            if(ValidationUtil.isEmpty(plotRegion.getRegionName()))
                return error("区域名称"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            else {
                //判断当前农场的地块区域名称是否重复
                TbPlotRegion region = new TbPlotRegion();
                region.setRegionStatus(NormalOrDeleteEnum.NORMAL.getCode());
                region.setRegionName(plotRegion.getRegionName());
                region.setFarmId(getFarmUUID());
                List<TbPlotRegion> plotRegions = tbPlotRegionService.selectTbPlotRegionList(region);
                if(!ValidationUtil.isEmpty(plotRegions))
                    return error("区域名称"+ ValidationConstants.SUFFIX_HAS_EXIST);
            }
        }
        return success();
    }
}
