package org.zcy.agriculture.operate.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zcy.agriculture.json.JSON;
import org.zcy.agriculture.operate.common.config.Global;
import org.zcy.agriculture.operate.common.json.JSONObject;
import org.zcy.agriculture.operate.common.utils.http.HttpUtilsOp;

/**
 * 获取地址类
 * 
 * @author numberone
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public static String getRealAddressByIP(String ip)
    {
        String address = "XX XX";

        // 内网不查询
        if (IpUtilsOp.internalIp(ip))
        {
            return "内网IP";
        }
        if (Global.isAddressEnabled())
        {
            String rspStr = HttpUtilsOp.sendPost(IP_URL, "ip=" + ip);
            if (StringUtilsOp.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }
            JSONObject obj;
            try
            {
                obj = JSON.unmarshal(rspStr, JSONObject.class);
                JSONObject data = obj.getObj("data");
                String region = data.getStr("region");
                String city = data.getStr("city");
                address = region + " " + city;
            }
            catch (Exception e)
            {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return address;
    }
}
