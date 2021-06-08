package com.isep.project.repository;

import com.isep.project.entity.Service;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/5/18
 */
public interface ServiceRepository extends OnlyGetAllRepository<Service, Long>,
        JpaSpecificationExecutor<Service>
{

}