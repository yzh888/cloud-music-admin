package com.soft1851.cloud.music.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soft1851.cloud.music.admin.annotation.ExcelVoAttribute;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzh
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("song")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song extends Model<Song> {

    private static final long serialVersionUID = 1L;

    /**
     * 歌曲id
     */
    @ExcelVoAttribute(name = "歌曲id", column = 0)
    @TableId(value = "song_id", type = IdType.INPUT)
    private String songId;

    /**
     * 歌曲名称
     */
    @ExcelVoAttribute(name = "歌曲名称", column = 1)
    @NotBlank(message = "歌曲名称不可以为空")
    @TableField("song_name")
    private String songName;

    /**
     * 排序id
     */
    @JsonIgnore
    @TableField("sort_id")
    private String sortId;

    /**
     * 歌手
     */
    @ExcelVoAttribute(name = "歌手", column = 2)
    @NotBlank(message = "歌手字段不可为空串")
    @TableField("singer")
    private String singer;

    /**
     * 时长
     */
    @ExcelVoAttribute(name = "时长", column = 5)
    @TableField("duration")
    @NotBlank(message = "时长不可为空")
    private String duration;

    /**
     * 封面图
     */
    @ExcelVoAttribute(name = "封面图", column = 3)
    @JsonIgnore
    @TableField("thumbnail")
    private String thumbnail;

    /**
     * 歌曲地址
     */
    @ExcelVoAttribute(name = "歌曲地址", column = 4)
    @TableField("url")
    @NotBlank(message = "歌曲地址不可为空串")
    private String url;

    /**
     * 歌词
     */
    @ExcelVoAttribute(name = "歌词", column = 6)
    @TableField("lyric")
    @NotNull(message = "歌词不可为空")
    private String lyric;

    /**
     * 评论量
     */
    @ExcelVoAttribute(name = "评论量", column = 7)
    @Min(0)
    @JsonIgnore
    @TableField("comment_count")
    private Integer commentCount;

    /**
     * 收藏量
     */
    @ExcelVoAttribute(name = "收藏量", column = 8)
    @JsonIgnore
    @Min(0)
    @TableField("like_count")
    private Integer likeCount;

    /**
     * 播放量
     */
    @JsonIgnore
    @Min(0)
    @TableField("play_count")
    private Integer playCount;

    /**
     * 删除标志
     */
    @JsonIgnore
    @TableField("delete_flag")
    @Size(max = 2)
    private String deleteFlag;

    /**
     * 修改时间
     */
    @JsonIgnore
    @TableField("update_time")
    @NotBlank(message = "时间不可为空串")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @ExcelVoAttribute(name = "创建时间", column = 9)
    @NotBlank(message = "时间不可为空串")
    @TableField("create_time")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.songId;
    }

}