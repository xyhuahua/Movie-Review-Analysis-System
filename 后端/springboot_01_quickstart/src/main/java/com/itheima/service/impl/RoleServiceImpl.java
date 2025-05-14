package com.itheima.service.impl;

import com.itheima.domain.RoleRank;
import com.itheima.service.RoleService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<RoleRank> getAllRoleRank() {
        //findAll() 返回集合中所有的文档
        List<RoleRank> RoleRankList = mongoTemplate.findAll(RoleRank.class, "RoleRank");
        return RoleRankList;
    }

}
