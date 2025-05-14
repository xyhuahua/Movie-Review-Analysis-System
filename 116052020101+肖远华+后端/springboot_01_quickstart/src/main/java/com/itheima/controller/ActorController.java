package com.itheima.controller;

import com.itheima.domain.RoleRank;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ActorController {
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/role/actor")
    public List<RoleRank> getAllRoleRank() {
        List<RoleRank> roleRanks = roleService.getAllRoleRank();
        System.out.println(roleRanks);
        return roleRanks;
    }


}
