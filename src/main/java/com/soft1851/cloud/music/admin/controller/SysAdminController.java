package com.soft1851.cloud.music.admin.controller;


import com.soft1851.cloud.music.admin.common.ResponseResult;
import com.soft1851.cloud.music.admin.domain.dto.SignDto;
import com.soft1851.cloud.music.admin.service.SysAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzh
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/sysAdmin")
@Slf4j
@Validated
public class SysAdminController {
    @Resource
    private SysAdminService sysAdminService;

    /**
     * 登录
     *
     * @return String
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody @Valid SignDto signDto) {
        log.info(signDto.getName());
        return ResponseResult.success(sysAdminService.sign(signDto));
    }

    @GetMapping("/menu")
    public ResponseResult getAdminRoleByAdminName(@Valid @Param("name") @NotNull String name) {
        return ResponseResult.success(sysAdminService.getAdminRoleByAdminName(name));
    }

//    @PostMapping("/person")
//    public ResponseResult postAdminRole(@Valid @Param("id") @NotNull String id) {
//        return ResponseResult.success(sysAdminService.postAdminRole(id));
//    }
}
