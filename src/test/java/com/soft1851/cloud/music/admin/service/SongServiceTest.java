package com.soft1851.cloud.music.admin.service;

import com.soft1851.cloud.music.admin.entity.Song;
import com.soft1851.cloud.music.admin.util.ExcelUtils;
import com.soft1851.cloud.music.admin.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/4/24
 * @Version 1.0
 */
@SpringBootTest
class SongServiceTest {
    @Resource
    private SongService songService;

    @Test
    void selectAll() {
        File file = new File("D:\\Downloads\\Chrome\\model.xlsx");
        songService.saveBatch(ExcelUtils.importExcel(file));
//        List<Song> songs = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            String id = UUID.randomUUID().toString();
//            System.out.println(id);
//            Song song = Song.builder()
//                    .songId(UUID.randomUUID().toString().replace("-",""))
//                    .songName("test" + i)
//                    .commentCount(0)
//                    .likeCount(0)
//                    .playCount(0)
//                    .build();
//            songs.add(song);
////            songService.save(song);
//        }
//        songService.saveBatch(songs);
    }
}