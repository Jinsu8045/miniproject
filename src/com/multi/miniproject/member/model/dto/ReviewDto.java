package com.multi.miniproject.member.model.dto;

public class ReviewDto {
//    CREATE TABLE REVIEWS(
//            REVIEW_NUM VARCHAR2(100) PRIMARY KEY,
//            ORDER_NUM VARCHAR2(100) REFERENCES ORDERS(ORDER_NUM),
//
//            --   	SELECT CAR_NAME FROM ORDERS //보완필요할지도?
//
//    RATING NUMBER NOT NULL,
//    TITLE VARCHAR2(100) NOT NULL,
//    CONTENTS VARCHAR2(1000) NOT NULL
//    );

    private String reviewNum;
    private String orderNum;
    private int rating;
    private String title;
    private String contents;

    public ReviewDto() {
    }

    public ReviewDto(String reviewNum, String orderNum, int rating, String title, String contents) {
        this.reviewNum = reviewNum;
        this.orderNum = orderNum;
        this.rating = rating;
        this.title = title;
        this.contents = contents;
    }

    public String getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(String reviewNum) {
        this.reviewNum = reviewNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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
                "reviewNum='" + reviewNum + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
