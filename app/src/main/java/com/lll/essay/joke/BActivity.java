package com.lll.essay.joke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BActivity extends AppCompatActivity {

    @BindView(R.id.btn_startC)
    Button btnStartB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_startC)
    public void onViewClicked() {
        Intent intent = new Intent(this,CActivity.class);
        startActivity(intent);
    }
}
