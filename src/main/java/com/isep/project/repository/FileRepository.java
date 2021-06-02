package com.isep.project.repository;

import com.isep.project.entity.File;
import com.isep.project.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "pictures", collectionResourceRel = "pictures")
public interface FileRepository extends JpaRepository<File, Long>, JpaSpecificationExecutor<File>
{

}
