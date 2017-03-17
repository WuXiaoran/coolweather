package com.coolweather.xiaoranas.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.coolweather.xiaoranas.fragment.InfoTabFragment;
import com.coolweather.xiaoranas.fragment.WeatherTabFragment;

/**
 * Created by Administrator on 2017/3/2.
 */

public class MainTabPagerAdapter extends FragmentPagerAdapter {

    public MainTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InfoTabFragment();
            case 1:
                return new WeatherTabFragment();
            case 2:
                return new WeatherTabFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
