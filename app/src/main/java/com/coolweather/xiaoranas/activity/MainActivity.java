package com.coolweather.xiaoranas.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.coolweather.xiaoranas.R;
import com.coolweather.xiaoranas.adapter.InfoPagerAdapter;
import com.coolweather.xiaoranas.entity.InfoListData;
import com.coolweather.xiaoranas.fragment.InfoTabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MainActivity extends AppCompatActivity {



    private String[] tabData;
    private String[] tabKey;

    /*
     * view
     */
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private CoordinatorLayout coordinatorlayout;


    private List<InfoListData> lists = new ArrayList<>();
    private List<InfoTabFragment> fragments;
    private InfoPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initTabData();
    }

    /**
     * 初始化控件
     */
    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        coordinatorlayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        viewPager.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        coordinatorlayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_CANCEL:
                        coordinatorlayout.setEnabled(true);
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 初始化tab数据
     */
    private void initTabData(){

        tabData = getResources().getStringArray(R.array.tabarray);
        tabKey = getResources().getStringArray(R.array.tabarraykey);
        fragments = new ArrayList<>();
        for (int i = 0;i < tabData.length;i++){
            InfoTabFragment infoTabFragment = InfoTabFragment.getInstance(i + 1,tabKey[i]);
            fragments.add(infoTabFragment);
        }
        adapter = new InfoPagerAdapter(getSupportFragmentManager(),fragments,tabData);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
