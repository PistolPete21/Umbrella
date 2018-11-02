package com.nerdery.umbrella.activity;

import android.support.v7.app.AppCompatActivity;

import com.nerdery.umbrella.services.WeatherService;


public class MainActivity extends AppCompatActivity{

    private WeatherService api;
//    private IconApi iconApi;
//
//    private RecyclerView mWeatherRecyclerView;
//    private RecyclerView.Adapter mHourlyForecastAdapter;
//    private ImageButton mCogButton;
//    private TextView mTemperature;
//    private TextView mWeatherCondition;
//    private TextView mLocation;
//    private CardView mWeatherCardView;
//    private DynamicGridLayoutManager mDynamicGridLayoutManager;
//
//    private HashMap<Double, Double> coordinates = new HashMap<>();
//    private int newDay;
//
//    private WeatherData mForecastData;
//
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_weather_forecast);
//        fetchForecast();
//
//        mWeatherRecyclerView = (RecyclerView) findViewById(R.id.fragment_weather_recyclerview);
//        mDynamicGridLayoutManager = new DynamicGridLayoutManager(this, 1);
//        mDynamicGridLayoutManager.isAutoMeasureEnabled();
//        mWeatherRecyclerView.setLayoutManager(mDynamicGridLayoutManager);
//
//        api = new WeatherService();
//
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_WEEK);
//        if (day < 4) { newDay = day; } else { newDay = day - 7; }
//        String dayOne = "Saturday";
//        String dayTwo = new DateFormatSymbols().getWeekdays()[newDay+3];
//        String dayThree = new DateFormatSymbols().getWeekdays()[newDay+4];
//        String dayFour = new DateFormatSymbols().getWeekdays()[newDay+5];
//        String dayFive = new DateFormatSymbols().getWeekdays()[newDay+6];
//
//        String[] data = {"Today", "Tomorrow", dayOne, dayTwo, dayThree, dayFour, dayFive};
//
//        mHourlyForecastAdapter = new HourlyForcastAdapter(this, data);
//        mWeatherRecyclerView.setAdapter(mHourlyForecastAdapter);
//        //getSupportActionBar().hide();
//    }
//
//    public void setVariables(WeatherData weatherData) {
//        CurrentObservation currentObservation = new CurrentObservation();
//
//        mCogButton = (ImageButton) findViewById(R.id.image_button);
//        mCogButton.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), ZipCodeActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        Float newTemp = weatherData.currentObservation.tempFahrenheit;
//        Integer roundedTemp = Math.round(newTemp);
//        mTemperature = (TextView) findViewById(R.id.weather_degrees);
//        mTemperature.setText(roundedTemp.toString() + (char) 0x00B0);
//
//        String weatherCon = weatherData.currentObservation.weather;
//        mWeatherCondition = (TextView) findViewById(R.id.weather_status);
//        mWeatherCondition.setText(weatherCon);
//
//        String cityLocation = weatherData.currentObservation.displayLocation.city;
//        String stateLocation = weatherData.currentObservation.displayLocation.state;
//        mLocation = (TextView) findViewById(R.id.location_status);
//        mLocation.setText(cityLocation + ", " + stateLocation);
//
//        mWeatherCardView = (CardView) findViewById(R.id.card_view_weather);
//    }
//
//    public void changeHeaderColor(WeatherData weatherData) {
//        if(weatherData.currentObservation.tempFahrenheit>=60){
//            mWeatherCardView.setBackgroundColor(getResources().getColor(weather_warm));
//        } else {
//            mWeatherCardView.setBackgroundColor(getResources().getColor(weather_cool));
//        }
//    }
//
//    public void fetchForecast(){
//
//        Call call;
//        coordinates.put(44.831029, -93.300450);
//
//        try{
//            call = api.getWeatherApi().getForecastForCoordinates(coordinates);
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//
//        finally {
//            call = api.getWeatherApi().getForecastForCoordinates(coordinates);
//        }
//
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Response response) {
//                response.body();
//                WeatherData weatherData = saveWeatherData(response);
//                setVariables(weatherData);
//                changeHeaderColor(weatherData);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                System.out.print("errorOnFailure");
//            }
//        });
//    }
//
//
//    public WeatherData saveWeatherData(Response response){
//        return (WeatherData) response.body();
//
//    }
}


