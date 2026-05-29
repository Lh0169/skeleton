package com.skeleton.common.core.exception;

public class ForbiddenException extends BusinessException {
    public ForbiddenException() { super(403, "无权限访问"); }
    public ForbiddenException(String message) { super(403, message); }
}
