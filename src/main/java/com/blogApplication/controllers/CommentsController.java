package com.blogApplication.controllers;

import com.blogApplication.model.Comments;
import com.blogApplication.model.Posts;
import com.blogApplication.service.CommentsService;
import com.blogApplication.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
public class CommentsController {
    @Autowired
    private PostsService postsService;
    @Autowired
    private CommentsService commentsService;
    @RequestMapping("post/{id}/addComment")
    public String addComment(@PathVariable("id") int id, @RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("comment") String commentData){
        Comments comment=new Comments();
        Optional<Posts> post=postsService.findPost(id);
        Posts postData=post.get();
        Date date=new Date();
        comment.setName(name);
        comment.setEmail(email);
        comment.setText(commentData);
        comment.setCreatedAt(date);
        comment.setPost_id(1);

        postData.getComments().add(comment);
        postsService.addComment(postData);
        return "redirect:/post/"+id;
    }

    @PostMapping("/editComment{id}")
    public String editComment(@PathVariable int id, Model model){
        Optional<Comments> comments=commentsService.findComment(id);
        Comments comment=comments.get();
        model.addAttribute("comment",comment);
        return "CommentsUpdatePage";
    }
    @PostMapping("/updateComment{id}")
    public String updateComment(@PathVariable("id") int id, @ModelAttribute("comment") Comments comment){
        Date date=new Date();
        int postId;
        Optional<Comments> comments=commentsService.findComment(id);
        Comments commentData=comments.get();
        commentData.setName(comment.getName());
        commentData.setEmail(comment.getEmail());
        commentData.setText(comment.getText());
        commentData.setUpdated_at(date);
        postId=commentData.getPost_id();
        commentsService.saveComment(commentData);
        return "redirect:/post/"+postId;
    }
    @PostMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable("id") int id){
        int postId;
        Optional<Comments> comments=commentsService.findComment(id);
        Comments commentData=comments.get();
        postId=commentData.getPost_id();
        commentsService.deleteComment(id);
        return "redirect:/post/"+postId;
    }
}
