package com.myproject.admin.task.modal;

import java.io.Serializable;

public class CountryModal implements Serializable {

    private String name, alpha2_code, alpha3_code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2_code() {
        return alpha2_code;
    }

    public void setAlpha2_code(String alpha2_code) {
        this.alpha2_code = alpha2_code;
    }

    public String getAlpha3_code() {
        return alpha3_code;
    }

    public void setAlpha3_code(String alpha3_code) {
        this.alpha3_code = alpha3_code;
    }

}
