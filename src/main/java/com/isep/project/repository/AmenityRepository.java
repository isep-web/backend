package com.isep.project.repository;

import com.isep.project.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/5
 */
@RepositoryRestResource(exported = false)
public interface AmenityRepository extends JpaRepository<Amenity, Long>,
        JpaSpecificationExecutor<Amenity>
{

}