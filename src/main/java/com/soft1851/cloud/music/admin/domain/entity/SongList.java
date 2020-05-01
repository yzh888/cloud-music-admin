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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("song_list")
public class SongList extends Model<SongList> {

    private static final long serialVersionUID = 1L;

    /**
     * 歌单id
     */
    @NotNull(message = "songListId 不能为空")
    @TableId("song_list_id")
    private String songListId;

    /**
     * 歌单名称
     */
    @NotNull(message = "songListName 不能为空")
    @TableField("song_list_name")
    private String songListName;

    /**
     * 封面
     */
    @JsonIgnore
    private String thumbnail;

    /**
     * 歌单类型
     */
    @NotNull(message = "type 不能为空")
    @TableField("type")
    private String type;

    /**
     * 歌曲数
     */
    @NotNull(message = "songCount 不能为空")
    @TableField("song_count")
    private Integer songCount;

    /**
     * 收藏数
     */
    @NotNull(message = "likeCount 不能为空")
    @TableField("like_count")
    private Integer likeCount;

    /**
     * 评论数
     */
    @JsonIgnore
    @TableField("comment_count")
    private Integer commentCount;

    /**
     * 删除标志
     */
    @JsonIgnore
    @TableField("delete_flag")
    private Integer deleteFlag;

    /**
     * 修改时间
     */
    @JsonIgnore
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 播放量
     */
    @TableField("plays_counts")
    private Integer playsCounts;


    @Override
    protected Serializable pkVal() {
        return this.songListId;
    }

}
