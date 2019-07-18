package com.wpes.worldofmedicine.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.TextView;


import com.wpes.worldofmedicine.MainActivity;
import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.adapter.ItemDetailsAdapter;
import com.wpes.worldofmedicine.model.ItemDetailsModel;
import com.wpes.worldofmedicine.utils.AppController;

import java.util.ArrayList;


public class ItemDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RecyclerView recyclerView, rvItems;
    public static final String TAG = ItemDetails.class.getCanonicalName();
    ItemDetailsAdapter itemDetailsAdapter;
    ItemDetailsModel itemDetailsModel;
    Context context;
    Intent from;
    AppController ct;
    CardView cvAddToCart, cvBuyNow;
    String pic, pName, pRating, pRrice,pid;
    private ArrayList<ItemDetailsModel> itemDetailsModels = new ArrayList<>();
    private ArrayList<String> productPics = new ArrayList<>();
    //ProductsPicAdapter productsPicAdapter;
    TextView tvAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        context = ItemDetails.this;
        cvAddToCart = findViewById(R.id.cvAddToCart);
        cvBuyNow = findViewById(R.id.cvBuyNow);
        tvAddToCart = findViewById(R.id.tvAddToCart);
        ct=  (AppController) getApplicationContext();

      Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.i(TAG, "onCreate: extras " + extras.get("product_name"));
            pName = extras.getString("product_name");
            pid = extras.getString("id");
            pRrice = extras.getString("mrp");
        }

      /*  // inner recyclerview
        rvItems = findViewById(R.id.rvSideAngleItemPic);
        rvItems.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvItems.setLayoutManager(layoutManager);
        rvItems.setItemAnimator(new DefaultItemAnimator());
        itemDetailsAdapter = new ItemDetailsAdapter(ItemDetails.this, itemDetailsModels);
        rvItems.setAdapter(itemDetailsAdapter);
*/
        //main outer Recycler view
        recyclerView = findViewById(R.id.rvItemDetails);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        itemDetailsAdapter = new ItemDetailsAdapter(ItemDetails.this, getItemsList());
        recyclerView.setAdapter(itemDetailsAdapter);

        cvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ct.getCart().setProducts(itemDetailsModel);
                from = new Intent(context, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("from", "ItemDetails");
                from.putExtras(bundle);
                startActivity(from);
                finish();
            }
        });

        cvBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,Delivery.class));
            }
        });
        for (int i = 0; i < itemDetailsModels.size(); i++) {
          /*  products = new AddToCartModel(R.drawable.ur , "product1 ","100");
              ct.setProducts(products);
            */
            itemDetailsModel = new ItemDetailsModel(R.drawable.product12, itemDetailsModel.getProduct_name(), itemDetailsModel.getPrice());
            ct.setProducts(itemDetailsModel);

        /*
            for (int j = 0; j < itemDetailsModels.size(); j++) {
                if (itemDetailsModels.get(i).getPrice().contentEquals(itemDetailsModels.get(j).getPrice())) {
                    Toast.makeText(this, "On?", Toast.LENGTH_SHORT).show();
                }
            }*/


        }

    }
    private ArrayList<ItemDetailsModel> getItemsList() {
        itemDetailsModels.clear();
        itemDetailsModel = new ItemDetailsModel(
                R.drawable.product1,
                pName,
                pRating,
                pRrice,
                productPics
        );
        itemDetailsModels.add(itemDetailsModel);
        return itemDetailsModels;
    }


   /* public Controller ct() {
        return (Controller) getApplicationContext();
    }*/


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        itemDetailsAdapter.notifyDataSetChanged();
    }
}