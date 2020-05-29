package com.example.easyshop.service;

import com.example.easyshop.entity.Category;

public class ProductData {

    public Long getId;
    private String name;
    private double price;
    private String description;
    private int rate;
    private CategoryData categoryData;

    public ProductData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public CategoryData getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(CategoryData categoryData) {
        this.categoryData = categoryData;
    }

    public Long getGetId() {
        return getId;
    }

    public void setGetId(Long getId) {
        this.getId = getId;
    }
}
