package com.itheima.dao;

import com.itheima.domain.Chat;
import com.itheima.domain.Comment;
import com.itheima.domain.Movie;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public class ChatDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    public void addChat(Chat chat){
        mongoTemplate.insert(chat,"chat");
    }

    public List<Chat> findAll(String roomid,String type,int size) {
        Query query = new Query();
        query.addCriteria(Criteria.where("roomid").is(roomid));
        query.addCriteria(Criteria.where("type").is(type));

        Document source = new Document();
        source.put("locale", "zh");
        source.put("numericOrdering", true);
        Collation collation = Collation.from(source);
        query.collation(collation);

        PageRequest pageRequest = PageRequest.of(0,size);
        query.with(pageRequest);

        query.with(Sort.by(Sort.Direction.DESC, "time"));

        List<Chat> chats =  mongoTemplate.find(query,Chat.class);
        return chats;
    }

    public long getLen(String roomid,String type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("roomid").is(roomid));
        query.addCriteria(Criteria.where("type").is(type));

        Document source = new Document();
        source.put("locale", "zh");
        source.put("numericOrdering", true);
        Collation collation = Collation.from(source);
        query.collation(collation);

        long ret = mongoTemplate.count(query, "chat");

        return ret;
    }



}
