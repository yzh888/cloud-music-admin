package com.soft1851.cloud.music.admin.controller;


import com.soft1851.cloud.music.admin.common.ResponseResult;
import com.soft1851.cloud.music.admin.domain.entity.SysMenu;
import com.soft1851.cloud.music.admin.service.SysMenuService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wf
 * @since 2020-04-21
 */
@RestController
@RequestMapping(value = "/sysMenu")
@Validated
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @GetMapping(value = "/list")
    public Map<String, Object> selectAll() {
        return sysMenuService.selectAll();
    }

    @PostMapping(value = "/single")
    public ResponseResult insertSingle(@RequestBody @Valid SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return ResponseResult.success();
    }

    @DeleteMapping(value = "/single/{id}")
    public ResponseResult deleteSingle(@Valid @PathVariable @NotBlank(message = "参数不能为空") int id) {
        sysMenuService.removeById(id);
        return ResponseResult.success();
    }
}