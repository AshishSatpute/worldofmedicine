package com.wpes.worldofmedicine.productcategories;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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
import com.wpes.worldofmedicine.activities.Login;
import com.wpes.worldofmedicine.adapter.CategoryAdapter;
import com.wpes.worldofmedicine.adapter.DashBoardListItemsAdapter;
import com.wpes.worldofmedicine.fragments.Home;
import com.wpes.worldofmedicine.model.CategoriesModal;
import com.wpes.worldofmedicine.model.DashBoardListItems;
import com.wpes.worldofmedicine.model.ProductsCat;
import com.wpes.worldofmedicine.utils.AppController;
import com.wpes.worldofmedicine.utils.UrlLinks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BabyProduct extends AppCompatActivity {

    RecyclerView rv;

    Context context;
    public static final String TAG = BabyProduct.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_product);
        rv=findViewById(R.id.rvCat);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle("Baby Products");
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

    }

