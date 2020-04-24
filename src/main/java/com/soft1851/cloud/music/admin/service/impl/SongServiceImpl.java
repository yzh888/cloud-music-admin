package com.soft1851.cloud.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.soft1851.cloud.music.admin.dto.TimeDto;
import com.soft1851.cloud.music.admin.entity.Song;
import com.soft1851.cloud.music.admin.mapper.SongMapper;
import com.soft1851.cloud.music.admin.service.SongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wf
 * @since 2020-04-21
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {
    @Resource
    private SongMapper songMapper;

    @Override
    public List<Song> selectAll() {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        return songMapper.selectList(wrapper);
    }

    @Override
    public List<Song> getSongBy(String field) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.like("singer", field)
                .or()
                .like("song_name", field)
                .or()
                .eq("song_id", field)
                .orderByDesc("play_count");
        return songMapper.selectList(wrapper);
    }

    @Override
    public List<Song> getSongByDate(String flag) {
        //判断flag是什么标志（周、月等）
        TimeDto timeDto = new TimeDto();
        switch (flag){
            case "week":
                timeDto.setWeek(flag);
                break;
            case "month":
                timeDto.setMonth(flag);
                break;
            case "quarter":
                timeDto.setQuarter(flag);
                break;
            default:
                timeDto.setYesterday(flag);
                break;
        }
        return songMapper.getSongByTimeParagraph(timeDto);
    }

    @Override
    public void batchInsert(List<Song> songs) {

    }
}
