package com.skeleton.common.security.utils;

import com.skeleton.common.core.model.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static LoginUser getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof LoginUser) {
            return (LoginUser) principal;
        }
        return null;
    }

    public static Long getUserId() {
        LoginUser user = getLoginUser();
        return user != null ? user.getUserId() : null;
    }

    public static String getUsername() {
        LoginUser user = getLoginUser();
        return user != null ? user.getUsername() : null;
    }
}
