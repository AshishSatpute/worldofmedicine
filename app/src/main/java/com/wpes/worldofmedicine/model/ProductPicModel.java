package com.wpes.worldofmedicine.model;

public class ProductPicModel {
    int product_pic;

    public ProductPicModel() {
    }

    public ProductPicModel(int product_pic) {
        this.product_pic = product_pic;
    }

    public int getProduct_pic() {
        return product_pic;
    }

    public void setProduct_pic(int product_pic) {
        this.product_pic = product_pic;
    }


    @Override
    public String toString() {
        return "ProductPicModel{" +
                "product_pic=" + product_pic +
                '}';
    }
}
