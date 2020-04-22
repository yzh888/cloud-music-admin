package com.soft1851.cloud.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.cloud.music.admin.common.ResultCode;
import com.soft1851.cloud.music.admin.dto.SignDto;
import com.soft1851.cloud.music.admin.entity.RoleAdmin;
import com.soft1851.cloud.music.admin.entity.SysAdmin;
import com.soft1851.cloud.music.admin.exception.CustomException;
import com.soft1851.cloud.music.admin.mapper.RoleAdminMapper;
import com.soft1851.cloud.music.admin.mapper.SysAdminMapper;
import com.soft1851.cloud.music.admin.service.SysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.cloud.music.admin.util.JwtTokenUtil;
import com.soft1851.cloud.music.admin.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
@Slf4j
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {
    @Resource
    private SysAdminMapper sysAdminMapper;

    @Override
    public String sign(SignDto signDto) {
        SysAdmin admin = getAdminByName(signDto.getName());
        String password = Md5Util.getMd5(signDto.getPassword(), true, 32);
        if (admin.getPassword().equals(password)) {
            return JwtTokenUtil.getToken(signDto.getName(), admin.getId(), new Date(System.currentTimeMillis() + 600L * 1000));
        }
        throw new CustomException("账号密码错误", ResultCode.USER_PASSWORD_ERROR);
    }

    @Override
    public SysAdmin getAdminByName(String name) {
        QueryWrapper<SysAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        SysAdmin sysAdmin = sysAdminMapper.selectOne(wrapper);
        if (sysAdmin != null) {
            return sysAdmin;
        }
        throw new CustomException("用户不存在", ResultCode.USER_NOT_FOUND);
    }

    @Override
    public List<Map<String, Object>> getAdminRoleByAdminName(String name) {
        List<Map<String, Object>> maps = sysAdminMapper.getAdminRoleByAdminName(name);
        if (maps != null) {
            return maps;
        }
        throw new CustomException("用户角色查询异常", ResultCode.DATABASE_ERROR);
    }
}
