package com.soft1851.cloud.music.admin.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPool
 * @Description 线程池类
 * @Author mq_xu
 * @Date 2020/4/27
 * @Version 1.0
 */
public class ThreadPool {
    /**
     * 异步线程
     */
    private final static ThreadPoolExecutor executor =
            new ThreadPoolExecutor(20, 100, 10, TimeUnit.MINUTES,
                    new ArrayBlockingQueue<>(2000),
                    r -> new Thread(r, "excelExportThread"),
                    new ThreadPoolExecutor.AbortPolicy());

    public static ThreadPoolExecutor getExecutor() {
        return executor;
    }
}