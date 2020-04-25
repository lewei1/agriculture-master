package org.zcy.agriculture.exception;

import org.zcy.agriculture.constants.ErrorCodeEnum;

public class AuthException extends RuntimeException {

    private ErrorCodeEnum errorCode;

    public AuthException(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }
}
