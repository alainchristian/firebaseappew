package com.example.firebaseappd;

public class Product {
    String prodName;
    float prodPrice;
    String prodDescr;
    int prdImage;


    public Product(String prodName,int prdImage,float prodPrice,String prodDescr) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodDescr = prodDescr;
        this.prdImage = prdImage;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public float getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(float prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdDescr() {
        return prodDescr;
    }

    public void setProdDescr(String prodDescr) {
        this.prodDescr = prodDescr;
    }

    public int getPrdImage() {
        return prdImage;
    }

    public void setPrdImage(int prdImage) {
        this.prdImage = prdImage;
    }
}
