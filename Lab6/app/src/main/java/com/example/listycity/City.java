package com.example.listycity;

import java.util.Objects;

/**
 * This is a class that keeps track of a city
 */
public class City {
    private String name;
    private String province;

    /**
     * This is a constructor for the City class
     * @param name
     *      This is the name of the city
     * @param province
     *      This is the name of the province
     */
    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    /**
     * This returns the name of the city
     * @return
     *      Return the name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * This sets the name of the city
     * @param name
     *      This is the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This returns the name of the province
     * @return
     *      Return the name of the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * This sets the name of the province
     * @param province
     *      This is the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name) &&
                Objects.equals(province, city.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, province);
    }
}
