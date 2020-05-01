package com.soft1851.cloud.music.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author wf
 * @since 2020-04-21
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_admin")
public class SysAdmin extends Model<SysAdmin> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotNull(message = "id 不能为空")
    @TableId("id")
    private String id;

    /**
     * 用户名
     */
    @NotNull(message = "name 不能为空")
    @TableField("name")
    private String name;

    /**
     * 密码
     */
    @NotNull(message = "password 不能为空")
    @TableField("password")
    private String password;

    /**
     * 加密盐
     */
    @NotNull(message = "salt 不能为空")
    @TableField("salt")
    private String salt;

    /**
     * 头像
     */
    @NotNull(message = "avatar 不能为空")
    @TableField("avatar")
    private String avatar;

    /**
     * 创建时间
     */
    @NotNull(message = "createTime 不能为空")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @NotNull(message = "updateTime 不能为空")
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 账户状态：0 禁用 1 启用
     */
    @NotNull(message = "status 不能为空")
    @TableField("status")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
