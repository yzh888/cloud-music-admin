package com.soft1851.cloud.music.admin.controller;


import com.soft1851.cloud.music.admin.common.ResponseResult;
import com.soft1851.cloud.music.admin.domain.dto.SignDto;
import com.soft1851.cloud.music.admin.domain.entity.SysAdmin;
import com.soft1851.cloud.music.admin.service.SysAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

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
    public ResponseResult login(@RequestBody SignDto signDto) {
        log.info(signDto.getName());
        return ResponseResult.success(sysAdminService.sign(signDto));
    }

    @GetMapping("/menu")
    public ResponseResult getAdminRoleByAdminName(@Valid @Param("name") @NotBlank(message = "参数不能为空") String name) {
        return ResponseResult.success(sysAdminService.getAdminRoleByAdminName(name));
    }

    @GetMapping("/info")
    public SysAdmin getInfoByName(@Valid @Param("name") @NotBlank(message = "参数不能为空") String name) {
        return sysAdminService.getAdminByName(name);
    }

    @PutMapping("/info")
    public ResponseResult updateInfo(@RequestBody @Valid SysAdmin sysAdmin) {
        sysAdminService.updateInfo(sysAdmin);
        return ResponseResult.success();
    }

    @PutMapping("/single")
    public ResponseResult updatePassword(@RequestBody @Valid SysAdmin admin) {
        log.info(String.valueOf(admin));
        sysAdminService.updatePassword(admin);
        return ResponseResult.success();
    }

    @PostMapping("/single")
    public ResponseResult insertInfo(@RequestBody @Valid SysAdmin sysAdmin) {
        sysAdminService.insertSingle(sysAdmin);
        return ResponseResult.success();
    }

    @DeleteMapping("/many")
    public ResponseResult batchDelete(@Valid @Param("adminId") @NotBlank(message = "参数不能为空") String adminId) {
        sysAdminService.batchDelete(adminId);
        return ResponseResult.success();
    }

    @GetMapping("/list")
    public Map<String, Object> selectAll() {
        return sysAdminService.selectAll();
    }
}