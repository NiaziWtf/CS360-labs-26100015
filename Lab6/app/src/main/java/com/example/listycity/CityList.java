package com.example.listycity;

import java.util.ArrayList;
import java.util.List;

public class CityList {
    private List<City> cities = new ArrayList<>();

    /**
     * This method adds a city to the list
     * @param city
     *      This is a candidate city to add
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * This method returns a sorted list of cities
     * @return
     *      Return the sorted list
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * This method returns a boolean indicating if the city exists in the list
     * @param city
     *      This is the city to check for
     * @return
     *      Return true if the city exists, false otherwise
     */
    public boolean hasCity(City city) {
        return cities.contains(city);
    }

    /**
     * This method deletes a city from the list
     * @param city
     *      This is the city to delete
     * @throws IllegalArgumentException
     *      If the city does not exist in the list
     */
    public void delete(City city) {
        if (!cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.remove(city);
    }

    /**
     * This method returns the number of cities in the list
     * @return
     *      Return the number of cities
     */
    public int countCities() {
        return cities.size();
    }
}
