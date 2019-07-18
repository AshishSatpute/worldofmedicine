package com.wpes.worldofmedicine.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.activities.Login;
import com.wpes.worldofmedicine.activities.Upload;

import com.wpes.worldofmedicine.adapter.CategaryItemDashBoardAdapter;
import com.wpes.worldofmedicine.adapter.DashBoardListItemsAdapter;
import com.wpes.worldofmedicine.adapter.ViewPagerDashBoardAdapter;
import com.wpes.worldofmedicine.model.CategoriesModal;
import com.wpes.worldofmedicine.model.DashBoardListItems;
import com.wpes.worldofmedicine.model.User;
import com.wpes.worldofmedicine.sessionmanager.SharedPrefManager;
import com.wpes.worldofmedicine.utils.AppController;
import com.wpes.worldofmedicine.utils.UrlLinks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;


public class Home extends Fragment {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    RecyclerView recyclerView,recyclerView1;
    Button uploadIm;
    View view;
    String id;
    SharedPrefManager prefManager;
    ProgressBar progressBar;
    DashBoardListItemsAdapter dashBoardListItemsAdapter;
    ArrayList<DashBoardListItems>   mDashBoardListItems = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;
    public static final String TAG = Home.class.getCanonicalName();

    public Home() {
        // Required empty public constructor
    }
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        init();
        getActivity().setTitle("Home");
        //progressBar.setVisibility(View.VISIBLE);
        progressBar=view.findViewById(R.id.pBar);
        mDashBoardListItems = getListItem();


        /*prefManager = new SharedPrefManager(getContext());
        Log.i(TAG, "onCreateView: ");
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh1);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuffle();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
*/
        uploadIm=view.findViewById(R.id.uploadImage);
        uploadIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Upload.class));
            }
        });

        mDashBoardListItems = new ArrayList<>();
        dashBoardListItemsAdapter = new DashBoardListItemsAdapter(getActivity(), mDashBoardListItems);
        recyclerView = view.findViewById(R.id.rvCategory);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        ((GridLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dashBoardListItemsAdapter);

        return view;
    }

 /*   @Override
    public void onStart() {
        super.onStart();
        dashBoardListItemsAdapter.notifyDataSetChanged();
    }*/
 public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

     private int spanCount;
     private int spacing;
     private boolean includeEdge;

     public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
         this.spanCount = spanCount;
         this.spacing = spacing;
         this.includeEdge = includeEdge;
     }}

    private void shuffle() {
        Collections.shuffle(mDashBoardListItems, new Random(System.currentTimeMillis()));
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, mDashBoardListItems);
        recyclerView.setAdapter(dashBoardListItemsAdapter);}

    private ArrayList<DashBoardListItems> getListItem() {
        progressBar.setVisibility(View.VISIBLE);

        HashMap<String, String> hashMap = new HashMap<>();
        JSONObject parameters = new JSONObject(hashMap);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                UrlLinks.PRODUCTS_URL,
                parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d("TAG", "onResponse: " + response);
                        try {
                            if (response.getString("success").contains("1")) {
                                mDashBoardListItems.clear();
                                try {
                                    JSONArray jsonArray = response.getJSONArray("Data");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        DashBoardListItems model = new DashBoardListItems(
                                                object.getInt("id"),
                                                object.getString("product_name"),
                                                object.getString("slug"),
                                                object.getString("image"),
                                                object.getInt("mrp"),
                                                object.getInt("current_stock")
                                        );
                                        mDashBoardListItems.add(model);
                                    }
                                } catch (JSONException e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    e.printStackTrace();
                                }
                                dashBoardListItemsAdapter = new DashBoardListItemsAdapter(getContext(), mDashBoardListItems);
                                recyclerView.setAdapter(dashBoardListItemsAdapter);
                            } else {
                            }
                        } catch (JSONException e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "onErrorResponse: " + error);
            }
        });

        RetryPolicy policy = new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonRequest.setRetryPolicy(policy);
        jsonRequest.setShouldCache(false);
        AppController.getInstance(getContext()).addToRequest(jsonRequest);

        return mDashBoardListItems;
    }

    private void init() {
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);

        mPager = view.findViewById(R.id.pager);
        mPager.setAdapter(new ViewPagerDashBoardAdapter(getActivity(), XMENArray));
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4500, 4000);
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
