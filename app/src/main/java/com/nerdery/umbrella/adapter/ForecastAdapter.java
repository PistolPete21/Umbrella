package com.nerdery.umbrella.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nerdery.umbrella.R;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private ItemClickListener itemClickListener;
    private LayoutInflater inflater;
    private List<String> items;

    public ForecastAdapter(Context context, List<String> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = inflater.inflate(R.layout.list_weather_forecast, parent, false);
                return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        String hourlyForecast = items.get(position);
        holder.forecast.setText(hourlyForecast);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView day;
        TextView forecast;

        ForecastViewHolder(View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.forecast_day);
            forecast = itemView.findViewById(R.id.forecast_data);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
