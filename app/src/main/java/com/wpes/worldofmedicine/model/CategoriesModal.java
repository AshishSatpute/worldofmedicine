package com.wpes.worldofmedicine.model;

public class CategoriesModal {
    int icon;
    String name;

    public CategoriesModal() {

    }

    @Override
    public String toString() {
        return "Category{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                '}';
    }
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoriesModal(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }
}