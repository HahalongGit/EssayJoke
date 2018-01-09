package com.lll.essay.joke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.lll.baselibrary.ioc.ViewById;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_textContent)
    TextView tvTextContent;
    @BindView(R.id.btn_startB)
    Button btnStartB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.e("TAG","A-onCreate:"+MainActivity.this);
        tvTextContent.setText("通过注解设置文字");
    }


    @OnClick(R.id.btn_startB)
    public void onViewClicked() {
        Intent intent = new Intent(this,BActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("TAG","A-main-onNewIntent");
        Log.e("TAG","A-onNewIntent:"+MainActivity.this);
    }
}
