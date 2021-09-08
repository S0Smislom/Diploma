package com.market.marketplace.models;

public class CommentRequest {
    private Long id;
    private Long userID;
    private Long productID;
    private String text;
    private float rate;

    public CommentRequest() {
    }

    public CommentRequest(Long id, Long userID, Long productID, String text, float rate) {
        this.id = id;
        this.userID = userID;
        this.productID = productID;
        this.text = text;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
