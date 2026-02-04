package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        TextView addCityButton = findViewById(R.id.add_city);

        dataList = new ArrayList<>();
        dataList.add(new City("Edmonton", "Alberta"));
        dataList.add(new City("Vancouver", "British Columbia"));
        dataList.add(new City("Moscow", "Russia"));
        dataList.add(new City("Sydney", "Australia"));
        dataList.add(new City("Berlin", "Germany"));
        dataList.add(new City("Vienna", "Austria"));
        dataList.add(new City("Tokyo", "Japan"));
        dataList.add(new City("Beijing", "China"));
        dataList.add(new City("Osaka", "Japan"));
        dataList.add(new City("New Delhi", "India"));

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        addCityButton.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City selectedCity = dataList.get(position);
            AddCityFragment.newInstance(selectedCity).show(getSupportFragmentManager(), "EDIT_CITY");
        });

        cityList.setOnItemLongClickListener((parent, view, position, id) -> {
            dataList.remove(position);
            cityAdapter.notifyDataSetChanged();
            return true;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onOkPressed(City newCity) {
        dataList.add(newCity);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditPressed(City city) {
        cityAdapter.notifyDataSetChanged();
    }
}