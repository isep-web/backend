package com.isep.project.repository;

import com.isep.project.entity.Constraint;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/5
 */
public interface ConstraintRepository extends OnlyGetAllRepository<Constraint, Long>,
        JpaSpecificationExecutor<Constraint>
{

}