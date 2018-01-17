package com.lll.essay.joke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.lll.essay.joke.list.WrapRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加头部和底部的RecycleView适配器
 */
public class HeaderFooterActivity extends AppCompatActivity {

    @BindView(R.id.recycleView)
    WrapRecyclerView recycleView;
    @BindView(R.id.btn_addHeadView)
    Button btnAddView;

    @BindView(R.id.btn_addFootView)
    Button btnFootView;

    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_footer);
        ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        WrapAdapter wrapAdapter = new WrapAdapter(this);
        recycleView.setAdapter(wrapAdapter);
    }

    @OnClick({R.id.btn_addHeadView,R.id.btn_addFootView})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_addHeadView:{
                View view1 = LayoutInflater.from(this).inflate(R.layout.header_view_layout,recycleView,false);
                recycleView.addHeadView(view1);
                break;
            }case R.id.btn_addFootView:{
                View view2 = LayoutInflater.from(this).inflate(R.layout.footer_view_layout,recycleView,false);
                recycleView.addFootView(view2);
                break;
            }
        }


    }
}
