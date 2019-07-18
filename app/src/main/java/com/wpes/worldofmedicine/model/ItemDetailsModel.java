package com.wpes.worldofmedicine.model;

import java.util.ArrayList;

public class ItemDetailsModel {
    private int urlPhoto;
    private String product_name;
    private String rating;
    private String price;
    private String pType;
    private ArrayList<String> productPics = new ArrayList<String>();
    private  int pQty;


    public ItemDetailsModel() {
    }

    public ItemDetailsModel(int urlPhoto, String product_name, String rating, String price, String pType) {
        this.urlPhoto = urlPhoto;
        this.product_name = product_name;
        this.rating = rating;
        this.price = price;
        this.pType = pType;
    }


    public ItemDetailsModel(int urlPhoto, String product_name, String price, int pQty) {
        this.urlPhoto = urlPhoto;
        this.product_name = product_name;
        this.price = price;
        this.pQty = pQty;
    }


    public ItemDetailsModel(int urlPhoto, String product_name, String rating, String price, ArrayList<String> productPics) {
        this.urlPhoto = urlPhoto;
        this.product_name = product_name;
        this.rating = rating;
        this.price = price;
        this.productPics = productPics;
    }

    public ItemDetailsModel(int urlPhoto, String product_name, String price) {
        this.urlPhoto = urlPhoto;
        this.product_name = product_name;
        this.price = price;
    }


    public int getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(int urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<String> getProductPics() {
        return productPics;
    }

    public void setProductPics(ArrayList<String> productPics) {
        this.productPics = productPics;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public int getpQty(Object itemAtPosition) {
        return pQty;
    }

    public void setpQty(int pQty) {
        this.pQty = pQty;
    }

    @Override
    public String toString() {
        return "ItemDetailsModel{" +
                "urlPhoto=" + urlPhoto +
                ", product_name='" + product_name + '\'' +
                ", rating='" + rating + '\'' +
                ", price='" + price + '\'' +
                ", pType='" + pType + '\'' +
                ", productPics=" + productPics +
                ", pQty=" + pQty +
                '}';
    }
}
