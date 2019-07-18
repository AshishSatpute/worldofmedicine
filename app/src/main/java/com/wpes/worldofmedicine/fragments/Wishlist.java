package com.wpes.worldofmedicine.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.adapter.WishlistItemsAdapter;
import com.wpes.worldofmedicine.model.WishlistItemsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Wishlist extends Fragment {

    RecyclerView rvWishlist;
    WishlistItemsAdapter wishlistItemsAdapter;
    ArrayList<WishlistItemsModel> wishlistItemsModelsArrayList = new ArrayList<WishlistItemsModel>();
    int CartSize;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public Wishlist() {
        // Required empty public constructor
    }

    public static Wishlist newInstance(String param1, String param2) {
        Wishlist fragment = new Wishlist();
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
        getActivity().setTitle("Wishlist");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        mSwipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        rvWishlist = (RecyclerView) view.findViewById(R.id.rvWishlist);


        rvWishlist.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvWishlist.setLayoutManager(layoutManager);
        rvWishlist.setItemAnimator(new DefaultItemAnimator());
        wishlistItemsAdapter = new WishlistItemsAdapter(getContext(), listOfWishlist());
        rvWishlist.setAdapter(wishlistItemsAdapter);




        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuffle();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }
    private void shuffle() {
        Collections.shuffle(wishlistItemsModelsArrayList, new Random(System.currentTimeMillis()));
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, wishlistItemsModelsArrayList);
        rvWishlist.setAdapter(wishlistItemsAdapter);
    }

    private ArrayList<WishlistItemsModel> listOfWishlist() {

        WishlistItemsModel wishlistItemsModel = new WishlistItemsModel(
                R.drawable.product1,
                "Zyrtec",
                "199",
                "Syrup"
        );
        wishlistItemsModelsArrayList.add(wishlistItemsModel);


        wishlistItemsModel = new WishlistItemsModel(
                R.drawable.product3,
                "Baby And Me",
                "399",
                "Powder"
        );
        wishlistItemsModelsArrayList.add(wishlistItemsModel);
        return wishlistItemsModelsArrayList;
    }
}
