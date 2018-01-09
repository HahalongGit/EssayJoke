package com.lll.baselibrary.ioc;

import android.app.Activity;
import android.view.View;

/**
 * Created by longlong on 2018/1/9.
 *
 * @ClassName: ViewFinder
 * @Description: View viewFinderById 的辅助类
 * @Date 2018/1/9
 */

public class ViewFinder {

    private View mView;

    private Activity mActivity;

    public ViewFinder(View view) {
        this.mView = view;
    }

    public ViewFinder(Activity activity) {
        this.mActivity = activity;
    }

    public View findViewById(int viewId){
        return mActivity!=null?mActivity.findViewById(viewId):mView.findViewById(viewId);
    }

}
