package com.soft1851.cloud.music.admin.service.impl;
import com.soft1851.cloud.music.admin.common.ResultCode;
import com.soft1851.cloud.music.admin.domain.entity.Song;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.cloud.music.admin.domain.dto.TimeDto;
import com.soft1851.cloud.music.admin.exception.CustomException;
import com.soft1851.cloud.music.admin.mapper.SongMapper;
import com.soft1851.cloud.music.admin.service.SongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.cloud.music.admin.util.ExcelConsumer;
import com.soft1851.cloud.music.admin.util.ExportDataAdapter;
import com.soft1851.cloud.music.admin.util.ThreadPool;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzh
 * @since 2020-04-21
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
@Slf4j
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

    @Override
    public void batchDelete(String id) {
        String[] ids = id.split(",");
        List<String> idList = Arrays.asList(ids);
        try {
            songMapper.deleteBatchIds(idList);
        }catch (Exception e){
            throw new CustomException("歌曲批量删除异常", ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public void delete(String id) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.eq("song_id", id);
        try {
            songMapper.delete(wrapper);
        } catch (Exception e) {
            throw new CustomException("歌曲删除异常", ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    @SneakyThrows
    public void exportData(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletResponse response = attributes.getResponse();
        assert response != null;
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment");
        //导出excel对象
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);
        //数据缓冲
        ExportDataAdapter<Song> exportDataAdapter = new ExportDataAdapter<>();
        //线程同步对象
        CountDownLatch latch = new CountDownLatch(2);
        //启动线程获取数据(生产者)
        ThreadPool.getExecutor().submit(() -> produceExportData(exportDataAdapter, latch));
        //启动线程导出数据（消费者）
        ThreadPool.getExecutor().submit(() -> new ExcelConsumer<>(Song.class, exportDataAdapter, sxssfWorkbook, latch, "歌曲数据").run());
        latch.await();
        //使用字节流写数据
        OutputStream outputStream = null;
        outputStream = response.getOutputStream();
        sxssfWorkbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 生产者生产数据
     *
     * @param exportDataAdapter
     * @param latch
     */
    private void produceExportData(ExportDataAdapter<Song> exportDataAdapter, CountDownLatch latch) {
        List<Song> songs = songMapper.selectList(null);
        songs.forEach(exportDataAdapter::addData);
        log.info("数据生产完成");
        //数据生产结束
        latch.countDown();
    }
}