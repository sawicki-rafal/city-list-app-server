package com.sawraf.citylist.city.service;

import com.sawraf.citylist.city.entity.City;
import com.sawraf.citylist.city.repository.CityRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test class for {@link CityService}
 */
@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    private static City city_1;

    private static City city_2;

    private static City city_3;

    @BeforeAll
    static void setup() {
        city_1 = new City();
        city_2 = new City();
        city_3 = new City();
    }

    @Test
    void getAllShouldReturnThreeCities() {
        final List<City> cities = new LinkedList<>();
        cities.add(city_1);
        cities.add(city_2);
        cities.add(city_3);
        when(cityRepository.findAll()).thenReturn(cities);
        final List<City> returnedCities = cityService.getAll();
        assertThat(returnedCities).containsExactlyElementsOf(cities);
    }

    @Test
    void getAllShouldReturnOneCity() {
        final List<City> cities = new LinkedList<>();
        cities.add(city_1);
        when(cityRepository.findAll()).thenReturn(cities);
        final List<City> returnedCities = cityService.getAll();
        assertThat(returnedCities).containsExactlyElementsOf(cities);
    }

    @Test
    void getAllShouldReturnNoCity() {
        when(cityRepository.findAll()).thenReturn(Collections.emptyList());
        final List<City> returnedCities = cityService.getAll();
        assertThat(returnedCities).isEmpty();
        verify(cityRepository).findAll();
    }

    @Test
    void findByNameStartingWithShouldReturnNoCity() {
        final String prefixToBeSearched = "LON";
//
//        city_1.setName("Wroclaw");
//        city_1.setName("Krakow");
//        city_1.setName("Lisbon");
//        final List<City> cities = new LinkedList<>();
//        cities.add(city_1);
//        cities.add(city_2);
//        cities.add(city_3);
//
        when(cityRepository.findByNameStartingWith(anyString())).thenReturn(Collections.emptyList());
        final List<City> returnedCities = cityService.findByNameStartingWith(prefixToBeSearched);
        assertThat(returnedCities).isEmpty();
        verify(cityRepository).findByNameStartingWith(prefixToBeSearched);
    }

    @Test
    void updateShouldReturnOriginalCity() {
        final Long cityId = 1L;
        city_1.setId(cityId);
        city_1.setName("WROCLAW");
        city_1.setPhotoUrl("TEST_PHOTO_URL");
        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city_1));
        when(cityRepository.save(city_1)).thenReturn(city_1);

        final City updatedCity = cityService.update(cityId, city_1);

        assertThat(updatedCity.getId()).isEqualTo(city_1.getId());
        assertThat(updatedCity.getName()).isEqualTo(city_1.getName());
        assertThat(updatedCity.getPhotoUrl()).isEqualTo(city_1.getPhotoUrl());

        verify(cityRepository).findById(cityId);
        verify(cityRepository).save(city_1);

    }
}