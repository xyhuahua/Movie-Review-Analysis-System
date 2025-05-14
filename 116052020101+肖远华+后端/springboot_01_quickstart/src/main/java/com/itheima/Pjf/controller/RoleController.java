package com.itheima.Pjf.controller;

import com.itheima.Pjf.domain.Role;
import com.itheima.Pjf.service.RoleService;
import com.itheima.dao.MovieDao;
import com.itheima.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private MovieDao movieDao;

    @RequestMapping("/role/getRoleRank")
    public List<Map> getRoleRank(@RequestParam Map<String,Object> params){
        String type = (String) params.get("type");
        String page = (String) params.get("page");
        return roleService.getRoleRank(type, Integer.parseInt(page));
    }

    @RequestMapping("/role/getRoleDetail")
    public Role getRoleDetail(@RequestParam Map<String,Object> params){
        String id = (String) params.get("roleId");
        return roleService.getRoleById(id);
    }

    @RequestMapping("/role/getRecentMovie")
    public List<Movie> getRecentMovie(@RequestParam Map<String,Object> params){
        String actorId = (String) params.get("actorId");
//        System.out.println(actorId);
//        System.out.println(roleService.getRecentMovie(actorId));1
        return roleService.getRecentMovie(actorId);
    }

}
