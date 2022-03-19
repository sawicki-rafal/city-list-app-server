package com.sawraf.citylist.city.dto;

/**
 * Simple JavaBean domain transfer object representing a City.
 */
public class CityDTO {

    private Long id;

    private String name;

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
}
