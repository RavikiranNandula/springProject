package com.blogApplication.service;

import com.blogApplication.model.Tags;

import java.util.List;

public interface TagsService {
    void saveTag(Tags tag);
    Tags getTags(String name);

    List<Tags> getAllTags();
}
