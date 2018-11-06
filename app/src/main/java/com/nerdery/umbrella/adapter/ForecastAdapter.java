package com.nerdery.umbrella.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.model.Forecast;
import com.nerdery.umbrella.model.ForecastData;
import com.nerdery.umbrella.services.IconApi;
import com.nerdery.umbrella.utility.DateTime;
import com.nerdery.umbrella.utility.Screen;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private Context context;
    private List<ForecastData> dailyForecast;
    private List<ForecastData> hourlyForecast;

    private Boolean alreadyExecuted = false;

    public ForecastAdapter(Context context, List<ForecastData> dailyForecast) {
        this.context = context;
        this.dailyForecast = dailyForecast;
    }

    @Override
    public int getItemCount() {
        return dailyForecast == null ? 0 : 2;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.list_weather_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        ForecastData day = dailyForecast.get(position);

        String dayName = DateTime.convertDateToString(new Date(day.getTime().longValue() * 1000), DateTime.dayFormatter);

        switch (position) {
            case 0:
                holder.day.setText(R.string.today);
                break;
            case 1:
                holder.day.setText(R.string.tomorrow);
                break;
        }

        Double dailyTemperatureMax = day.getTemperatureMax();
        Double dailyTemperatureMinimum = day.getTemperatureMin();

        float availableScreenWidth;
        availableScreenWidth = Screen.getWidth(getContext()) - getContext().getResources().getDimensionPixelSize(R.dimen.item_margin) - getContext().getResources().getDimensionPixelSize(R.dimen.item_margin);

        float thumbnailWidthHeight = getContext().getResources().getDimension(R.dimen.item_width_height);
        float thumbnailMargin = getContext().getResources().getDimension(R.dimen.item_margin);

        float numberOfItemsNeeded = hourlyForecast.size();

        int numberOfItemsPerRow = (int) Math.floor(availableScreenWidth / thumbnailWidthHeight);
        if ((thumbnailWidthHeight * numberOfItemsPerRow) + ((numberOfItemsPerRow + 2) * thumbnailMargin) > availableScreenWidth) {
            numberOfItemsPerRow--;
        }

        int numberOfRowsNeeded = (int) Math.ceil(numberOfItemsNeeded / numberOfItemsPerRow);

        holder.getGridLayout().removeAllViews();
        holder.getGridLayout().setColumnCount(numberOfItemsPerRow);
        holder.getGridLayout().setRowCount(numberOfRowsNeeded);

        for (int x = 0; x < numberOfItemsNeeded; x++) {
            View gridItem = LayoutInflater.from(getContext()).inflate(getContext().getResources().getLayout(R.layout.image_grid_item), null);

            TextView hourlyTextView = gridItem.findViewById(R.id.grid_item_hourly_hour);
            ImageView iconImageView = gridItem.findViewById(R.id.grid_item_hourly_icon);
            TextView temperatureTextView = gridItem.findViewById(R.id.grid_item_hourly_temperature);

            ForecastData hourly = hourlyForecast.get(x);
            int hourlyPosition = hourlyForecast.indexOf(hourly);

            Date timeDate = new Date(hourly.getTime() * 1000);

            String stringTime = DateTime.convertDateToString(timeDate, DateTime.timeFormatter);
            Integer hourlyCurrentTime = DateTime.toHours(stringTime);

            String temperature = String.valueOf(Math.round(hourly.getTemperature())) + "\u00B0";

            String militaryTime = DateTime.convertDateToString(timeDate, DateTime.militaryTimeFormatter);
            Integer newMilitaryTime = DateTime.toHours(militaryTime);

            String currentTime = DateTime.convertDateToString(Calendar.getInstance().getTime(), DateTime.militaryTimeFormatter);
            Integer newCurrentTimeExact = DateTime.toHours(currentTime);
            if (position == 0) {
                if (hourlyPosition < 25) {
                    if (newMilitaryTime > newCurrentTimeExact) {
                        holder.getGridLayout().addView(gridItem);
                    }
                }
            } else {
                if (hourlyPosition <= 24) {
                    if (newMilitaryTime < newCurrentTimeExact) {
                        holder.getGridLayout().addView(gridItem);
                    }
                }

                if (hourlyPosition < 48 && hourlyPosition >= 24) {
                    if (newMilitaryTime >= newCurrentTimeExact) {
                        holder.getGridLayout().addView(gridItem);
                    }
                }
            }

            hourlyTextView.setText(stringTime);
            temperatureTextView.setText(temperature);

            String hourlyIcon = hourly.getIcon();
            IconApi iconApi = new IconApi();
            Boolean highlighted = false;
            int indexOfHigh = 0;
            int indexOfLow = 0;

            if (hourly.getTemperature().equals(dailyTemperatureMax)) {
                indexOfHigh = hourlyForecast.indexOf(hourly);
            } else if (hourly.getTemperature().equals(dailyTemperatureMinimum)) {
                indexOfLow = hourlyForecast.indexOf(hourly);
            }

//            if(!alreadyExecuted) {
//                if (indexOfHigh == hourlyPosition) {
//                    highlighted = true;
//                } else if (indexOfLow == hourlyPosition) {
//                    highlighted = true;
//                }
//            }
//            alreadyExecuted = true;
            highlighted = hourly.getTemperature().equals(dailyTemperatureMax) || hourly.getTemperature().equals(dailyTemperatureMinimum);

            Picasso.with(getContext())
                    .load(iconApi.getUrlForIcon(hourlyIcon, highlighted))
                    .into(iconImageView);

            if (!(hourly.getTemperature().equals(dailyTemperatureMax) && hourly.getTemperature().equals(dailyTemperatureMinimum))) {
                if (hourly.getTemperature().equals(dailyTemperatureMax)) {
                    iconImageView.setColorFilter(ContextCompat.getColor(context, R.color.weather_warm), android.graphics.PorterDuff.Mode.SRC_IN);
                    hourlyTextView.setTextColor(getContext().getResources().getColor(R.color.weather_warm));
                    temperatureTextView.setTextColor(getContext().getResources().getColor(R.color.weather_warm));
                } else if (hourly.getTemperature().equals(dailyTemperatureMinimum)) {
                    iconImageView.setColorFilter(ContextCompat.getColor(context, R.color.weather_cool), PorterDuff.Mode.SRC_IN);
                    hourlyTextView.setTextColor(getContext().getResources().getColor(R.color.weather_cool));
                    temperatureTextView.setTextColor(getContext().getResources().getColor(R.color.weather_cool));
                } else {
                    iconImageView.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                    hourlyTextView.setTextColor(getContext().getResources().getColor(R.color.black));
                    temperatureTextView.setTextColor(getContext().getResources().getColor(R.color.black));
                }
            } else {
                iconImageView.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                hourlyTextView.setTextColor(getContext().getResources().getColor(R.color.black));
                temperatureTextView.setTextColor(getContext().getResources().getColor(R.color.black));
            }
        }
    }

    private Context getContext() {
        return context;
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {

        TextView day;
        GridLayout gridLayout;

        private ForecastViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.forecast_day);
            gridLayout = itemView.findViewById(R.id.grid_layout);
        }

        GridLayout getGridLayout() {
            return gridLayout;
        }
    }

    public void updateForecastData(Forecast forecast) {
        dailyForecast = forecast.getDaily().getData();
        hourlyForecast = forecast.getHourly().getData();
        notifyDataSetChanged();
    }
}
