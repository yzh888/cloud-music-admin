package com.soft1851.cloud.music.admin.controller;


import com.soft1851.cloud.music.admin.entity.Song;
import com.soft1851.cloud.music.admin.service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wf
 * @since 2020-04-21
 */
@RestController
@RequestMapping(value = "/song")
@Slf4j
public class SongController {
    @Resource
    private SongService songService;

    @GetMapping("/list")
    public List<Song> selectAll() {
        return songService.selectAll();
    }

    @GetMapping("/blur")
    public List<Song> getSongBy(@Param("field") String field) {
        return songService.getSongBy(field);
    }

    @GetMapping("/paragraph")
    public List<Song> getSongByTime(@Param("flag") String flag) {
        log.info(flag);
        return songService.getSongByDate(flag);
    }
}
