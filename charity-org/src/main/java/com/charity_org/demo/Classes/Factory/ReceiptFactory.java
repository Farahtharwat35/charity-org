package com.charity_org.demo.Classes.Factory;

public class ReceiptFactory {
    public String createView(String source){
        String returnView;
        switch (source) {
            case "money-type" -> returnView = "MoneyReceipt";
            case "furniture-type" -> returnView = "FurnitureReceipt";
            case "clothes-type" -> returnView = "ClothesReceipt";
            case "blood-type" -> returnView = "BloodReceipt";
            default -> returnView = "error";
        }
        return returnView;
    }
}
