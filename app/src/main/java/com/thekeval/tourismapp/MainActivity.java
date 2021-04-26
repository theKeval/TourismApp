package com.thekeval.tourismapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Spinner spinner_countries;
    RecyclerView rv_poi;
    TextView tvCapital, tvTotalPrice;
    ImageView ivFlag;
    Button btnCalculate, btnReset;
    EditText etVisitors;

    ArrayList<CountryModel> lstCountries;
    ArrayList<String> lstCountryNames;
    public static ArrayList<PlaceModel> currentPlaces;
    PlacesAdapter poiAdapter;
    public static PlaceModel selectedPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get UI elements references
        spinner_countries = findViewById(R.id.spinner_countries);
        tvCapital = findViewById(R.id.tv_capital);
        ivFlag = findViewById(R.id.img_flag);
        rv_poi = findViewById(R.id.rv_poi);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnReset = findViewById(R.id.btn_reset);
        etVisitors = findViewById(R.id.et_visitors);
        tvTotalPrice = findViewById(R.id.tv_totalAmount);

        // Initialize and fillData
        lstCountries = new ArrayList<>();
        fillData();
        lstCountryNames = new ArrayList<>();
        lstCountryNames.add("Canada"); lstCountryNames.add("USA"); lstCountryNames.add("England");

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lstCountryNames);
        spinner_countries.setAdapter(countryAdapter);
        spinner_countries.setSelection(0, true);

        spinner_countries.setOnItemSelectedListener(new CountrySelectionListener());

        currentPlaces = lstCountries.get(0).poi;
        poiAdapter = new PlacesAdapter(this, currentPlaces);
        rv_poi.setAdapter(poiAdapter);
        rv_poi.setLayoutManager(new LinearLayoutManager(this));

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etVisitors.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter number of visitors", Toast.LENGTH_SHORT).show();
                    return;
                }

                double totalPrice = 0;

                double price = selectedPlace.price;
                int visitors =  Integer.parseInt(etVisitors.getText().toString());

                if (visitors > 15) {
                    totalPrice = visitors * (price - (price * 0.05));
                }
                else {
                    totalPrice = visitors * price;
                }

                tvTotalPrice.setText("$ " + totalPrice);
            }
        });

    }




    public void fillData() {
        ArrayList<PlaceModel> canada_poi = new ArrayList<>();
        canada_poi.add(new PlaceModel("Niagara Falls", "niagara_falls", 100.0));
        canada_poi.add(new PlaceModel("CN Tower", "cn_tower", 30.0));
        canada_poi.add(new PlaceModel("The Butchart Gardens", "butchart_gardens", 30.0));
        canada_poi.add(new PlaceModel("Notre-Dame Basilica", "notre_dame_basilica", 50.0));
        lstCountries.add(new CountryModel("Canada", "canada_flag", "Ottawa", canada_poi));

        ArrayList<PlaceModel> usa_poi = new ArrayList<>();
        usa_poi.add(new PlaceModel("The Statue of Liberty", "statue_of_liberty", 90.0));
        usa_poi.add(new PlaceModel("The White House", "white_house", 30.0));
        usa_poi.add(new PlaceModel("Times Square", "times_square", 30.0));
        lstCountries.add(new CountryModel("USA", "usa_flag", "Washington", usa_poi));

        ArrayList<PlaceModel> england_poi = new ArrayList<>();
        england_poi.add(new PlaceModel("Big Ben", "big_ben", 30.0));
        england_poi.add(new PlaceModel("Westminster Abbey", "westminster_abbey", 25.0));
        england_poi.add(new PlaceModel("Hyde Park", "hyde_park", 15.0));
        lstCountries.add(new CountryModel("England", "england_flag", "London", england_poi));
    }


    private class CountrySelectionListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            CountryModel country = lstCountries.get(i);
            tvCapital.setText(country.capital);
            ivFlag.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, getResources().getIdentifier(country.flag, "drawable", MainActivity.this.getPackageName())));

            currentPlaces = country.poi;
            // poiAdapter.notifyDataSetChanged();
            poiAdapter = new PlacesAdapter(MainActivity.this, currentPlaces);
            rv_poi.setAdapter(poiAdapter);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}