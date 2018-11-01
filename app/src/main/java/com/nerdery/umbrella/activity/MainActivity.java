package com.nerdery.umbrella.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.api.ApiManager;
import com.nerdery.umbrella.api.IconApi;
import com.nerdery.umbrella.model.CurrentObservation;
import com.nerdery.umbrella.model.WeatherData;
import com.nerdery.umbrella.widget.DynamicGridLayoutManager;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nerdery.umbrella.R.color.weather_cool;
import static com.nerdery.umbrella.R.color.weather_warm;


public class MainActivity extends AppCompatActivity{

    private ApiManager api;
    private IconApi iconApi;
    private Integer zip = 56082;

    private RecyclerView mWeatherRecyclerView;
    private RecyclerView.Adapter mHourlyForecastAdapter;
    private ImageButton mCogButton;
    private TextView mTemperature;
    private TextView mWeatherCondition;
    private TextView mLocation;
    private CardView mWeatherCardView;
    private DynamicGridLayoutManager mDynamicGridLayoutManager;

    private WeatherData mForecastData;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_fragment);
        fetchForecast();

        mWeatherRecyclerView = (RecyclerView) findViewById(R.id.weather_recyclerview);
        mWeatherRecyclerView.setLayoutManager(new DynamicGridLayoutManager(this, 1));

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String dayOne = new DateFormatSymbols().getWeekdays()[day+2];
        String dayTwo = new DateFormatSymbols().getWeekdays()[day+3];
        String dayThree = new DateFormatSymbols().getWeekdays()[day+4];
        String dayFour = new DateFormatSymbols().getWeekdays()[day-2];
        String dayFive = new DateFormatSymbols().getWeekdays()[day-1];

        String[] data = {"Today", "Tomorrow", dayOne, dayTwo, dayThree, dayFour, dayFive };

        mHourlyForecastAdapter = new HourlyForcastAdapter(this, data);
        mWeatherRecyclerView.setAdapter(mHourlyForecastAdapter);
        //getSupportActionBar().hide();
    }

    public void setVariables(WeatherData weatherData) {
        CurrentObservation currentObservation = new CurrentObservation();

        mCogButton = (ImageButton) findViewById(R.id.image_button);
        mCogButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ZipCodeActivity.class);
                startActivity(intent);
            }
        });

        Float newTemp = weatherData.currentObservation.tempFahrenheit;
        Integer roundedTemp = Math.round(newTemp);
        mTemperature = (TextView) findViewById(R.id.weather_degrees);
        mTemperature.setText(roundedTemp.toString() + (char) 0x00B0);

        String weatherCon = weatherData.currentObservation.weather;
        mWeatherCondition = (TextView) findViewById(R.id.weather_status);
        mWeatherCondition.setText(weatherCon);

        String cityLocation = weatherData.currentObservation.displayLocation.city;
        String stateLocation = weatherData.currentObservation.displayLocation.state;
        mLocation = (TextView) findViewById(R.id.location_status);
        mLocation.setText(cityLocation + ", " + stateLocation);

        mWeatherCardView = (CardView) findViewById(R.id.card_view_weather);
    }

    public void changeHeaderColor(WeatherData weatherData) {
        if(weatherData.currentObservation.tempFahrenheit>=60){
            mWeatherCardView.setBackgroundColor(getResources().getColor(weather_warm));
        } else {
            mWeatherCardView.setBackgroundColor(getResources().getColor(weather_cool));
        }
    }

    public void fetchForecast(){

        Call call;
        try{
            call = api.getWeatherApi().getForecastForZip(zip);
        }
        catch (Exception e){
            System.out.println(e);
        }

        finally {
            call = api.getWeatherApi().getForecastForZip(zip);
        }

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) {
                response.body();
                WeatherData weatherData = saveWeatherData(response);
                setVariables(weatherData);
                changeHeaderColor(weatherData);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("errorOnFailure");
            }
        });
    }


    public WeatherData saveWeatherData(Response response){
        return (WeatherData) response.body();

    }
}


