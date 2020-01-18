package com.glg.actionbartest3;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;

import androidx.fragment.app.Fragment;


public class MyTabListener implements androidx.appcompat.app.ActionBar.TabListener {

    private final Activity activity;
    private final Class  aClass;
    private Fragment fragment;

    public MyTabListener(Activity activity, Class aClass) {
        this.activity = activity;
        this.aClass = aClass;
    }

    @Override
    public void onTabSelected(androidx.appcompat.app.ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {
        if(fragment  == null) {
            fragment  = Fragment.instantiate(activity, aClass.getName());
            ft.add(android.R.id.content, fragment, null);
        }
        ft.attach(fragment);//与新的fragment建立联系
    }

    @Override
    public void onTabUnselected(androidx.appcompat.app.ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {
        if(fragment != null) {
            ft.detach(fragment);//与旧的的fragment的解除联系
        }
    }

    @Override
    public void onTabReselected(androidx.appcompat.app.ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {

    }
}
