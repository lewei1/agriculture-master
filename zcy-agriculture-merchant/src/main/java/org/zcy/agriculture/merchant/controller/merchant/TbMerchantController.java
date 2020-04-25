package org.zcy.agriculture.merchant.controller.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.constants.BusinessType;
import org.zcy.agriculture.constants.Log;
import org.zcy.agriculture.entity.TbMerchant;
import org.zcy.agriculture.merchant.controller.BaseController;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbMerchantService;

import java.util.List;

/**
 * 商户（超级管理员，注册用户） 信息操作处理
 *
 * @author numberone
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/system/tbMerchant")
public class TbMerchantController extends BaseController {

    @Autowired
    private ITbMerchantService tbMerchantService;

    /**
     * 查询商户（超级管理员，注册用户）列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbMerchant tbMerchant) {
        startPage();
        List<TbMerchant> list = tbMerchantService.selectTbMerchantList(tbMerchant);
        return getDataTable(list);
    }



    /**
     * 新增保存商户（超级管理员，注册用户）
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbMerchant tbMerchant) {
        return toAjax(tbMerchantService.insertTbMerchant(tbMerchant));
    }


    /**
     * 修改保存商户（超级管理员，注册用户）
     */
    @Log(title = "商户（超级管理员，注册用户）", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbMerchant tbMerchant) {
        return toAjax(tbMerchantService.updateTbMerchant(tbMerchant));
    }

    /**
     * 删除商户（超级管理员，注册用户）
     */
    @Log(title = "商户（超级管理员，注册用户）", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tbMerchantService.deleteTbMerchantByIds(ids));
    }

}
