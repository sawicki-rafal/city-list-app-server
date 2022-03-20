package com.sawraf.citylist.city.service;

import com.sawraf.citylist.city.dto.CityDTO;
import com.sawraf.citylist.city.dto.CityUpdateDTO;
import com.sawraf.citylist.city.entity.City;
import com.sawraf.citylist.city.mapper.CityMapper;
import com.sawraf.citylist.city.repository.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for requesting {@link com.sawraf.citylist.city.entity.City}
 */
@Service
@Transactional
public class CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public Page<CityDTO> getAll(Pageable paging) {
        final Page<City> cities = cityRepository.findAll(paging);
        return cities.map(cityMapper::mapToDto);
    }

    public List<CityDTO> findByNameStartingWith(final String prefix) {
        return cityMapper.mapToDto(cityRepository.findByNameStartingWith(prefix));
    }

    public CityDTO update(Long id, CityUpdateDTO newCity) {
        final Optional<City> oldCity = cityRepository.findById(id);
        final City city = oldCity.get();
        city.setName(newCity.getName());
        city.setPhotoUrl(newCity.getPhotoUrl());
        return cityMapper.mapToDto(cityRepository.save(city));
    }
}
