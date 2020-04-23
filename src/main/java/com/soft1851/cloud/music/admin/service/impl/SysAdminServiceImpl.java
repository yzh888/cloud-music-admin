package com.soft1851.cloud.music.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.cloud.music.admin.common.ResultCode;
import com.soft1851.cloud.music.admin.dto.SignDto;
import com.soft1851.cloud.music.admin.entity.RoleAdmin;
import com.soft1851.cloud.music.admin.entity.SysAdmin;
import com.soft1851.cloud.music.admin.entity.SysRole;
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
import java.util.*;

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
    public Map<String, Object> sign(SignDto signDto) {
        SysAdmin admin = getAdminByName(signDto.getName());
        Map<String, Object> data = new LinkedHashMap<>();
        Map<String, Object> adminMap = new LinkedHashMap<>();
        //构建user信息（同下）
        adminMap.put("userId", admin.getId());
        adminMap.put("name", admin.getName());
        adminMap.put("avatar", admin.getAvatar());
        List<Map<String, Object>> maps = getAdminRoleByAdminName(signDto.getName());
        List<SysRole> roles = new ArrayList<>();
        //构建role角色（很笨的方法，先这样）
        for (Map<String, Object> map : maps) {
            SysRole sysRole = SysRole.builder()
                    .roleId(Integer.parseInt(map.get("role_id").toString()))
                    .roleName(map.get("role_name").toString())
                    .build();
            roles.add(sysRole);
        }
        String password = Md5Util.getMd5(signDto.getPassword(), true, 32);
        log.info("角色字符串位：" + JSONObject.toJSONString(roles));
        //对token序列化成字符串
        String token = JwtTokenUtil.getToken( admin.getId(), JSONObject.toJSONString(roles), new Date(System.currentTimeMillis() + 6000L * 1000));
        if (admin.getPassword().equals(password)) {
            data.put("user", adminMap);
            data.put("roles", roles);
            data.put("token", token);
            return data;
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
