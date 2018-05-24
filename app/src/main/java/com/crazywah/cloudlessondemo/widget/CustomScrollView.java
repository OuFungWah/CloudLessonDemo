package com.crazywah.cloudlessondemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {

    private static final String TAG = "CustomScrollView";

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        Log.d(TAG, "onScrollChanged: l = "+l);
//        Log.d(TAG, "onScrollChanged: t = "+t);
//        Log.d(TAG, "onScrollChanged: oldl = "+oldl);
//        Log.d(TAG, "onScrollChanged: oldt = "+oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
