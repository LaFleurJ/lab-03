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

import com.example.listycity.City;

public class AddCityFragment extends DialogFragment {

    private City city;

    interface AddCityDialogListener {
        void addCity(City city);
        void editCity(City city);
    }
    private AddCityDialogListener listener;
    //make a constructor for the Fragment that takes in a City, and store the City in the Fragment as an instance variable.
    public AddCityFragment(City city) {
        this.city = city;
    }
    // also add an empty constructor to the Fragment so you can use it when adding a new City.
    public AddCityFragment() {
        this.city = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        if (city != null) {
            editCityName.setText(city.getName());
            editProvinceName.setText(city.getProvince());
        }
        else {
            editCityName.setText("");
            editProvinceName.setText("");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add/Edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    // if city already there then edit it, if its not then add it
                    if (city != null) {
                        city.setName(cityName);
                        city.setProvince(provinceName);
                        listener.editCity(city);
                    } else {
                        listener.addCity(new City(cityName, provinceName));
                    }
                })
                .create();
    }
}