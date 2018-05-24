package com.crazywah.cloudlessondemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.crazywah.cloudlessondemo.ui.adapter.MyAdapter;
import com.crazywah.cloudlessondemo.ui.fragment.AFragment;
import com.crazywah.cloudlessondemo.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements CustomViewPager.OnVerticalScrollListener {

    private static final String TAG = "MainActivity";

    private static final int DELTA = 10;

    private RelativeLayout title_bar;

    private CustomViewPager viewPager;

    private TabLayout tabLayout;

    private List<Fragment> list = new ArrayList<>();

    private MyAdapter myAdapter;

    private int height = 0;
    private int offset = 0;

    private float lastRawY = 0;
    private float lastRawX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title_bar = findViewById(R.id.title_bar);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tab_layout);

        viewPager.setOnVerticalScrollListener(this);

        for (int i = 0; i < 4; i++) {
            list.add(new AFragment());
        }

        myAdapter = new MyAdapter(list, getSupportFragmentManager());

        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < 4; i++) {
            tabLayout.getTabAt(i).setText("Tab" + (i + 1));
        }

    }

    // TODO: 2018/5/21 等待解决滑动冲突 (已解决)

    // TODO: 2018/5/22 为什么一下操作在ontouch里面会卡，而在dispatchtouch里面不卡

    @Override
    public boolean onOrientationScroll(float deltaY) {

        if((deltaY<0&&title_bar.getBottom()==0)||(deltaY>0&&title_bar.getTop()==0)){
            return false;
        }

        height = title_bar.getHeight();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) title_bar.getLayoutParams();

        if (deltaY < 0) {
            offset = offset - (1+Math.abs(deltaY)%50) < -height ? -height : offset - (int) (1+Math.abs(deltaY)%50);
        } else if (deltaY > 0) {
            offset = offset + (1+Math.abs(deltaY)%50) > 0 ? 0 : offset + DELTA;
        }

        layoutParams.setMargins(0, offset, 0, 0);
        title_bar.setLayoutParams(layoutParams);

        // TODO: 2018/5/24  如果设置了title_bar.GONE，上面判断是否滑动至顶部的判断条件则无法成立，所以会导致上滑时ScrollView卡顿
//        if (offset == -height) {
//            title_bar.setVisibility(View.GONE);
//        } else {
//            title_bar.setVisibility(View.VISIBLE);
//        }
        return true;
    }
}
