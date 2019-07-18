package com.wpes.worldofmedicine.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wpes.worldofmedicine.R;

public class Delivery extends AppCompatActivity {


    public static final String TAG = Delivery.class.getCanonicalName();
    //TextView tvAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        Log.i(TAG, "onCreate: ");

    }
}
