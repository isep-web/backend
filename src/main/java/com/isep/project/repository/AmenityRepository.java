package com.isep.project.repository;

import com.isep.project.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AmenityRepository extends JpaRepository<Amenity, Long>,
        JpaSpecificationExecutor<Amenity>
{

}