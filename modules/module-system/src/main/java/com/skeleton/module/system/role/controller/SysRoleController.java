package com.skeleton.module.system.role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeleton.common.core.result.PageResult;
import com.skeleton.common.core.result.Result;
import com.skeleton.module.system.role.domain.entity.SysRole;
import com.skeleton.module.system.role.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "角色管理")
@RestController
@RequestMapping("/api/system/role")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService roleService;

    @Operation(summary = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public Result<PageResult<SysRole>> page(@RequestParam(defaultValue = "1") long page,
                                             @RequestParam(defaultValue = "10") long size) {
        IPage<SysRole> p = roleService.page(new Page<>(page, size));
        return Result.success(PageResult.of(p.getRecords(), p.getTotal(), p.getCurrent(), p.getSize()));
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Result<SysRole> get(@PathVariable Long id) {
        return Result.success(roleService.getById(id));
    }

    @Operation(summary = "新增")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:role:add')")
    public Result<Void> add(@RequestBody SysRole role) {
        roleService.save(role);
        return Result.success();
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public Result<Void> update(@PathVariable Long id, @RequestBody SysRole role) {
        role.setId(id);
        roleService.updateById(role);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public Result<Void> delete(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.success();
    }
}
