package com.myproject.admin.task.allcountry;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.myproject.admin.task.R;
import com.myproject.admin.task.modal.CountryModal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AllCountryPresenter {

    private String TAG = "COUNTRY PRESENTER";
    private Context context;
    private AllCountryView view;

    private RequestQueue requestQueue;

    private List<CountryModal> countryModalList = new ArrayList<CountryModal>();

    AllCountryPresenter(Context context, AllCountryView view){
        this.context = context;
        this.view = view;
        requestQueue = Volley.newRequestQueue(context);

    }

    public void fetchAllCountryList() {
        view.showLoadingDialog();
        try{
            String rateURL ="http://services.groupkt.com/country/get/all";
            StringRequest getCountryListRequest = new StringRequest(Request.Method.GET , rateURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    showResponse(response);
                }
            }, new Response.ErrorListener() { //error are handled in this section according to the status code
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    view.dismissLoadingDialog();
                    NetworkResponse response = volleyError.networkResponse;

                    if (volleyError instanceof NetworkError) {
                        String message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        //return;
                    }
                    else{
                        Toast.makeText(context, R.string.server_error, Toast.LENGTH_SHORT).show();
                    }
                }
            });
            getCountryListRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(getCountryListRequest);

        }
        catch (Exception e){
            view.dismissLoadingDialog();
            Log.e(TAG, " "+e);
        }
    }

    private void showResponse(String response) {
        view.dismissLoadingDialog();
        //Log.e(TAG, " RESPONSE: "+response);
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONObject RestResponse = jsonObject.getJSONObject("RestResponse");
            JSONArray countryArray = RestResponse.getJSONArray("result");

            for (int i = 0; i < countryArray.length(); i++) {
                JSONObject countryData = countryArray.getJSONObject(i);
                String name = countryData.getString("name");
                String alpha2_code = countryData.getString("alpha2_code");
                String alpha3_code = countryData.getString("alpha3_code");

                CountryModal countryModal = new CountryModal();
                countryModal.setName(name);
                countryModal.setAlpha2_code(alpha2_code);
                countryModal.setAlpha3_code(alpha3_code);

                countryModalList.add(countryModal);
            }

            view.displayAllCountryName(countryModalList);
        }
        catch (Exception e){
            Log.e(TAG, " EXCEPTION"+e);
        }
    }
}
