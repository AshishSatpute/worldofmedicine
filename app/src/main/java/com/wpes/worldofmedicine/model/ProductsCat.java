package com.wpes.worldofmedicine.model;

public class ProductsCat {

    String proName,proSlug,image;
    int proId,proStock;

    public ProductsCat(String proName, String proSlug, String image, int proId, int proStock) {
        this.proName = proName;
        this.proSlug = proSlug;
        this.image = image;
        this.proId = proId;
        this.proStock = proStock;
    }



    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProSlug() {
        return proSlug;
    }

    public void setProSlug(String proSlug) {
        this.proSlug = proSlug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getProStock() {
        return proStock;
    }

    public void setProStock(int proStock) {
        this.proStock = proStock;
    }

    @Override
    public String toString() {
        return "ProductsCat{" +
                "proName='" + proName + '\'' +
                ", proSlug='" + proSlug + '\'' +
                ", image='" + image + '\'' +
                ", proId=" + proId +
                ", proStock=" + proStock +
                '}';
    }
}
