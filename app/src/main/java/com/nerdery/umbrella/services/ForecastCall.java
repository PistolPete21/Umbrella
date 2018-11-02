package com.nerdery.umbrella.services;

import com.nerdery.umbrella.model.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit interface for fetching weather data
 */
public interface ForecastCall {

    @GET("{latitude},{longitude}")
    Call<Forecast> getForecast(
            @Path("latitude") Double latitude,
            @Path("longitude") Double longitude
    );
}
