package com.skeleton.common.security.filter;

import cn.hutool.core.util.StrUtil;
import com.skeleton.common.core.constant.SecurityConstants;
import com.skeleton.common.core.model.LoginUser;
import com.skeleton.common.core.utils.JwtUtils;
import com.skeleton.common.redis.service.RedisService;
import com.skeleton.common.security.manager.TokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        if (StrUtil.isBlank(header) || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(SecurityConstants.TOKEN_PREFIX.length()).trim();
        if (StrUtil.isBlank(token) || jwtUtils.isExpired(token)) {
            chain.doFilter(request, response);
            return;
        }

        LoginUser loginUser = tokenManager.getLoginUser(token);
        if (loginUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var authorities = loginUser.getPermissions().stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(loginUser, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
