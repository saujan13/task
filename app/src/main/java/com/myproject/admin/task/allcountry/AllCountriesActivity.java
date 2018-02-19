package com.myproject.admin.task.allcountry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.myproject.admin.task.R;
import com.myproject.admin.task.adapter.CountryAdapter;
import com.myproject.admin.task.modal.CountryModal;
import com.myproject.admin.task.singlecountry.SingleCountry;
import com.myproject.admin.task.utils.LoadingUtils;

import java.util.List;

public class AllCountriesActivity extends AppCompatActivity implements AllCountryView{

    private AllCountryPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_countries);

        initialization();
        getAllCountryList();
    }

    private void initialization() {
        presenter = new AllCountryPresenter(getApplicationContext(), this);
        recyclerView = findViewById(R.id.all_country);
    }

    private void getAllCountryList() {
        presenter.fetchAllCountryList();
    }


    @Override
    public void showLoadingDialog() {
        LoadingUtils.showProcessDialogBox(getString(R.string.retrieving_country_list), getString(R.string.loading), AllCountriesActivity.this);
    }

    @Override
    public void dismissLoadingDialog() {
        LoadingUtils.dissmissDialog();

    }

    @Override
    public void displayAllCountryName(List<CountryModal> countryModalList) {
        CountryAdapter mAdapter = new CountryAdapter(countryModalList, new CountryAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(CountryModal item) {
                //Toast.makeText(AllCountriesActivity.this, item.getName(), Toast.LENGTH_LONG).show();
                Intent singleCountry = new Intent(AllCountriesActivity.this, SingleCountry.class);
                singleCountry.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                singleCountry.putExtra("singleCountryModal", item);
                startActivity(singleCountry);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
