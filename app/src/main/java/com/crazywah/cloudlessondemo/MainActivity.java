package com.crazywah.cloudlessondemo;

import android.animation.ObjectAnimator;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.crazywah.cloudlessondemo.ui.adapter.FragmentPagerAdapter;
import com.crazywah.cloudlessondemo.ui.fragment.AFragment;
import com.crazywah.cloudlessondemo.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomViewPager.OnOrientationScrollListener {

    private static final String TAG = "MainActivity";

    private static final int DELTA = 10;

    private RelativeLayout title_bar;

    private CustomViewPager viewPager;

    private TabLayout tabLayout;

    private List<Fragment> list = new ArrayList<>();

    private FragmentPagerAdapter fragmentPagerAdapter;

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

        viewPager.setOnOrientationScrollListener(this);

        for (int i = 0; i < 4; i++) {
            list.add(new AFragment());
            tabLayout.addTab(tabLayout.newTab().setText("Tab" + (i + 1)));
        }

        fragmentPagerAdapter = new FragmentPagerAdapter(list, getSupportFragmentManager());

        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < 4; i++) {
            tabLayout.getTabAt(i).setText("Tab" + (i + 1));
        }

    }

    // TODO: 2018/5/21 等待解决滑动冲突 (已解决)

    // TODO: 2018/5/22 为什么一下操作在ontouch里面会卡，而在dispatchtouch里面不卡

    @Override
    public void onOrientationScroll(float deltaY) {
        height = title_bar.getHeight();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) title_bar.getLayoutParams();
        if (deltaY < 0) {
            offset = offset - DELTA < -height ? -height : offset - DELTA;
        } else if (deltaY > 0) {
            offset = offset + DELTA > 0 ? 0 : offset + DELTA;
        }

        layoutParams.setMargins(0, offset, 0, 0);
        title_bar.setLayoutParams(layoutParams);

        if (offset == -height) {
            title_bar.setVisibility(View.GONE);
        } else {
            title_bar.setVisibility(View.VISIBLE);
        }
    }
}
