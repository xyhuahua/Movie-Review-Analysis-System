package com.itheima.Pjf.controller;


import com.itheima.Pjf.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/comment/getHotComments")
    public List<Map<String,Object>> test4(@RequestParam Map<String, Object> params){
        String page = (String) params.get("page");
        String type = (String) params.get("type");
        return commentService.getHotComments(Integer.parseInt(page),type);
    }

    @GetMapping(value = "/comment/getHomepageComments")
    public List<Map<String,Object>> getHomepageComments(@RequestParam Map<String, Object> params){
        String page = (String) params.get("page");
        String type = (String) params.get("type");
        return commentService.getHotComments(Integer.parseInt(page),type);
    }


}
