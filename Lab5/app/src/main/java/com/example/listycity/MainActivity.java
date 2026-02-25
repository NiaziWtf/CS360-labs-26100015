package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivity";
    ListView cityList;
    CityAdapter cityAdapter;
    ArrayList<City> dataList;

    FirebaseFirestore db;
    CollectionReference citiesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        citiesRef = db.collection("cities");

        Button addCityButton = findViewById(R.id.add_city_button);
        Button deleteCityButton = findViewById(R.id.delete_city_button);
        cityList = findViewById(R.id.city_list);

        dataList = new ArrayList<>();
        cityAdapter = new CityAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        citiesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().isEmpty()) {
                    addInitialCities();
                }
            }
        });

        citiesRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot querySnapshots, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e(TAG, "Error getting cities", error);
                    return;
                }

                dataList.clear();
                for (QueryDocumentSnapshot doc : querySnapshots) {
                    City city = doc.toObject(City.class);
                    dataList.add(city);
                }
                cityAdapter.notifyDataSetChanged();
            }
        });

        addCityButton.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");
        });

        deleteCityButton.setOnClickListener(v -> {
            showDeleteCityDialog();
        });
    }

    @Override
    public void onOkPressed(City newCity) {
        citiesRef.document(newCity.getCityName()).set(newCity)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "City successfully written!"))
                .addOnFailureListener(e -> Log.w(TAG, "Error writing city", e));
    }

    @Override
    public void onEditPressed(City city) {
        citiesRef.document(city.getCityName()).set(city)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "City successfully updated!"))
                .addOnFailureListener(e -> Log.w(TAG, "Error updating city", e));
    }

    private void addInitialCities() {
        ArrayList<City> initialCities = new ArrayList<>(Arrays.asList(
                new City("Edmonton", "AB"),
                new City("Calgary", "AB"),
                new City("Victoria", "BC"),
                new City("Winnipeg", "MB"),
                new City("Toronto", "ON")
        ));

        for (City city : initialCities) {
            citiesRef.document(city.getCityName()).set(city);
        }
    }

    private void showDeleteCityDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setHint("Enter city name to delete");
        builder.setView(input);
        builder.setPositiveButton("Delete", (dialog, which) -> {
            String cityName = input.getText().toString();
            if (!cityName.isEmpty()) {
                deleteCity(cityName);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void deleteCity(String cityName) {
        citiesRef.document(cityName).delete()
            .addOnSuccessListener(aVoid -> {
                Log.d(TAG, "City successfully deleted!");
                Toast.makeText(MainActivity.this, "City deleted", Toast.LENGTH_SHORT).show();
            })
            .addOnFailureListener(e -> {
                Log.w(TAG, "Error deleting city", e);
                Toast.makeText(MainActivity.this, "Error deleting city", Toast.LENGTH_SHORT).show();
            });
    }
}
