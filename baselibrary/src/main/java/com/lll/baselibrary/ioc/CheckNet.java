package com.lll.baselibrary.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by longlong on 2018/1/9.
 *
 * @ClassName: ViewById
 * @Description: View 事件 注解(检测网络)
 * @Date 2018/1/9
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckNet {
    //Target 表示注解使用的位置
    //@ViewById
    //Retention 表示什么时候生效
}
