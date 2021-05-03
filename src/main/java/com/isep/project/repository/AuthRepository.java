package com.isep.project.repository;

import com.isep.project.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthRepository extends JpaRepository<Auth, Long>, JpaSpecificationExecutor<Auth>
{

}