package com.skeleton.module.system.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeleton.common.core.result.PageResult;
import com.skeleton.common.core.result.Result;
import com.skeleton.module.system.user.domain.entity.SysUser;
import com.skeleton.module.system.user.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/api/system/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public Result<PageResult<SysUser>> page(@RequestParam(defaultValue = "1") long page,
                                             @RequestParam(defaultValue = "10") long size) {
        IPage<SysUser> p = userService.page(new Page<>(page, size));
        return Result.success(PageResult.of(p.getRecords(), p.getTotal(), p.getCurrent(), p.getSize()));
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public Result<SysUser> get(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @Operation(summary = "新增")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public Result<Void> add(@RequestBody SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return Result.success();
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public Result<Void> update(@PathVariable Long id, @RequestBody SysUser user) {
        user.setId(id);
        if (user.getPassword() != null) user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateById(user);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public Result<Void> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success();
    }
}
