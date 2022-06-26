package com.blogApplication.controllers;

import com.blogApplication.model.Comments;
import com.blogApplication.model.Posts;
import com.blogApplication.model.Tags;
import com.blogApplication.service.CommentsService;
import com.blogApplication.service.PostsService;
import com.blogApplication.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    PostsService postsService;
    @Autowired
    TagsService tagsService;
    @Autowired
    CommentsService commentsService;
    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Posts> allBlogPostsList=postsService.getAllBlogPosts();
        List<Tags> allTagsList=tagsService.getAllTags();
        model.addAttribute("tagsList",allTagsList);
        model.addAttribute("blogsList",allBlogPostsList);
        return "homePage";
    }
    @RequestMapping("/post/{id}")
    public String viewPost(@PathVariable Integer id, Model model){
        Optional<Posts> post=postsService.findPost(id);
        Posts postData=post.get();
        List<Comments> commentsList=postData.getComments();
        model.addAttribute("commentsList",commentsList);
        model.addAttribute("postData",postData);
        return "viewpostPage";
    }
    @PostMapping("/searchPost")
    public String searchPost(@RequestParam("search") String search){
        System.out.println(search);
        System.out.println("came");
        return null;
    }

}
