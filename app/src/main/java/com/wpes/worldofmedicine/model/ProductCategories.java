package com.wpes.worldofmedicine.model;

public class ProductCategories {


    private String title;
  //  private String imArrow;

    public ProductCategories(String title) {
        this.title = title;
       // this.imArrow = imArrow;
    }



    @Override
    public String toString() {
        return "ProductCategories{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }



}
