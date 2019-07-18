package com.wpes.worldofmedicine.model;

public class dealsoftheday {
    int icon;
    String name;

    public dealsoftheday() {
    }
    @Override
    public String toString() {
        return "dealsoftheday{" +
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

    public dealsoftheday(int icon, String name) {
        this.icon = icon;
        this.name = name;
}
}
