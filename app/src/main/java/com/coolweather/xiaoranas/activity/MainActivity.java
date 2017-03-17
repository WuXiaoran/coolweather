package com.coolweather.xiaoranas.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.coolweather.xiaoranas.fragment.WeatherTabFragment;

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
        main_tabLayout_foots = (TabLayout) findViewById(R.id.main_tablayout_foots);
    }

    /**
     * 初始化tab数据
     */
    private void initTabData(){
        final MainTabFragment mainTabFragment = new MainTabFragment();
        final WeatherTabFragment weatherTabFragment = new WeatherTabFragment();

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
            main_tabLayout_foots.addTab(tab,i == 0 ? true : false);
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_fragment,mainTabFragment);
        fragmentTransaction.commit();
        main_tabLayout_foots.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (position){
                    case 0:
                        transaction.hide(weatherTabFragment);
                        if (!mainTabFragment.isAdded()){
                            transaction.add(R.id.main_fragment,mainTabFragment);
                        }else{
                            transaction.show(mainTabFragment);
                        }
                        transaction.commit();
                        break;
                    case 1:
                        transaction.hide(mainTabFragment);
                        if (!weatherTabFragment.isAdded()){
                            transaction.add(R.id.main_fragment,weatherTabFragment);
                        }else{
                            transaction.show(weatherTabFragment);
                        }
                        transaction.commit();
                        break;
                    case 2:
                        transaction.hide(mainTabFragment);
                        if (!weatherTabFragment.isAdded()){
                            transaction.add(R.id.main_fragment,weatherTabFragment);
                        }else{
                            transaction.show(weatherTabFragment);
                        }
                        transaction.commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
