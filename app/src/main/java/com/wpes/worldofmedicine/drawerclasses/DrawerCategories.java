package com.wpes.worldofmedicine.drawerclasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.adapter.CategoryAdapter;
import com.wpes.worldofmedicine.model.ProductCategories;

import com.wpes.worldofmedicine.utils.AppController;
import com.wpes.worldofmedicine.utils.UrlLinks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class DrawerCategories extends AppCompatActivity {
    //a list to store all the products

    ArrayList<ProductCategories> mCategoryAdpater = new ArrayList<>();

    CategoryAdapter categoryAdapter;
    private Context context;
    //the recyclerview
   private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        settingToolBar();
        //getting the recyclerview from xml
        recyclerView= findViewById(R.id.recyclerView);

        getListItems();

        mCategoryAdpater = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, mCategoryAdpater);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }
    private void settingToolBar()  {
        Toolbar toolBar = findViewById(R.id.toolbar);
        toolBar.setTitle("Categories");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setLogo(R.drawable.ic_action_navigation_arrow_back);
        toolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setSupportActionBar(toolBar);
    }
    private ArrayList<ProductCategories> getListItems() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        HashMap<String, String> hashMap = new HashMap<>();
        JSONObject parameters = new JSONObject(hashMap);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                UrlLinks.ALLCATEGORY_URL,
                parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject responses) {
                        progressDialog.dismiss();
                        Log.d("TAG", "onResponse: " + responses);
                        try {
                            if (responses.getString("success").contains("1")) {
                                mCategoryAdpater.clear();
                                try {
                                    JSONArray jsonArray = responses.getJSONArray("Data");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);

                                        object.getString("id");
                                        ProductCategories model = new ProductCategories(
                                               object.getString("cat_name")
                                               // object.getString("id")

                                        );
                                        mCategoryAdpater.add(model);
                                    }
                                } catch (JSONException e) {
                                    progressDialog.dismiss();
                                    e.printStackTrace();
                                }
                                categoryAdapter = new CategoryAdapter(context, mCategoryAdpater);
                                recyclerView.setAdapter(categoryAdapter);
                            } else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "onErrorResponse: " + error);
            }
        });

        AppController.getInstance(context).addToRequest(jsonRequest);

        return mCategoryAdpater;
    }
}
