package com.example.crudfirebase.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.crudfirebase.fragment.Fragment1;
import com.example.crudfirebase.fragment.Fragment2;


public class MyFragmentAdapter extends FragmentStateAdapter {



    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new Fragment1();
        }
        return new Fragment2();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
