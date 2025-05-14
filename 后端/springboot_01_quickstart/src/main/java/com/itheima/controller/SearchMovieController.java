package com.itheima.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.MovieDao;
import com.itheima.domain.Movie;
import com.itheima.domain.RoleRank;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class SearchMovieController {
    @Autowired
    private MovieDao movieDao;

    @ResponseBody
    @PostMapping(value = "/search")
    public String searchMovies(@RequestBody JSONObject jsonObject) throws IOException {
        String searchContent = jsonObject.getString("searchContent");
        String page = jsonObject.getString("page");

        searchContent=searchContent.replace("\"","");
        System.out.println(searchContent+page);
        List<Movie> movies = movieDao.searchMovie(Integer.parseInt(page),searchContent);
        int number = movieDao.searchLength(searchContent);
        System.out.println("movies:"+movies);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("data", movies);
        generator.writeNumberField("number", number);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        //System.out.println(s);

        return s;

    }


}
