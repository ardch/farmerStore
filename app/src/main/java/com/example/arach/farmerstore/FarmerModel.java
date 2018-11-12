package com.example.arach.farmerstore;

public class FarmerModel {
    private String farmName, farmDescription, farmerNumber, memberID;
    private Integer farmSellProduct, farmRating;
    public void FarmerModel(){}
    public void FarmerModel(String farmName, String farmDescription, String farmerNumber,
                            String memberID, Integer farmSellProduct, Integer farmRating){
        this.farmName = farmName;
        this.farmDescription = farmDescription;
        this.farmerNumber = farmerNumber;
        this.memberID = memberID;
        this.farmSellProduct = farmSellProduct;
        this.farmRating = farmRating;
    }
    public Integer getRating (){
        return farmRating;
    }
    public Integer getSellProduct(){
        return farmSellProduct;
    }
}
