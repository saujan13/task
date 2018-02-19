package com.myproject.admin.task.allcountry;

import com.myproject.admin.task.modal.CountryModal;

import java.util.List;

public interface AllCountryView {

    void showLoadingDialog();

    void dismissLoadingDialog();

    void displayAllCountryName(List<CountryModal> countryModalList);
}
