package com.wpes.worldofmedicine.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.model.ItemDetailsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.CartViewHolder> implements AdapterView.OnItemSelectedListener {

    private static final String TAG = AddToCartAdapter.class.getCanonicalName();
    private Context mContext;

    private ArrayList<ItemDetailsModel> mItemDetailsModels = new ArrayList<ItemDetailsModel>();

    ItemDetailsModel addToCart;

    public AddToCartAdapter(Context mContext, ArrayList<ItemDetailsModel> mAddToCartModels) {
        this.mContext = mContext;
        this.mItemDetailsModels = mAddToCartModels;
    }
   @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item, viewGroup, false);
        return new CartViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, final int position) {
        addToCart = mItemDetailsModels.get(position);
        cartViewHolder.tvItemName.setText(addToCart.getProduct_name());
        cartViewHolder.tvRepees.setText(addToCart.getPrice());
        cartViewHolder.ivItem.setImageDrawable(mContext.getDrawable(R.drawable.product4));
        cartViewHolder.spinner.setOnItemSelectedListener(this);
        List<String> number = Arrays.asList(mContext.getResources().getStringArray(R.array.number));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, number);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cartViewHolder.spinner.setAdapter(dataAdapter);


        Log.i(TAG, "onBindViewHolder: pos " + addToCart);


        Log.i(TAG, "onBindViewHolder: " + Integer.parseInt(addToCart.getPrice()));


        cartViewHolder.clRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the clicked item label
                deleteCartItem(mItemDetailsModels.get(position));
            }
        });

        cartViewHolder.clSaveForLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Added To Wishlist", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteCartItem(ItemDetailsModel itemDetailsModel) {
        // Remove the item on remove/button click
        mItemDetailsModels.remove(itemDetailsModel);
        notifyDataSetChanged();
        Toast.makeText(mContext, "Removed " + itemDetailsModel.getProduct_name(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return mItemDetailsModels.size();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemSelected: " + parent.getItemAtPosition(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItem;
        TextView tvItemName, tvRepees;
        CardView clSaveForLater, clRemove;
        View view;
        Spinner spinner;
        int qty;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ivItem = (ImageView) view.findViewById(R.id.ivItem);
            tvItemName = (TextView) view.findViewById(R.id.tvItemName);
            tvRepees = (TextView) view.findViewById(R.id.tvRepees);
            clRemove = (CardView) view.findViewById(R.id.clRemove);
            clSaveForLater = (CardView) view.findViewById(R.id.clSaveForLater);
            spinner = (Spinner) view.findViewById(R.id.spNumber);
        }
    }
}
