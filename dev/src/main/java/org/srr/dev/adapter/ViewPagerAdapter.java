package org.srr.dev.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by Administrator on 2016/1/25.
 */
public class ViewPagerAdapter extends CacheFragmentStatePagerAdapter {
    private List<Fragment> listFragment;                         //fragment列表
    private List<String> listTitle;                              //tab名的列表


    public ViewPagerAdapter(FragmentManager fm, List<Fragment> listFragment, List<String> listTitle) {
        super(fm);
        this.listFragment = listFragment;
        this.listTitle = listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    protected Fragment createItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position % listTitle.size());
    }
}
