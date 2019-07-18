package com.wpes.worldofmedicine.model;


import java.util.ArrayList;

public class ModelCart {

    public static final String TAG = ModelCart.class.getCanonicalName();

    private ArrayList<ItemDetailsModel> cartProducts = new ArrayList<ItemDetailsModel>();

    public ItemDetailsModel getProducts(int position) {
        return cartProducts.get(position);
    }

    public void setProducts(ItemDetailsModel products) {
        cartProducts.add(products);
    }

    public int getCartsize() {
        return cartProducts.size();
    }

    public boolean checkProductInCart(ItemDetailsModel aproducts) {
        return cartProducts.contains(aproducts);
    }


}
