package com.blogApplication.repository;

import com.blogApplication.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepositry extends JpaRepository<Tags, Integer> {
    Tags findTagByName(String name);
}
