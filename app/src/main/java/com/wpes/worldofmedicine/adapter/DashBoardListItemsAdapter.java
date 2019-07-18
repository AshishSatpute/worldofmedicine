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

import java.util.ArrayList;

public class DashBoardListItemsAdapter extends RecyclerView.Adapter<DashBoardListItemsAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<DashBoardListItems> mDashBoardListItems = new ArrayList<>();

    public DashBoardListItemsAdapter(Context mContext, ArrayList<DashBoardListItems> mDashBoardListItems) {
        this.mContext = mContext;
        this.mDashBoardListItems = mDashBoardListItems; }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_grid_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int positon) {
        final DashBoardListItems itemPosition = mDashBoardListItems.get(positon);

        Picasso.get().load(itemPosition.getImage()).into(myViewHolder.urlPhoto);
        myViewHolder.product_name.setText(itemPosition.getProduct_name());
        myViewHolder.price.setText(String.valueOf(itemPosition.getPrice()));
        myViewHolder.product_id.setText(String.valueOf(itemPosition.getProduct_id()));

        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: product_name");
                Intent intent = new Intent(mContext,ItemDetails.class);
                intent.putExtra("product_name",itemPosition.getProduct_name());
                intent.putExtra("id",itemPosition.getProduct_id());
                intent.putExtra("mrp",itemPosition.getPrice());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDashBoardListItems.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView product_id,product_name,price;
        private ImageView urlPhoto;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            urlPhoto = view.findViewById(R.id.ivItemPic);
            Log.d("TAG", "MyViewHolder: "+urlPhoto);
            product_name = view.findViewById(R.id.tvItemTitle);
            price = view.findViewById(R.id.tvRepees);
            product_id = view.findViewById(R.id.tvProid);
        }
    }
}