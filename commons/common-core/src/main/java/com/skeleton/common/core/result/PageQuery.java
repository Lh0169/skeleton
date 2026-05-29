package com.skeleton.common.core.result;

import lombok.Data;

@Data
public class PageQuery {
    private long page = 1;
    private long size = 10;
    private String orderBy;
    private boolean asc = true;
}
