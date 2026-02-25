package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    private EditText cityNameEditText;
    private EditText provinceNameEditText;
    private OnFragmentInteractionListener listener;
    private City city;

    public interface OnFragmentInteractionListener {
        void onOkPressed(City newCity);
        void onEditPressed(City city);
    }

    public static AddCityFragment newInstance(City city) {
        AddCityFragment fragment = new AddCityFragment();
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_city, null);
        cityNameEditText = view.findViewById(R.id.city_name_edit_text);
        provinceNameEditText = view.findViewById(R.id.province_name_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        Bundle args = getArguments();
        if (args != null) {
            city = (City) args.getSerializable("city");
            builder.setTitle("Edit City");
            cityNameEditText.setText(city.getCityName());
            provinceNameEditText.setText(city.getProvinceName());
        } else {
            builder.setTitle("Add City");
        }

        return builder
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialog, which) -> {
                    String cityName = cityNameEditText.getText().toString();
                    String provinceName = provinceNameEditText.getText().toString();
                    if (!cityName.isEmpty() && !provinceName.isEmpty()) {
                        if (city != null) {
                            city.setCityName(cityName);
                            city.setProvinceName(provinceName);
                            listener.onEditPressed(city);
                        } else {
                            listener.onOkPressed(new City(cityName, provinceName));
                        }
                    }
                })
                .create();
    }
}