package com.isep.project;

import com.isep.project.entity.File;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@StoreRestResource
public interface FileContentStore extends ContentStore<File, String>
{
}
