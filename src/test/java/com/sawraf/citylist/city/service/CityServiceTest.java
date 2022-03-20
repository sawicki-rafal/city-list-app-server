package com.sawraf.citylist.city.service;

import com.sawraf.citylist.city.dto.CityDTO;
import com.sawraf.citylist.city.dto.CityUpdateDTO;
import com.sawraf.citylist.city.entity.City;
import com.sawraf.citylist.city.mapper.CityMapper;
import com.sawraf.citylist.city.repository.CityRepository;
import com.sawraf.citylist.exception.ApplicationException;
import com.sawraf.citylist.exception.message.MessageCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static com.sawraf.citylist.exception.message.MessageCode.ERROR_ENTITY_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Unit test class for {@link CityService}
 */
@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    private static CityDTO cityDTO_1;

    @InjectMocks
    private CityService cityService;

    private static City city_1;

    private static City city_2;

    private static City city_3;
    private static CityDTO cityDTO_2;
    private static CityDTO cityDTO_3;
    @Mock
    private CityMapper cityMapper;

    @BeforeAll
    static void setup() {
        city_1 = new City();
        city_2 = new City();
        city_3 = new City();
        cityDTO_1 = new CityDTO();
        cityDTO_2 = new CityDTO();
        cityDTO_3 = new CityDTO();
    }

    private static Stream<Arguments> provideTestedCollections() {

        final List<City> cities_1 = new ArrayList<>();
        cities_1.add(city_1);

        final List<CityDTO> cityDTOList_1 = new ArrayList<>();
        cityDTOList_1.add(cityDTO_1);

        final List<City> cities_2 = new ArrayList<>();
        cities_2.add(city_1);
        cities_2.add(city_2);

        final List<CityDTO> cityDTOList_2 = new ArrayList<>();
        cityDTOList_2.add(cityDTO_1);
        cityDTOList_2.add(cityDTO_2);

        final List<City> cities_3 = new ArrayList<>();
        cities_3.add(city_1);
        cities_3.add(city_2);
        cities_3.add(city_3);

        final List<CityDTO> cityDTOList_3 = new ArrayList<>();
        cityDTOList_3.add(cityDTO_1);
        cityDTOList_3.add(cityDTO_2);
        cityDTOList_3.add(cityDTO_3);

        return Stream.of(
                Arguments.of(Collections.emptyList(), Collections.emptyList()),
                Arguments.of(cities_1, cityDTOList_1),
                Arguments.of(cities_2, cityDTOList_2),
                Arguments.of(cities_3, cityDTOList_3)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestedCollections")
    void getAllShouldReturnRespectedCollection(final List<City> inputCities,
                                               final List<CityDTO> outputCities) {

        final int pageSize = inputCities.size() > 0 ? inputCities.size() : 1;
        final PageRequest pageRequest = PageRequest.of(0, pageSize);

        when(cityRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(inputCities));

        final AtomicInteger counter = new AtomicInteger(0);
        inputCities.forEach(inputCity -> {
            int currentCityCounter = counter.incrementAndGet() - 1;
            when(cityMapper.mapToDto(inputCity)).thenReturn(outputCities.get(currentCityCounter));

        });

        final Page<CityDTO> pagedResult = cityService.getAll(pageRequest);
        final List<CityDTO> returnedCityDTOList = pagedResult.getContent();
        assertThat(returnedCityDTOList).containsExactlyElementsOf(outputCities);
    }

    @ParameterizedTest
    @MethodSource("provideTestedCollections")
    void findByNameStartingWithShouldReturnRespectedCollection(final List<City> inputCities,
                                                               final List<CityDTO> outputCities) {
        final String prefixToBeSearched = "LON";
        when(cityRepository.findByNameStartingWith(prefixToBeSearched)).thenReturn(inputCities);
        when(cityMapper.mapToDto(inputCities)).thenReturn(outputCities);
        final List<CityDTO> returnedCityDTOList = cityService.findByNameStartingWith(prefixToBeSearched);
        assertThat(returnedCityDTOList).containsExactlyElementsOf(outputCities);
    }

    @Test
    void updateShouldReturnOriginalCity() {
        final Long cityId = 1L;
        final String name = "WROCLAW";
        final String photoUrl = "TEST_PHOTO_URL";

        final CityUpdateDTO cityUpdateDTO = new CityUpdateDTO();
        cityUpdateDTO.setName(name);
        cityUpdateDTO.setPhotoUrl(photoUrl);

        final CityDTO cityDTO = new CityDTO();
        cityDTO.setId(cityId);
        cityDTO.setName(name);
        cityDTO.setPhotoUrl(photoUrl);

        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city_1));
        when(cityRepository.save(city_1)).thenReturn(city_1);
        when(cityMapper.mapToDto(city_1)).thenReturn(cityDTO);

        final CityDTO updatedCity = cityService.update(cityId, cityUpdateDTO);

        assertThat(updatedCity.getId()).isEqualTo(cityId);
        assertThat(updatedCity.getName()).isEqualTo(cityUpdateDTO.getName());
        assertThat(updatedCity.getPhotoUrl()).isEqualTo(cityUpdateDTO.getPhotoUrl());
    }

    @Test
    public void updateWithWrongIdShouldThrowError() {
        final Long cityId = 123456789L;
        final CityUpdateDTO cityUpdateDTO = new CityUpdateDTO();

        when(cityRepository.findById(cityId)).thenReturn(Optional.empty());
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            cityService.update(cityId, cityUpdateDTO);
        });


        final List<Object> messageArgs = exception.getMessageArgs();
        final MessageCode messageCode = exception.getMessageCode();

        assertThat(messageArgs).contains(cityId);
        assertThat(messageCode).isEqualTo(ERROR_ENTITY_NOT_FOUND);

    }
}