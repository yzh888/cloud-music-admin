package com.soft1851.cloud.music.admin.interceptor;

import com.alibaba.fastjson.JSONArray;
import com.soft1851.cloud.music.admin.common.ResultCode;
import com.soft1851.cloud.music.admin.domain.entity.SysRole;
import com.soft1851.cloud.music.admin.exception.CustomException;
import com.soft1851.cloud.music.admin.exception.JwtException;
import com.soft1851.cloud.music.admin.service.RedisService;
import com.soft1851.cloud.music.admin.service.SysRoleService;
import com.soft1851.cloud.music.admin.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName JwtInterceptor
 * @Description Jwt拦截器
 * @Author yzh
 * @Date 2020/4/15
 * @Version 1.0
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private RedisService redisService;
    /**
     * 前置处理，拦截请求
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        String adminId = request.getHeader("adminId");
        log.info("用户的id: " + adminId);
        if (token == null) {
            throw new CustomException("用户未登录，请先登录", ResultCode.USER_NOT_SIGN_IN);
        } else {
            String secret = redisService.getValue(adminId, String.class);
            // 认证
            if (JwtTokenUtil.isExpiration(token, secret)) {
                log.info("##  token已失效  ##");
                // 通过自定义异常抛出token失效的信息，由全局统一处理
                throw new CustomException("token失效", ResultCode.USER_TOKEN_EXPIRES);
            } else {
                //获取参数roleId
                log.info("获取角色id: " + request.getHeader("roleId") );
                int roleId = Integer.parseInt(request.getHeader("roleId"));
                //获取token中携带的roles
                String roles = JwtTokenUtil.getUserRole(token, secret);
                //将roles进行反序列化
                List<SysRole> maps = JSONArray.parseArray(roles, SysRole.class);
                //鉴权，校验token中的role是否存在用户传来的role
                boolean flag = sysRoleService.checkRole(maps, roleId);
                if (flag) {
                    return true;
                }
                //通过认证，放行到controller层
                throw new JwtException("权限不足", ResultCode.PERMISSION_NO_ACCESS);
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}