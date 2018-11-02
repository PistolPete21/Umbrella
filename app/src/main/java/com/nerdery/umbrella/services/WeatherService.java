package com.nerdery.umbrella.services;

import android.location.Location;
import android.util.Log;

import com.nerdery.umbrella.adapter.ForecastAdapter;
import com.nerdery.umbrella.fragment.ForecastFragment;
import com.nerdery.umbrella.model.Forecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Manages access to the various APIs we are using
 */
public class WeatherService {
    private DataListener dataListener;

    public interface DataListener {
        void onDataLoaded(Forecast forecast);
    }

    public WeatherService(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    private ForecastCall getWeatherService() {
        RetrofitClient retrofitClient = new RetrofitClient();
        return retrofitClient.getClient().create(ForecastCall.class);
    }

    public void fetchWeatherData(Location location, ForecastAdapter adapter, final ForecastFragment forecastFragment) {
        WeatherService weatherService = new WeatherService(dataListener);
        ForecastCall forecastCall = weatherService.getWeatherService();

        Log.i("API CALL", forecastCall.getForecast(location.getLatitude(), location.getLongitude()).request().url().toString());

        forecastCall.getForecast(location.getLatitude(), location.getLongitude()).enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && dataListener != null) {
                        //Send message to main fragment to let it know that the data is loaded
                        dataListener.onDataLoaded(response.body());
                    }
                } else {
                    Log.e("REST ERRPOR", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.d("Weather API", "error loading from API");
                Log.d("Weather API", t.getMessage());
            }
        });
    }
}
