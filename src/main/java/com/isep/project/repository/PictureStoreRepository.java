package com.isep.project.repository;

import com.isep.project.entity.Picture;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;

/**
 * @author Xuan MIAO
 */
@StoreRestResource(path = "pictures", linkRel = "pictures")
public interface PictureStoreRepository extends ContentStore<Picture, String>
{
}
