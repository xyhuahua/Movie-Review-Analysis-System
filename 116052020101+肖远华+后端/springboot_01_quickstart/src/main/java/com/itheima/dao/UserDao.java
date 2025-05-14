package com.itheima.dao;

import com.itheima.domain.*;
import com.mongodb.BasicDBObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;



    /**
     * 用户收藏列表查询
     */
    public List<MovieList> findFavList(String username) {
        Query query = new Query();

        Document source = new Document();
        source.put("locale", "zh");
        source.put("numericOrdering", true);
        Collation collation = Collation.from(source);
        query.collation(collation);
        Pattern pattern1 = Pattern.compile("^.*" + username + ".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("userName").regex(pattern1));
//        PageRequest pageRequest = PageRequest.of(page-1,20);
//        query.with(pageRequest)
        User user =  mongoTemplate.findOne(query,User.class);
        System.out.println(user);
        List<MovieList> movies = user.getMovieList();
        return movies;
    }

    public int deleteOne(String username,String movieId){
        Query query = Query.query(Criteria.where("userName").is(username));
        BasicDBObject s = new BasicDBObject();
        s.put("movieId", movieId);
        Update update = new Update();
        update.pull("movieList", s);
        mongoTemplate.updateFirst(query, update, User.class);
        return 1;
    }

    public int findLength() {
        Query query = new Query();
        List<Movie> movies =  mongoTemplate.find(query,Movie.class);
        System.out.println(movies.size());
        return movies.size();
    }

    public void addUser(User user){
        mongoTemplate.insert(user,"user");
    }

    public User selectUser(User user){
        Query query = new Query(Criteria.where("userName").is(user.getUserName()));
        User user1 = mongoTemplate.findOne(query,User.class);
        return user1;

    }
    public User selectUserByName(String username){
        Query query = new Query(Criteria.where("userName").is(username));
        User user1 = mongoTemplate.findOne(query,User.class);
        return user1;

    }
    public void saveUser(User user){
        Query query = new Query(Criteria.where("userName").is(user.getUserName()));
        Update update = new Update();
        update.set("age", user.getAge());
        update.set("tel", user.getTel());
        update.set("address", user.getAddress());
        update.set("info", user.getInfo());
        update.set("sex", user.getSex());
        update.set("like",user.getLike());
        mongoTemplate.upsert(query, update, "user");
    }

    public void saveUserHeader(String username,String HeaderId){
        Query query=new Query();
        query.addCriteria(Criteria.where("userName").is(username));
        Update update = Update.update("headId", HeaderId);
        mongoTemplate.updateFirst(query,update,User.class);
    }

    public List<MovieSawList> playRecord(String username, String type) throws ParseException {
        Query query = new Query();

        System.out.println(username);

        query.addCriteria(Criteria.where("userName").is(username));
        User user1 = mongoTemplate.findOne(query,User.class);
        List<MovieSawList> movies =  user1.getMovieSawList();
        System.out.println(movies);
        listSort(movies);
        System.out.println(movies);

        if(type.equals("1")) {
            List<MovieSawList> movies1 = new ArrayList<MovieSawList>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String endTime = sdf.format(System.currentTimeMillis());
            System.out.println("结束时间：" + endTime);

            //获取七天前的日期
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 7);
            String startTime = sdf.format(calendar.getTime());
            System.out.println("开始时间：" + startTime);

            for (MovieSawList o : movies) {
                Date d1 = sdf.parse(o.getDateTime());
                Date d2 = sdf.parse(startTime);
                if (d1.getTime() > d2.getTime()) {
                    movies1.add(o);
                }

            }
            System.out.println(movies1);
            return movies1;
        }else if(type.equals("2")){
            List<MovieSawList> movies1 = new ArrayList<MovieSawList>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            //获取31天前的日期
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 31);
            String startTime = sdf.format(calendar.getTime());
            System.out.println("开始时间：" + startTime);

            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.DATE, calendar1.get(Calendar.DATE) - 7);
            String endTime = sdf.format(calendar1.getTime());
            System.out.println("结束时间：" + endTime);

            for (MovieSawList o : movies) {
                Date d1 = sdf.parse(o.getDateTime());
                Date d2 = sdf.parse(startTime);
                Date d3 = sdf.parse(endTime);
                if (d1.getTime() > d2.getTime() && d1.getTime() < d3.getTime() ) {
                    movies1.add(o);
                }

            }
            System.out.println(movies1);
            return movies1;

        }else if(type.equals("3")){
            List<MovieSawList> movies1 = new ArrayList<MovieSawList>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String endTime = sdf.format(System.currentTimeMillis());
            System.out.println("结束时间：" + endTime);

            //获取30天前的日期
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 30);
            String startTime = sdf.format(calendar.getTime());
            System.out.println("开始时间：" + startTime);

            for (MovieSawList o : movies) {
                Date d1 = sdf.parse(o.getDateTime());
                Date d2 = sdf.parse(startTime);
                if (d1.getTime() < d2.getTime()) {
                    movies1.add(o);
                }

            }
            System.out.println(movies1);
            return movies1;
        }

        return movies;

    }
    private static void listSort(List<MovieSawList> list) {
        Collections.sort(list, new Comparator<MovieSawList>() {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            public int compare(MovieSawList o1, MovieSawList o2) {
                try {
                    Date dt1 = sf.parse(o1.getDateTime());
                    Date dt2 = sf.parse(o2.getDateTime());
                    if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
    //hym
    public void addCollect(String username,MovieList movieList){
        Query query = Query.query(Criteria.where("userName").is(username));
        Update update = new Update();
        update.addToSet("movieList",movieList);
        mongoTemplate.upsert(query, update, "user");
    }
    //hym
    public void addSaw(String username, MovieSawList movieSawList){

        Query query = Query.query(Criteria.where("userName").is(username));
        Update update = new Update();
        update.addToSet("movieSawList", movieSawList); //push不去重
        mongoTemplate.updateFirst(query, update, "user");
    }
    //hym
    public void wrightReview(Comment comment){
        mongoTemplate.insert(comment,"comment");
    }

    //hym
    public User GuessLike(String userName){
        Query query = new Query();

        query.addCriteria(Criteria.where("userName").is(userName));
        User user =  mongoTemplate.findOne(query , User.class);
        return user;
    }
    //hym
    public List<Movie> Guess() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "score"));
        query.with(Sort.by(Sort.Direction.DESC, "release_date"));
        PageRequest pageRequest = PageRequest.of(2, 5);
        query.with(pageRequest);
        List<Movie> movies =  mongoTemplate.find(query,Movie.class);
        return movies;
    }







}
