package com.itheima.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.Pjf.domain.Role;
import com.itheima.dao.MovieDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Comment;
import com.itheima.domain.Movie;
import com.itheima.domain.MovieSawList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class MovieDetailController {
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/getMovieDetail")
    public String getMovieDetail(@RequestParam Map<String, Object> params) throws IOException {
        String movieId = (String) params.get("movieId");
        String username = (String) params.get("username");

        System.out.println("movieId and username:"+movieId+","+username);

        Movie movie = movieDao.MovieDetail(movieId);
        MovieSawList movieSawList = new MovieSawList();
        movieSawList.setMovieId(movie.getMovieId());
        movieSawList.setMovieName(movie.getTitle());
        movieSawList.setMovieUrl(movie.getUrl());
        movieSawList.setDateTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        movieSawList.setImageUrl(movie.getCover_url());
        movieSawList.setActors(movie.getActors());
        //System.out.println(username+"----------"+movieSawList);


        if(username!=null){
            userDao.addSaw(username,movieSawList);
        }

       // System.out.println("666:"+movie);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("movie", movie);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        //System.out.println(s);

        return s;
    }
    @GetMapping(value = "/getMovieDetailReviews")
    public String getMovieDetailReviews(@RequestParam Map<String, Object> params) throws IOException {
        String movieId = (String) params.get("movieId");
        String page = (String) params.get("page");

        System.out.println("movieIdhot:"+movieId+page);

        List<Comment> hotReviews = movieDao.hotReviews(Integer.parseInt(page),movieId);
        List<Comment> newReviews = movieDao.newReviews(Integer.parseInt(page),movieId);
//        int count = movieDao.hotReviewsLength(movieId).size();
//        System.out.println("count:"+count);

        System.out.println("hot:");
        System.out.println(hotReviews);


        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("hotReviews", hotReviews);
        generator.writeObjectField("newReviews", newReviews);
        generator.writeObjectField("count", 300);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
       // System.out.println(s);

        return s;
    }
    @GetMapping(value = "/getAllCiYun")
    public String allCiYun(@RequestParam Map<String, Object> params) throws IOException {
        String movieId = (String) params.get("movieId");

        List<Movie> allCiYun = movieDao.getAllCiYun(movieId);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("allCiYun", allCiYun);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        //System.out.println(s);

        return s;
    }
    @GetMapping(value = "/rateAndCount")
    public String rateAndCount(@RequestParam Map<String, Object> params) throws IOException {
        String movieId = (String) params.get("movieId");

//        System.out.println("movieId7777:"+movieId);

        List<Movie> CountAndRate = movieDao.getCountAndRate(movieId);

//        System.out.println("CountAndRate:"+CountAndRate);


        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("CountAndRate", CountAndRate);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        //System.out.println(s);

        return s;
    }
    @GetMapping(value = "/wrightReview")
    public String wrightReview(@RequestParam Map<String, Object> params) throws IOException {
        String reviewScore = (String) params.get("reviewScore");
        String reviewCont = (String) params.get("reviewCont");
        String movieId = (String) params.get("movieId");
        String movieName = (String) params.get("movieName");
        String commentUser = (String) params.get("commentUser");
        String photoUrl = (String) params.get("photoUrl");
        System.out.println(reviewScore+","+reviewCont+","+movieId+","+movieName+","+commentUser+","+photoUrl);
        int rating = Integer.parseInt(reviewScore);
        Comment comment = new Comment();
        if(rating == 1){
            //很差
            reviewScore = "很差";
        }else if(rating == 2){
            //较差
            reviewScore = "较差";
        }else if(rating == 3){
            //还行
            reviewScore = "还行";
        }else if(rating == 4){
            //推荐
            reviewScore = "推荐";
        }else if(rating == 5){
            //力荐
            reviewScore = "力荐";
        }
        comment.setMovieId(movieId);
        comment.setMovieName(movieName);
        comment.setCommentUser(commentUser);
        comment.setRating("['allstar"+rating*10+"', 'rating']");
        comment.setAttitude(reviewScore);
        comment.setIp("");
        comment.setPhotoUrl(photoUrl);
        comment.setTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        comment.setIfUse("0");
        comment.setCommentContent(reviewCont);
        comment.setIfSee("看过");
        userDao.wrightReview(comment);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        //generator.writeObjectField("allCiYun", allCiYun);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        //System.out.println(s);

        return s;
    }
    @GetMapping(value = "/getSimilarMovies")
    public String getSimilarMovies(@RequestParam Map<String, Object> params) throws IOException {
        String type = (String) params.get("type");
        String regions = (String) params.get("regions");
        System.out.println("type+regions:"+type+regions);
        List<Movie> SimilarMovies = movieDao.similarMovie(type,regions);
//        System.out.println("CountAndRate:"+CountAndRate);
        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("SimilarMovies", SimilarMovies);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }

    @GetMapping(value = "/getActor")
    public String getActor(@RequestParam Map<String, Object> params) throws IOException{
        String actorId = (String) params.get("actorId");
        Role role = movieDao.getActor(actorId);
        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("role", role);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }



}
