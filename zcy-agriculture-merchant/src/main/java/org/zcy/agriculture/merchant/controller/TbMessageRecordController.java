package org.zcy.agriculture.merchant.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbMessageRecord;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbMessageRecordService;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 消息记录 信息操作处理
 *
 * @author
 * @date 2019-07-05
 */
@Controller
@RequestMapping("/api/tbMessageRecord")
public class TbMessageRecordController extends BaseController {
    private String prefix = "api/tbMessageRecord";

    @Autowired
    private ITbMessageRecordService tbMessageRecordService;

    @GetMapping()
    public String tbMessageRecord() {
        return prefix + "/tbMessageRecord";
    }

    /**
     * 查询消息记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody TbMessageRecord tbMessageRecord) {
        startPage();
        tbMessageRecord.setReceiveUserId(getFarmUserCode());
        tbMessageRecord.setFarmId(getFarmUUID());
        List<TbMessageRecord> list = tbMessageRecordService.selectTbMessageRecordList(tbMessageRecord);
        return getDataTable(list);
    }


    /**
     * 导出消息记录列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody TbMessageRecord tbMessageRecord) {
        List<TbMessageRecord> list = tbMessageRecordService.selectTbMessageRecordList(tbMessageRecord);
        ExcelUtil<TbMessageRecord> util = new ExcelUtil<TbMessageRecord>(TbMessageRecord.class);
        return util.exportExcel(list, "tbMessageRecord");
    }

    /**
     * 新增消息记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存消息记录
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody TbMessageRecord tbMessageRecord) {
        if (tbMessageRecord.getMessageType() != 0 && tbMessageRecord.getMessageType() != 1) {
            return error("消息类型不正确");
        }
        if (tbMessageRecord.getMessageType() == 0 && ValidationUtil.isEmpty(tbMessageRecord.getFarmId())) {
            return error("农场ID不能为空");
        }
        if (tbMessageRecord.getMessageType() == 1 && ValidationUtil.isEmpty(tbMessageRecord.getReceiveUserId())) {
            return error("消息接收用户不能为空");
        }
        // 任务消息，添加发送方
        if (tbMessageRecord.getMessageType() == 1) {
            tbMessageRecord.setSendUserId(getFarmUserCode());
            tbMessageRecord.setFarmId(null);
        } else {
            //报警消息
            tbMessageRecord.setSendUserId(null);
            tbMessageRecord.setReceiveUserId(null);
        }
        tbMessageRecord.setCreateTime(new Date());
        tbMessageRecord.setMessageStatus(0);
        return toAjax(tbMessageRecordService.insertTbMessageRecord(tbMessageRecord));
    }

    /**
     * 修改消息记录
     */
    @GetMapping("/edit/{messageId}")
    public String edit(@PathVariable("messageId") Long messageId, ModelMap mmap) {
        TbMessageRecord tbMessageRecord = tbMessageRecordService.selectTbMessageRecordById(messageId);
        mmap.put("tbMessageRecord", tbMessageRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存消息记录
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody TbMessageRecord tbMessageRecord) {
        if (tbMessageRecord.getMessageType() != 0 && tbMessageRecord.getMessageType() != 1) {
            return error("消息类型不正确");
        }
        if (tbMessageRecord.getMessageType() == 0 && ValidationUtil.isEmpty(tbMessageRecord.getFarmId())) {
            return error("农场ID不能为空");
        }
        if (tbMessageRecord.getMessageType() == 1 && ValidationUtil.isEmpty(tbMessageRecord.getReceiveUserId())) {
            return error("消息接收用户不能为空");
        }
        // 任务消息，添加发送方
        if (tbMessageRecord.getMessageType() == 1) {
            tbMessageRecord.setSendUserId(getFarmUserCode());
            tbMessageRecord.setFarmId(null);
        } else {
            //报警消息
            tbMessageRecord.setSendUserId(null);
            tbMessageRecord.setReceiveUserId(null);
        }
        tbMessageRecord.setCreateTime(new Date());
        tbMessageRecord.setMessageStatus(0);
        return toAjax(tbMessageRecordService.updateTbMessageRecord(tbMessageRecord));
    }


    /**
     * 批量已读消息 by 消息id
     */
    @PostMapping("/readMessage")
    @ResponseBody
    public AjaxResult readMessage(@RequestBody List<TbMessageRecord> tbMessageRecords) {

        List<Long> messageIds = new ArrayList<>();
        for (TbMessageRecord tbMessageRecord : tbMessageRecords) {
            messageIds.add(tbMessageRecord.getMessageId());
        }
        if (ValidationUtil.isEmpty(messageIds)) {
            return error("消息id不能为空");
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("messageStatus", 1);//1为已读
        map.put("receiveUserId", getFarmUserCode());
        map.put("farmId", getFarmUUID());
        map.put("messageIds", messageIds.toArray());
        return toAjax(tbMessageRecordService.updateMessageStatusByIds(map));
    }


    /**
     * 已读全部消息
     */
    @PostMapping("/readAllMessage")
    @ResponseBody
    public AjaxResult readAllMessage(@RequestBody TbMessageRecord tbMessageRecord) {
        if (ValidationUtil.isEmpty(tbMessageRecord.getMessageType())) {
            return error("消息类型不能为空");
        }
        //报警消息
        if (tbMessageRecord.getMessageType() == 0) {
            tbMessageRecord.setFarmId(getFarmUUID());
        } else {
            // 任务消息
            tbMessageRecord.setReceiveUserId(getFarmUserCode());
        }
        tbMessageRecord.setMessageStatus(1); // 1为已读
        return toAjax(tbMessageRecordService.updateMessageStatus(tbMessageRecord));
    }


    /**
     * 删除消息记录
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody TbMessageRecord tbMessageRecord) {

        if (ValidationUtil.isEmpty(tbMessageRecord.getFarmId()) && ValidationUtil.isEmpty(tbMessageRecord.getSendUserId()) && ValidationUtil.isEmpty(tbMessageRecord.getReceiveUserId())) {
            return error("用户id或农场id不能为空");
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        if (!ValidationUtil.isEmpty(tbMessageRecord.getSendUserId())) {
            //发送方删除任务消息
            map.put("sendUserId", getFarmUserCode());
        }
        if (!ValidationUtil.isEmpty(tbMessageRecord.getReceiveUserId())) {
            //接收方删除任务消息
            map.put("receiveUserId", getFarmUserCode());
        }
        if (!ValidationUtil.isEmpty(tbMessageRecord.getFarmId())) {
            //农场方删除报警消息
            map.put("farmId", getFarmUUID());
        }
        map.put("messageIds", new Long[]{tbMessageRecord.getMessageId()});
        return toAjax(tbMessageRecordService.deleteTbMessageRecordByIds(map));
    }

}
