package com.example.easyshop.service;

public class ProductInstanceData {
    private long id;
    private long enterDate;
    private long purchaseDate;
    private long expireDate;
    private ProductData productData;

    public ProductInstanceData() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(long enterDate) {
        this.enterDate = enterDate;
    }

    public long getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(long purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }

    public ProductData getProductData() {
        return productData;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
    }
}
