package com.skeleton.common.core.constant;

public interface CacheConstants {
    String LOGIN_TOKEN_KEY = "login_tokens:";
    long TOKEN_EXPIRE_SECONDS = 86400L;
    String CAPTCHA_CODE_KEY = "captcha_codes:";
    long CAPTCHA_EXPIRE_MINUTES = 2L;
    String USER_KEY = "user:";
    String ROLE_KEY = "role:";
}
