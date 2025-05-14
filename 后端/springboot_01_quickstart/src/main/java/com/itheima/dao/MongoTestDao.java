package com.itheima.dao;

import com.itheima.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoTestDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     */
    public void saveTest(Comment comment) {
        mongoTemplate.save(comment);
    }


    public List<Comment> findTestByName(String name) {
        Query query=new Query(Criteria.where("ifSee").is("看过")).limit(2000);
        List<Comment> list =  mongoTemplate.find(query , Comment.class);
        return list;
    }

    public List<Comment> findAll(){
//        Query query=new Query(Criteria.where("movieName").is("绞肉行动"));
        System.out.println("arrive");
        List<Comment> comments = mongoTemplate.findAll(Comment.class,"comment");
        System.out.println("finish");
        return comments;
    }




}
