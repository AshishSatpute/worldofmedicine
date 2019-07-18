package com.wpes.worldofmedicine.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.wpes.worldofmedicine.MainActivity;
import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.network.CheckConnection;

import com.wpes.worldofmedicine.utils.AppController;
import com.wpes.worldofmedicine.utils.UrlLinks;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    TextView login;
    private EditText input_name, input_email, input_mobile, input_password, conform_password;
    Button sign_up;
    String name, email, mobile, aadhar, pan, address, pass, conpass;
    ImageView dsh;
    AwesomeValidation awesomeValidation;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);



        progressBar=findViewById(R.id.progressBar);
        input_name = findViewById(R.id.etUsername);
        input_email = findViewById(R.id.etUserEmail);
        input_mobile = findViewById(R.id.etUserMob);
        input_password = findViewById(R.id.etPassword);
        sign_up = findViewById(R.id.btnRegister);
        login = findViewById(R.id.tvSignUp);
        conform_password = findViewById(R.id.etConPassword);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Validation
        awesomeValidation.addValidation(this, R.id.etUsername, "^[A-Za-z\\\\s]{1,}[\\\\.]{0,1}[A-Za-z\\\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etUserMob, "^[+]?[0-9]{10,13}$", R.string.moberr);
        awesomeValidation.addValidation(this, R.id.etUserEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        //awesomeValidation.addValidation(this,R.id.address,"",R.string.regaddress);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";

        awesomeValidation.addValidation(this, R.id.etPassword, regexPassword, R.string.passerror);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_password.getText().toString().equals(conform_password.getText().toString())) {
                    conform_password.setError("Password Not matched");
                    conform_password.requestFocus();
                } else
                    if (awesomeValidation.validate()) {
                        signup();
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.network), Toast.LENGTH_LONG).show();
                    }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, Login.class));
            }
        });
    }
    private void signup() {
        progressBar.setVisibility(View.VISIBLE);

        Map<String, String> params = new HashMap<>();
        params.put("full_name", input_name.getText().toString());
        params.put("mobile", input_mobile.getText().toString());
        params.put("email", input_email.getText().toString());
        params.put("password", input_password.getText().toString());

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                UrlLinks.RegistrationUrl,
                parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d("data", "onResponse: " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            String message = response.getString("success");
                            if (message.equals("1")) {
                                JSONObject data = new JSONObject(String.valueOf(jsonObject)).getJSONObject("Data");

                                Intent intent = new Intent(Registration.this, OtpVerification.class);
                                intent.putExtra("otp", data.getString("otp"));
                                intent.putExtra("customer_id", data.getString("customer_id"));
                                intent.putExtra("mobile", input_mobile.getText().toString());
                                startActivity(intent);

                                //Toast.makeText(Registration.this, "", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Registration.this, "Mobile Number Already Exits", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Registration.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        AppController.getInstance(this).addToRequest(request);
    }

    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}