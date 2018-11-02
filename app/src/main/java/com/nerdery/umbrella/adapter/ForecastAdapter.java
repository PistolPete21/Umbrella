package com.nerdery.umbrella.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.model.ForecastData;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ForecastData> dailyForecast;

    public ForecastAdapter(Context context, List<ForecastData> dailyForecast) {
        this.context = context;
        this.dailyForecast = dailyForecast;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_weather_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        //ForecastData day = wee.get(position);

        //String currentDay = day.getTime()
    }

    @Override
    public int getItemCount() {
        return 0;
        //return items == null ? 0 : items.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {

        TextView day;
        TextView forecast;

        ForecastViewHolder(View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.forecast_day);
            forecast = itemView.findViewById(R.id.forecast_data);
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
