package com.example.myapplication3;

public class Products {
    private String name, endDate, addDate, kind, img;

    public Products(){}

    public Products(String name, String endDate, String addDate, String kind, String img) {
        this.name = name;
        this.endDate = endDate;
        this.addDate = addDate;
        this.kind = kind;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
