package org.zcy.agriculture.merchant.aspect;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.exception.ServiceException;
import org.zcy.agriculture.page.AjaxResult;

import com.alibaba.fastjson.JSONObject;

@ControllerAdvice
public class ControllerExceptionAdvice {

	public static Logger log = LogManager.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public AjaxResult exception(HttpServletRequest request, ServiceException ex) {
        log.error(String.format("requestid:%s,controller:%s,error:%s,parameter:%s", request.getParameter("requestId"), request.getRequestURI(), ex.getMessage(), JSONObject.toJSON(request.getParameterMap())));
        return AjaxResult.error(ex.getStatus(), ex.getMessage());
    }


    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public AjaxResult invalidexception(HttpServletRequest request, InvalidFormatException ex) {
        log.error(String.format("requestid:%s,controller:%s,error:%s,parameter:%s", request.getParameter("requestId"), request.getRequestURI(), ex.getMessage(), JSONObject.toJSON(request.getParameterMap())));
        return AjaxResult.error("请求参数有误，请检查请求参数");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public AjaxResult notReadableException(HttpServletRequest request, HttpMessageNotReadableException ex) {
        log.error(String.format("requestid:%s,controller:%s,error:%s,parameter:%s", request.getParameter("requestId"), request.getRequestURI(), ex.getMessage(), JSONObject.toJSON(request.getParameterMap())));
        return AjaxResult.error("请求参数有误，请检查请求参数");
    }


}
