package com.blogApplication.controllers;

import com.blogApplication.model.Posts;
import com.blogApplication.model.Tags;
import com.blogApplication.service.PostsService;
import com.blogApplication.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {
    @Autowired
    private PostsService postsService;
    @Autowired
    private TagsService tagsService;

    public Date createdDate;

    @GetMapping("/newpost")
    public String viewNewPostPage(Model model){
        model.addAttribute("heading","New Post");
        model.addAttribute("buttonName","Save");
        Posts post =new Posts();
        model.addAttribute("post",post);
        Tags tag=new Tags();
        model.addAttribute("tag",tag);
        return "newPostPage";
    }
    @PostMapping("/savePost")
    public String savePost(@ModelAttribute("post") Posts post,@ModelAttribute("tag") Tags tag){
        postsService.savePost(post,tag);
        return "redirect:/newpost";
    }
    @PostMapping("/publishPost")
    public String publishPost(@ModelAttribute("post") Posts post,@ModelAttribute("tag") Tags tag){
        postsService.publishPost(post,tag);
        return "redirect:/newpost";
    }
    @GetMapping("/editpost/{id}")
    public String viewEditPostPage(@PathVariable int id, Model model){
        model.addAttribute("buttonName","Update");
        model.addAttribute("heading","Edit Post");
        Optional<Posts> optionalPosts=postsService.findPost(id);
        Posts post= optionalPosts.get();
        createdDate=post.getCreatedAt();
        List<Tags> tagsList=post.getTags();
        String tagsData="";
        for (Tags tag:tagsList) {
            tagsData=tagsData+tag.getName()+",";
        }
        model.addAttribute("tagsData",tagsData);
        model.addAttribute("post",post);
        return "newPostPage";
    }
    @PostMapping("/editpost/updatePost")
    public String updatePost(@ModelAttribute("post") Posts post,@ModelAttribute("tag") Tags tag){
        postsService.saveEditedPost(post,tag,createdDate);
        return "redirect:/";
    }
    @PostMapping("editpost/publishPost")
    public String publishUpdatedPost(@ModelAttribute("post") Posts post,@ModelAttribute("tag") Tags tag){
        postsService.publishEditedPost(post,tag,createdDate);
        return "redirect:/";
    }
}
