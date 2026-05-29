package com.skeleton.module.auth.service;

import com.skeleton.common.core.enums.ResultCode;
import com.skeleton.common.core.exception.BusinessException;
import com.skeleton.common.core.model.LoginUser;
import com.skeleton.common.security.manager.TokenManager;
import com.skeleton.module.auth.dto.LoginRequest;
import com.skeleton.module.auth.dto.RegisterRequest;
import com.skeleton.module.auth.vo.LoginVO;
import com.skeleton.module.system.user.domain.entity.SysUser;
import com.skeleton.module.system.user.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SysUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;

    public LoginVO login(LoginRequest request) {
        SysUser user = userMapper.selectOne(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getUsername, request.getUsername()));
        if (user == null) throw new BusinessException(ResultCode.USER_NOT_FOUND);
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BusinessException(ResultCode.PASSWORD_ERROR);
        if (user.getStatus() != null && user.getStatus() == 0)
            throw new BusinessException(403, "账号已被禁用");

        LoginUser loginUser = LoginUser.builder()
                .userId(user.getId()).username(user.getUsername())
                .nickname(user.getNickname()).permissions(Set.of()).roles(Set.of()).build();
        String token = tokenManager.createToken(loginUser);

        return LoginVO.builder().token(token).username(user.getUsername())
                .nickname(user.getNickname()).avatar(user.getAvatar()).build();
    }

    public void register(RegisterRequest request) {
        Long exists = userMapper.selectCount(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getUsername, request.getUsername()));
        if (exists > 0) throw new BusinessException(ResultCode.USERNAME_EXIST);

        SysUser user = new SysUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNickname(request.getNickname() != null ? request.getNickname() : request.getUsername());
        user.setEmail(request.getEmail());
        user.setStatus(1);
        userMapper.insert(user);
    }
}
