package com.sawraf.citylist.city.controller;

import com.sawraf.citylist.city.dto.CityUpdateDTO;
import com.sawraf.citylist.city.service.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

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
        final PageRequest pageRequest = PageRequest.of(0, 8);
        cityController.getAll(pageRequest);
        verify(cityService).getAll(pageRequest);
    }

    @Test
    void testDelegateGetCityByNameStartingWith() {
        final PageRequest pageRequest = PageRequest.of(0, 8);
        final String prefixToBeSearched = "TEST_PREFIX";
        cityController.getCitiesByNameStartingWith(prefixToBeSearched,pageRequest);
        verify(cityService).findByNameStartingWith(prefixToBeSearched,pageRequest);
    }

    @Test
    void testDelegateUpdateCity() {
        final Long id = 1L;
        final CityUpdateDTO cityUpdateDTO = new CityUpdateDTO();
        cityController.updateCity(id, cityUpdateDTO);
        verify(cityService).update(id, cityUpdateDTO);
    }
}