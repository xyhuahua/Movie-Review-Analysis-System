package com.itheima.Pjf.dao;

import com.itheima.Pjf.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 热门评论
     *
     * @param page
     * @return
     */
    public List<Comment> getHotComments(int page, String type) {
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc(type)));
        PageRequest pageRequest = PageRequest.of(page - 1, 25);
        query.with(pageRequest);
        List<Comment> list = mongoTemplate.find(query, Comment.class,"comment");
        return list;
    }





}
