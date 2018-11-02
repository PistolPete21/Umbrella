package com.nerdery.umbrella.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.adapter.ForecastAdapter;
import com.nerdery.umbrella.model.Forecast;
import com.nerdery.umbrella.model.WeatherLocation;
import com.nerdery.umbrella.services.WeatherService;
import com.nerdery.umbrella.widget.DynamicGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class ForecastFragment extends android.support.v4.app.Fragment {

    private ForecastAdapter adapter;
    private RecyclerView recyclerView;
    private DynamicGridLayoutManager dynamicGridLayoutManager;
    private List<String> adaterItems = new ArrayList<>();
    private View view;
    private FrameLayout frameLayout;
    private CardView cardView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        if (adapter == null) {
            //adapter = new ForecastAdapter(getActivity(), adaterItems);
        }

        dynamicGridLayoutManager = new DynamicGridLayoutManager(getContext(), 1);
        recyclerView = view.findViewById(R.id.fragment_weather_recyclerview);
        recyclerView.setLayoutManager(dynamicGridLayoutManager);
        recyclerView.setAdapter(adapter);

        cardView = view.findViewById(R.id.fragment_card_view_weather);
        cardView.setBackgroundColor(getResources().getColor(R.color.weather_warm));

        //fetchWeatherData();

        return view;
    }

    private void fetchWeatherData() {
        WeatherLocation location = new WeatherLocation(getActivity());

        location.setLatitude(Double.toString(44.831135));
        location.setLongitude(Double.toString(-93.300445));
        location.setName("Bloomington, MN");

        // Make sure the user has put in a location.
        if (location.getName() != null) {
            WeatherService.getWeatherData(location.getLatitudeLongitude(), adapter, this);

            TextView locationStatus = view.findViewById(R.id.fragment_location_status);
            locationStatus.setText(location.getName());

            // If they haven't, ask them to put in a location.
        } else {
            //NewCityFragment newCityFragment = new NewCityFragment().newInstance();
        }
    }

    public void updateCurrentForecast(Forecast weatherData) {
        // If the view doesn't exist, an error will occur because we are calling it below. Return to prevent this.
        if (view == null) {
            return;
        }

        TextView currentTemp = view.findViewById(R.id.fragment_weather_degrees);
        TextView currentCondition = view.findViewById(R.id.fragment_weather_status);
        TextView locationStatus = view.findViewById(R.id.fragment_location_status);

        // Grab data
        String summary = weatherData.getCurrently().getSummary();
        long temperature = Math.round(weatherData.getCurrently().getTemperature());

        // Change the header color
        if(weatherData.getCurrently().getTemperature()>=60){
            cardView.setBackgroundColor(getResources().getColor(R.color.weather_warm));
        } else {
            cardView.setBackgroundColor(getResources().getColor(R.color.weather_cool));
        }

        // Change the text values
        currentTemp.setText(Long.toString(temperature));
        currentCondition.setText(summary);

    }
}
