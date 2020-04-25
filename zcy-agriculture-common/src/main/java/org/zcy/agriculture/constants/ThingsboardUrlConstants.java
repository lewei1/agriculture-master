package org.zcy.agriculture.constants;

public class ThingsboardUrlConstants {
    public static final String ROOT_URL = "http://172.16.5.204:8080";

    public static final String LOGIN_URL = "/api/auth/login";

    public static final String DEVICE_STATUS_URL1 = "/api/plugins/telemetry/DEVICE/";

    public static final String DEVICE_STATUS_URL2 = "/values/attributes";

    public static final String DEVICE_STATUS_URL3 = "/SHARED_SCOPE?keys=videoUrl";

    public static final String DEVICE_TIMESERIES_SUB_URL = "/keys/timeseries";

    public static final String DEVICE_VALUES_TIMESERIES_SUB_URL = "/values/timeseries";

    //物联网统一用户名
    public static final String IOT_USERNAME = "tenant@thingsboard.org"; //"cry2133@gmail.com";
    //物联网统一密码
    public static final String IOT_PASSWORD = "tenant";

    public static String getDeviceUrl(String devNum) {
        return ROOT_URL + DEVICE_STATUS_URL1 + devNum +  DEVICE_STATUS_URL2;
    }

    /*******************************控制设备 *******************************/
    /**
     * 控制设备请求URL
     */
    public static final String DEVICE_CONTROL_URL = "/api/plugins/rpc/device/control";
    /**
     * 控制设备-方法
     */
    public static final String DEVICE_CONTROL_METHOD = "switch";

    public static final String DEVICE_CAMERA_METHOD = "capture";

}
