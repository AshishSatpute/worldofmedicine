package com.wpes.worldofmedicine.utils;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wpes.worldofmedicine.model.ModelCart;
import com.wpes.worldofmedicine.model.ItemDetailsModel;

import java.util.ArrayList;

public class AppController extends Application {

    private RequestQueue requestQueue;
    private static AppController myInstance;
    private static Context mCtx;

    public AppController() {
    }
    private AppController(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized AppController getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new AppController(context);
        }
        return myInstance;
    }
    public <T> void addToRequest(Request<T> request) {
        requestQueue.add(request);
    }
    public static final String TAG = AppController.class.getCanonicalName();
    ArrayList<ItemDetailsModel> myProduct = new ArrayList<ItemDetailsModel>();
    private ModelCart myCart = new ModelCart();


    ItemDetailsModel getProducts(int position) {
        return myProduct.get(position);
    }

    public void setProducts(ItemDetailsModel products) {
        myProduct.add(products);
    }

    public ModelCart getCart() {
        return myCart;
    }

    public int getProductArrayListsize() {
        return myProduct.size();
    }
}
