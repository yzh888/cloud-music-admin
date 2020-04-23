package com.soft1851.cloud.music.admin.util;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.soft1851.cloud.music.admin.common.ResultCode;
import com.soft1851.cloud.music.admin.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 采用hutool的ExcelUtil工具类生成excel
 * @Author wf
 * @Date 2020/4/22
 * @Version 1.0
 */
@Slf4j
public class ExcelUtils {
    //list 变量是导出的数据，map存放表头的标题信息，title表格名
    public static void exportExcel(HttpServletResponse response, List list, Map<String, String> map, String title){
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        Set<Map.Entry<String, String>> entries = map.entrySet();
        //迭代器遍历数据
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            //自定义表头
            writer.addHeaderAlias(next.getKey(), next.getValue());
        }

        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(map.size() - 1, title);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        //out为OutputStream，需要写出到的目标流
        try {
            writer.flush(response.getOutputStream(), true);
        } catch (IOException e) {
            log.info("歌单导出异常");
            throw new CustomException("歌单数据导出异常", ResultCode.DATABASE_EXPORT_ERROR);
        }
        // 关闭writer，释放内存
        writer.close();
    }

    /**
     * 写出map数据
     *
     * @param response
     * @param list  输出到excel的列表
     * @param title excel 标题
     */
    public static void exportExcel(HttpServletResponse response, List<Map<String, Object>> list, String title) throws IOException {
        //设置输出头
        response.setHeader("Content-Disposition", "attachment;fileName=" + new String((new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx").getBytes("UTF-8")));
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(list.size() - 1, title);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        //out为OutputStream，需要写出到的目标流
        writer.flush(response.getOutputStream());
        // 关闭writer，释放内存
        writer.close();
    }
}
