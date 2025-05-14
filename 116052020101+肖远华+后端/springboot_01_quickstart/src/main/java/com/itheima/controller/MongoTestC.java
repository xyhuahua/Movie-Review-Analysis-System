package com.itheima.controller;


import com.itheima.dao.MongoTestDao;
import com.itheima.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MongoTestC {

    @Autowired
    private MongoTestDao mtdao;

    @GetMapping(value = "/test1")
    public String saveTest() throws Exception {
        return "OK";
    }

    @GetMapping(value = "/test2")
    public List<Comment> FindById() throws Exception {

        List<Comment> testByName = mtdao.findTestByName("拥桑");
        System.out.println(testByName);
        return testByName;

    }

    @GetMapping(value = "/test3")
    public List<Comment> FindAll() throws Exception {

        List<Comment> testByName = mtdao.findAll();
        System.out.println(testByName);
        return testByName;

    }


}
