package com.nerdery.umbrella.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.fragment.ForecastFragment;
import com.nerdery.umbrella.utility.FragmentTags;

public class ForecastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        FragmentManager fm = getSupportFragmentManager();
        ForecastFragment fragment = (ForecastFragment) fm.findFragmentByTag(FragmentTags.ForecastFragment);

        if (fragment == null) {
            fragment = new ForecastFragment();
            fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }
}
