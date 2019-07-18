package com.wpes.worldofmedicine.model;

public class    DashBoardListItems {


    private String product_name,slug,image;
    private int product_id,price,stock;



    public DashBoardListItems(int product_id, String product_name, String slug, String image, int price, int stock) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.slug = slug;
        this.image = image;
        this.price = price;
        this.stock = stock;

    }

    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    @Override
    public String toString() {
        return "DashBoardListItems{" +
                "product_name='" + product_name + '\'' +
                ", slug='" + slug + '\'' +
                ", image='" + image + '\'' +
                ", product_id=" + product_id +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
