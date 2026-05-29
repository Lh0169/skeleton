package com.skeleton.module.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skeleton.module.system.user.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
