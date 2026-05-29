package com.skeleton.common.core.model;

import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class LoginUser {
    private Long userId;
    private String username;
    private String nickname;
    private String token;
    private Set<String> permissions;
    private Set<String> roles;
}
