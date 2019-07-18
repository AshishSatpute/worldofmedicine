package com.wpes.worldofmedicine.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wpes.worldofmedicine.MainActivity;
import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.adapter.AddToCartAdapter;
import com.wpes.worldofmedicine.model.ItemDetailsModel;
import com.wpes.worldofmedicine.utils.AppController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class Cart extends Fragment {

    private RecyclerView recyclerView;
    private BottomSheetBehavior sheetBehavior;
    private LinearLayout layoutBottomSheet;
    private Button btnProcess;
    private AddToCartAdapter addToCartAdapter;
    private ImageView arrow;
    private TextView tvTotalPrice, tvTotalPriceOfterDelivery, tvprice;
    private int CartSize, deviveryCharges = 50;
    private String pic, pName, pRating, pRrice, price;
    private ArrayList<ItemDetailsModel> mItemDetailsModels = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    AppController ct;

    public Cart() {

    }
    public static Cart newInstance(String param1, String param2) {
        Cart fragment = new Cart();
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
        getActivity().setTitle("Cart");

        View view =inflater.inflate(R.layout.fragment_cart, container, false);
        layoutBottomSheet = view.findViewById(R.id.include_bottumSheet);
        arrow = layoutBottomSheet.findViewById(R.id.arrow);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuffle();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        setCartLayout();
        btnProcess = layoutBottomSheet.findViewById(R.id.btnProcess);
        tvTotalPrice = layoutBottomSheet.findViewById(R.id.tvTotalPrice);
        tvTotalPriceOfterDelivery = layoutBottomSheet.findViewById(R.id.tvTotalPriceOfterDelivery);
        tvprice = layoutBottomSheet.findViewById(R.id.tvprice);
        //
        ct = (AppController) Objects.requireNonNull(getActivity()).getApplication();
        CartSize = ct.getCart().getCartsize();

        Bundle extras = (getActivity()).getIntent().getExtras();
        if (extras != null) {
            //  String pic = extras.getString("urlPhoto");
            Log.i("TAG", "onCreate: extras11 " + extras.get("from"));
            pName = extras.getString("product_name1");
            pRating = extras.getString("rating1");
            pRrice = extras.getString("price1");
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.rvItemInCart);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        int total = 0;
        int cartItems = 0;

        for (int i = 0; i < CartSize; i++) {
            mItemDetailsModels.add(ct.getCart().getProducts(i));
            price = ct.getCart().getProducts(i).getPrice();
            int  qty  = mItemDetailsModels.get(i).getpQty(i);

            total = total + Integer.parseInt(price);
            // For Remove
            cartItems = mItemDetailsModels.size();
            addToCartAdapter = new AddToCartAdapter(getContext(), mItemDetailsModels);
            recyclerView.setAdapter(addToCartAdapter);
            addToCartAdapter.notifyDataSetChanged();
        }

        int amountPayable = deviveryCharges + total;
        tvTotalPrice.setText(String.valueOf(total));
        tvTotalPriceOfterDelivery.setText(String.valueOf(amountPayable));
        tvprice.setText("Price " + String.valueOf(cartItems) + " Items");

        layoutBottomSheet.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (sheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN: {
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("TAG", "onSlide: " + slideOffset);
                if (slideOffset == 0.0) {
                    arrow.setImageDrawable(getActivity().getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
                }
                if (slideOffset == 1.0) {
                    arrow.setImageDrawable(getActivity().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
                }
            }
        });

        return view;
    }

    private void shuffle() {
        Collections.shuffle(mItemDetailsModels, new Random(System.currentTimeMillis()));
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, mItemDetailsModels);
        recyclerView.setAdapter(addToCartAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void replaceFragment(Fragment fragment) {
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
    protected void setCartLayout(){

    }
}
