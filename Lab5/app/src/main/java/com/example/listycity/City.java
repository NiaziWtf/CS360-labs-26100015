package com.example.listycity;

import java.io.Serializable;

public class City implements Serializable {
    private String cityName;
    private String provinceName;

    public City() {
        // Firestore requires a no-argument constructor
    }

    public City(String city, String province) {
        this.cityName = city;
        this.provinceName = province;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return this.cityName + ", " + this.provinceName;
    }
}
