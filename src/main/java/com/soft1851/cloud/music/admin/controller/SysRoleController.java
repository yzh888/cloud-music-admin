package com.soft1851.cloud.music.admin.controller;


import com.soft1851.cloud.music.admin.common.ResponseResult;
import com.soft1851.cloud.music.admin.service.RoleAdminService;
import com.soft1851.cloud.music.admin.service.RoleMenuService;
import com.soft1851.cloud.music.admin.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
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
@RequestMapping("/sysRole")
@Slf4j
@Valid
public class SysRoleController {
    @Resource
    private RoleAdminService roleAdminService;
    @Resource
    private SysRoleService sysRoleService;

    @GetMapping(value = "/list")
    public Map<String, Object> getRoleMenuByRoleId(@Valid @Param("roleId") @NotNull int roleId) {
        return sysRoleService.getRoleMenuByRoleId(roleId);
    }
}
