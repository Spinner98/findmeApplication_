package org.techtown.findmeapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPager2Adapter extends FragmentStateAdapter {



public int mCount;
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity,int count) {
        super(fragmentActivity);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        if(index==0) {
            diaryFragment diaryFragment = new diaryFragment();
            return diaryFragment;
        }
        else {
            questionFragment questionfragment = new questionFragment();
            return questionfragment;
        }

    }

    private int getRealPosition(int position) {
        return position % mCount;
    }

    @Override
    public int getItemCount() {
        return 1000;
    }
}
