package com.skeleton.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skeleton.common.core.result.Result;
import com.skeleton.common.core.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        ServletUtils.renderString(response, objectMapper.writeValueAsString(Result.error(403, "无权限访问")));
    }
}
