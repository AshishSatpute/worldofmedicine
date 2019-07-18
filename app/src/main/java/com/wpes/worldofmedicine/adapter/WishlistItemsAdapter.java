package com.wpes.worldofmedicine.adapter;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.model.WishlistItemsModel;

import java.util.ArrayList;

public class WishlistItemsAdapter extends RecyclerView.Adapter<WishlistItemsAdapter.MyAdatper> {

    private Context context;
    private ArrayList<WishlistItemsModel> mWishlistItemsModels = new ArrayList<WishlistItemsModel>();

    private static int currentSelectedIndex = -1;

    public WishlistItemsAdapter(Context context, ArrayList<WishlistItemsModel> wishlistItemsModels) {
        this.context = context;
        this.mWishlistItemsModels = wishlistItemsModels;
    }

    @NonNull
    @Override
    public MyAdatper onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wishlist, viewGroup, false);
        return new MyAdatper(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull WishlistItemsAdapter.MyAdatper myAdatper, final int position) {
        final WishlistItemsModel wishlistItemsModel = mWishlistItemsModels.get(position);
        myAdatper.ivItemPic1.setImageDrawable(context.getDrawable(wishlistItemsModel.getUrlPhoto()));
        myAdatper.tvProductName.setText(wishlistItemsModel.getProduct_name());
        //myAdatper.tvItemTyle1.setText(wishlistItemsModel.getpType());
       // myAdatper.tvRating1.setText(wishlistItemsModel.getRating());
        myAdatper.tvRepees1.setText(wishlistItemsModel.getPrice());


        myAdatper.getAdapterPosition();

        myAdatper.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 mWishlistItemsModels.get(position);

                mWishlistItemsModels.remove(position);
            }
        });

        myAdatper.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + wishlistItemsModel, Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public int getItemCount() {
        return mWishlistItemsModels.size();
    }

    public class MyAdatper extends RecyclerView.ViewHolder {
        ImageView ivItemPic1, ivDelete;
        TextView tvProductName, tvRating1, tvStar1, tvRepees1, tvDiscoun;
        View view;
        public MyAdatper(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvProductName = (TextView) view.findViewById(R.id.tvProductName);
           // tvItemTyle1 = (TextView) view.findViewById(R.id.tvItemTyle1);
           // tvRating1 = view.findViewById(R.id.tvRating1);
            tvStar1 = view.findViewById(R.id.tvStar1);
            tvRepees1 = view.findViewById(R.id.tvRepees1);
            tvDiscoun = view.findViewById(R.id.tvDiscoun);
            ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            ivItemPic1 = (ImageView) view.findViewById(R.id.ivItemPic1);
        }
    }

}
