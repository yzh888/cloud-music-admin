package com.soft1851.cloud.music.admin.controller;


import com.soft1851.cloud.music.admin.common.ResponseResult;
import com.soft1851.cloud.music.admin.service.RoleAdminService;
import com.soft1851.cloud.music.admin.service.RoleMenuService;
import com.soft1851.cloud.music.admin.service.SysRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/sysRole")
public class SysRoleController {
    @Resource
    private RoleAdminService roleAdminService;
    @Resource
    private SysRoleService sysRoleService;

    @GetMapping(value = "/list")
    public ResponseResult getRoleMenuByRoleId(@Param("roleId") int roleId, @Param("name") String name) {
        return ResponseResult.success(sysRoleService.getRoleMenuByRoleId(roleId, name));
    }
}
