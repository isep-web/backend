package com.isep.project.repository;

import com.isep.project.entity.House;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

/**
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/5/18
 */
public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House>
{

    List<House> findByServicesIdIn(@Param("ids") Collection<Long> id);

    List<House> findByServicesId(@Param("id") Long id);
}