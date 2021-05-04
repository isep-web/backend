package com.isep.project.repository;

import com.isep.project.entity.Constraint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConstraintRepository extends JpaRepository<Constraint, Long>,
        JpaSpecificationExecutor<Constraint>
{

}