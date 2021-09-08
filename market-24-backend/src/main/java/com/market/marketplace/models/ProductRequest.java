package com.market.marketplace.models;

public class ProductRequest {
    private Long id;
    private String name;
    private float price;
    private int count;
    private String imgUrl;
    private String description;
    private float rating;
    private Long seller;
    private String city;
    private String region;

    public ProductRequest(Long id, String name, float price, int count, String imgUrl, String description, float rating, Long seller, String city, String region) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
        this.description = description;
        this.rating = rating;
        this.seller = seller;
        this.city = city;
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ProductRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }
}
