package com.skeleton.module.system.user.dto;

import com.skeleton.common.core.result.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageQuery extends PageQuery {
    private String username;
    private String nickname;
    private Integer status;
}
