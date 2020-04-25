package org.zcy.agriculture.merchant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.entity.TbResDeviceAttributes;
import org.zcy.agriculture.enums.DevStatusEnum;
import org.zcy.agriculture.enums.DevTypeEnum;
import org.zcy.agriculture.enums.ThingsboardDeviceEnum;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.param.LiveStreamingParam;
import org.zcy.agriculture.service.ITbLiveStreamingService;
import org.zcy.agriculture.service.ITbResDeviceAttributesService;
import org.zcy.agriculture.service.ITbResDeviceService;
import org.zcy.agriculture.service.IThingsboardService;
import org.zcy.agriculture.util.HttpCodeMsg;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.*;

import java.util.HashMap;
import java.util.List;

/**
 * 视频直播 信息操作处理
 */
@Controller
@RequestMapping("/api/livestreaming")
public class TbLiveStreamingController extends BaseController {
    private String prefix = "api/livestreaming";

    @Autowired
    private ITbLiveStreamingService tbLiveStreamingService;

    @Autowired
    private ITbResDeviceService tbResDeviceService;

    @Autowired
    private IThingsboardService tingsboardService;

    @GetMapping()
    public String tbLiveStreaming() {
        return prefix + "/tbLiveStreaming";
    }


    /**
     * 查询摄像头列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody LiveStreamingParam liveStreamingParam) {
        startPage();
        liveStreamingParam.setFarmId(getFarmUUID());
        List<LiveStreamingDetailVo> list = tbLiveStreamingService.selectCameraList(liveStreamingParam);
        return getDataTable(list);
    }


    /**
     * 添加摄像头
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 添加摄像头
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody LiveStreamingParam liveStreamingParam) {
        if (ValidationUtil.isEmpty(liveStreamingParam.getDevNum())) {
            return error("摄像头ID不能为空");
        }
        if (ValidationUtil.isEmpty(liveStreamingParam.getDevName())) {
            return error("摄像头名称不能为空");
        }
        if (liveStreamingParam.getDevName().length() > 20) {
            return error("摄像头名称不能大于20个字符");
        }
        if (tbResDeviceService.getCountFromTbResDeviceByDevNum(liveStreamingParam.getDevNum()) > 0) {
            return error("设备已添加，不可重复添加");
        }
        //先注释，方便测试
        //确保设备序列号在物联网端是能查询得到的
        if (tbResDeviceService.isDeviceExistinDevice(liveStreamingParam.getDevNum()) == ThingsboardDeviceEnum.NOT_EXIST.getCode()) {
            return error("设备不存在");
        }
        //默认状态是待机
        liveStreamingParam.setStatus(3);
        liveStreamingParam.setFarmId(getFarmUUID());
        return toAjax(tbLiveStreamingService.insertCameraDevice(liveStreamingParam));
    }

    /**
     * 修改设备
     */
    @GetMapping("/edit/{devId}")
    public String edit(@PathVariable("devId") Long devId, ModelMap mmap) {
        LiveStreamingParam liveStreamingParam = new LiveStreamingParam();
        liveStreamingParam.setFarmId(getFarmUUID());
        liveStreamingParam.setDevId(devId);
        LiveStreamingDetailVo liveStreamingDetailVo = tbLiveStreamingService.selectCameraById(liveStreamingParam);
        mmap.put("liveStreamingDetailVo", liveStreamingDetailVo);
        return prefix + "/edit";
    }

    /**
     * 查询摄像头详情
     */
    @PostMapping("/detail/info")
    @ResponseBody
    public AjaxResult getDetailInfo(@RequestBody LiveStreamingParam liveStreamingParam) {
        liveStreamingParam.setFarmId(getFarmUUID());
        LiveStreamingDetailVo detailVo = tbLiveStreamingService.selectCameraById(liveStreamingParam);
        if (detailVo != null) {
            return success(detailVo);
        } else {
            return error("查询失败");
        }
    }

    /**
     * 修改并保存设备
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody LiveStreamingParam liveStreamingParam) {
        if (ValidationUtil.isEmpty(liveStreamingParam.getDevId())) {
            return error("摄像头ID不能为空");
        }
        if (ValidationUtil.isEmpty(liveStreamingParam.getDevNum())) {
            return error("摄像头序列号不能为空");
        }
        if (ValidationUtil.isEmpty(liveStreamingParam.getDevName())) {
            return error("摄像头名称不能为空");
        }
        if (liveStreamingParam.getDevName().length() > 20) {
            return error("摄像头名称不能大于20个字符");
        }
        //确保设备序列号在物联网端是能查询得到的
        if (tbResDeviceService.isDeviceExistinDevice(liveStreamingParam.getDevNum()) == ThingsboardDeviceEnum.NOT_EXIST.getCode()) {
            return error("设备不存在");
        }
        //默认状态是待机
        liveStreamingParam.setStatus(3);
        liveStreamingParam.setFarmId(getFarmUUID());
        return toAjax(tbLiveStreamingService.updateCameraDevice(liveStreamingParam));
    }

    /**
     * 删除设备
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody LiveStreamingParam liveStreamingParam) {
        if (ValidationUtil.isEmpty(liveStreamingParam.getDevId())) {
            return error("摄像头ID不能为空");
        }
        liveStreamingParam.setFarmId(getFarmUUID());
        return toAjax(tbLiveStreamingService.deleteCameraDeviceById(liveStreamingParam));
    }


    /**
     * 获取直播源
     *
     * @param liveStreamingParam
     * @return
     */
    @PostMapping("/getLiveStreaming")
    @ResponseBody
    public AjaxResult getLiveStreaming(@RequestBody LiveStreamingParam liveStreamingParam) {
        //传入的设备序列号不能为空
        if (ValidationUtil.isEmpty(liveStreamingParam.getDevNum())) {
            return error("设备序列号不能为空");
        }

        //校验该设备是不是属于该用户
        liveStreamingParam.setFarmId(getFarmUUID());
        LiveStreamingDetailVo detailVo = tbLiveStreamingService.selectCameraByDevNum(liveStreamingParam);
        if (detailVo == null) {
            return error("设备不存在");
        }

        HttpCodeMsg msg = tingsboardService.getLiveStreamingFromThingsboard(liveStreamingParam.getDevNum());

        if (msg.getCode() == 401) { //token已过期，重新设置
            return error("服务器token过期，稍后再试");
        }
        if (msg.getCode() != 200 || msg.getMsg() == null) {
            return error("解析错误");
        }

        HashMap<String, String> map = new HashMap<>();
        try {
            JSONArray jsonArr = JSONArray.parseArray(msg.getMsg());
            map.put(jsonArr.getJSONObject(0).getString("key"), jsonArr.getJSONObject(0).getString("value"));
        } catch (Exception e) {
            return error("解析错误");
        }
        return success(map);

    }


}
