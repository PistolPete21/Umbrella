package com.nerdery.umbrella.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.fragment.NewCityFragment;
import com.nerdery.umbrella.utils.FragmentTags;

public class NewCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        FragmentManager fm = getSupportFragmentManager();
        NewCityFragment fragment = (NewCityFragment) fm.findFragmentByTag(FragmentTags.NewCityFragment);

        if (fragment == null) {
            fragment = new NewCityFragment();
            fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }
}
