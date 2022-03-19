package com.sawraf.citylist.city.mapper;

import com.sawraf.citylist.city.dto.CityDTO;
import com.sawraf.citylist.city.entity.City;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps City to CityDTO
 */
@Component
public class CityMapper {

    public CityDTO mapToDto(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setPhotoUrl(city.getPhotoUrl());
        return cityDTO;
    }

    public List<CityDTO> mapToDto(Collection<City> cities) {
        return cities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toUnmodifiableList());
    }

}
