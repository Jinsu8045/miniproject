package com.multi.miniproject.member.model.dto;

public class CarDto {
//    CREATE TABLE CARS(
//    CAR_NUM VARCHAR2(100) PRIMARY KEY,
//    CAR_NAME VARCHAR2(100) NOT NULL,
//    CAR_CATEGORY VARCHAR2(100) NOT NULL,
//    CAR_FEATURE VARCHAR2(100) NOT NULL,
//    CAR_PREF_COMFORT NUMBER NOT NULL,
//    CAR_PREF_WEIGHT NUMBER NOT NULL,
//    CAR_PREF_PASSENGER NUMBER NOT NULL
//            );

    private String carNum;
    private String carName;
    private String carCategory;
    private String carFeature;
    private int carPrefComfort;
    private int carPrefWeight;
    private int carPrefPassenger;


    public CarDto(){}

    public CarDto(String carNum, String carName, String carCategory, String carFeature, int carPrefComfort, int carPrefWeight, int carPrefPassenger) {
        this.carNum = carNum;
        this.carName = carName;
        this.carCategory = carCategory;
        this.carFeature = carFeature;
        this.carPrefComfort = carPrefComfort;
        this.carPrefWeight = carPrefWeight;
        this.carPrefPassenger = carPrefPassenger;
    }


    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public String getCarFeature() {
        return carFeature;
    }

    public void setCarFeature(String carFeature) {
        this.carFeature = carFeature;
    }

    public int getCarPrefComfort() {
        return carPrefComfort;
    }

    public void setCarPrefComfort(int carPrefComfort) {
        this.carPrefComfort = carPrefComfort;
    }

    public int getCarPrefWeight() {
        return carPrefWeight;
    }

    public void setCarPrefWeight(int carPrefWeight) {
        this.carPrefWeight = carPrefWeight;
    }

    public int getCarPrefPassenger() {
        return carPrefPassenger;
    }

    public void setCarPrefPassenger(int carPrefPassenger) {
        this.carPrefPassenger = carPrefPassenger;
    }


    @Override
    public String toString() {
        return "CarDto{" +
                "carNum='" + carNum + '\'' +
                ", carName='" + carName + '\'' +
                ", carCategory='" + carCategory + '\'' +
                ", carFeature='" + carFeature + '\'' +
                ", carPrefComfort=" + carPrefComfort +
                ", carPrefWeight=" + carPrefWeight +
                ", carPrefPassenger=" + carPrefPassenger +
                '}';
    }
}

