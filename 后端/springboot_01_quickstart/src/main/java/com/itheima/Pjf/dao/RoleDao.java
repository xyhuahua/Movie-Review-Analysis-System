package com.itheima.Pjf.dao;

import com.itheima.Pjf.domain.Role;
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
public class RoleDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Role getRoleById(String id) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("actorId").is(id));
        return mongoTemplate.findOne(query, Role.class, "role");
    }

    public List<Role> getRoleRank(String type,int page){
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc(type)));
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        query.with(pageRequest);
        return mongoTemplate.find(query,Role.class,"role");
    }

}
