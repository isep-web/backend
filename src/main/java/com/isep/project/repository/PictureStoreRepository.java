package com.isep.project.repository;

import com.isep.project.entity.Picture;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/31
 */
@CrossOrigin
@StoreRestResource(path = "pictures", linkRel = "content")
public interface PictureStoreRepository extends ContentStore<Picture, String>
{
}
