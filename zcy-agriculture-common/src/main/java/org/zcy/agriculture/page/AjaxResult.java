package org.zcy.agriculture.page;

import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.util.ValidationUtil;

import java.util.HashMap;

/**
 * 操作消息提醒
 *
 * @author numberone
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 Message 对象
     */
    public AjaxResult() {
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static AjaxResult error() {
        return error(RequestStatus.PARAM_REQUIRED.getStatus(), "操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(String msg) {
        return error(RequestStatus.FAILED.getStatus(), msg);
    }

    /**
     * 返回错误消息
     *
     * @param code 错误码
     * @param msg  内容
     * @return 错误消息
     */
    public static AjaxResult error(int code, String msg) {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", RequestStatus.SUCCESS.getStatus());
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param obj 内容
     * @return 成功消息
     */
    public static AjaxResult success(Object obj) {
        AjaxResult json = new AjaxResult();
        json.put("msg", "");
        json.put("code", RequestStatus.SUCCESS.getStatus());
        json.put("rows", obj);
        return json;
    }
    /**
     * 返回成功消息
     *
     * @param obj 内容
     * @return 成功消息
     */
    public static AjaxResult success(Object obj,String msg) {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", RequestStatus.SUCCESS.getStatus());
        json.put("rows", obj);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param key   键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 获取结果状态值
     * @param ajaxResult
     * @return
     */
    public static Integer getResultStatus(AjaxResult ajaxResult) {

        if(!ValidationUtil.isEmpty(ajaxResult)) {
            Object code = ajaxResult.get("code");
            if(!ValidationUtil.isEmpty(code)) {
                return (Integer)code;
            }
        }
        return RequestStatus.FAILED.getStatus();
    }

    /**
     * 获取结果数据
     * @param ajaxResult
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getResultRows(AjaxResult ajaxResult, Class<T> tClass) {

        if(!ValidationUtil.isEmpty(ajaxResult)) {
            Object data = ajaxResult.get("rows");
            if(!ValidationUtil.isEmpty(data)) {
                return (T)data;
            }
        }
        return null;
    }

    /**
     * 获取结果消息
     * @param ajaxResult
     * @return
     */
    public static String getResultMsg(AjaxResult ajaxResult) {

        if(!ValidationUtil.isEmpty(ajaxResult)) {
            Object msg = ajaxResult.get("msg");
            if(!ValidationUtil.isEmpty(msg)) {
                return (String)msg;
            }
        }
        return "";
    }
}
