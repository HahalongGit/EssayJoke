package com.lll.baselibrary.ioc;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by longlong on 2018/1/9.
 *
 * @ClassName: ViewUtils
 * @Description:
 * @Date 2018/1/9
 */

public class ViewUtils {

    public static void inject(Activity activity) {
        inject(new ViewFinder(activity), activity);
    }

    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    //object 是反射要执行的类
    public static void inject(View view, Object object) {
        inject(new ViewFinder(view), object);
    }

    //兼容上面的三个方法
    private static void inject(ViewFinder viewFinder, Object object) {
        injectField(viewFinder, object);
        injectEvent(viewFinder, object);
    }

    /**
     * 注入Field
     *
     * @param viewFinder
     * @param object     对象
     */
    private static void injectField(ViewFinder viewFinder, Object object) {
        //1.找到View，
        Class<?> clazz = object.getClass();
//        Field[] fields = clazz.getFields();//getField()只能访问public 的字段，不能访问其他类型的字段，可以访问父类的字段
        Field[] fields = clazz.getDeclaredFields(); //可以访问任意类型的字段，但是不能访问父类的字段
        for (Field field : fields) {
            ViewById viewById = field.getAnnotation(ViewById.class);
            if (viewById != null) {
                int value = viewById.value();
                //2.findViewById
                View view = viewFinder.findViewById(value);
                if (view != null) {
                    field.setAccessible(true);//可以注入所有的修饰符
                    //3.动态注入VIEW
                    try {
                        field.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 事件注入
     *
     * @param viewFinder
     * @param object
     */
    private static void injectEvent(ViewFinder viewFinder, Object object) {
        //1.获取所有的方法
        //2.获取注入OnClick的Value值
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {//获取Activity中的所有的方法（注解传入了this,也就是Activity）
            Log.e("TAG", "method-Name:" + method.getName());
            OnClick onClick = method.getAnnotation(OnClick.class);
            CheckNet checkNet = method.getAnnotation(CheckNet.class);
            boolean isCheckNet = (checkNet != null);//是否检测网络
            if (onClick != null) {//找到添加了OnClick注解的这个方法
                int[] viewIds = onClick.value();
                for (int viewId : viewIds) {
                    //3.findViewById 找到View
                    View view = viewFinder.findViewById(viewId);
                    if (view != null) {
                        //4.反射执行方法
                        view.setOnClickListener(new DeclaredOnClickListener(method, object, isCheckNet));
                    }
                }
            }
        }
    }

    /**
     * 是否有网络链接
     *
     * @param context
     * @return
     */
    public static boolean networkAvaiable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Log.e("TAG","networkInfo:"+networkInfo.getTypeName());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 点击事件实现
     */
    public static class DeclaredOnClickListener implements View.OnClickListener {

        private Method mMethod;

        private Object mObject;

        private boolean isCheckNet;
        //模拟View的XML中OnClick 的获取执行

        public DeclaredOnClickListener(Method method, Object object, boolean isCheckNet) {
            this.mMethod = method;
            this.mObject = object;
            this.isCheckNet = isCheckNet;
        }

        @Override
        public void onClick(View v) {
            if (isCheckNet) {
                if (!networkAvaiable(v.getContext())) {
                    //提示文字需要配置在外部
                    Toast.makeText(v.getContext(), "您的网络不太合适", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Log.e("TAG","网络正常，可以访问");
                }
            }
            try {
                //给所有的OnClick进行的try catch,点击事件异常不会闪退，会有异常的日志（特别注意）
                mMethod.setAccessible(true);//设置访问所有的方法
                mMethod.invoke(mObject, v);//执行带有一个View参数的方法onClick()
            } catch (Exception e) {
                e.printStackTrace();
                //执行没有View参数的情况下的点击方法
                try {
                    mMethod.invoke(mObject, null);//执行mObject （Activity）这个类的，参数是null的方法
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


}
