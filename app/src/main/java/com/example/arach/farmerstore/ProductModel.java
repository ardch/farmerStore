package com.example.arach.farmerstore;

public class ProductModel {
    String farmID, productName, fruitName, unit, date;
    int quantity, price;
    public ProductModel(){}
    public ProductModel(String farmID, String fruitName, String productName, int price,
                        int quantity, String unit){
        this.productName = productName;
        this.farmID = farmID;
        this.fruitName = fruitName;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
    }
    public ProductModel(String farmID, String productName, String fruitName, int price,
                        int quantity, String unit, String date){
        this.productName = productName;
        this.farmID = farmID;
        this.fruitName = fruitName;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.date = date;
    }
    //public String getPName() { return productName; }
    //public String getFName(){return fruitName;}
}
