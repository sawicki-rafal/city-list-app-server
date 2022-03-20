package com.sawraf.citylist.city.entity;

import com.sawraf.citylist.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

/**
 * Simple JavaBean domain object representing a City.
 */
@Entity
public class City extends AbstractEntity {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Photo url is required")
    @Column(name = "photo")
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
        return "City{" + super.toString() + ", name=" + name + ", photoUrl=" + photoUrl + "}";
    }
}
