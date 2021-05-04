package com.isep.project.repository;

import com.isep.project.entity.HouseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HouseServiceRepository extends JpaRepository<HouseService, Long>,
        JpaSpecificationExecutor<HouseService>
{

}