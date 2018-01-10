package com.lll.essay.joke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lll.baselibrary.ioc.CheckNet;
import com.lll.baselibrary.ioc.OnClick;
import com.lll.baselibrary.ioc.ViewById;
import com.lll.baselibrary.ioc.ViewUtils;




public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.tv_textContent)
    TextView tvTextContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

        tvTextContent.setText("通过注解设置文字12");
    }

    @CheckNet
    @OnClick({R.id.tv_textContent,R.id.btn_test})
    public void onClick(View view){
        Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();

    }

}
