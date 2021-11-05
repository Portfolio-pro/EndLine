package com.example.endline_v1;

public class Products {
    private String UID, barcode, productName, category, brand, addDay, buyDay, endline, use, img;

    public Products(){}

    public Products(String UID, String barcode, String productName, String category, String brand,
                    String addDay, String buyDay, String endline, String use, String img) {
        this.UID = UID;
        this.barcode= barcode;
        this.productName= productName;
        this.category= category;
        this.brand= brand;
        this.addDay= addDay;
        this.buyDay= buyDay;
        this.endline= endline;
        this.use = use;
        this.img= img;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAddDay() {
        return addDay;
    }

    public void setAddDay(String addDay) {
        this.addDay = addDay;
    }

    public String getBuyDay() {
        return buyDay;
    }

    public void setBuyDay(String buyDay) {
        this.buyDay = buyDay;
    }

    public String getEndline() {
        return endline;
    }

    public void setEndline(String endline) {
        this.endline = endline;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
