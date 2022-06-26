package com.blogApplication.service;

import com.blogApplication.model.Posts;
import com.blogApplication.model.Tags;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PostsService {
    void savePost(Posts post, Tags tag);
    void addComment(Posts post);
    void publishPost(Posts posts, Tags tag);
    List<Posts> getAllBlogPosts();
    Optional<Posts> findPost(Integer id);
    void publishEditedPost(Posts post,Tags tag,Date createdDate);
    void saveEditedPost(Posts post, Tags tag,Date createdDate);



}
