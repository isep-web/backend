package com.isep.project.repository;

import com.isep.project.entity.HouseAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HouseAmenityRepository extends JpaRepository<HouseAmenity, Long>,
        JpaSpecificationExecutor<HouseAmenity>
{

}