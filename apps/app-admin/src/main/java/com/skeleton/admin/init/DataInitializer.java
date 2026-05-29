package com.skeleton.admin.init;

import com.skeleton.module.system.user.domain.entity.SysUser;
import com.skeleton.module.system.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SysUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userService.count() == 0) {
            SysUser admin = new SysUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setNickname("管理员");
            admin.setStatus(1);
            userService.save(admin);
            log.info("初始化管理员: admin / admin123");
        }
    }
}
