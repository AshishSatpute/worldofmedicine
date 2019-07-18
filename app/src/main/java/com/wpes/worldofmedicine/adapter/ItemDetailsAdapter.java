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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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


public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.MyViewHolder> implements AdapterView.OnItemSelectedListener {
    private Animation animation;
    public static final String TAG = ItemDetailsAdapter.class.getCanonicalName();
    private Context mContext;
    private ArrayList<ItemDetailsModel> mItemDetailsModels = new ArrayList<>();
    // private ArrayList<ProductPicModel> mProductPicModels = new ArrayList<>();
    ItemDetailsModel itemPosition;

    public ItemDetailsAdapter(Context mContext, ArrayList<ItemDetailsModel> mItemDetailsModels) {
        this.mContext = mContext;
        this.mItemDetailsModels = mItemDetailsModels;
        //ct = (Controller)mContext.getApplicationContext();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_details, viewGroup, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        itemPosition = mItemDetailsModels.get(position);
        myViewHolder.ivMainItemPic.setImageDrawable(mContext.getDrawable(itemPosition.getUrlPhoto()));
        myViewHolder.tvItemName.setText(itemPosition.getProduct_name());
        myViewHolder.tvRepees.setText(itemPosition.getPrice());
        myViewHolder.tvRating.setText(itemPosition.getRating());

        myViewHolder.spinner.setOnItemSelectedListener(this);
        List<String> number = Arrays.asList(mContext.getResources().getStringArray(R.array.number));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, number);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myViewHolder.spinner.setAdapter(dataAdapter);

        myViewHolder.ivAddToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // animation = AnimationUtils.loadAnimation(mContext, R.anim.fade_in);
                Toast.makeText(mContext, "Item Added", Toast.LENGTH_SHORT).show();
                myViewHolder.ivAddToWishlist.setImageDrawable(mContext.getDrawable(R.drawable.iconheart));
               // myViewHolder.ivAddToWishlist.startAnimation(animation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemDetailsModels.size();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemSelected: " + parent.getItemAtPosition(position));
        itemPosition.setpQty(Integer.parseInt(String.valueOf(parent.getItemIdAtPosition(position))));
        Log.i(TAG, "onItemSelected: qqq " + itemPosition.getpQty(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMainItemPic, ivAddToWishlist;
        TextView tvItemName, tvRepees, tvRating,tvProductid;
        CardView cvAddToCart, cvBuyNow;
        private View view;
        RecyclerView rvInner;
        Spinner spinner;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ivMainItemPic = view.findViewById(R.id.ivMainItemPic);
            tvItemName = view.findViewById(R.id.tvItemName);
            tvRepees = view.findViewById(R.id.tvRepees);
            tvRating = view.findViewById(R.id.tvRating);
            tvProductid=view.findViewById(R.id.tvProID);
            spinner = view.findViewById(R.id.spNum);
            cvAddToCart = view.findViewById(R.id.cvAddToCart);
            cvBuyNow = view.findViewById(R.id.cvBuyNow);
            ivAddToWishlist = view.findViewById(R.id.ivAddToWishlist);
            rvInner = view.findViewById(R.id.rvSideAngleItemPic);
        }
    }

}
