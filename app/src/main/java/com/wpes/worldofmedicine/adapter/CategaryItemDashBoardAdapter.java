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

import com.wpes.worldofmedicine.R;
import com.wpes.worldofmedicine.drawerclasses.DrawerCategories;
import com.wpes.worldofmedicine.model.CategoriesModal;

import java.util.ArrayList;

public class CategaryItemDashBoardAdapter extends RecyclerView.Adapter<CategaryItemDashBoardAdapter.CategaryViewHolder> {

    String categoryType;

    private Context mContext;
    private ArrayList<CategoriesModal> mCategoryArrayList = new ArrayList<>();



    public CategaryItemDashBoardAdapter(Context mContext, ArrayList<CategoriesModal> mCategoryArrayList) {
        this.mContext = mContext;
        this.mCategoryArrayList = mCategoryArrayList;
    }

    @NonNull
    @Override
    public CategaryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, viewGroup, false);
        return new CategaryViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull CategaryViewHolder holder, int i) {
        holder.setIsRecyclable(false);
        final CategoriesModal category = mCategoryArrayList.get(i);
        holder.title.setText(category.getName());
        holder.icon.setImageDrawable(mContext.getDrawable(category.getIcon()));
        //  Glide.with(mContext).load("http://goo.gl/gEgYUd").into(holder.icon);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryType = category.getName();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryArrayList.size();
    }

    public class CategaryViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView title;
        private View view;


        public CategaryViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            icon = (ImageView) view.findViewById(R.id.ivItem1);
            title = (TextView) view.findViewById(R.id.categoryTitle);
        }
    }
}
