package com.blogApplication.repository;

import com.blogApplication.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepositry extends JpaRepository<Posts,Integer> {
    List<Posts> findPostsBySearchInput(String name);
}
