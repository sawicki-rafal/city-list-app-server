package com.sawraf.citylist.city.controller;

import com.sawraf.citylist.city.entity.City;
import com.sawraf.citylist.city.service.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


/**
 * Unit test class for {@link CityController}
 */
@ExtendWith(MockitoExtension.class)
class CityControllerTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @Test
    void testDelegateGetAll() {
        cityController.getAll();
        verify(cityService).getAll();
    }

    @Test
    void testDelegateGetCityByNameStartingWith() {
        final String prefixToBeSearched = "TEST_PREFIX";
        cityController.getCityByNameStartingWith(prefixToBeSearched);
        verify(cityService).findByNameStartingWith(prefixToBeSearched);
    }

    @Test
    void testDelegateUpdateCity() {
        final Long id = 1L;
        final City city = new City();
        cityController.updateCity(id, city);
        verify(cityService).update(id, city);
    }
}