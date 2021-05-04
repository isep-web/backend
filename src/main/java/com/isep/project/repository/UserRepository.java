package com.isep.project.repository;

import com.isep.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/5
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{

}