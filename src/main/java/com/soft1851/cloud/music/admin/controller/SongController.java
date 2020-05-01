package com.soft1851.cloud.music.admin.controller;


import com.soft1851.cloud.music.admin.common.ResponseResult;
import com.soft1851.cloud.music.admin.domain.entity.Song;
import com.soft1851.cloud.music.admin.service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzh
 * @since 2020-04-21
 */
@RestController
@RequestMapping(value = "/song")
@Slf4j
@Validated
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
    public List<Song> getSongByTime(@Valid @Param("flag") @NotNull String flag) {
        log.info(flag);
        return songService.getSongByDate(flag);
    }

    @DeleteMapping("/many")
    public ResponseResult batchDelete(@Valid @Param("id") @NotBlank String id) {
        songService.batchDelete(id);
        return ResponseResult.success();
    }

    @DeleteMapping("/single/{id}")
    public ResponseResult singleDelete(@Valid @PathVariable @NotBlank String id) {
        songService.delete(id);
        return ResponseResult.success();
    }

    @GetMapping(value = "/export")
    public void exportData() {
        songService.exportData();
    }
}