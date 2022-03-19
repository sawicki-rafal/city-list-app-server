package com.sawraf.citylist.city.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Simple JavaBean domain transfer object representing a City.
 */
public class CityDTO {

    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Photo url is required")
    private String photoUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CityDTO{id=" + id + ", name=" + name + ", photoUrl=" + photoUrl + "}";
    }
}
