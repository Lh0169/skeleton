package com.skeleton.module.system.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skeleton.module.system.user.domain.entity.SysUser;
import com.skeleton.module.system.user.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {
}
