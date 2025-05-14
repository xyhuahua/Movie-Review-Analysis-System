package com.itheima.Pjf.service;

import com.itheima.Pjf.dao.RoleDao;
import com.itheima.Pjf.domain.Role;
import com.itheima.Pjf.utils.RoleUtil;
import com.itheima.dao.MovieDao;
import com.itheima.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MovieDao movieDao;

    public List<Map> getRoleRank(String type, int page) {
        List<Map> list = new ArrayList<>();
        List<Role> roleRank = roleDao.getRoleRank(type, page);
//        for (Role r : roleRank) {
//            List<Movie> movieList = new ArrayList<>();
//            Map<String, Object> maps = new HashMap<>();
//            maps.put("role", r);
//            for (String s : RoleUtil.getMovieId(r)) {
//                Movie movie = movieDao.findMovieById(s);
//                if (movie != null) {
//                    movieList.add(movie);
//                }
//            }
//            maps.put("movies", movieList);
//            list.add(maps);
//        }
        for (int i = 0;i<roleRank.size();i++) {
            List<Movie> movieList = new ArrayList<>();
            Map<String, Object> maps = new HashMap<>();
            Role r = roleRank.get(i);
            maps.put("role", r);
            List<String> movieIds = RoleUtil.getMovieId(r);
            for (int j = 0;j < movieIds.size(); j++) {
                Movie movie = movieDao.findMovieById(movieIds.get(j));
                if (movie != null) {
                    movieList.add(movie);
                }
            }
            maps.put("movies", movieList);
            list.add(maps);
        }
        return list;
    }

    public Role getRoleById(String id) {
        return roleDao.getRoleById(id);
    }

    public List<Movie> getRecentMovie(String actorId){
        Role role = roleDao.getRoleById(actorId);
        List<String> movieIds = RoleUtil.getMovieId(role);
        List<Movie> movies = new ArrayList<>();
        for (String s: movieIds) {
            Movie movieById = movieDao.findMovieById(s);
            if(movieById != null){
                movies.add(movieById);
            }
        }
        return movies;
    }

}
