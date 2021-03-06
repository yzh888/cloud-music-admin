package com.soft1851.cloud.music.admin.service;

import com.soft1851.cloud.music.admin.domain.entity.Song;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzh
 * @since 2020-04-21
 */
public interface SongService extends IService<Song> {

    /**
     * 查询所有
     * @return
     */
    List<Song> selectAll();

    /**
     * 模糊查
     * @return
     */
    List<Song> getSongBy(String filed);

    /**
     * 查询一段时间内
     * @return
     */
    List<Song> getSongByDate(String flag);

    /**
     * 批量插入
     * @param songs
     */
    void batchInsert(List<Song> songs);

    /**
     * 批量删除
     * @param idList
     */
    void batchDelete(String idList);

    /**
     * 单个删除
     * @param id
     */
    void delete(String id);

    /**
     * 导出歌曲数据
     */
    void exportData();
}