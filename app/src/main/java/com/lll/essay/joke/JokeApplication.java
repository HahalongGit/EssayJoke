package com.lll.essay.joke;

import android.app.Application;

import org.xutils.x;

/**
 * Created by longlong on 2018/1/9.
 *
 * @ClassName: JokeApplication
 * @Description:
 * @Date 2018/1/9
 */

public class JokeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
