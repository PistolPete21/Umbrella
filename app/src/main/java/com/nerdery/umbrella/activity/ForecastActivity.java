package com.nerdery.umbrella.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.fragment.ForecastFragment;
import com.nerdery.umbrella.utils.FragmentTags;

public class ForecastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_weather_forecast);
        FragmentManager fm = getSupportFragmentManager();
        ForecastFragment fragment = (ForecastFragment) fm.findFragmentByTag(FragmentTags.ForecastFragment);

        if (fragment == null) {
            fragment = new ForecastFragment();
            fm.beginTransaction().add(fragment, FragmentTags.ForecastFragment).commit();
        }
    }
}
