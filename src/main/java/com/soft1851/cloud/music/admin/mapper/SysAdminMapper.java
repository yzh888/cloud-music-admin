package com.soft1851.cloud.music.admin.mapper;

import com.soft1851.cloud.music.admin.domain.entity.SysAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzh
 * @since 2020-04-21
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

    /**
     * 根据用户名称获取角色
     * @param name
     * @return
     */
    List<Map<String, Object>> getAdminRoleByAdminName(String name);

    /**
     * 获取所有用户信息
     * @return
     */
    List<Map<String, Object>> selectAll();
}