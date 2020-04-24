package com.soft1851.cloud.music.admin.mapper;

import com.soft1851.cloud.music.admin.dto.TimeDto;
import com.soft1851.cloud.music.admin.entity.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wf
 * @since 2020-04-21
 */
public interface SongMapper extends BaseMapper<Song> {

    /**
     * 根据时间段查询数据
     * @return
     */
    List<Song> getSongByTimeParagraph(@Param("timeDto") TimeDto timeDto);
}
