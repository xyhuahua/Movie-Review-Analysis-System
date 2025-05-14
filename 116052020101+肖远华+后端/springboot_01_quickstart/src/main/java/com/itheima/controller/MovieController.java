package com.itheima.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.MovieDao;
import com.itheima.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    @Autowired
    private MovieDao movieDao;

    @GetMapping(value = "/movie/findAll")
    public String getAllMovie(@RequestParam Map<String, Object> params) throws IOException {
        String movieSort = (String) params.get("movieSort");
        String page = (String) params.get("page");
        String currentSort_type = (String) params.get("currentSort_type");
        String currentSort_country = (String) params.get("currentSort_country");
        String currentSort_time = (String) params.get("currentSort_time");
        String currentSort_hot = (String) params.get("currentSort_hot");


       // System.out.println(page);
        //System.out.println(movieSort);
        List<Movie> movies = movieDao.findAll(Integer.parseInt(page),currentSort_type,currentSort_country,currentSort_time,currentSort_hot);
        //System.out.println(movies);
        long count = movieDao.findcount(Integer.parseInt(page),currentSort_type,currentSort_country,currentSort_time,currentSort_hot);
        //24为一页显示的个数
        System.out.println("999"+count);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("movies", movies);
        generator.writeNumberField("count", count);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        //System.out.println(s);

        return s;
    }

    /**
     *
     * @param params
     * @return
     */
    @GetMapping(value = "/movie/getMoiveRank")
    public Map<String,Object> getMovieRank(@RequestParam Map<String ,Object> params){
        String param = (String) params.get("type");
        System.out.println(param);
        String year = (String) params.get("year");
        System.out.println(year);
        String page = (String) params.get("page");
        System.out.println(page);
        return movieDao.getMovieRank(param, Integer.parseInt(page),year);
    }

}
