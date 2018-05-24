package com.crazywah.cloudlessondemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 自定义Viewpager
 */
public class CustomViewPager extends ViewPager {

    private OnVerticalScrollListener onVerticalScrollListener = null;

    private Scrollable scrollable = null;

    private float lastRawY = 0;
    private float lastRawX = 0;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 事件分发
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean flag = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastRawY = ev.getRawY();
                lastRawX = ev.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - lastRawY;
                final float deltaX = ev.getRawX() - lastRawX;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    return super.dispatchTouchEvent(ev);
                } else {
                    if (onVerticalScrollListener != null) {
                        flag = onVerticalScrollListener.onOrientationScroll(deltaY);
                    }
                    lastRawY = ev.getRawY();
                    lastRawX = ev.getRawX();
                }
                break;
        }
        return flag ? flag : super.dispatchTouchEvent(ev);
    }

    public OnVerticalScrollListener getOnVerticalScrollListener() {
        return onVerticalScrollListener;
    }

    public void setOnVerticalScrollListener(OnVerticalScrollListener onVerticalScrollListener) {
        this.onVerticalScrollListener = onVerticalScrollListener;
    }

    public Scrollable getScrollable() {
        return scrollable;
    }

    public void setScrollable(Scrollable scrollable) {
        this.scrollable = scrollable;
    }

    /**
     * 竖直方向滑动监听器接口
     */
    public interface OnVerticalScrollListener {

        boolean onOrientationScroll(float deltaY);

    }

    public interface Scrollable {

        boolean isScrollable();

    }

}
