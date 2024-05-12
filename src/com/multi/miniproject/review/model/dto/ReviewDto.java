package com.multi.miniproject.review.model.dto;

public class ReviewDto {


    //REVIEW_NUM NUMBER PRIMARY KEY,
    //    ORDER_NUM NUMBER REFERENCES ORDERS(ORDER_NUM),
    //    REVIEW_RATING NUMBER NOT NULL,
    //    REVIEW_NAME VARCHAR2(100) NOT NULL,
    //    REVIEW_CONTENTS VARCHAR2(1000) NOT NULL


    private int orderNum;
    private String rating; //null
    private String title; //null
    private String contents; //null


    public ReviewDto(){}

    public ReviewDto(String rating, String title, String contents) {
        this.rating = rating;
        this.title = title;
        this.contents = contents;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "orderNum=" + orderNum +
                ", rating='" + rating + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
