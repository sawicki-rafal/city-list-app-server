package com.sawraf.citylist.city.service;

import com.sawraf.citylist.city.dto.CityDTO;
import com.sawraf.citylist.city.dto.CityUpdateDTO;
import com.sawraf.citylist.city.entity.City;
import com.sawraf.citylist.city.mapper.CityMapper;
import com.sawraf.citylist.city.repository.CityRepository;
import com.sawraf.citylist.exception.ApplicationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.sawraf.citylist.exception.message.MessageCode.ERROR_ENTITY_NOT_FOUND;

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

    public Page<CityDTO> findByNameStartingWith(final String prefix,Pageable paging) {
        final Page<City> cities = cityRepository.findByNameStartingWith(prefix,paging);
        return cities.map(cityMapper::mapToDto);
    }

    public CityDTO update(Long id, CityUpdateDTO newCity) {
        final Optional<City> oldCity = cityRepository.findById(id);
        if (oldCity.isPresent()) {
            final City city = oldCity.get();
            city.setName(newCity.getName());
            city.setPhotoUrl(newCity.getPhotoUrl());
            return cityMapper.mapToDto(cityRepository.save(city));
        } else {
            throw new ApplicationException(ERROR_ENTITY_NOT_FOUND, City.class.getName(), id);
        }
    }
}
