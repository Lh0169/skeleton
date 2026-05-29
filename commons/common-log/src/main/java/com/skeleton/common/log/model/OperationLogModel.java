package com.skeleton.common.log.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OperationLogModel {
    private Long id;
    private String module;
    private String action;
    private String operator;
    private String requestMethod;
    private String requestUrl;
    private String requestParams;
    private Long costTime;
    private LocalDateTime createTime;
}
