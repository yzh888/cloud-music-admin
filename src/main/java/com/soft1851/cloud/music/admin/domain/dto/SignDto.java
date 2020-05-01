package com.soft1851.cloud.music.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/4/21
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignDto {
    @NotBlank(message = "用户名不可为空串")
    private String name;
    @NotBlank(message = "密码不可为空串")
    private String password;
    @NotBlank(message = "验证码不可为空")
    @Size(max = 6, message = "长度不可超过6位")
    private String verifyCode;
}