package com.skeleton.module.auth.vo;

import lombok.Data;
import java.util.Set;

@Data
public class UserProfileVO {
    private Long userId;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;
    private Set<String> roles;
    private Set<String> permissions;
}
