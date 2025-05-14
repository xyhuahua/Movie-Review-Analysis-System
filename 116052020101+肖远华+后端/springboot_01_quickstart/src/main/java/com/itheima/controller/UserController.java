package com.itheima.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.UserDao;
import com.itheima.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

//    @Autowired
//    HttpServletRequest request;

    @GetMapping(value = "/user/favList/getAll")
    public String getAllMovie(@RequestParam Map<String, Object> params) throws IOException {
//        HttpSession session = request.getSession();
//        ServletContext application = session.getServletContext();
       // HashSet sessions = (HashSet) application.getAttribute("sessions");

//        String username = (String) request.getSession().getAttribute("username");
//        String username = (String) params.get("username");
        String username = (String) params.get("username");
        System.out.println("fav"+username);

        List<MovieList> favList = userDao.findFavList(username);
        System.out.println(favList);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("FavList", favList);
        generator.writeNumberField("count", 3273);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }


    @GetMapping(value = "/user/favList/deleteOne")
    public String deleteOneMovie(@RequestParam Map<String, Object> params) throws IOException {
        String username = (String) params.get("username");
        String movieId = (String) params.get("movieId");
        System.out.println(movieId);

        int favList = userDao.deleteOne(username,movieId);
        System.out.println(favList);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }

    @GetMapping(value = "/user/register")
    public String register(@RequestParam Map<String, Object> params) throws IOException {
        String username = (String) params.get("userName");
        String password = (String) params.get("password");
        String age = (String) params.get("age");
        String address = (String) params.get("address");
        String info = (String) params.get("info");
        String sex = (String) params.get("sex");
        String tel = (String) params.get("tel");
        String str[] = ((String)params.get("checkList")).split(",");
        ArrayList<Like> like = new ArrayList<Like>();
        String msg = "";

        for (int i=0;i<str.length;i++){
            Like like1 = new Like();
            like1.setLike(str[i]);
            like.add(like1);
        }
        System.out.println(like);
        User user = new User(username,password,age,address,info,sex,tel,like);
        if(userDao.selectUser(user)==null){
            userDao.addUser(user);
            msg = "success";
        }else{
            msg = "用户名已经存在了";
        }

        System.out.println(user);
        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", msg);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }

    @GetMapping(value = "/user/login")
    public String login(@RequestParam Map<String, Object> params) throws IOException {
        String username = (String) params.get("userName");
        String password = (String) params.get("password");
        System.out.println(username);
        System.out.println(password);
        String msg = "";
        if(userDao.selectUserByName(username)==null){
            msg = "用户不存在";
        }else{
            User user1 = userDao.selectUserByName(username);
            if(user1.getPassword().equals(password)){
                msg = "登陆成功";

            }else{
                msg = "密码错误，请检查";
            }

        }

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", msg);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }

    @GetMapping(value = "/user/info")
    public String userinfo(@RequestParam Map<String, Object> params) throws IOException {
        String username = (String) params.get("userName");
        System.out.println(username);
        String msg = "";

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);

        if(userDao.selectUserByName(username)==null){
            msg = "用户不存在";
        }else {
            User user1 = userDao.selectUserByName(username);
            generator.writeObjectField("info", user1);
            msg = "success";
        }

        generator.writeStringField("msg", msg);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }

    @GetMapping(value = "/user/modifyInfo")
    public String modifyInfo(@RequestParam Map<String, Object> params) throws IOException {
        String username = (String) params.get("userName");
        String password = (String) params.get("password");
        String age = (String) params.get("age");
        String address = (String) params.get("address");
        String info = (String) params.get("info");
        String sex = (String) params.get("sex");
        String tel = (String) params.get("tel");
        String str[] = ((String)params.get("checkList")).split(",");
        ArrayList<Like> like = new ArrayList<Like>();
        String msg = "";

        for (int i=0;i<str.length;i++){
            Like like1 = new Like();
            like1.setLike(str[i]);
            like.add(like1);
        }
        System.out.println(like);
        User user = new User(username,password,age,address,info,sex,tel,like);
        userDao.saveUser(user);
        msg = "success";

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", msg);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }

    @GetMapping(value = "/user/playRecord")
    public String playRecord(@RequestParam Map<String, Object> params) throws IOException, ParseException {
        String username = (String) params.get("username");
        String type = (String) params.get("type");
        System.out.println(type);
        System.out.println(username);
        List<MovieSawList> movies = userDao.playRecord(username,type);
        System.out.println(movies);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("movies", movies);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }

    //hym
    @GetMapping(value = "/user/addOneCollect")
    public String addOneCollect(@RequestParam Map<String, Object> params) throws IOException {
        String username = (String) params.get("username");
        String movieId = (String) params.get("movieId");
        String movieName = (String) params.get("movieName");
        String movieUrl = (String) params.get("movieUrl");
        String dateTime = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String imageUrl = (String) params.get("imageUrl");
        ArrayList<Actor> actors = (ArrayList<Actor>) params.get("actors");
        MovieList movie = new MovieList(movieId,movieName,movieUrl,dateTime,imageUrl,actors);


        System.out.println("username:"+username);

        userDao.addCollect(username,movie);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }
}
