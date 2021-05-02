package com.isep.project.repository;

import com.isep.project.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationRepository extends JpaRepository<Application, Integer>,
        JpaSpecificationExecutor<Application>
{

}