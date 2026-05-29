package com.skeleton.common.core.exception;

import com.skeleton.common.core.enums.ResultCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final int code;

    public BusinessException(ResultCode rc) { super(rc.getMessage()); this.code = rc.getCode(); }
    public BusinessException(int code, String message) { super(message); this.code = code; }
    public BusinessException(String message) { super(message); this.code = 500; }
}
