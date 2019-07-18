package com.wpes.worldofmedicine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.model.ProductPicModel;

import java.util.ArrayList;

public class ProductsPicAdapter extends RecyclerView.Adapter<ProductsPicAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProductPicModel> productPicModels = new ArrayList<ProductPicModel>();


    public ProductsPicAdapter(Context context, ArrayList<ProductPicModel> productPicModels) {
        this.context = context;
        this.productPicModels = productPicModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int possition) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_pic, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsPicAdapter.ViewHolder viewHolder, int possition) {
        viewHolder.setIsRecyclable(false);
        final ProductPicModel productPicModel = productPicModels.get(possition);
      //  viewHolder.ivProduct.setImageResource(R.drawable.ur);
    }

    @Override
    public int getItemCount() {
        return productPicModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProduct);
        }
    }
}
