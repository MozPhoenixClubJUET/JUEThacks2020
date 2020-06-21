package com.example.android.hackathonapp.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.hackathonapp.Fragments.information_fragment;
import com.example.android.hackathonapp.Fragments.tools_fragment;

public class viewPagerAdapter extends FragmentPagerAdapter {

    public viewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new tools_fragment();
            case 0:
                return new information_fragment();
        }

        return null; //does not happen
    }

    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 1:
                return "Tools";
            case 0:
                return "Information";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
