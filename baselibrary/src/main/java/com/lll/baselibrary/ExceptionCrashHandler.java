package com.lll.baselibrary;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by longlong on 2018/1/11.
 *
 * @ClassName: ExceptionCrashHandler
 * @Description: 异常捕捉 上传服务器
 * @Date 2018/1/11
 */

public class ExceptionCrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "ExceptionCrashHandler";

    private static ExceptionCrashHandler mExceptionCrashHandler;

    /**
     * 系统默认的异常处理
     */
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    private Context mContent;

    public static ExceptionCrashHandler getInstance(){
        if(mExceptionCrashHandler==null){
            synchronized (ExceptionCrashHandler.class){
                if(mExceptionCrashHandler==null){
                    mExceptionCrashHandler = new ExceptionCrashHandler();
                }
            }
        }
        return mExceptionCrashHandler;
    }

    public void init(Context context){
        this.mContent = context;
        //设置全局的类为本类
        Thread.currentThread().setUncaughtExceptionHandler(this);
        mUncaughtExceptionHandler = Thread.currentThread().getDefaultUncaughtExceptionHandler();

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        mUncaughtExceptionHandler.uncaughtException(t,e);//执行系统方法，查看异常的信息系统显示的那些Log
        Log.e(TAG,"报异常了：");
        //写到本地文件（版本信息，手机信息），上传到服务器
        //这里获取的异常和线程：
        //上传版本信息，包名信息，手机品牌信息 ，这里发生了异常，不能上传服务器，保存在文件中，等应用再次启动的时候上传到服务器。

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

            File file = new File(mContent.getFilesDir()+File.separator+"crash"+File.separator);
            if(!file.exists()){
                file.mkdirs();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(e.getMessage());
            sb.append(e.getCause());
            String fileName = file.toString()+File.separator+getAssitmentTime("yyyy_MM_dd_HH_mm")+".txt";
            FileOutputStream os = null;
            try {
                os = new FileOutputStream(fileName);
                os.write(sb.toString().getBytes());
                os.flush();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }finally {
                if(os!=null){
                    try {
                        os.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }


    }

    /**
     * 格式化时间
     * @param partent
     * @return
     */
    private String getAssitmentTime(String partent) {
        SimpleDateFormat format = new SimpleDateFormat(partent);
        String formatDate = format.format(new Date());
        return formatDate;
    }
}
