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

public class NewCityAdapter extends RecyclerView.Adapter<NewCityAdapter.NewCityViewHolder> {

    private Context context;
    private List<String> items;
    private final ItemClickListener itemClickListener;

    public interface ItemClickListener<T> {
        void onClick(T item, int index);
    }

    public NewCityAdapter(Context context, List<String> items, ItemClickListener itemClickListener) {
        this.context = context;
        this.items = items;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public NewCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.list_new_city_item, parent, false);
        return new NewCityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewCityViewHolder holder, final int position) {
        String value = items.get(position);
        final Object cell = items.get(position);

        switch (position) {
            case 0:
                holder.getTextLabel().setText(R.string.zip);
                holder.getTextSubtitle().setText(value);
                break;
            case 1:
                holder.getTextLabel().setText(R.string.units);
                holder.getTextSubtitle().setText(value);
                break;
        }

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemClickListener().onClick(cell, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class NewCityViewHolder extends RecyclerView.ViewHolder {
        private final View itemView;
        private final TextView textLabel;
        private final TextView textSubtitle;

        public NewCityViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.textLabel = itemView.findViewById(R.id.list_new_city_item_label);
            this.textSubtitle = itemView.findViewById(R.id.list_new_city_item_value);
        }

        public View getItemView() {
            return itemView;
        }

        public TextView getTextLabel() {
            return textLabel;
        }

        public TextView getTextSubtitle() {
            return textSubtitle;
        }

        private void setOnClickListener(View.OnClickListener listener) {
            itemView.setOnClickListener(listener);
        }
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void updateView(List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
