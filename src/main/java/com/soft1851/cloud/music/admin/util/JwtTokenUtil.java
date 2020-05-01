package com.soft1851.cloud.music.admin.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.soft1851.cloud.music.admin.common.ResultCode;
import com.soft1851.cloud.music.admin.domain.entity.SysRole;
import com.soft1851.cloud.music.admin.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName JwtTokenUtil
 * @Description TODO
 * @Author mq_xu
 * @Date 2020/4/15
 * @Version 1.0
 */
@Slf4j
public class JwtTokenUtil {
    /**
     * 加密
     *
     * @param userId
     * @param roles
     * @param expiresAt
     * @return String
     */
    public static String getToken(final String userId, final String roles, final String secret, Date expiresAt) {
        String token = null;
        try {
            log.info("传过来的role:" + roles);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("userId", userId)
                    .withClaim("roles", roles)
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法, mySecret是用来加密数字签名的密钥
                    .sign(Algorithm.HMAC256(secret));
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码格式");
        }

        return token;
    }

    /**
     * 解密
     *
     * @param token
     * @return DecodedJWT
     */
    public static DecodedJWT deToken(final String token,final String secret) {
        DecodedJWT jwt;
        JWTVerifier verifier = null;
        try {
            verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("auth0")
                    .build();
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码格式");
        }
        assert verifier != null;
        jwt = verifier.verify(token);
        return jwt;
    }

    /**
     * 获取userId
     *
     * @param token
     * @return String
     */
    public static String getUserId(String token, final String secret) {
        String userId = deToken(token, secret).getClaim("roles").asString();
        if(userId != null){
            return userId;
        }
        throw new CustomException("token中的UserId获取异常", ResultCode.DATA_IS_WRONG);
    }

    /**
     * 获取role
     *
     * @param token
     * @return String
     */
    public static String getUserRole(String token, final String secret) {
        return deToken(token, secret).getClaim("roles").asString();
    }

    /**
     * 验证是否过期
     *
     * @param token
     * @return boolean
     */
    public static boolean isExpiration(String token, final String secret) {
        return deToken(token, secret).getExpiresAt().before(new Date());
    }

    public static void main(String[] args) {
        /*String token = getToken("2000100193", "admin", new Date(System.currentTimeMillis() + 10L * 1000L));
        System.out.println(token);
        while (true) {
            boolean flag = isExpiration(token);
            System.out.println(flag);
            if (flag) {
                System.out.println("token已失效");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

    }

}