package com.isep.project.repository;

import com.isep.project.entity.Picture;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Xuan MIAO
 */
@CrossOrigin
@StoreRestResource(path = "pictures", linkRel = "pictures")
public interface PictureStoreRepository extends ContentStore<Picture, String>
{
}
