package com.sawraf.citylist.city.repository;

import com.sawraf.citylist.city.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for CRUD operations on {@link City}
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Page<City> findByNameStartingWith(String prefix, Pageable pageable);
}
