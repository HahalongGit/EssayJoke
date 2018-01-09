package com.lll.essay.joke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CActivity extends AppCompatActivity {

    @BindView(R.id.btn_startD)
    Button btnStartC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_startD)
    public void onViewClicked() {
        Intent intent = new Intent(this,DActivity.class);
        startActivity(intent);
    }
}
