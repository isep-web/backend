package com.isep.project.repository;

import com.isep.project.entity.Userdtl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserdtlRepository extends JpaRepository<Userdtl, Long>,
        JpaSpecificationExecutor<Userdtl>
{

}