package com.lll.essay.joke;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lll.baselibrary.ioc.CheckNet;
import com.lll.baselibrary.ioc.OnClick;
import com.lll.baselibrary.ioc.ViewById;
import com.lll.framelibrary.BaseSkinActivity;


public class MainActivity extends BaseSkinActivity {

    @ViewById(R.id.tv_textContent)
    TextView tvTextContent;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        tvTextContent.setText("通过注解设置文字12");
    }

    @Override
    protected void initData() {

    }

    @CheckNet
    @OnClick({R.id.tv_textContent,R.id.btn_test})
    public void onClick(View view){
        Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();

    }

}
