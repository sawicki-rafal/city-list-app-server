package com.sawraf.citylist.city.dto;

import javax.validation.constraints.NotBlank;

/**
 * Simple JavaBean domain transfer object for UPDATE representing a City.
 */
public class CityUpdateDTO {

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

    @Override
    public String toString() {
        return "CityUpdateDTO{name=" + name + ", photoUrl=" + photoUrl + "}";
    }
}