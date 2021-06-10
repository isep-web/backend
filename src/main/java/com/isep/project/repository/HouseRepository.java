package com.isep.project.repository;

import com.isep.project.entity.House;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/5/18
 */
@CrossOrigin
public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House>
{

    List<House> findByUserId(@Param("id") Long id);

    @RestResource(exported = false)
    @Query(value = "select h from House h where h.id = :id")
    House selectById(@Param("id") Long id);

    @RestResource(exported = false)
    @Query(value = "SELECT DISTINCT(h.id) FROM House h"
            + " WHERE((:areaMin is null OR :areaMax is null OR :areaMin='' OR :areaMax='') "
            + "OR h.area BETWEEN :areaMin AND :areaMax ) "
            + "AND (:title is null OR :title='' OR h.title like CONCAT('%',:title,'%'))"
            + "AND (:guestNumber is null OR :guestNumber='' OR h.guestNumber >=:guestNumber)"
            + "AND (h.id in (SELECT h.id FROM House h left JOIN h.amenities ha where ha.id in "
            + "(:amenities) group by h.id having count(h)= :amenitiesNum))"
    )
    List<Long> advSearch(@Param("areaMin") String areaMin,
            @Param("areaMax") String areaMax, @Param("title") String title,
            @Param("guestNumber") String guestNumber, @Param("amenities") List<Long> amenities,
            @Param("amenitiesNum") Long amenitiesNum,
            Pageable page);

    @RestResource(exported = false)
    @Query(value = "SELECT DISTINCT(h.id) FROM House h"
            + " WHERE((:areaMin is null OR :areaMax is null OR :areaMin='' OR :areaMax='') "
            + "OR h.area BETWEEN :areaMin AND :areaMax ) "
            + "AND (:title is null OR :title='' OR h.title like CONCAT('%',:title,'%'))"
            + "AND (:guestNumber is null OR :guestNumber='' OR h.guestNumber >=:guestNumber)")
    List<Long> advSearchWithoutAnemities(@Param("areaMin") String areaMin,
            @Param("areaMax") String areaMax, @Param("title") String title,
            @Param("guestNumber") String guestNumber, Pageable page);

}