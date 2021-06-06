package com.isep.project.repository;

import com.isep.project.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "pictures", collectionResourceRel = "pictures")
public interface PictureRepository extends JpaRepository<Picture, Long>,
        JpaSpecificationExecutor<Picture>
{

}
