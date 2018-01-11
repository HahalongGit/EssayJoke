package com.lll.baselibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lll.baselibrary.ioc.ViewUtils;

/**
 * 模板设计模式创建BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_base);
        setContentView();
        ViewUtils.inject(this);
        initView();
        initData();
        initTitle();
    }

    protected abstract void initTitle();


    protected abstract void initData();


    protected abstract void initView();


    protected abstract void setContentView();

    /**
     * 启动Activity
     * @param clazz
     */
    public void startActivity(Class<?> clazz){
        Intent intent = new Intent(this,clazz);
        this.startActivity(intent);
    }

    /**
     * 旧版的Android Studio 需要findViewById 的时候强转类型，这里泛型简化强转（新版直接不需要强转）
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findViewById2(int viewId){
        return (T)findViewById(viewId);
    }

}
