package com.skeleton.common.core.result;

import lombok.Data;
import java.util.List;

@Data
public class PageResult<T> {
    private long total;
    private long page;
    private long size;
    private List<T> rows;

    public static <T> PageResult<T> of(List<T> rows, long total, long page, long size) {
        PageResult<T> r = new PageResult<>();
        r.setRows(rows);
        r.setTotal(total);
        r.setPage(page);
        r.setSize(size);
        return r;
    }
}
