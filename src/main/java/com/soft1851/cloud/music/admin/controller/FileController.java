package com.soft1851.cloud.music.admin.controller;

import com.soft1851.cloud.music.admin.service.FileService;
import com.soft1851.cloud.music.admin.service.SongListService;
import com.soft1851.cloud.music.admin.util.ExcelUtils;
import com.soft1851.cloud.music.admin.util.FileUtil;
import com.soft1851.cloud.music.admin.util.PoiExcelsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/4/22
 * @Version 1.0
 */
@RestController
@RequestMapping("/resources")
@Slf4j
public class FileController {
    @Resource
    private SongListService songListService;
    @Resource
    private FileService fileService;

    @GetMapping("/songList")
    @ResponseBody
    public void export(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletResponse response = attributes.getResponse();
        assert response != null;
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment");
        ExcelUtils.exportExcel(response, songListService.selectAll(), fileService.exportSongList(), "歌单表");
    }

    @PostMapping(value = "/guide")
    public void exportResource(@RequestParam("file") MultipartFile file){
        log.info(file.getName());
        File file1 = FileUtil.fileConversion(file);
        fileService.importSong(file1);
    }

    @GetMapping(value = "/model")
    public void downloadModel() {
        //创建
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        //获取response响应
        HttpServletResponse response = attributes.getResponse();
        assert response != null;
        //设置响应类型位excel文件类型
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //设置响应头，允许文件在浏览器中下载
        response.setHeader("Content-Disposition","attachment");
        //导出模板
        ExcelUtils.downloadModel(response, fileService.downloadSongModel(), "音乐模板");

    }
}
