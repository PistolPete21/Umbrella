package com.nerdery.umbrella.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.activity.NewCityActivity;
import com.nerdery.umbrella.adapter.ForecastAdapter;
import com.nerdery.umbrella.model.Forecast;
import com.nerdery.umbrella.model.ForecastData;
import com.nerdery.umbrella.model.WeatherLocation;
import com.nerdery.umbrella.services.WeatherService;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.util.List;
import java.util.Objects;

public class ForecastFragment extends Fragment implements WeatherService.DataListener {

    private ForecastAdapter adapter;
    private RecyclerView recyclerView;
    private WeatherLocation location;
    private CardView cardView;
    private TextView currentTemp;
    private TextView currentCondition;
    private TextView locationStatus;
    private ImageView settingsIcon;
    private List<ForecastData> forecast;

    private LovelyProgressDialog lovelyProgressDialog;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        recyclerView = v.findViewById(R.id.fragment_weather_recyclerview);

        lovelyProgressDialog = new LovelyProgressDialog(getContext());
        lovelyProgressDialog.setIcon(R.mipmap.ic_launcher);
        lovelyProgressDialog.setTitle(R.string.fetching_weather);
        lovelyProgressDialog.setTopColor(Objects.requireNonNull(getContext()).getResources().getColor(R.color.weather_warm));
        lovelyProgressDialog.show();

        if (adapter == null) {
            adapter = new ForecastAdapter(getActivity(), null, lovelyProgressDialog);
        }

        location = new WeatherLocation("Bloomington, MN","44.831135", "-93.300445" );

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        cardView = v.findViewById(R.id.fragment_card_view_weather);
        settingsIcon = v.findViewById(R.id.fragment_image_button);
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NewCityActivity.class);
                startActivity(i);
            }
        });
        currentTemp = v.findViewById(R.id.fragment_weather_degrees);
        currentCondition = v.findViewById(R.id.fragment_weather_status);
        locationStatus = v.findViewById(R.id.fragment_location_status);

        cardView.setBackgroundColor(getResources().getColor(R.color.weather_warm));
        locationStatus.setText(location.getName());

        getWeatherForecast();

        return v;
    }

    private void getWeatherForecast() {
        WeatherService weatherService = new WeatherService(this);
        weatherService.fetchWeatherData(location.getLatitudeLongitude(), adapter, this);
    }

    @Override
    public void onDataLoaded(Forecast forecast) {
        if (forecast != null) {
            adapter.updateForecastData(forecast);
            // Grab data
            String summary = forecast.getCurrently().getSummary();
            String temperature = String.valueOf(Math.round(forecast.getCurrently().getTemperature())) + "\u00B0";

            // Change the header color
            if(forecast.getCurrently().getTemperature()>=60){
                cardView.setBackgroundColor(getResources().getColor(R.color.weather_warm));
            } else {
                cardView.setBackgroundColor(getResources().getColor(R.color.weather_cool));
            }

            // Change the text values
            currentTemp.setText(temperature);
            currentCondition.setText(summary);
        }
    }
}
