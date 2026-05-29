package com.skeleton.common.core.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    BAD_REQUEST(400, "参数错误"),
    UNAUTHORIZED(401, "未授权，请登录"),
    FORBIDDEN(403, "无权限访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),
    INTERNAL_ERROR(500, "服务器内部错误"),
    USERNAME_EXIST(1001, "用户名已存在"),
    USER_NOT_FOUND(1002, "用户不存在"),
    PASSWORD_ERROR(1003, "密码错误"),
    TOKEN_EXPIRED(1004, "Token已过期"),
    TOKEN_INVALID(1005, "Token无效"),
    CAPTCHA_ERROR(1006, "验证码错误");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
