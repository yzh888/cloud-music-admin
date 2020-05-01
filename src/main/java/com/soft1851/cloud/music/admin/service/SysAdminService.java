package com.soft1851.cloud.music.admin.service;

import com.soft1851.cloud.music.admin.domain.dto.SignDto;
import com.soft1851.cloud.music.admin.domain.entity.SysAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzh
 * @since 2020-04-21
 */
public interface SysAdminService extends IService<SysAdmin> {

    /**
     * 用户登录
     * @param signDto
     * @return
     */
    Map<String, Object> sign(@Valid SignDto signDto);

    /**
     * 通过账号获取用户信息
     * @param name
     * @return
     */
    SysAdmin getAdminByName(@Valid String name);

    /**
     * 根据用户name获取用户角色
     * @param name
     * @return
     */
    List<Map<String, Object>> getAdminRoleByAdminName(@Valid String name);
    /**
     * 为指定的管理员生成token
     * @param adminId
     * @param roles
     * @param secrect
     * @param expiresAt
     * @return String
     */
    String getToken(final String adminId, final String roles, final String secrect, Date expiresAt);
}
