package com.myproject.admin.task.singlecountry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.myproject.admin.task.R;
import com.myproject.admin.task.modal.CountryModal;

public class SingleCountry extends AppCompatActivity implements SingleCountryView{

    private TextView name, alpha2_code, alpha3_code;
    private CountryModal countryModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_country);
        initialization();
        displaySingleCountryInfo();
    }

    private void initialization() {
        name = findViewById(R.id.single_country_name);
        alpha2_code = findViewById(R.id.single_country_alpha2_code);
        alpha3_code = findViewById(R.id.single_country_alpha3_code);
        countryModal = (CountryModal) getIntent().getSerializableExtra("singleCountryModal");
    }

    @Override
    public void displaySingleCountryInfo() {
        name.setText(countryModal.getName());
        alpha2_code.setText(countryModal.getAlpha2_code());
        alpha3_code.setText(countryModal.getAlpha3_code());

    }
}
