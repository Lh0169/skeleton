package com.skeleton.module.auth.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginVO {
    private String token;
    private String username;
    private String nickname;
    private String avatar;
}
