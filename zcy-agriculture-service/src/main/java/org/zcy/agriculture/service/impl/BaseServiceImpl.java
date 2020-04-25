package org.zcy.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.entity.SysDictData;
import org.zcy.agriculture.entity.SysDictType;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.mapper.system.SysDictDataMapper;
import org.zcy.agriculture.mapper.system.SysDictTypeMapper;
import org.zcy.agriculture.param.irrigation.IrrigationIOTParam;
import org.zcy.agriculture.redis.JedisClient;
import org.zcy.agriculture.util.HttpUtils;
import org.zcy.agriculture.util.RedisKeyUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.ThingsboardTokenVo;
import org.zcy.agriculture.vo.irrigation.IrrigationReturnIOTVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公有service层方法
 */
@Service
public abstract class BaseServiceImpl {

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    @Autowired
    private JedisClient jedisClient;

    /**
     * 通过字典表的中文名字查找列表
     * @param dictName
     * @return
     */
    protected List<SysDictData> getDictList(String dictName) {
        List<SysDictData> resultList = Lists.newArrayList();

        if(ValidationUtil.isEmpty(dictName)) {
            return resultList;
        }

        SysDictType dictType = new SysDictType();
        dictType.setDictName(dictName);
        List<SysDictType> dictTypeList = sysDictTypeMapper.selectDictTypeList(dictType);
        if(!ValidationUtil.isEmpty(dictTypeList) && dictTypeList.size() == 1) {
            return sysDictDataMapper.selectDictDataByType(dictTypeList.get(0).getDictType());
        }
        return resultList;
    }

    /**
     * 通过字典表数据的id查找数据
     * @param dictCode
     * @return
     */
    protected SysDictData getDictData(Long dictCode) {
        SysDictData sysDictData = new SysDictData();
        if(ValidationUtil.isEmpty(dictCode)) {
            return sysDictData;
        }
        return sysDictDataMapper.selectDictDataById(dictCode);
    }


    /**
     * 获取物联网token
     * @return
     */
    public String getIOTToken() {

        Boolean exists = jedisClient.exists(RedisKeyUtils.getIOTTokenKey());
        if(!exists) {
            //给物联网发请求，重新获取token
            Map<String, String> map = new HashMap<>();
            map.put("username", ThingsboardUrlConstants.IOT_USERNAME);
            map.put("password", ThingsboardUrlConstants.IOT_PASSWORD);

            String res = HttpUtils.doPost(ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.LOGIN_URL, JSONObject.toJSONString(map));

            /**解析请求出来的字符串*/
            JSONObject jsonObject = JSONObject.parseObject(res);
            ThingsboardTokenVo tokenVo = JSONObject.toJavaObject(jsonObject, ThingsboardTokenVo.class);
            String token = tokenVo.getToken();
            if (token != null) {
                jedisClient.set(RedisKeyUtils.getIOTTokenKey(), tokenVo.getToken());
                //提前100秒过期，触发请求更新
                jedisClient.expire(RedisKeyUtils.getIOTTokenKey(),43100);
            }

            return token;
        }else
            return jedisClient.get(RedisKeyUtils.getIOTTokenKey());
    }


    /**
     * 控制物联网-灌溉设备
     * @param param
     * @return
     */
    protected List<TbResDevice> processIOTDevice(IrrigationIOTParam param) {
        //发送灌溉设备控制请求
        String result = HttpUtils.doPostWithToken(ThingsboardUrlConstants.ROOT_URL + ThingsboardUrlConstants.DEVICE_CONTROL_URL , JSONObject.toJSONString(param), getIOTToken());
        if(ValidationUtil.isEmpty(result))
            return null;

        IrrigationReturnIOTVo returnIOTVo = JSONObject.parseObject(result, IrrigationReturnIOTVo.class);
        return returnIOTVo.getDeviceList();
    }

}
