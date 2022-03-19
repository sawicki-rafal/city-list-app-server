package com.sawraf.citylist.city.controller;

import com.sawraf.citylist.city.entity.City;
import com.sawraf.citylist.city.service.CityService;
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
    public List<City> getAll() {
        return cityService.getAll();
    }

    @GetMapping(value = "/{prefix}")
    public List<City> getCityByNameStartingWith(@PathVariable String prefix) {
        return cityService.findByNameStartingWith(prefix);
    }

    @PutMapping(value = "/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City newCity) {
        return cityService.update(id, newCity);
    }
}
