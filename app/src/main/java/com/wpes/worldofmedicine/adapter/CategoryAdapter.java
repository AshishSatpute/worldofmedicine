package com.wpes.worldofmedicine.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.activities.ItemDetails;
import com.wpes.worldofmedicine.model.DashBoardListItems;
import com.wpes.worldofmedicine.model.ProductCategories;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder>{


    //this context we will use to inflate the layout
    private Context mCtx;
    //we are storing all the products in a list
    private ArrayList<ProductCategories> productList = new ArrayList<>();

    //getting the context and product list with constructor
    public CategoryAdapter(Context mCtx, ArrayList<ProductCategories> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false);
        return new MyHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        final ProductCategories itemPosition = productList.get(i);
        myHolder.tvName.setText(itemPosition.getTitle());
       // myHolder.tvId.setText(itemPosition.getImArrow());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView tvId;
        private View view;

        public MyHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvName=view.findViewById(R.id.tvCat);
          //  tvId=view.findViewById(R.id.imArrow);
        }
    }
}