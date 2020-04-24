package com.soft1851.cloud.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft1851.cloud.music.admin.common.ResultCode;
import com.soft1851.cloud.music.admin.entity.SongList;
import com.soft1851.cloud.music.admin.exception.CustomException;
import com.soft1851.cloud.music.admin.mapper.SongListMapper;
import com.soft1851.cloud.music.admin.service.SongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.cloud.music.admin.service.SongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wf
 * @since 2020-04-21
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {
    @Resource
    private SongListMapper songListMapper;

    @Override
    public List<Map<String, Object>> selectAll() {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.select("song_list_id", "song_list_name", "thumbnail", "plays_counts", "type", "song_count", "create_time")
                .orderByDesc("plays_counts");
        List<Map<String, Object>> songLists = songListMapper.selectMaps(wrapper);
        if(songLists != null){
            return songLists;
        }
        throw new CustomException("歌单查询异常", ResultCode.DATABASE_ERROR);
    }

    @Override
    public List<SongList> getByPage(int current, int size) {
        Page<SongList> page = new Page<>(current, size);
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        IPage<SongList> iPage = songListMapper.selectPage(page, wrapper);
        return iPage.getRecords();
    }

    @Override
    public List<Map<String, Object>> getByType() {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        //根据type字段进行分组，按照plays_counts进行降序排序
        wrapper.select("type").groupBy("type").orderByDesc("plays_counts");
        List<Map<String, Object>> maps = songListMapper.selectMaps(wrapper);
        for (Map<String, Object> map : maps) {
            if ("0".equals(map.get("type"))) {
                list().remove(map);
            }else {
                QueryWrapper<SongList> wrapper1 = new QueryWrapper<>();
                //根据父类的type类型查询属于该类型的数据
                wrapper1.orderByDesc("plays_counts").eq("type", map.get("type"));
                List<Map<String, Object>> songLists = songListMapper.selectMaps(wrapper1);
                map.put("child", songLists);
            }
        }
        return maps;
    }

    @Override
    public List<SongList> blurSelect(String field) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        //like = like %变量%, leftLike = like %变量 rightLike = like 变量%
        wrapper.like("song_list_name", field)
                .or().like("type", field);
        return songListMapper.selectList(wrapper);
    }
}
