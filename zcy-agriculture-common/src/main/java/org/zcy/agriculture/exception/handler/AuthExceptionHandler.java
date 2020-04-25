package org.zcy.agriculture.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zcy.agriculture.constants.ErrorCodeEnum;
import org.zcy.agriculture.exception.AuthException;
import org.zcy.agriculture.page.AjaxResult;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(value = AuthException.class)
    @ResponseBody
    public AjaxResult handleAuthException(AuthException exception) {
        ErrorCodeEnum errorCode = exception.getErrorCode();
        return AjaxResult.error(errorCode.getCode(), errorCode.getVal());
    }

}
