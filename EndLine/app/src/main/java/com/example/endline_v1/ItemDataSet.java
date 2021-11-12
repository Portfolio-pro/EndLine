package com.example.endline_v1;

public class ItemDataSet {
    private String UID, barcode, name, category, brand, add_date, buy_date, end_date, use, photo_url;

    public ItemDataSet(){}

    public ItemDataSet(String barcode, String name, String category, String brand,
                       String add_date, String buy_date, String end_date, String use, String photo_url) {
        this.barcode= barcode;
        this.name= name;
        this.category= category;
        this.brand= brand;
        this.add_date= add_date;
        this.buy_date= buy_date;
        this.end_date= end_date;
        this.use = use;
        this.photo_url= photo_url;
    }

    // 리사이클러뷰 어댑터
    public ItemDataSet(String name, String category, String buy_date, String end_date, String photo_url) {
        this.name= name;
        this.category= category;
        this.buy_date= buy_date;
        this.end_date= end_date;
        this.photo_url= photo_url;
    }

    // 상품 > 바코드, 제품명, 제조사, 이미지 url
    public ItemDataSet(String barcode, String name, String brand, String photo_url) {
        this.barcode = barcode;
        this.name = name;
        this.brand = brand;
        this.photo_url= photo_url;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(String buy_date) {
        this.buy_date = buy_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
