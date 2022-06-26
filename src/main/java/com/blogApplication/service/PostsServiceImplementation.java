package com.blogApplication.service;


import com.blogApplication.model.Posts;
import com.blogApplication.model.Tags;
import com.blogApplication.repository.PostsRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostsServiceImplementation implements PostsService{
    @Autowired
    private PostsRepositry postsRepositry;
    @Autowired
    private TagsService tagsService;
    @Override
    public void savePost(Posts post, Tags tag) {
        String excerpt;
        String content=post.getContent();
        Date date = new Date();
        int length=content.length();
        if(length>150){
            excerpt=content.substring(0,150)+".........";
        }
        else{
            excerpt=content+"......";
        }
        post.setExcerpt(excerpt);
        post.setAuthor("Ravi");
        post.setPublishedAt(null);
        post.setIsPublished(false);
        post.setCreatedAt(date);
        post.setUpdatedAt(null);

        String[] tagslist=tag.getName().split(",");
        for (String tagName:tagslist) {
            Tags newTag=new Tags();
            Tags tagData=tagsService.getTags(tagName.toLowerCase());
            tag.getPosts().add(post);
            if(tagData==null){
                newTag.setName(tagName.toLowerCase());
                newTag.setCreatedAt(date);
                newTag.setUpdatedAt(null);
                tagsService.saveTag(newTag);
                post.getTags().add(newTag);
            }
            else{
                post.getTags().add(tagData);
            }
        }
        postsRepositry.save(post);
    }

    @Override
    public void addComment(Posts post) {
        postsRepositry.save(post);
    }

    @Override
    public void publishPost(Posts post, Tags tag) {
        String content=post.getContent();
        String excerpt;
        Date date = new Date();
        int length=content.length()/2;
        if(length>150){
            excerpt=content.substring(0,150)+".........";
        }
        else{
            excerpt=content+"......";
        }
        post.setExcerpt(excerpt);
        post.setAuthor("Ravi");
        post.setPublishedAt(date);
        post.setIsPublished(true);
        post.setCreatedAt(date);
        post.setUpdatedAt(null);

        String[] tagslist=tag.getName().split(",");
        for (String tagName:tagslist) {
            Tags newTag=new Tags();
            Tags tagData=tagsService.getTags(tagName.toLowerCase());
            tag.getPosts().add(post);
            if(tagData==null){
                newTag.setName(tagName.toLowerCase());
                newTag.setCreatedAt(date);
                newTag.setUpdatedAt(null);
                tagsService.saveTag(newTag);
                post.getTags().add(newTag);
            }
            else{
                post.getTags().add(tagData);
            }
        }
        postsRepositry.save(post);
    }
    @Override
    public List<Posts> getAllBlogPosts() {
        List<Posts> allBlogPostsList=postsRepositry.findAll();
        return allBlogPostsList;
    }
    @Override
    public Optional<Posts> findPost(Integer id) {
        return postsRepositry.findById(id);
    }

    public void publishEditedPost(Posts post,Tags tag,Date createdDate) {
        String excerpt;
        String content=post.getContent();
        Date date = new Date();
        int length=content.length();
        if(length>150){
            excerpt=content.substring(0,150)+".........";
        }
        else{
            excerpt=content+"......";
        }
        post.setExcerpt(excerpt);
        post.setAuthor("Ravi");
        post.setPublishedAt(date);
        post.setCreatedAt(createdDate);
        post.setIsPublished(true);
        post.setUpdatedAt(date);

        String[] tagslist=tag.getName().split(",");
        for (String tagName:tagslist) {
            Tags newTag=new Tags();
            Tags tagData=tagsService.getTags(tagName.toLowerCase());
            tag.getPosts().add(post);
            if(tagData==null){
                newTag.setName(tagName.toLowerCase());
                newTag.setCreatedAt(date);
                newTag.setUpdatedAt(null);
                tagsService.saveTag(newTag);
                post.getTags().add(newTag);
            }
            else{
                post.getTags().add(tagData);
            }
        }
        postsRepositry.save(post);
    }

    @Override
    public void saveEditedPost(Posts post, Tags tag,Date createdDate) {
        String title=post.getTitle();
        String excerpt;
        String content=post.getContent();
        Date date = new Date();
        int length=content.length();
        if(length>100){
            excerpt=content.substring(0,100)+".........";
        }
        else{
            excerpt=content+"......";
        }
        post.setTitle(title);
        post.setAuthor("Ravi");
        post.setCreatedAt(createdDate);
        post.setExcerpt(excerpt);
        post.setIsPublished(false);
        post.setContent(content);
        post.setUpdatedAt(date);

        String[] tagslist=tag.getName().split(",");
        for (String tagName:tagslist) {
            Tags newTag=new Tags();
            Tags tagData=tagsService.getTags(tagName.toLowerCase());
            tag.getPosts().add(post);
            if(tagData==null){
                newTag.setName(tagName.toLowerCase());
                newTag.setCreatedAt(date);
                newTag.setUpdatedAt(null);
                tagsService.saveTag(newTag);
                post.getTags().add(newTag);
            }
            else{
                post.getTags().add(tagData);
            }
        }
        postsRepositry.save(post);
    }
}
