package com.isep.project;

import com.isep.project.entity.File;
import org.springframework.content.commons.repository.ContentStore;

public interface FileContentStore extends ContentStore<File, String> {
}
