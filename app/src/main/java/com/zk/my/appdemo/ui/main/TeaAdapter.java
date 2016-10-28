package com.zk.my.appdemo.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by bodhixu on 2016/10/10.
 */
public class TeaAdapter extends FragmentPagerAdapter{//适配器的作用是为了让两者一一对应,不包括传值

    //数据源
    private List<Fragment> fragments;
    private String[] titles;

    public TeaAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //显示Tab的title
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
