package com.lll.essay.joke;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;
import com.lll.baselibrary.ExceptionCrashHandler;

import org.xutils.x;

/**
 * Created by longlong on 2018/1/9.
 *
 * @ClassName: BaseApplication
 * @Description:
 * @Date 2018/1/9
 */

public class BaseApplication extends Application {

    public static PatchManager patchManager ;

    @Override
    public void onCreate() {
        super.onCreate();
        patchManager = new PatchManager(this);
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            patchManager.init(packageInfo.versionName);//current version
            patchManager.loadPatch();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //设置异常日志处理
        ExceptionCrashHandler.getInstance().init(this);

    }
}
