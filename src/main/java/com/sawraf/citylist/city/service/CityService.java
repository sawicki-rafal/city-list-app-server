package com.sawraf.citylist.city.service;

import com.sawraf.citylist.city.entity.City;
import com.sawraf.citylist.city.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        final Optional<City> byId = cityRepository.findById(id);
        cityRepository.save(byId.get());
        return byId.get();
    }
}
