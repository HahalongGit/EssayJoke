package com.lll.baselibrary.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by longlong on 2018/1/9.
 *
 * @ClassName: ViewById
 * @Description: ViewById 注解
 * @Date 2018/1/9
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewById {
    //Target 表示注解使用的位置
    //@ViewById
    //Retention 表示什么时候生效
    int value();
}
