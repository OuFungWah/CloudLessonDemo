package com.crazywah.cloudlessondemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 自定义Viewpager
 */
public class CustomViewPager extends ViewPager {

    private OnOrientationScrollListener onOrientationScrollListener = null;

    private float lastRawY = 0;
    private float lastRawX = 0;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (lastRawY == 0) {
                    lastRawY = ev.getRawY();
                    lastRawX = ev.getRawX();
                } else {
                    final float deltaY = ev.getRawY() - lastRawY;
                    final float deltaX = ev.getRawX() - lastRawX;
                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        return super.dispatchTouchEvent(ev);
                    } else {
                        if (onOrientationScrollListener != null) {
                            onOrientationScrollListener.onOrientationScroll(deltaY);
                        }
                        lastRawY = ev.getRawY();
                        lastRawX = ev.getRawX();
                        return true;
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public OnOrientationScrollListener getOnOrientationScrollListener() {
        return onOrientationScrollListener;
    }

    public void setOnOrientationScrollListener(OnOrientationScrollListener onOrientationScrollListener) {
        this.onOrientationScrollListener = onOrientationScrollListener;
    }

    public interface OnOrientationScrollListener {

        void onOrientationScroll(float deltaY);

    }

}
