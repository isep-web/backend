package com.isep.project.repository;

import com.isep.project.entity.Application;
import com.isep.project.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/30
 */
@RepositoryRestResource(path = "pictures", collectionResourceRel = "pictures")
public interface PictureRepository extends JpaRepository<Picture, Long>,
        JpaSpecificationExecutor<Picture>
{
    @RestResource(exported = false)
    @Query(value = "select p from Picture p where p.id = :id")
    Picture selectById(@Param("id") Long id);

}
