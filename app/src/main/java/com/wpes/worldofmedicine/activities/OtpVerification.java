package com.wpes.worldofmedicine.activities;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.utils.AppController;
import com.wpes.worldofmedicine.utils.UrlLinks;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class OtpVerification extends AppCompatActivity {


    public static final String TAG = OtpVerification.class.getCanonicalName();
    EditText inputOtp;
    TextView showNum;
    Button btnVerify;
    String id, otp, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);


        showNum=findViewById(R.id.numberText);
        inputOtp=findViewById(R.id.etOtp);

        btnVerify=findViewById(R.id.btnConform);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("customer_id");
            otp = extras.getString("otp");
            mobile = extras.getString("mobile");
            showNum.setText(mobile);
        }

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtp(id, otp);
                handle();
            }
        });

    }

    private void handle() {
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    OtpVerification.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(OtpVerification.this, Login.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    });

                }
            }
        };
        timerThread.start();
    }

    private void verifyOtp(String id, String otp) {


        Map<String, String> params = new HashMap<>();
        params.put("customer_id", id);
        params.put("otp", otp);
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                UrlLinks.OtpVerification,
                parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "registorUser: "+response);
                        try {
                            String message = response.getString("success");
                            if (message.equals("1")) {
                                inputOtp.setVisibility(View.INVISIBLE);
                                showCustomDialog();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse: " + error);
                        Toast.makeText(OtpVerification.this, "" + error, Toast.LENGTH_SHORT).show();
                    }
                });
        AppController.getInstance(getApplicationContext()).addToRequest(jsonObjectRequest);
    }

    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.otp_dialog, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}