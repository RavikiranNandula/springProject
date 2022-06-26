package com.blogApplication.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class FIltersController {
    @RequestMapping("/getTagRelevantPosts")
    public String getTagRelevantPosts(@RequestParam("Filters") String filters){
        System.out.println(filters);
        System.out.println("came");
        return null;
    }


}
