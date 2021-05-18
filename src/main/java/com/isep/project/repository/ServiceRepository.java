package com.isep.project.repository;

import com.isep.project.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/5/18
 */
@RepositoryRestResource(exported = false)
public interface ServiceRepository extends JpaRepository<Service, Long>,
        JpaSpecificationExecutor<Service>
{

}