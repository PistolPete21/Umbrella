package com.nerdery.umbrella.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.adapter.NewCityAdapter;
import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import java.util.ArrayList;
import java.util.List;

public class NewCityFragment extends Fragment implements NewCityAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private NewCityAdapter adapter;
    private List<String> items = new ArrayList<>();
    private String zip;
    private String unit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_city, container, false);

        zip = "55431";
        unit = "Fahrenheit";

        items.add(0, zip);
        items.add(1, unit);

        //setup recyclerview
        recyclerView = v.findViewById(R.id.fragment_new_city_recyclerview);
        if (adapter == null) {
            adapter = new NewCityAdapter(getActivity(), items, this);
        }
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //setup toolbar
        toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.umbrella_toolbar_text));
        ((AppCompatActivity) v.getContext()).setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        ((AppCompatActivity) v.getContext()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return v;
    }

    @Override
    public void onClick(Object item, int index) {
        if (item != null) {
            if (index == 0) {
                new LovelyTextInputDialog(getContext(), R.style.AppTheme)
                        .setTopColorRes(R.color.weather_cool)
                        .setTitle(R.string.zip)
                        .setMessage(R.string.zip_message)
                        .setIcon(R.mipmap.ic_launcher)
                        .setInputFilter(R.string.zip_error, new LovelyTextInputDialog.TextFilter() {
                            @Override
                            public boolean check(String text) {
                                if (text.length() > 5) {
                                    return false;
                                } else {
                                    return text.matches("\\w+");
                                }
                            }
                        })
                        .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                            @Override
                            public void onTextInputConfirmed(String text) {
                                items.remove(0);
                                items.add(0, text);
                                adapter.updateView(items);
                            }
                        })
                        .show();
            } else if (index == 1){
                final String[] unitNames = getResources().getStringArray(R.array.units);
                new LovelyChoiceDialog(getContext())
                        .setTopColorRes(R.color.weather_cool)
                        .setTitle(R.string.units)
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage(R.string.unit_message)
                        .setItems(unitNames, new LovelyChoiceDialog.OnItemSelectedListener<String>() {
                            @Override
                            public void onItemSelected(int position, String item) {
                                if (position == 0) {
                                    items.remove(1);
                                    items.add("Fahrenheit");
                                } else if (position == 1) {
                                    items.remove(1);
                                    items.add("Celsius");
                                }
                                adapter.updateView(items);
                            }
                        })
                        .show();
            }
        }
    }
}
