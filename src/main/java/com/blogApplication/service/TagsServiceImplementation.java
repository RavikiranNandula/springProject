package com.blogApplication.service;

import com.blogApplication.model.Tags;
import com.blogApplication.repository.TagsRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImplementation implements TagsService{
    @Autowired
    private TagsRepositry tagsRepositry;
    @Override
    public void saveTag(Tags tag) {
        tagsRepositry.save(tag);
    }

    @Override
    public Tags getTags(String tagName) {
        Tags tags=tagsRepositry.findTagByName(tagName);
        return tags;
    }

    @Override
    public List<Tags> getAllTags() {
        return tagsRepositry.findAll();
    }
}
