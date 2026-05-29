package com.skeleton.common.security.manager;

import com.skeleton.common.core.constant.CacheConstants;
import com.skeleton.common.core.model.LoginUser;
import com.skeleton.common.core.utils.JwtUtils;
import com.skeleton.common.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TokenManager {

    private final JwtUtils jwtUtils;
    private final RedisService redisService;

    public String createToken(LoginUser loginUser) {
        String token = jwtUtils.generateToken(loginUser.getUsername());
        loginUser.setToken(token);
        refreshToken(loginUser);
        return token;
    }

    public void refreshToken(LoginUser loginUser) {
        String key = CacheConstants.LOGIN_TOKEN_KEY + loginUser.getUsername();
        redisService.set(key, loginUser, CacheConstants.TOKEN_EXPIRE_SECONDS, TimeUnit.SECONDS);
    }

    public LoginUser getLoginUser(String token) {
        String username = jwtUtils.getSubject(token);
        String key = CacheConstants.LOGIN_TOKEN_KEY + username;
        LoginUser user = redisService.get(key, LoginUser.class);
        if (user != null) {
            refreshToken(user);
        }
        return user;
    }

    public void removeToken(String username) {
        redisService.delete(CacheConstants.LOGIN_TOKEN_KEY + username);
    }
}
