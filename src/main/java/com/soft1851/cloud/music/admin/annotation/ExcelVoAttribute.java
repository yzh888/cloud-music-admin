package com.soft1851.cloud.music.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zh_yan
 * @ClassName ExcelVoAttribute
 * @Description TODO
 * @Date 2020/4/27
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelVoAttribute {
    /**
     * 导出到Excel的列名
     *
     * @return
     */
    public abstract String name();

    /**
     * 配置列的展现顺序，如A(0),B(1)
     *
     * @return
     */
    public abstract int column();

    /**
     * 是否需要数字格式展示
     *
     * @return
     */
    public abstract boolean isNumber() default false;

    /**
     * 是否需要日期时间格式展示
     * @return
     */
    public abstract  boolean isDateTime() default  false;
}