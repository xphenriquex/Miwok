package com.exemplo.android.miwok;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragments = new ArrayList<>();
    private List<String> listTitle = new ArrayList<>();

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragmentTab(Fragment fragment, String title){
        listFragments.add(fragment);
        listTitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
}
