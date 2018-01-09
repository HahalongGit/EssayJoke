package com.lll.baselibrary.ioc;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by longlong on 2018/1/9.
 *
 * @ClassName: ViewUtils
 * @Description:
 * @Date 2018/1/9
 */

public class ViewUtils {

    public static void inject(Activity activity){
        inject(new ViewFinder(activity),activity);
    }

    public static void inject(View view){
        inject(new ViewFinder(view),view);
    }

    //object 是反射要执行的类
    public static void inject(View view,Object object){
        inject(new ViewFinder(view),object);
    }

    //兼容上面的三个方法
    private static void inject(ViewFinder viewFinder,Object object ){
        injectField(viewFinder,object);
        injectEvent(viewFinder,object);
    }


    private static void injectEvent(ViewFinder viewFinder, Object object) {

    }

    private static void injectField(ViewFinder viewFinder, Object object) {
        //1.找到View，
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            ViewById viewById = field.getAnnotation(ViewById.class);
            if(viewById!=null){
                int value = viewById.value();
                //2.findViewById
                View view = viewFinder.findViewById(value);
                field.setAccessible(true);//可以注入所有的修饰符
                //3.动态注入VIEW
                try {
                    field.set(object,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
