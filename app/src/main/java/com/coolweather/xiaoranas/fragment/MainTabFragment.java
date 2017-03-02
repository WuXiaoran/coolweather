package com.coolweather.xiaoranas.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coolweather.xiaoranas.R;
import com.coolweather.xiaoranas.adapter.InfoPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 */

public class MainTabFragment extends Fragment {

    private String[] tabData;
    private String[] tabKey;
    private List<InfoTabFragment> fragments;
    private InfoPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_fragment_new,null);
        viewPager = (ViewPager) view.findViewById(R.id.new_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        init();
        return view;
    }

    private  void init(){
        tabData = getResources().getStringArray(R.array.tabarray);
        tabKey = getResources().getStringArray(R.array.tabarraykey);
        fragments = new ArrayList<>();
        for (int i = 0;i < tabData.length;i++){
            InfoTabFragment infoTabFragment = InfoTabFragment.getInstance(i + 1,tabKey[i]);
            fragments.add(infoTabFragment);
        }
        adapter = new InfoPagerAdapter(getChildFragmentManager(),fragments,tabData);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
