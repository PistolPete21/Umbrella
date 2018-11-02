package com.nerdery.umbrella.services;

import android.location.Location;
import android.util.Log;

import com.nerdery.umbrella.BuildConfig;
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
    private static final String BASE_URL = BuildConfig.API_URL;

    private static ForecastCall getWeatherService() {
        return RetrofitClient.getClient(BASE_URL + BuildConfig.API_KEY).create(ForecastCall.class);
    }

    public static void getWeatherData(Location location, ForecastAdapter adapter, final ForecastFragment forecastFragment) {
        ForecastCall call = WeatherService.getWeatherService();

        Log.i("API CALL", call.getForecast(location.getLatitude(), location.getLongitude()).request().url().toString());

        call.getForecast(location.getLatitude(), location.getLongitude()).enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    // Update the current conditions views.
                    forecastFragment.updateCurrentForecast(response.body());
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
