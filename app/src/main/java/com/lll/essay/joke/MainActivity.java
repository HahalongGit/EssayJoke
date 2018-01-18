package com.lll.essay.joke;

import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lll.baselibrary.dialog.AlertDialog;
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
        File fixFile = new File(Environment.getExternalStorageDirectory(), "fix.apatch");
        if (fixFile.exists()) {
            try {
                if (!TextUtils.isEmpty(fixFile.getAbsolutePath())) {
                    FixBugManager fixBugManager = new FixBugManager(this);
                    fixBugManager.fixDex(fixFile.getAbsolutePath());
                    BaseApplication.patchManager.addPatch(fixFile.getAbsolutePath());
                    Toast.makeText(this, "修复成功:" + fixFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "修复失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void initData() {

    }

    @CheckNet
    @OnClick({R.id.tv_textContent, R.id.btn_test})
    public void onClick(View view) {
//        Log.e("TAG", "MainActivity-onClick");
//        Toast.makeText(this, "点击"+(3/0), Toast.LENGTH_SHORT).show();
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("提示");
//        builder.setMessage("确定删除？");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//        alertDialog.show();

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_test_layout)
                .setText(R.id.tv_dialog, "新的Dialog文本")
                .setWidthHeight(600,400)
                .fromBottom(true)
                .setFullWidth()
                .create();
        final EditText editText = alertDialog.getView(R.id.edit_input);
        alertDialog.setOnClickListener(R.id.iv_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击图片:"+editText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }

}
