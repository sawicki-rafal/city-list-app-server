package com.sawraf.citylist.city.controller;

import com.sawraf.citylist.city.dto.CityDTO;
import com.sawraf.citylist.city.dto.CityUpdateDTO;
import com.sawraf.citylist.city.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Service for requesting {@link com.sawraf.citylist.city.entity.City}
 */
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public Page<CityDTO> getAll(Pageable pageable) {
        return cityService.getAll(pageable);
    }

    @GetMapping(value = "/{prefix}")
    public List<CityDTO> getCityByNameStartingWith(@PathVariable String prefix) {
        return cityService.findByNameStartingWith(prefix);
    }

    @PutMapping(value = "/{id}")
    public CityDTO updateCity(@PathVariable Long id, @RequestBody CityUpdateDTO newCity) {
        return cityService.update(id, newCity);
    }
}
