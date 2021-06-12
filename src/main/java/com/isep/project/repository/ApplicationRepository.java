package com.isep.project.repository;

import com.isep.project.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/5
 */
public interface ApplicationRepository extends JpaRepository<Application, Long>,
        JpaSpecificationExecutor<Application>
{

    @RestResource(exported = false)
    @Query(value = "select a from Application a where a.id = :id")
    Application selectById(@Param("id") Long id);

}