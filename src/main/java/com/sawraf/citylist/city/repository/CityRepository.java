package com.sawraf.citylist.city.repository;

import com.sawraf.citylist.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operations on {@link City}
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
