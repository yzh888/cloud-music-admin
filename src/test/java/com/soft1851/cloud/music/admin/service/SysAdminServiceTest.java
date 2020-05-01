package com.soft1851.cloud.music.admin.service;

import com.soft1851.cloud.music.admin.domain.dto.SignDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/4/21
 * @Version 1.0
 */
@SpringBootTest
@Slf4j
class SysAdminServiceTest {
    @Resource
    private SysAdminService sysAdminService;
    @Resource
    private RedisService redisService;

    @Test
    void sign() {
        SignDto signDto = SignDto.builder()
                .name("soft1851")
                .password("123456")
                .verifyCode("123456")
                .build();
        System.out.println(sysAdminService.getAdminRoleByAdminName("soft1851"));;

    }
}