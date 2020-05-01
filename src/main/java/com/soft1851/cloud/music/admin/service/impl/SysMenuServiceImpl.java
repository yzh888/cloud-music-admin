package com.soft1851.cloud.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.cloud.music.admin.common.ResultCode;
import com.soft1851.cloud.music.admin.domain.entity.RoleMenu;
import com.soft1851.cloud.music.admin.domain.entity.SysMenu;
import com.soft1851.cloud.music.admin.exception.CustomException;
import com.soft1851.cloud.music.admin.mapper.RoleMenuMapper;
import com.soft1851.cloud.music.admin.mapper.SysMenuMapper;
import com.soft1851.cloud.music.admin.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzh
 * @since 2020-04-21
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Map<String, Object> selectAll() {
        List<SysMenu> menus = sysMenuMapper.selectList(new QueryWrapper<>());
        List<Map<String, Object>> maps = sysMenuMapper.selectAll();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("all", menus);
        map.put("menus", maps);
        return map;
    }

    @Override
    public void insertSingle(SysMenu menu) {
        try {
            sysMenuMapper.insert(menu);
        } catch (Exception e) {
            throw new CustomException("菜单资源新增异常", ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public void deleteSingle(int menuId) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_id", menuId);
        try {
            roleMenuMapper.delete(wrapper);
            sysMenuMapper.deleteById(menuId);
        } catch (Exception e) {
            throw new CustomException("删除菜单资源异常", ResultCode.DATABASE_ERROR);
        }
    }
}
