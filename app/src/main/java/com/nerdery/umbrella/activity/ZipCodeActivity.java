package com.nerdery.umbrella.activity;

import android.support.v7.app.AppCompatActivity;

public class ZipCodeActivity extends AppCompatActivity {

//    private WeatherService api;
//    private IconApi iconApi;
//
//    private int zip = 56082;
//
//    private TextView mForC;
//    private TextView mZip;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_zip_code);
//        fetchForecast();
//
//    }
//
//    public void setVariables(WeatherData weatherData) {
//        String zip = weatherData.currentObservation.displayLocation.zip;
//        mZip = (TextView) findViewById(R.id.actual_zip);
//        mZip.setText(zip);
//
//        Float foc = weatherData.currentObservation.tempFahrenheit;
//        mForC = (TextView) findViewById(R.id.actual_forc);
//        mForC.setText("Fahrenheit");
//    }
//
//    public void fetchForecast(){
//        Call call;
//        try{
//            call = api.getWeatherApi().getForecastForZip(zip);
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//
//        finally {
//            call = api.getWeatherApi().getForecastForZip(zip);
//        }
//
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Response response) {
//                response.body();
//                WeatherData weatherData = saveWeatherData(response);
//                setVariables(weatherData);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });
//    }
//
//    public WeatherData saveWeatherData(Response response){
//        return (WeatherData) response.body();
//
//    }
}
