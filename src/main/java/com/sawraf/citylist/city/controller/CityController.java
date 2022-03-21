package com.sawraf.citylist.city.controller;

import com.sawraf.citylist.city.dto.CityDTO;
import com.sawraf.citylist.city.dto.CityUpdateDTO;
import com.sawraf.citylist.city.service.CityService;
import com.sawraf.citylist.security.annotations.EditPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public Page<CityDTO> getCitiesByNameStartingWith(@NotNull @PathVariable String prefix, Pageable paging) {
        return cityService.findByNameStartingWith(prefix, paging);
    }

    @PutMapping(value = "/{id}")
    @EditPermission
    public CityDTO updateCity(@PathVariable Long id, @Valid @RequestBody CityUpdateDTO newCity) {
        return cityService.update(id, newCity);
    }
}
