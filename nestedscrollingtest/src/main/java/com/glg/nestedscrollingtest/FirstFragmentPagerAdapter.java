package com.glg.nestedscrollingtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by gao on 2020/7/21 10:43.
 * Function:
 */
class FirstFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public FirstFragmentPagerAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        fragments = mFragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getClass().getSimpleName();
    }
}
