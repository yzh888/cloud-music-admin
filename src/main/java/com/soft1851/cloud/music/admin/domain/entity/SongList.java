package com.soft1851.cloud.music.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzh
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("song_list")
public class SongList extends Model<SongList> {

    private static final long serialVersionUID = 1L;

    /**
     * 歌单id
     */
    @TableId("song_list_id")
    private String songListId;

    /**
     * 歌单名称
     */
    @TableField("song_list_name")
    @NotNull(message = "歌单名不允许为空")
    private String songListName;

    /**
     * 封面
     */
    @JsonIgnore
    private String thumbnail;

    /**
     * 歌单类型
     */
    @TableField("type")
    private String type;

    /**
     * 歌曲数
     */
    @TableField("song_count")
    @Min(0)
    private Integer songCount;

    /**
     * 收藏数
     */
    @TableField("like_count")
    @Min(0)
    private Integer likeCount;

    /**
     * 评论数
     */
    @JsonIgnore
    @TableField("comment_count")
    @Min(0)
    private Integer commentCount;

    /**
     * 删除标志 0 逻辑删  1 存在
     */
    @JsonIgnore
    @TableField("delete_flag")
    @Max(1)
    private Integer deleteFlag;

    /**
     * 修改时间
     */
    @JsonIgnore
    @TableField("update_time")
    @NotBlank(message = "时间不能为空")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @NotBlank(message = "时间不能为空")
    private LocalDateTime createTime;

    /**
     * 播放量
     */
    @TableField("plays_counts")
    @Min(0)
    private Integer playsCounts;


    @Override
    protected Serializable pkVal() {
        return this.songListId;
    }

}