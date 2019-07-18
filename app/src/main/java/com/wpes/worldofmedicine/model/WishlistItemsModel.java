package com.wpes.worldofmedicine.model;

public class WishlistItemsModel {
    private int urlPhoto;
    private String product_name;
   // private String rating;
    private String price;
    private String pType;


    public WishlistItemsModel() {
    }

    public WishlistItemsModel(int urlPhoto, String product_name, String price, String pType) {
        this.urlPhoto = urlPhoto;
        this.product_name = product_name;
       // this.rating = rating;
        this.price = price;
        this.pType = pType;
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

/*    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }*/

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }


    @Override
    public String toString() {
        return "WishlistItemsModel{" +
                "urlPhoto=" + urlPhoto +
                ", product_name='" + product_name + '\'' +
                ", price='" + price + '\'' +
                ", pType='" + pType + '\'' +
                '}';
    }
}
