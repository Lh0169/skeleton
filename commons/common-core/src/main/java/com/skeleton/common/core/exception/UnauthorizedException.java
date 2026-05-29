package com.skeleton.common.core.exception;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException() { super(401, "未授权，请登录"); }
    public UnauthorizedException(String message) { super(401, message); }
}
