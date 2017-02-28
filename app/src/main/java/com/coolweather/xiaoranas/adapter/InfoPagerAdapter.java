package com.coolweather.xiaoranas.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coolweather.xiaoranas.fragment.InfoTabFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class InfoPagerAdapter extends FragmentPagerAdapter {



    private List<InfoTabFragment> fragments;
    private String[] tabData;

    public InfoPagerAdapter(FragmentManager fm,List<InfoTabFragment> fragments,String[] tabData) {
        super(fm);
        this.fragments = fragments;
        this.tabData = tabData;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabData[position];
    }
}
