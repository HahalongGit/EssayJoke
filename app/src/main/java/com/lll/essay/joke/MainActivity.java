package com.lll.essay.joke;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lll.baselibrary.fixBug.FixBugManager;
import com.lll.baselibrary.ioc.CheckNet;
import com.lll.baselibrary.ioc.OnClick;
import com.lll.baselibrary.ioc.ViewById;
import com.lll.framelibrary.BaseSkinActivity;

import java.io.File;
import java.io.IOException;


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
        initAlibabaFix();
    }


    private void initAlibabaFix() {
        File fixFile = new File(Environment.getExternalStorageDirectory(),"fix.apatch");
        if(fixFile.exists()){
            try {
                if(!TextUtils.isEmpty(fixFile.getAbsolutePath())){
                    FixBugManager fixBugManager = new FixBugManager(this);
                    fixBugManager.fixDex(fixFile.getAbsolutePath());
                    BaseApplication.patchManager.addPatch(fixFile.getAbsolutePath());
                    Toast.makeText(this, "修复成功:"+fixFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "修复失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void initData() {

    }

    @CheckNet
    @OnClick({R.id.tv_textContent,R.id.btn_test})
    public void onClick(View view){
        Toast.makeText(this, "点击"+(3/0), Toast.LENGTH_SHORT).show();
    }

}
