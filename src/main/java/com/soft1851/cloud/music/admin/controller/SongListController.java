package com.soft1851.cloud.music.admin.controller;


import com.soft1851.cloud.music.admin.domain.entity.SongList;
import com.soft1851.cloud.music.admin.service.SongListService;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzh
 * @since 2020-04-21
 */
@RestController
@RequestMapping(value = "/songList")
@Validated
public class SongListController {
    @Resource
    private SongListService songListService;

    @GetMapping("/list")
    public List<Map<String, Object>> selectAll() {
        return songListService.selectAll();
    }

    @GetMapping("/page")
    public List<SongList> getByPage(@Valid @Param("currentPage") @Min(0) int currentPage, @Valid @Param("size") @Min(0) int size) {
        return songListService.getByPage(currentPage, size);
    }

    @GetMapping("/blur")
    public List<SongList> blurSelectSongList(@Param("field") String field) {
        return songListService.blurSelect(field);
    }

    @GetMapping("/type")
    public List<Map<String, Object>> getByType(){
        return songListService.getByType();
    }
}