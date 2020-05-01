package com.soft1851.cloud.music.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzh
 * @since 2020-04-21
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_admin")
@AllArgsConstructor
@NoArgsConstructor
public class SysAdmin extends Model<SysAdmin> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 用户名
     */
    @TableField("name")
    @NotBlank(message = "用户名不可为空串")
    private String name;

    /**
     * 密码
     */
    @TableField("password")
    @Size(min = 6, message = "密码最少不可低于六位数")
    private String password;

    /**
     * 加密盐
     */
    @JsonIgnore
    @TableField("salt")
    private String salt;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 账户状态：0 禁用 1 启用
     */
    @TableField("status")
    @Max(1)
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}