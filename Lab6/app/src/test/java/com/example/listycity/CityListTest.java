package com.example.listycity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CityListTest {

    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    @Test
    void testHasCity() {
        CityList cityList = mockCityList();
        City city = mockCity();
        assertTrue(cityList.hasCity(city));

        City city2 = new City("Regina", "Saskatchewan");
        assertFalse(cityList.hasCity(city2));
    }

    @Test
    void testDelete() {
        CityList cityList = mockCityList();
        City city = mockCity();
        assertEquals(1, cityList.countCities());

        cityList.delete(city);
        assertEquals(0, cityList.countCities());
        assertFalse(cityList.hasCity(city));

        // Test deleting a city that doesn't exist
        City city2 = new City("Regina", "Saskatchewan");
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.delete(city2);
        });
    }

    @Test
    void testCountCities() {
        CityList cityList = new CityList();
        assertEquals(0, cityList.countCities());

        cityList.add(mockCity());
        assertEquals(1, cityList.countCities());

        cityList.add(new City("Regina", "Saskatchewan"));
        assertEquals(2, cityList.countCities());
    }
}
