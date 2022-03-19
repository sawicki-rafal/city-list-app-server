package com.sawraf.citylist.city.service;

import com.sawraf.citylist.city.entity.City;
import com.sawraf.citylist.city.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for requesting {@link com.sawraf.citylist.city.entity.City}
 */
@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public List<City> findByNameStartingWith(final String prefix) {
        return cityRepository.findByNameStartingWith(prefix);
    }

    public City update(Long id, City newCity) {

    }
}
