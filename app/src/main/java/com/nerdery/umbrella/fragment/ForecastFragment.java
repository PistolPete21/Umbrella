package com.nerdery.umbrella.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.activity.NewCityActivity;
import com.nerdery.umbrella.adapter.ForecastAdapter;
import com.nerdery.umbrella.model.Forecast;
import com.nerdery.umbrella.model.WeatherLocation;
import com.nerdery.umbrella.services.WeatherService;
import com.nerdery.umbrella.widget.DynamicGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class ForecastFragment extends Fragment implements WeatherService.DataListener {

    private ForecastAdapter adapter;
    private RecyclerView recyclerView;
    private DynamicGridLayoutManager dynamicGridLayoutManager;
    private List<String> adaterItems = new ArrayList<>();
    private FrameLayout frameLayout;
    private WeatherLocation location;
    private CardView cardView;
    private TextView currentTemp;
    private TextView currentCondition;
    private TextView locationStatus;
    private ImageView settingsIcon;
    private List<Forecast> forecast;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        recyclerView = v.findViewById(R.id.fragment_weather_recyclerview);
        if (adapter == null) {
            //adapter = new ForecastAdapter(getActivity(), forecast);
        }

        location = new WeatherLocation("Bloomington, MN","44.831135", "-93.300445" );

        dynamicGridLayoutManager = new DynamicGridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(dynamicGridLayoutManager);
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
