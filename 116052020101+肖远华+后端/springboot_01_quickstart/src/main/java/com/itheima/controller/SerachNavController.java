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
public class SerachNavController {
    @Autowired
    private MovieDao movieDao;

    @GetMapping(value = "/getAllMovieName")
    public String getAllMovieName(@RequestParam Map<String, Object> params) throws IOException {

        String queryString = (String) params.get("queryString");
        System.out.println("queryString："+queryString);

        List<Movie> allMovieName = movieDao.getAllMovieName(queryString);
        System.out.println("allMovieName");
        System.out.println(allMovieName);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("allMovieName", allMovieName);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }
}
