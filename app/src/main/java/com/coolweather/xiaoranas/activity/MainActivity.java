package com.coolweather.xiaoranas.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coolweather.xiaoranas.R;
import com.coolweather.xiaoranas.fragment.MainTabFragment;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MainActivity extends AppCompatActivity {


    private String[] tabData_foots;
    private String[] tabData_foots_icon;

    /*
     * view
     */
    private Toolbar toolbar;
    private TabLayout main_tabLayout_foots;
    private CoordinatorLayout coordinatorlayout;


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
        coordinatorlayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        main_tabLayout_foots = (TabLayout) findViewById(R.id.main_tablayout_foots);
    }

    /**
     * 初始化tab数据
     */
    private void initTabData(){
        tabData_foots = getResources().getStringArray(R.array.tabarray_foots);
        tabData_foots_icon = getResources().getStringArray(R.array.tabarray_foots_icon);
        for (int i = 0;i < tabData_foots.length;i++){
            View tabitem = LayoutInflater.from(this).inflate(R.layout.item_maintab,null);
            ImageView icon = (ImageView) tabitem.findViewById(R.id.maintab_icon);
            TextView text = (TextView) tabitem.findViewById(R.id.maintab_text);
            icon.setBackgroundResource(getResources().getIdentifier(tabData_foots_icon[i],
                                             "drawable",
                                              getBaseContext().getPackageName()));//通过字符串的图片名称去拿资源id
            text.setText(tabData_foots[i]);
            TabLayout.Tab tab = main_tabLayout_foots.newTab().setCustomView(tabitem);
            main_tabLayout_foots.addTab(tab);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment,new MainTabFragment());
        transaction.commit();
    }

}
