package com.itheima.dao;

import com.itheima.Pjf.domain.Role;
import com.itheima.Pjf.utils.MovieUtil;
import com.itheima.domain.Comment;
import com.itheima.domain.Movie;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class MovieDao {
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 根据用户名查询对象
     * @return
     */
    public List<Movie> findAll(int page,String currentSort_type,String currentSort_country,String currentSort_time,String currentSort_hot) {
        Query query = new Query();

        if(currentSort_type.equals("全部")){

        }else if(currentSort_type!=null){
            Pattern pattern = Pattern.compile("^.*" + currentSort_type + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("types").regex(pattern));
        }

        if(currentSort_country.equals("全部")){

        }else if(currentSort_country!=null){
            Pattern pattern1 = Pattern.compile("^.*" + currentSort_country + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("regions").regex(pattern1));
        }

        if(currentSort_time.equals("全部")){

        }else if(currentSort_time.equals("更早")){

        }else if(currentSort_time!=null){
            Pattern pattern2 = Pattern.compile("^.*" + currentSort_time + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("release_date").regex(pattern2));
        }

        Document source = new Document();
        source.put("locale", "zh");
        source.put("numericOrdering", true);
        Collation collation = Collation.from(source);
        query.collation(collation);

        PageRequest pageRequest = PageRequest.of(page-1,24);
        query.with(pageRequest);

        System.out.println(currentSort_hot);

        if(currentSort_hot.equals("高分好评")){
            query.with(Sort.by(Sort.Direction.DESC, "score"));
        }else if(currentSort_hot.equals("最新")){
            query.with(Sort.by(Sort.Direction.DESC, "release_date"));
        }

        List<Movie> movies =  mongoTemplate.find(query,Movie.class);
        return movies;
    }

    public long findcount(int page,String currentSort_type,String currentSort_country,String currentSort_time,String currentSort_hot) {
        Query query = new Query();

        if(currentSort_type.equals("全部")){

        }else if(currentSort_type!=null){
            Pattern pattern = Pattern.compile("^.*" + currentSort_type + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("types").regex(pattern));
        }

        if(currentSort_country.equals("全部")){

        }else if(currentSort_country!=null){
            Pattern pattern1 = Pattern.compile("^.*" + currentSort_country + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("regions").regex(pattern1));
        }

        if(currentSort_time.equals("全部")){

        }else if(currentSort_time.equals("更早")){

        }else if(currentSort_time!=null){
            Pattern pattern2 = Pattern.compile("^.*" + currentSort_time + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("release_date").regex(pattern2));
        }

        Document source = new Document();
        source.put("locale", "zh");
        source.put("numericOrdering", true);
        Collation collation = Collation.from(source);
        query.collation(collation);



        System.out.println(currentSort_hot);

        if(currentSort_hot.equals("高分好评")){
            query.with(Sort.by(Sort.Direction.DESC, "score"));
        }else if(currentSort_hot.equals("最新")){
            query.with(Sort.by(Sort.Direction.DESC, "release_date"));
        }

        long count =  mongoTemplate.count(query,Movie.class);
        return count;
    }

    public int findLength() {
        Query query = new Query();
        List<Movie> movies =  mongoTemplate.find(query,Movie.class);
        System.out.println(movies.size());
        return movies.size();
    }

//hym
    public List<Movie> searchMovie(int page,String searchContent){
        Query query = new Query();
        PageRequest pageRequest = PageRequest.of(page-1,5);
        query.with(pageRequest);
        Pattern pattern1 = Pattern.compile("^.*" + searchContent + ".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("title").regex(pattern1));
        List<Movie> movies =  mongoTemplate.find(query,Movie.class);
        return movies;
    }


    //hym
    public int searchLength(String searchContent) {
        Query query = new Query();
        Pattern pattern1 = Pattern.compile("^.*" + searchContent + ".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("title").regex(pattern1));
        List<Movie> movies =  mongoTemplate.find(query,Movie.class);
        return movies.size();
    }

    //hym
    public Movie MovieDetail(String movieId){
        Query query = new Query();
        query.addCriteria(Criteria.where("movieId").is(movieId));
        Movie movie =  mongoTemplate.findOne(query,Movie.class);
        return movie;
    }

    //hym
    public List<Comment> hotReviews(int page,String movieId) {
        Query query = new Query();

        query.addCriteria(Criteria.where("movieId").is(movieId));
        //query.with(Sort.by(Sort.Direction.DESC, "ifUse"));
        PageRequest pageRequest = PageRequest.of(page - 1, 25);
        query.with(pageRequest);
        List<Comment> hotComments =  mongoTemplate.find(query , Comment.class);
        return hotComments;

    }
    public List<Comment> newReviews(int page,String movieId) {
        Query query = new Query();

        query.addCriteria(Criteria.where("movieId").is(movieId));
        query.with(Sort.by(Sort.Direction.DESC, "time"));
        PageRequest pageRequest = PageRequest.of(page - 1, 25);
        query.with(pageRequest);
        List<Comment> newReviews =  mongoTemplate.find(query , Comment.class);
        return newReviews;
    }


    /**
     * pjf
     * @param id
     * @return
     */
    public Movie findMovieById(String id){
        Query query = new Query(Criteria.where("movieId").is(id));
        Movie movie = mongoTemplate.findOne(query,Movie.class);
        return movie;
    }

    /**
     * 电影排行榜
     * pjf
     *
     * @return
     */
    public Map<String,Object> getMovieRank(String param, int page, String year) {
        Query query = new Query();
        Pattern pattern = Pattern.compile("^" + year + ".*$", Pattern.CASE_INSENSITIVE);

        if (param.equals("score")) {
            Criteria criteria = new Criteria();
            criteria.andOperator(
                    Criteria.where("release_date").regex(pattern)
            );
            query.addCriteria(criteria);
        }
        query.with(Sort.by(
                Sort.Order.desc(param)
        ));
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        query.with(pageRequest);
        List<Movie> movies = mongoTemplate.find(query, Movie.class);

        for(int i = 0 ; i < movies.size();i++){
            for (int j = 0; j < movies.get(i).getActors().size(); j++) {
                movies.get(i).getActors().get(j).setActorid(getActorName(movies.get(i).getActors().get(j).getActorid()));
            }
        }

        Map<String,Object> maps = new HashMap<>();
        maps.put("movieRank",movies);
        List<Map> TenMap = MovieUtil.RegionMap(movies);
        maps.put("TopTenMovie",TenMap);
        List<Map> hundredMaps = MovieUtil.scoreMap(movies);
        maps.put("HundredScoreMovie",hundredMaps);


        return maps;
    }


    //hym
    public List<Movie> getAllCiYun(String movieId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("movieId").is(movieId));
        List<Movie> allCiYun =  mongoTemplate.find(query,Movie.class);
        return allCiYun;
    }


    //hym
    public List<Movie> getAllMovieName(String queryString) {
        Query query = new Query();
        Pattern pattern1 = Pattern.compile("^.*" + queryString + ".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("title").regex(pattern1)).limit(30);
        query.with(Sort.by(Sort.Direction.DESC, "score"));
        query.fields().include("title");
        List<Movie> allMovieName =  mongoTemplate.find(query , Movie.class);
        return allMovieName;
    }

    //hym
    public List<Movie> getCountAndRate(String movieId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("movieId").is(movieId));
        List<Movie> allCiYun =  mongoTemplate.find(query,Movie.class);
        return allCiYun;
    }

    //hym
    public List<Movie> similarMovie(String currentSort_type,String currentSort_country) {
        Query query = new Query();


        if(currentSort_type!=null){
            Pattern pattern = Pattern.compile("^.*" + currentSort_type + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("types").regex(pattern));
        }
        query.with(Sort.by(Sort.Direction.DESC, "score"));

//        if(currentSort_country!=null){
//            Pattern pattern1 = Pattern.compile("^.*" + currentSort_country + ".*$", Pattern.CASE_INSENSITIVE);
//            query.addCriteria(Criteria.where("types").regex(pattern1));
//            //query.addCriteria(Criteria.where("regions").regex(pattern1));
//        }

        PageRequest pageRequest = PageRequest.of(1,24);
        query.with(pageRequest);

        List<Movie> movies =  mongoTemplate.find(query,Movie.class);
        return movies;
    }

    public String getActorName(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("actorId").is(id));
        Role role = mongoTemplate.findOne(query,Role.class);
        if(role != null){
            return role.getActorname();
        }
        return null;
    }

    public Role getActor(String actorId){
        Query query = new Query();
        query.addCriteria(Criteria.where("actorId").is(actorId));
        Role role =  mongoTemplate.findOne(query, Role.class);
        return role;
    }








}
