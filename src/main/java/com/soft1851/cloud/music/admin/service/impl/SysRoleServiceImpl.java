package com.soft1851.cloud.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.cloud.music.admin.common.ResultCode;
import com.soft1851.cloud.music.admin.entity.RoleAdmin;
import com.soft1851.cloud.music.admin.entity.SysAdmin;
import com.soft1851.cloud.music.admin.entity.SysRole;
import com.soft1851.cloud.music.admin.exception.CustomException;
import com.soft1851.cloud.music.admin.mapper.RoleAdminMapper;
import com.soft1851.cloud.music.admin.mapper.RoleMenuMapper;
import com.soft1851.cloud.music.admin.mapper.SysAdminMapper;
import com.soft1851.cloud.music.admin.mapper.SysRoleMapper;
import com.soft1851.cloud.music.admin.service.RoleMenuService;
import com.soft1851.cloud.music.admin.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wf
 * @since 2020-04-21
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Resource
    private SysAdminMapper adminMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Map<String, Object> getRoleMenuByRoleId(int roleId) {
        Map<String, Object> data = new HashMap<>();
        // 根据角色id查询角色信息
        List<Map<String, Object>> maps = roleMenuMapper.getParentMenuByRoleId(roleId);
        for (Map<String, Object> map1 : maps) {
            map1.remove("role_id");
            map1.remove("menu_id");
        }
        if (maps != null) {
            data.put("permissions", maps);
            return data;
        }
        throw new CustomException("数据查找异常", ResultCode.DATA_IS_WRONG);
    }

    @Override
    public boolean checkRole(List<SysRole> roles, int roleId) {
        for (SysRole sysRole : roles) {
            if (roleId == sysRole.getRoleId()) {
                return true;
            }
        }
        return false;
    }
}
