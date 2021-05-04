package com.isep.project.repository;

import com.isep.project.entity.HouseConstraint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HouseConstraintRepository extends JpaRepository<HouseConstraint, Long>,
        JpaSpecificationExecutor<HouseConstraint>
{

}