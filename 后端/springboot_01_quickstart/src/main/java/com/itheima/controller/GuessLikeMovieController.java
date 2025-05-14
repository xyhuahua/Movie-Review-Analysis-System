package com.itheima.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.UserDao;
import com.itheima.domain.Comment;
import com.itheima.domain.Guess;
import com.itheima.domain.Movie;
import com.itheima.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GuessLikeMovieController {
    @Autowired
    private UserDao userDao;
    @GetMapping(value = "/getRecommendLikeMovies")
    public String getRecommendLikeMovies(@RequestParam Map<String, Object> params) throws IOException {
        String userName = (String) params.get("userName");
        //System.out.println("------------------"+userName);

        ArrayList<Guess> guessLike = null;
        if(userName!=null){
            User users = userDao.GuessLike(userName);
            guessLike = users.getGuess();
        }else {
            List<Movie> movies = userDao.Guess();
            //System.out.println("============"+movies);
            ArrayList<Guess> guesses =  new ArrayList<>();

            movies.forEach(movie -> {
                Guess guess = new Guess();
                guess.setMovieId(movie.getMovieId());
                guess.setMovieUrl(movie.getUrl());
                guess.setImageUrl(movie.getCover_url());
                guess.setMoveName(movie.getTitle());
                guess.setScore(movie.getScore());
                System.out.println("----------------");
                System.out.println(guess);
                guesses.add(guess);
                System.out.println(guesses);
            });
            System.out.println("----------------");
            System.out.println(guesses);
            guessLike = guesses;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("guessLike",guessLike);
        generator.writeEndObject();
        generator.flush();
        generator.close();
        String s = bout.toString();
        return s;
    }
}
