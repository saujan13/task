package com.myproject.admin.task.utils;


import android.app.Activity;
import android.app.ProgressDialog;

public class LoadingUtils {

    private static ProgressDialog progressDialog;

    public static void showProcessDialogBox(String message,String title, Activity activity){
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dissmissDialog(){
        progressDialog.dismiss();
    }
}
