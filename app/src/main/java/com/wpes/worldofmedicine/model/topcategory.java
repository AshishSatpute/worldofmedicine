package com.wpes.worldofmedicine.model;

public class topcategory {

   private String name;

    public topcategory(String name) {
        this.name = name;
    }



    public topcategory() {

    }


    @Override
    public String toString() {
        return "CategoriesModal{" +
                ", name='" + name + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public topcategory(int icon, String name) {

        this.name = name;
    }
}
