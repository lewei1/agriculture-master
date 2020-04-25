package org.zcy.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.redis.JedisClient;
import org.zcy.agriculture.service.IThingsboardService;
import org.zcy.agriculture.util.HttpCodeMsg;
import org.zcy.agriculture.util.HttpUtils;
import org.zcy.agriculture.vo.ThingsboardTokenVo;

import java.util.HashMap;
import java.util.Map;

@Service
public class IThingsboardServiceImpl extends BaseServiceImpl implements IThingsboardService {
    @Autowired
    private JedisClient jedisClient;


    //doPostHead
    public HttpCodeMsg getDeviceAttributesFromThingsboard(String devNum) {
        String url = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1 + devNum + ThingsboardUrlConstants.DEVICE_STATUS_URL2;
        HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(url, getIOTToken());

        return msg;
    }

    //doPostHead
    public HttpCodeMsg getLiveStreamingFromThingsboard(String devNum) {
        String url = ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_STATUS_URL1 + devNum + ThingsboardUrlConstants.DEVICE_STATUS_URL2 + ThingsboardUrlConstants.DEVICE_STATUS_URL3;
        HttpCodeMsg msg = HttpUtils.doGetAndReturnCodeMsg(url, getIOTToken());
        return msg;
    }

}
