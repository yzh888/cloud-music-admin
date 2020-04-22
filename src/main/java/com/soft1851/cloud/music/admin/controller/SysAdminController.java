package com.soft1851.cloud.music.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.cloud.music.admin.common.ResponseResult;
import com.soft1851.cloud.music.admin.common.ResultCode;
import com.soft1851.cloud.music.admin.dto.SignDto;
import com.soft1851.cloud.music.admin.entity.SysAdmin;
import com.soft1851.cloud.music.admin.service.SysAdminService;
import com.soft1851.cloud.music.admin.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wf
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/sysAdmin")
@Slf4j
public class SysAdminController {
    @Resource
    private SysAdminService sysAdminService;

    /**
     * 登录
     *
     * @return String
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody SignDto signDto) {
        return ResponseResult.success(sysAdminService.sign(signDto));
    }

    @GetMapping("/menu")
    public ResponseResult getAdminRoleByAdminName(@Param("name") String name) {
        return ResponseResult.success(sysAdminService.getAdminRoleByAdminName(name));
    }
}
