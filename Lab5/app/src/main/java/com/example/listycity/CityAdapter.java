package com.example.listycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<City> {

    private Context context;
    private ArrayList<City> cities;

    public CityAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
        this.context = context;
        this.cities = cities;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        City city = cities.get(position);

        TextView cityText = view.findViewById(R.id.city_text);
        TextView provinceText = view.findViewById(R.id.province_text);

        cityText.setText(city.getCityName());
        provinceText.setText(city.getProvinceName());

        return view;
    }
}
