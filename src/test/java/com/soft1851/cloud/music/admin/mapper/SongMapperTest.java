package com.soft1851.cloud.music.admin.mapper;

import com.soft1851.cloud.music.admin.dto.TimeDto;
import com.soft1851.cloud.music.admin.entity.Song;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/4/24
 * @Version 1.0
 */
@SpringBootTest
class SongMapperTest {
    @Resource
    private SongMapper songMapper;

    @Test
    void getSongByTimeParagraph() {
        TimeDto timeDto = new TimeDto();
        timeDto.setQuarter("1");
        List<Song> songs = songMapper.getSongByTimeParagraph(timeDto);
        for (Song song : songs) {
            System.out.println(song.getCreateTime());
        }
    }

}