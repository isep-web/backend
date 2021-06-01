package com.isep.project;

import com.isep.project.entity.Picture;
import org.springframework.content.commons.repository.ContentStore;

public interface PictureContentStore extends ContentStore<Picture, String> {
}
