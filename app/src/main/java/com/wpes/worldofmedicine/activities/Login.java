package com.wpes.worldofmedicine.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.wpes.worldofmedicine.MainActivity;
import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.fragments.Home;
import com.wpes.worldofmedicine.model.User;
import com.wpes.worldofmedicine.network.CheckConnection;
import com.wpes.worldofmedicine.sessionmanager.SharedPrefManager;
import com.wpes.worldofmedicine.utils.AppController;
import com.wpes.worldofmedicine.utils.UrlLinks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    TextView tvRegister, tvSkip,forgetpass;
    EditText etUser, etPass;
    ImageView imFb, imGmail;
    Button login;
    CheckBox savelogin;
    ProgressDialog progressDialog;
    CoordinatorLayout coordinatorLayout;

    ProgressBar progressBar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        progressBar = findViewById(R.id.progressBar);

        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        context = this;
        setTitle("Registration");

        handleIntent(getIntent());
        tvRegister = findViewById(R.id.tvSignIn);
        tvSkip = findViewById(R.id.tvSkip);
        etUser = findViewById(R.id.etUserName);
        etPass = findViewById(R.id.etPass);
        imFb = findViewById(R.id.imFb);
        imGmail = findViewById(R.id.imGmail);
        login = findViewById(R.id.btnLogin);
        forgetpass = findViewById(R.id.txt_forgotpassword);
        setTitle("Login");

        coordinatorLayout = findViewById(R.id.coordinatorLay);



        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changepassword_dialog();
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                    String mUser = etUser.getText().toString().trim();
                    String mPass = etPass.getText().toString().trim();
                    if (mUser.equals("") && mPass.equals("")) {
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Please Enter Valid Credentails", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else if (mUser.equals("")) {
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Please Enter Your Email", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else if (mPass.equals("")) {
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Please Enter Your Password", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                        doLogin(mUser, mPass);
                    }
                }
            }
        });
    }
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    public void doLogin(final String username, final String password)
    {
        progressBar.setVisibility(View.VISIBLE);
        Map<String, String> params = new HashMap<>();
        params.put("email", username);
        params.put("password", password);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                UrlLinks.SLoginUrl,
                parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d("TAG", "onResponse: " + response);
                        try {

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {

                                JSONObject data = new JSONObject(String.valueOf(jsonObject)).getJSONObject("Data");
                                User user = new User(
                                        data.getString("id"),
                                        data.getString("full_name"),
                                        data.getString("email"),
                                        data.getString("mobile")
                                );
                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                startActivity(new Intent(Login.this, MainActivity.class));

                            } else if(success.equals("0")){

                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Login.this, "Invalid Credentails..! Please Enter Valid Email", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(Login.this, "User Not Active", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        RetryPolicy policy = new DefaultRetryPolicy(40000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        request.setShouldCache(false);
        AppController.getInstance(getApplicationContext()).addToRequest(request);

    }


    public void forgetPass(final String email,final Dialog dialog){

        final ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final StringRequest postreq = new StringRequest(Request.Method.POST, UrlLinks.ForgetEmail, new Response.Listener<String>() {

            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String s1 = jsonObject.getString("success");
                    if (s1.equals("1")) {
                        Toast.makeText(Login.this, "Sucesss", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Registration.class));
                        progressDialog.dismiss();
                    } else if (s1.equals("2")) {
                        Toast.makeText(Login.this, "Sent", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this,Login.class));
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e1) {
                    Log.e("k1", e1.toString());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error connecting to server", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("email", email);
                return map;
            }
        };
        RetryPolicy policy = new DefaultRetryPolicy(500000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        postreq.setRetryPolicy(policy);
        postreq.setShouldCache(false);
        AppController.getInstance(getApplicationContext()).addToRequest(postreq);

    }

    public void changepassword_dialog() {
        final Dialog dialog = new Dialog(Login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.forget_pass);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;

        final EditText email = (EditText) dialog.findViewById(R.id.input_email);

        AppCompatButton btn_change = (AppCompatButton) dialog.findViewById(R.id.btn_reset);
        AppCompatButton btn_cancel = (AppCompatButton) dialog.findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = Login.this.getCurrentFocus();
                if (view != null) {
                    CheckConnection.hideKeyboard(Login.this, view);
                }
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
                    //dialog.cancel();
                    forgetPass(email.getText().toString().trim(), dialog);

                } else {
                    email.setError(getString(R.string.email_invalid));
                    // Toast.makeText(LoginActivity.this, "email is invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }
}
