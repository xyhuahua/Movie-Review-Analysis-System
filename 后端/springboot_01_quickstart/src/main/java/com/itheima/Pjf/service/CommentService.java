package com.itheima.Pjf.service;


import com.itheima.Pjf.dao.CommentDao;
import com.itheima.Pjf.domain.Comment;
import com.itheima.dao.MovieDao;
import com.itheima.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private MovieDao movieDao;
    @Autowired
    private CommentDao mtdao;


    /**
     * 获取热门评论
     * @param page 页码
     * @return
     */
    public List<Map<String,Object>> getHotComments(int page,String type){
        List<Comment> comments = mtdao.getHotComments(page,type);
        List<Map<String,Object>> list = new ArrayList<>();
        for (Comment c:comments) {
            Map<String,Object> maps = new HashMap<>();
            Movie movie = movieDao.findMovieById(c.getMovieId());
            maps.put("comment",c);
            maps.put("movie",movie);
            list.add(maps);
        }
        return list;
    }
}
