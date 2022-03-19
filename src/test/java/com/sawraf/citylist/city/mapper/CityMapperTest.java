package com.sawraf.citylist.city.mapper;

import com.sawraf.citylist.city.dto.CityDTO;
import com.sawraf.citylist.city.entity.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Unit test class for {@link CityMapper}
 */
@ExtendWith(MockitoExtension.class)
class CityMapperTest {

    private static City city_1;
    private static City city_2;
    private static City city_3;
    private final CityMapper cityMapper = new CityMapper();

    @BeforeAll
    static void setup() {
        city_1 = new City();
        city_1.setName("city1");
        city_1.setId(1L);
        city_1.setPhotoUrl("photourl1");

        city_2 = new City();
        city_2.setName("city2");
        city_2.setId(2L);
        city_2.setPhotoUrl("photourl2");

        city_3 = new City();
        city_3.setName("city3");
        city_3.setId(3L);
        city_3.setPhotoUrl("photourl3");
    }

    @Test
    void shouldMapSingle() {
        final CityDTO cityDTO = cityMapper.mapToDto(city_1);
        assertThat(cityDTO.getId()).isEqualTo(city_1.getId());
        assertThat(cityDTO.getName()).isEqualTo(city_1.getName());
        assertThat(cityDTO.getPhotoUrl()).isEqualTo(city_1.getPhotoUrl());
    }

    @Test
    void shouldMapCollection() {
        final List<City> cities = new LinkedList<>();
        cities.add(city_1);
        cities.add(city_2);
        cities.add(city_3);

        final List<CityDTO> cityDTOList = cityMapper.mapToDto(cities);

        cityDTOList.forEach(cityDTO -> {
            final List<City> citiesById = cities.stream()
                    .filter(city -> city.getId().equals(cityDTO.getId()))
                    .collect(Collectors.toUnmodifiableList());
            assertThat(citiesById.size()).isEqualTo(1);

            final City city = citiesById.get(0);
            assertThat(city.getName()).isEqualTo(cityDTO.getName());
            assertThat(city.getPhotoUrl()).isEqualTo(cityDTO.getPhotoUrl());
        });

    }

}